package com.games.catan.operate;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.games.catan.Const.ConstConfigFileData;
import com.games.catan.exception.CatanLogicException;
import com.games.catan.pojo.Dice;
import com.games.catan.pojo.Room;
import com.games.catan.pojo.User;
import com.games.catan.service.DiceService;
import com.games.catan.service.GameRoomService;
import com.games.catan.service.UserService;
import com.games.catan.service.notice.RoomNotice;

public class DiceOperate extends BaseOperate {
	
	/**
	 * First notify the user should random dice first.
	 * After DICE_TIME(2 seconds) should notify client to get the exact dice.
	 */
	@Override
	public void process() throws JSONException, CatanLogicException {
		String connectorId = this.websocketTool.getCurrentConnectorId();
		User user = this.userService.getUserByConnectorId(connectorId);
		Room room = user.getRoom();
		
		if (room == null) {
			throw new CatanLogicException("The user with name: " + user.getName() + " has no room.");
		}
		Dice randomDice = this.diceService.generateRandomDice();
		this.gameRoomService.updateDiceInRoom(randomDice, room);
		this.roomNotice.setRoom(room);
		addDelayNotice(roomNotice);
		
		Dice exactDice = this.diceService.generateDice();
		this.gameRoomService.updateDiceInRoom(exactDice, room);

		Timer timer = new Timer();
		timer.schedule(new RoomNoticeTask(), DICE_TIME);
		this.addNextStatusIdByCurrentUser(ConstConfigFileData.OPERATE_SUCCESS_TO);
	}
	// DiceOperate Logger
	private Logger logger = Logger.getLogger(DiceOperate.class);
	
	// Dice Service
	private DiceService diceService;
	
	// User Service
	private UserService userService;
	
	// Room Notice
	private RoomNotice roomNotice;
	private GameRoomService gameRoomService;
	// 
	private final int DICE_TIME = 2000;
	
	/**
	 * Set method for dice service.
	 * @param diceService
	 */
	public void setDiceService(DiceService diceService) {
		this.diceService = diceService;
	}
	
	public void setGameRoomService(GameRoomService gameRoomService) {
		this.gameRoomService = gameRoomService;
	}

	/**
	 * Set method for user service.
	 * @param clientStatusService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Set method for room notice.
	 * @param diceNotice
	 */
	public void setRoomNotice(RoomNotice roomNotice) {
		this.roomNotice = roomNotice;
	}

	/**
	 * Executed after DICE_TIME.
	 * @author chuxi
	 *
	 */
	class RoomNoticeTask extends TimerTask {
	    public void run() {
	    	String connectorId = websocketTool.getCurrentConnectorId();
			User user = userService.getUserByConnectorId(connectorId);
			Room room = user.getRoom();
			
	    	try {
				roomNotice.setRoom(room);
			} catch (JSONException e) {
				logger.error("JSONException when try to notice room.", e);
			} catch (CatanLogicException e) {
				logger.error("CatanLogicException when try to notice room.", e);
			}
	    }
	}
}
