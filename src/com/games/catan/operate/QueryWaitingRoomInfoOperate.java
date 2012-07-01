package com.games.catan.operate;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.Const.ConstConnectorData;
import com.games.catan.exception.CatanLogicException;
import com.games.catan.pojo.WaitingRoom;
import com.games.catan.service.WaitingRoomService;

public class QueryWaitingRoomInfoOperate extends BaseOperate {

	@Override
	public void process()
			throws JSONException, CatanLogicException {
		WaitingRoom waitingRoom = waitingRoomService.getWaitingRoom();
		
		JSONObject responseJsonObject = websocketTool.getResponseJSONObject();
		responseJsonObject.put(ConstConnectorData.RESPONSE_TYPE_NAME, ConstConnectorData.RESPONSE_TYPE_VALUE_SUCCESS);
		responseJsonObject.put("waitingRoom", waitingRoom.toJSON());
		
		websocketTool.sendJsonObject(responseJsonObject);
	}
	
	WaitingRoomService waitingRoomService;
	
	public WaitingRoomService getWaitingRoomService() {
		return waitingRoomService;
	}

	public void setWaitingRoomService(WaitingRoomService waitingRoomService) {
		this.waitingRoomService = waitingRoomService;
	}
}
