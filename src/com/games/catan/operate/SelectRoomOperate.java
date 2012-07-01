package com.games.catan.operate;


import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.Const.ConstConfigFileData;
import com.games.catan.Const.ConstConnectorData;
import com.games.catan.exception.CatanLogicException;
import com.games.catan.pojo.Room;
import com.games.catan.pojo.User;
import com.games.catan.service.GameRoomService;
import com.games.catan.service.UserService;
import com.games.catan.service.WaitingRoomService;
import com.games.catan.service.notice.RoomNotice;
import com.games.catan.service.notice.WaitingRoomNotice;

public class SelectRoomOperate extends BaseOperate {
	
	@Override
	public void process() throws JSONException, CatanLogicException {
		JSONObject responseJSONObject = websocketTool.getResponseJSONObject();
		
		JSONObject requestJSONObject = websocketTool.getRequestJSONObject();
		String roomId = requestJSONObject.get("roomId").toString();
		String connectorId = websocketTool.getCurrentConnectorId();
		User user  = userService.getUserByConnectorId(connectorId);
		gameRoomService.joinRoom(user, roomId);
		waitingRoomService.deleteUserFromWaitingRoom(user);
	
		responseJSONObject.put(ConstConnectorData.RESPONSE_TYPE_NAME, ConstConnectorData.RESPONSE_TYPE_VALUE_SUCCESS);
		responseJSONObject.put("roomId",roomId);
		
		websocketTool.sendJsonObject(responseJSONObject);
		
		Room room = gameRoomService.getRoomById(roomId);
		//when the room is full, the game will be started.
		if(gameRoomService.isRoomFull(room)){
			gameRoomService.gameStartInit(room);
		}
		this.roomNotice.setRoom(room);
		this.waitingRoomNotice.setWaitingRoom(waitingRoomService.getWaitingRoom());
		addDelayNotice(roomNotice);
		addDelayNotice(waitingRoomNotice);
		
		this.addNextStatusIdByCurrentUser(ConstConfigFileData.OPERATE_SUCCESS_TO);
	}
	
	private GameRoomService gameRoomService;
	private RoomNotice roomNotice;
	private WaitingRoomNotice waitingRoomNotice;
	private UserService userService;
	private WaitingRoomService waitingRoomService;
	
	public void setWaitingRoomService(WaitingRoomService waitingRoomService) {
		this.waitingRoomService = waitingRoomService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setGameRoomService(GameRoomService gameRoomService) {
		this.gameRoomService = gameRoomService;
	}
	public void setWaitingRoomNotice(WaitingRoomNotice waitingRoomNotice) {
		this.waitingRoomNotice = waitingRoomNotice;
	}
	public void setRoomNotice(RoomNotice roomNotice) {
		this.roomNotice = roomNotice;
	}
}
