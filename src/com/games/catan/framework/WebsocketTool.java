package com.games.catan.framework;

import java.util.Collection;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import org.jwebsocket.api.WebSocketConnector;
import org.jwebsocket.listener.WebSocketServerTokenEvent;
import org.jwebsocket.token.Token;

import com.games.catan.Const.ConstConnectorData;

public class WebsocketTool {
	public void sendJsonObject(JSONObject jsonObject){
		Token aToken = catanLocalThread.getToken();
		WebSocketServerTokenEvent aEvent = catanLocalThread.getEvent();
		aToken.setString(ConstConnectorData.CATAN_MESSAGE_NAME, jsonObject.toString());
		aEvent.sendToken(aToken);
	}
	
	public JSONObject getRequestJSONObject() throws JSONException{
		return this.catanLocalThread.getRequestJsonObject();
	}
	
	public JSONObject getResponseJSONObject() throws JSONException{
		JSONObject requestJsonObject = getRequestJSONObject();
		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put(ConstConnectorData.REQUEST_TYPE_NAME, requestJsonObject.getString(ConstConnectorData.REQUEST_TYPE_NAME));
		return responseJsonObject;
	}
	
	
	
	public String getCurrentConnectorId(){
		return getCurrentConnector().getId();
	}
	public WebSocketConnector getCurrentConnector(){
		WebSocketServerTokenEvent aEvent = catanLocalThread.getEvent();
		return aEvent.getConnector();
	}
	public WebSocketConnector getConnectorById(String connectorId){
		WebSocketServerTokenEvent aEvent = catanLocalThread.getEvent();
		return aEvent.getConnector(connectorId);
	}
	public void sendJsonObject(Iterator<String> targetConnectorIdIterator,JSONObject sendJsonObject){
		WebSocketServerTokenEvent aEvent = catanLocalThread.getEvent();
		Token aToken = catanLocalThread.getToken();
		aToken.setString(ConstConnectorData.CATAN_MESSAGE_NAME, sendJsonObject.toString());
		
		WebSocketConnector sourceConnector = aEvent.getConnector();
		while(targetConnectorIdIterator.hasNext()){
			String targetConnectorId = targetConnectorIdIterator.next();
			WebSocketConnector targetConnector = getConnectorById(targetConnectorId);
			aEvent.sendToken(sourceConnector, targetConnector, aToken);
		}
	}
	public void sendJsonObject(Collection<String> targetConnectorIdCollection,JSONObject sendJsonObject){
		sendJsonObject(targetConnectorIdCollection.iterator(),sendJsonObject);
	}
	
	private CatanLocalThread catanLocalThread;
	public CatanLocalThread getCatanLocalThread() {
		return catanLocalThread;
	}
	public void setCatanLocalThread(CatanLocalThread catanLocalThread) {
		this.catanLocalThread = catanLocalThread;
	}
}
