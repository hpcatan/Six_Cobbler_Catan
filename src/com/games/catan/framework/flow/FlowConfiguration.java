package com.games.catan.framework.flow;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.games.catan.Const.ConstConfigFileData;
import com.games.catan.exception.CatanConfigureException;

class FlowConfiguration {
	private Set<Status> statusSet = new HashSet<Status>();
	private String firstStatusId;

	public String getFirstStatusId() {
		return firstStatusId;
	}

	public Set<Status> getStatusSet() {
		return statusSet;
	}
	
	public FlowConfiguration() throws CatanConfigureException{
		loadFlowsFromFile();
	}
	
	/**
	 * load status,operate,firstStatusId from the configure file.
	 * @throws DocumentException
	 * @throws CatanConfigureException
	 */
	private void loadFlowsFromFile() throws CatanConfigureException {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource(ConstConfigFileData.FLOW_CONFIG_FILE);
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(url);
		} catch (DocumentException e) {
			throw new CatanConfigureException("Exception when read the configure file "+ConstConfigFileData.FLOW_CONFIG_FILE, e);
		}
		Element rootElement = doc.getRootElement();

		// Get All Common Operate in configure file.
		Map<String, Operate> commonOperateMap = new HashMap<String, Operate>();
		@SuppressWarnings("unchecked")
		Iterator<Element> commonOperates = rootElement
				.elementIterator(XML.commonOperate);
		
		while (commonOperates.hasNext()) {
			Element commonOperateElement = commonOperates.next();
			Operate commonOperate = getOperateFromOperateElement(commonOperateElement);
			commonOperateMap.put(commonOperate.getId(), commonOperate);
		}

		// get all status in configure file.
		@SuppressWarnings("unchecked")
		Iterator<Element> statusIterator = rootElement
				.elementIterator(XML.status);
		while (statusIterator.hasNext()) {
			Status status = new Status();
			Element statusElement = statusIterator.next();
			String id = statusElement.attributeValue(XML.Status.id);
			String name = statusElement.attributeValue(XML.Status.name);
			status.setId(id);
			status.setName(name);
			
			//add the commonOperates in status.
			String commonOperatesStr = statusElement
					.attributeValue(XML.Status.commonOperates);
			if(commonOperatesStr!=null&&commonOperatesStr.length()>0){
				String[] commonOperatesStrs = commonOperatesStr.split(XML.separator);
				for (int i = 0; i < commonOperatesStrs.length; i++) {
					String commonOperateId = commonOperatesStrs[i];
					Operate commonOperate = commonOperateMap.get(commonOperateId);
					if (commonOperate == null) {
						throw new CatanConfigureException("Exception in "
								+ ConstConfigFileData.FLOW_CONFIG_FILE + ": " + status
								+ " has commonOperates '" + commonOperatesStrs
								+ "', But there is no commonOperate named "
								+ commonOperateId + ".");
					}
					status.addOperate(commonOperate);
				}
			}
			//add the operates in status
			@SuppressWarnings("unchecked")
			Iterator<Element> operateElements = statusElement.elementIterator(XML.operate);
			while(operateElements.hasNext()){
				Element operateElement = operateElements.next();
				Operate operate = getOperateFromOperateElement(operateElement);
				status.addOperate(operate);
			}
			
			statusSet.add(status);
		}

		// get the firstStatusId
		Element firstStatusElement = rootElement.element(XML.firstStatus);
		firstStatusId = firstStatusElement.attributeValue(XML.FirstStatus.statusId);
	}

	private Operate getOperateFromOperateElement(
			Element operateElement) throws CatanConfigureException {
		Operate operate = new Operate();
		String id = operateElement.attributeValue(XML.Operate.id);
		String name = operateElement.attributeValue(XML.Operate.name);
		String processClassName = operateElement.attributeValue(XML.Operate.processClass);
		operate.setId(id);
		operate.setName(name);
		operate.setProcessClassName(processClassName);
		@SuppressWarnings("unchecked")
		Iterator<Element> nextStatusIterator = operateElement.elementIterator("nextStatus");
		while(nextStatusIterator.hasNext()){
			Element nextStatusElement =  nextStatusIterator.next();
			NextStatus nextStatus = this.getNextStatusByNextStatusElement(nextStatusElement);
			operate.addNextStatus(nextStatus);
		}
		
		return operate;
	}

	private NextStatus getNextStatusByNextStatusElement(Element nextStatusElement) {
		NextStatus nextStatus = new NextStatus();
		String id = nextStatusElement.attributeValue(XML.Operate.NextStatus.id);
		String statusId = nextStatusElement.attributeValue(XML.Operate.NextStatus.statusId);
		nextStatus.setId(id);
		nextStatus.setStatusId(statusId);
		return nextStatus;
	}

	public static void main(String[] args) throws DocumentException, CatanConfigureException {
		
	}
}
