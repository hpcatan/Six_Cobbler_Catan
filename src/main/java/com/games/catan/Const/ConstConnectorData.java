package com.games.catan.Const;

public interface ConstConnectorData {
	String REQUEST_TYPE_NAME = "type";
	String RESPONSE_NOTICE_TYPE_NAME = "noticeType";
	String RESPONSE_TYPE_NAME = "responseType";
	String RESPONSE_MESSAGE_NAME = "message";
	int RESPONSE_TYPE_VALUE_SUCCESS = 0;
	int RESPONSE_TYPE_VALUE_FAILED = 1;
	int RESPONSE_TYPE_VALUE_NOTICE = 2;
	String CATAN_MESSAGE_NAME = "data";
	
	
	String BROADCAST_TYPE = "broadcastType";
	String BROADCAST_MESSAGE = "broadcastMsg";
	
	String ROOM_MESSAGE = "roomMessage";
	String ROOM_ID = "roomId";
}
