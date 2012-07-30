package com.games.catan.operate;

import org.json.JSONException;

import com.games.catan.exception.CatanLogicException;
import com.games.catan.pojo.User;
import com.games.catan.service.GameRoomService;
import com.games.catan.service.UserService;
import com.games.catan.service.notice.RoomNotice;
/**
 * after player click end round button, this operate will process it.
 * @author chenlan
 *
 */
public class EndRoundOperate extends BaseOperate {

	@Override
	public void process() throws JSONException, CatanLogicException {
		String currentConnectorId = this.websocketTool.getCurrentConnectorId();
		User user = this.userService.getUserByConnectorId(currentConnectorId);
		this.gameRoomService.endRoundByUser(user);

		boolean isUserWin = this.gameRoomService.isUserWin(user);
		if(isUserWin){
			//TODO add user win game logic
		}else{
			
		}
		this.roomNotice.setRoom(user.getRoom());		
		addDelayNotice(roomNotice);
	}
	
	private GameRoomService gameRoomService;
	private UserService userService;
	private RoomNotice roomNotice;
	public void setGameRoomService(GameRoomService gameRoomService) {
		this.gameRoomService = gameRoomService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRoomNotice(RoomNotice roomNotice) {
		this.roomNotice = roomNotice;
	}
	
}
