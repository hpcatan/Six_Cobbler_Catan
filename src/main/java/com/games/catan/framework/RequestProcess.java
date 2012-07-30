package com.games.catan.framework;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.jwebsocket.appserver.WebSocketDemo;
import org.jwebsocket.listener.WebSocketServerTokenEvent;
import org.jwebsocket.logging.Logging;
import org.jwebsocket.token.Token;
import org.springframework.web.context.WebApplicationContext;

import com.games.catan.Const.ConstConnectorData;
import com.games.catan.exception.CatanLogicException;
import com.games.catan.framework.flow.CatanFlowManager;
import com.games.catan.framework.flow.NextStatus;
import com.games.catan.framework.flow.Operate;
import com.games.catan.framework.flow.Status;
import com.games.catan.operate.BaseOperate;
import com.games.catan.service.notice.INotice;
import com.games.catan.util.UtilException;

public class RequestProcess {
	private Logger logger = null;
	private WebApplicationContext springContext;
	private ApplicationGameStatus applicationGameStatus;
	private CatanLocalThread catanLocalThread;
	public RequestProcess(){
		springContext = WebSocketDemo.getStringContext();
		applicationGameStatus = (ApplicationGameStatus) springContext.getBean("applicationGameStatus");
		catanLocalThread = (CatanLocalThread) springContext.getBean("catanLocalThread");
		try {
			logger = Logging.getLogger(RequestProcess.class);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private void processRequest(WebSocketServerTokenEvent aEvent, Token aToken)
			throws CatanLogicException, JSONException, InstantiationException, IllegalAccessException, UtilException {
		catanLocalThread.initLocalThread(aEvent, aToken);
		
		String currentConnectorId = aEvent.getConnector().getId();
		Status currentStatus = applicationGameStatus
				.getStatusByConnecterId(currentConnectorId);
		if (currentStatus == null) {
			applicationGameStatus.registerConnector(currentConnectorId);
		}
		currentStatus = applicationGameStatus
				.getStatusByConnecterId(currentConnectorId);

		String requestData = aToken.getString(ConstConnectorData.CATAN_MESSAGE_NAME);
		JSONObject jsonObject = new JSONObject(requestData);
		String operateId = jsonObject.get("type").toString();
		//if disconnection invoke disconnection.
		boolean isDisconnect = false;
		if(operateId.equals("disconnect")){
			operateId = "doLogout";
			isDisconnect = true;
		}
		
		Operate operate = getOperate(currentConnectorId,operateId);
		BaseOperate processOperate = executeOperate(operate);
		processUserStatus(operate,processOperate.getConnectorIdNextStatusIdMap());
		noticeProcess(processOperate.getAllDelayNotices());
		
		if(isDisconnect){
			applicationGameStatus.removeClientStatus(currentConnectorId);
		}
	}

	private Operate getOperate(String currentConnectorId, 
			String operateId) throws JSONException, CatanLogicException {
		Status currentStatus = applicationGameStatus.getClientStatusByConnectorId(currentConnectorId).getStatus();
		Set<Operate> operateSet = currentStatus.getOperates();
		Iterator<Operate> operateIterator = operateSet.iterator();
		while(operateIterator.hasNext()){
			Operate operate = operateIterator.next();
			if(operate.getId().equals(operateId)){
				return operate;
			}
		}
		throw new CatanLogicException("Exception when process request, the request type is "+operateId+". But it is not in current status="+currentStatus+".");
	}

	private void processUserStatus(Operate operate,
			Map<String, String> connectorIdExpectedNextStatusIdElementIdMap)
			throws CatanLogicException {
		Collection<String> expectedNextStatusElementIds = connectorIdExpectedNextStatusIdElementIdMap.values();
		this.validateStatusElementIdExistsInOperate(expectedNextStatusElementIds,operate);
		
		Set<String> connectorIdSet = connectorIdExpectedNextStatusIdElementIdMap.keySet();
		Iterator<String> connectorIdIterator = connectorIdSet.iterator();
		while(connectorIdIterator.hasNext()){
			String connectorId = connectorIdIterator.next();
			String expectedNextStatusElementId = connectorIdExpectedNextStatusIdElementIdMap.get(connectorId);
			
			String nextStatusId = null;
			Iterator<NextStatus> nextStatusIterator = operate.getNextStatuses().iterator();
			while(nextStatusIterator.hasNext()){
				NextStatus nextStatus = nextStatusIterator.next();
				if(nextStatus.getId().equals(expectedNextStatusElementId)){
					nextStatusId = nextStatus.getStatusId();
					break;
				}
			}
			ClientStatus clientStatus = applicationGameStatus.getClientStatusByConnectorId(connectorId);
			Status nextStatus = CatanFlowManager.getStatus(nextStatusId);
			clientStatus.setStatus(nextStatus);
		}
	}
	/**
	 * check whether the nextStatusElementIds exists in config file.
	 * @param expectedNextStatusElementIds
	 * @throws CatanLogicException 
	 */
	private void validateStatusElementIdExistsInOperate(Collection<String> expectedNextStatusElementIds,Operate operate) throws CatanLogicException {
		Iterator<NextStatus> nextStatusIterator = operate.getNextStatuses().iterator();
		Iterator<String> nextStatusElementIdIterator = expectedNextStatusElementIds.iterator();
		while(nextStatusElementIdIterator.hasNext()){
			String expectedNextStatusId = nextStatusElementIdIterator.next();
			boolean isExists = false;
			while(nextStatusIterator.hasNext()){
				NextStatus nextStatus = nextStatusIterator.next();
				String nextStatusId = nextStatus.getId();
				if(expectedNextStatusId.equals(nextStatusId)){
					isExists = true;
					break;
				}
			}
			if(!isExists){
				throw new CatanLogicException("can not find expectedNextStatusId="+expectedNextStatusId+" in operate="+operate);
			}
		}
	}
	private void noticeProcess(List<INotice> notices) throws JSONException, CatanLogicException{
		for(INotice notice:notices){
			notice.sendNotice();
		}
	}
	private BaseOperate executeOperate(Operate operate) throws JSONException,
			CatanLogicException {
		String processClassName = operate.getProcessClassName();
		BaseOperate operateProcess = (BaseOperate) springContext.getBean(processClassName);
		operateProcess.process();
		return operateProcess;
	}

	public void catanProcessRequest(WebSocketServerTokenEvent aEvent, Token aToken){
		JSONObject jsonObject = null;
		JSONObject responseJsonObject = null;
		String requestTypeName = ConstConnectorData.REQUEST_TYPE_NAME;
		String responseTypeName = ConstConnectorData.RESPONSE_TYPE_NAME;
		String responseMessageName = ConstConnectorData.RESPONSE_MESSAGE_NAME;
		try {
			try {
				String requestData = aToken.getString(ConstConnectorData.CATAN_MESSAGE_NAME);
				jsonObject = new JSONObject(requestData);
				String requestTypeValue = jsonObject.getString(requestTypeName);
				
				responseJsonObject = new JSONObject();
				responseJsonObject.put(requestTypeName, requestTypeValue);
				
				this.processRequest(aEvent, aToken);
			} catch (CatanLogicException e) {
				logger.warn("", e);
				responseJsonObject.put(responseTypeName, ConstConnectorData.RESPONSE_TYPE_VALUE_FAILED);
				responseJsonObject.put(responseMessageName, e.getClass()+":"+e.getMessage());
				aToken.setString(ConstConnectorData.CATAN_MESSAGE_NAME, responseJsonObject.toString());
				aEvent.sendToken(aToken);
			} catch (InstantiationException e) {
				logger.warn("", e);
				responseJsonObject.put(responseTypeName, ConstConnectorData.RESPONSE_TYPE_VALUE_FAILED);
				responseJsonObject.put(responseMessageName, e.getClass()+":"+e.getMessage());
				aToken.setString(ConstConnectorData.CATAN_MESSAGE_NAME, responseJsonObject.toString());
				aEvent.sendToken(aToken);
			} catch (IllegalAccessException e) {
				logger.warn("", e);
				responseJsonObject.put(responseTypeName, ConstConnectorData.RESPONSE_TYPE_VALUE_FAILED);
				responseJsonObject.put(responseMessageName, e.getClass()+":"+e.getMessage());
				aToken.setString(ConstConnectorData.CATAN_MESSAGE_NAME, responseJsonObject.toString());
				aEvent.sendToken(aToken);
			}catch(Exception e){
				logger.warn("", e);
				logger.error(e.getMessage(), e);
				responseJsonObject.put(responseTypeName, ConstConnectorData.RESPONSE_TYPE_VALUE_FAILED);
				responseJsonObject.put(responseMessageName, e.getClass()+":"+e.getMessage());
				aToken.setString(ConstConnectorData.CATAN_MESSAGE_NAME, responseJsonObject.toString());
				aEvent.sendToken(aToken);
			}
		} catch (JSONException e) {
			logger.warn("", e);
			logger.error("Exception when catanProcessRequest.",e);
		}
	}
	
}
