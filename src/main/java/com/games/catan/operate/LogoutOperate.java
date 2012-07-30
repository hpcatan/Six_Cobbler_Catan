package com.games.catan.operate;

import org.json.JSONException;

import com.games.catan.Const.ConstConfigFileData;
import com.games.catan.exception.CatanLogicException;
import com.games.catan.framework.ClientStatus;
import com.games.catan.pojo.Room;
import com.games.catan.pojo.User;
import com.games.catan.service.ClientStatusService;
import com.games.catan.service.GameRoomService;
import com.games.catan.service.UserService;
import com.games.catan.service.WaitingRoomService;
import com.games.catan.service.notice.RoomNotice;
import com.games.catan.service.notice.WaitingRoomNotice;

public class LogoutOperate extends BaseOperate {
	@Override
	public void process() throws JSONException, CatanLogicException {
		String connectorId = websocketTool.getCurrentConnectorId();
		ClientStatus clientStatus = clientStatusService.getClientStatusByConnectorId(connectorId);
		User user = clientStatus.getUser();
		Room room = user.getRoom();
		if(room!=null){
			gameRoomService.userLogoutRoom(user);
			this.roomNotice.setRoom(room);
			this.waitingRoomNotice.setWaitingRoom(waitingRoomService.getWaitingRoom());
			addDelayNotice(roomNotice);
			addDelayNotice(waitingRoomNotice);
		}
		if(waitingRoomService.isUserInWaitingRoom(user)){
			waitingRoomService.deleteUserFromWaitingRoom(user);
		}
		userService.userLogout(connectorId);
		
		this.addNextStatusIdByCurrentUser(ConstConfigFileData.OPERATE_SUCCESS_TO);
	}
	private WaitingRoomService waitingRoomService;
	private GameRoomService gameRoomService;
	private UserService userService;
	private ClientStatusService clientStatusService;
	private RoomNotice roomNotice;
	private WaitingRoomNotice waitingRoomNotice;
	public GameRoomService getGameRoomService() {
		return gameRoomService;
	}
	public void setGameRoomService(GameRoomService gameRoomService) {
		this.gameRoomService = gameRoomService;
	}
	public WaitingRoomService getWaitingRoomService() {
		return waitingRoomService;
	}
	public void setWaitingRoomService(WaitingRoomService waitingRoomService) {
		this.waitingRoomService = waitingRoomService;
	}
	public WaitingRoomNotice getWaitingRoomNotice() {
		return waitingRoomNotice;
	}
	public void setWaitingRoomNotice(WaitingRoomNotice waitingRoomNotice) {
		this.waitingRoomNotice = waitingRoomNotice;
	}
	public RoomNotice getRoomNotice() {
		return roomNotice;
	}
	public void setRoomNotice(RoomNotice roomNotice) {
		this.roomNotice = roomNotice;
	}
	public UserService getUserService() {
		return userService;
	}
	public ClientStatusService getClientStatusService() {
		return clientStatusService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setClientStatusService(ClientStatusService clientStatusService) {
		this.clientStatusService = clientStatusService;
	}
}
