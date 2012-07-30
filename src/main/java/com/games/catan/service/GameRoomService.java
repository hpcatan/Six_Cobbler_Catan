package com.games.catan.service;

import org.json.JSONException;

import com.games.catan.Const.ConstData;
import com.games.catan.exception.CatanLogicException;
import com.games.catan.pojo.Dice;
import com.games.catan.pojo.Land;
import com.games.catan.pojo.Player;
import com.games.catan.pojo.Room;
import com.games.catan.pojo.User;
import com.games.catan.util.ArrayUtil;

public class GameRoomService extends BaseService{
	public static final int WIN_POINTS = 10;
	public static final int ROAD_KING_REWARD_POINTS = 2;
	public static final int SOLDIER_KING_REWARD_POINTS = 2;
	public void updateDiceInRoom(Dice dice,Room room){
		room.setDice(dice);
	}
	public Room[] getAllRooms(){
		return applicationGameStatus.getAllRooms();
	}

	public boolean isUserWin(User user) throws CatanLogicException {
		Room room = user.getRoom();
		Player player = this.getPlayerByUser(user);
		
		int playerPoints = player.getBuildingAndCardPoints();
		Player roadKingPlayer = room.getRoadKingPlayer();
		Player soldierKingPlayer = room.getSoldierKingPlayer();
		if(roadKingPlayer!=null&&roadKingPlayer.equals(player)){
			playerPoints = playerPoints+ROAD_KING_REWARD_POINTS;
		}
		if(soldierKingPlayer!=null&&soldierKingPlayer.equals(player)){
			playerPoints = playerPoints+SOLDIER_KING_REWARD_POINTS;
		}
		if(playerPoints>=WIN_POINTS){
			return true;
		}
		return false;
	}
	
	private Player getPlayerByUser(User user) throws CatanLogicException{
		Room room = user.getRoom();
		if(room==null){
			throw new CatanLogicException("user is not in a room. the room of user is null" );
		}
		for(Player player:room.getPlayers()){
			if(player.getUser().equals(user)){
				return player;
			}
		}
		return null;
	}
	public void endRoundByUser(User user) throws CatanLogicException{
		Room currentRoom = user.getRoom();
		Player operatePlayer = currentRoom.getOperatePlayer();
		if(!operatePlayer.getUser().equals(user)){
			throw new CatanLogicException("current user is not operate user in room, current user is "+user+", but operate user is "+operatePlayer.getUser());
		}
		
		Player[] players = currentRoom.getPlayers();
		for(int i=0;i<players.length;i++){
			if(players[i].equals(operatePlayer)){
				Player nextOperatePlayer = players[++i%4];
				currentRoom.setOperatePlayer(nextOperatePlayer);
				break;
			}
		}
	}
	public void gameStartInit(Room room) throws CatanLogicException{
		if(room.isGameStarted()){
			throw new CatanLogicException("exception when start game in room "+room+", this room has been started.");
		}
		Land land = landService.getLandByGameStart();
		Player[] players = room.getPlayers();
		for(Player player:players){
			playerService.initPlayerWhenGameStart(player);
		}
		ArrayUtil.makeArrayRandom(players);
		room.setLand(land);
		room.setGameStarted(true);
	}
	public boolean isRoomFull(Room room){
		User[] users = getUsersFromRoom(room);
		for(User user:users){
			if(user==null)
				return false;
		}
		return true;
	}
	
	public User[] getUsersFromRoom(Room room){
		User[] users = new User[ConstData.ROOM_USER_COUNT];
		Player[] players = room.getPlayers();
		for(int i=0;i<users.length;i++){
			users[i] = players[i]==null||players[i].getUser()==null ? null:players[i].getUser();
		}
		return users;
	}
	public void joinRoom(User user,String roomId) throws CatanLogicException, JSONException{
		Room room = getRoomById(roomId);
		Player[] players = room.getPlayers();
		for(int i=0;i<players.length;i++){
			if(players[i].getUser()==null){
				players[i].setUser(user);
				user.setRoom(room);
				return;
			}
		}
		throw new CatanLogicException("room "+room+" is full, can not add new user.");
	}
	
	public void  userLogoutRoom(User user) throws CatanLogicException, JSONException{
		Room room = user.getRoom();
		Player[] players = room.getPlayers();
		for(int i=0;i<players.length;i++){
			if(players[i]!=null&&players[i].getUser()!=null&&players[i].getUser().equals(user)){
				user.exitRoom();
				players[i].setUser(null);
				return;
			}
		}
		throw new CatanLogicException("room "+room+" does not contain user "+user+", can not remove the user.");
	}
	public Room getRoomById(String roomId){
		return applicationGameStatus.getRoomById(roomId);
	}
	/******************************************************************************/
	private LandService landService;
	private PlayerService playerService;
	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}
	public void setLandService(LandService landService) {
		this.landService = landService;
	}
	
	
}
