package com.games.catan.framework.flow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.games.catan.exception.CatanConfigureException;

public class CatanFlowManager {
	private static Logger logger = Logger.getLogger(CatanFlowManager.class);
	
	private static FlowConfiguration flowConfiguration;
	private static Status firstStatus;
	private static Map<String,Status> statusMap;
	private static Map<String,Operate> idOperateMap;
	static{
		try {
			flowConfiguration = new FlowConfiguration();
		} catch (CatanConfigureException e) {
			logger.error("Error when creat Catan FlowConfiguration.", e);
			System.exit(0);
		}
		
		//init statusMap and init idOperateMap
		statusMap = new HashMap<String,Status>();
		idOperateMap = new HashMap<String,Operate>();
		Set<Status> statusSet = flowConfiguration.getStatusSet();
		Iterator<Status> statusIterator = statusSet.iterator();
		while(statusIterator.hasNext()){
			Status status = statusIterator.next();
			statusMap.put(status.getId(), status);
			Set<Operate> operateSet = status.getOperates();
			Iterator<Operate> operateIterator = operateSet.iterator();
			while(operateIterator.hasNext()){
				Operate operate = operateIterator.next();
				idOperateMap.put(operate.getId(), operate);
			}
		}
		//set firstStatus
		firstStatus = statusMap.get(flowConfiguration.getFirstStatusId());
		
	}
	
	public static Status getFirstStatus(){
		return firstStatus;
	}
	
	public static Status getStatus(String statusId){
		return statusMap.get(statusId);
	}
	
	public static Operate getOperateById(String operateId){
		return idOperateMap.get(operateId);
	}
}
