package com.games.catan.framework.flow;

public interface XML {
	String separator = ",";
	String operate = "operate";
	interface Operate{
		String id = "id";
		String name = "name";
		String processClass = "processClass";
		
		String nextStatus = "nextStatus";
		interface NextStatus{
			String id = "id";
			String statusId = "statusId";
		}
	}
	
	String commonOperate = "commonOperate";
	interface CommonOperate extends Operate{}
	
	String status = "status";
	interface Status{
		String id = "id";
		String name = "name";
		String commonOperates = "commonOperates";
	}
	
	String firstStatus = "firstStatus";
	interface FirstStatus{
		String statusId = "statusId";
	}
}
