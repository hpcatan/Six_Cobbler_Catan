package com.games.catan.pojo;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.Const.ConstData;
import com.games.catan.exception.CatanLogicException;
import com.games.catan.pojo.Player.PlayerColor;

public class Room extends BasePOJO{
	private String id;
	private Land land;
	private boolean isGameStarted;
	private Player[] players;
	private BattleHistory battleHistory;
	private ChatHistory chatHistory;
	private Player operatePlayer;
	private Player roadKingPlayer;
	private Player soldierKingPlayer;
	private Dice dice;
	public Room(){
		Player[] players = new Player[ConstData.ROOM_USER_COUNT];
		for(int j=0;j<players.length;j++){
			players[j] = new Player();
			players[j].setPlayerColor(PlayerColor.values()[j]);
		}
		this.setPlayers(players);
		
		BattleHistory battleHistory = new BattleHistory();
		BattleHistoryDetail battleHistoryDetail = new BattleHistoryDetail();
		battleHistoryDetail.setBattleInfo("Welcome to come in battle.");
		battleHistory.getBattleHistoryDetails().add(battleHistoryDetail);
		this.setBattleHistory(battleHistory);
		ChatHistory chatHistory = new ChatHistory();
		ChatHistoryDetail chatHistoryDetail  = new ChatHistoryDetail();
		chatHistoryDetail.setChatInfo("Welcome to come in chat.");
		chatHistory.getChatHistoryDetails().add(chatHistoryDetail);
		this.setChatHistory(chatHistory);
	}
	public Dice getDice() {
		return dice;
	}
	public void setDice(Dice dice) {
		this.dice = dice;
	}
	public Player getRoadKingPlayer() {
		return roadKingPlayer;
	}
	public void setRoadKingPlayer(Player roadKingPlayer) {
		this.roadKingPlayer = roadKingPlayer;
	}
	public Player getSoldierKingPlayer() {
		return soldierKingPlayer;
	}
	public void setSoldierKingPlayer(Player soldierKingPlayer) {
		this.soldierKingPlayer = soldierKingPlayer;
	}
	public Player getOperatePlayer() {
		return operatePlayer;
	}
	public void setOperatePlayer(Player operatePlayer) {
		this.operatePlayer = operatePlayer;
	}
	public BattleHistory getBattleHistory() {
		return battleHistory;
	}
	public void setBattleHistory(BattleHistory battleHistory) {
		this.battleHistory = battleHistory;
	}
	public ChatHistory getChatHistory() {
		return chatHistory;
	}
	public void setChatHistory(ChatHistory chatHistory) {
		this.chatHistory = chatHistory;
	}
	public Player[] getPlayers() {
		return players;
	}
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public boolean isGameStarted() {
		return isGameStarted;
	}
	public void setGameStarted(boolean isGameStarted) {
		this.isGameStarted = isGameStarted;
	}
	public Land getLand() {
		return land;
	}
	public void setLand(Land land) {
		this.land = land;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Room [id=" + id + ", land=" + land + ", isGameStarted="
				+ isGameStarted + ", players=" + Arrays.toString(players) + "]";
	}
	@Override
	public JSONObject toJSON() throws JSONException, CatanLogicException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		jsonObject.put("players", getJSONArray(players));
		jsonObject.put("land", land==null?land:land.toJSON());
		jsonObject.put("battleHistory", this.battleHistory.toJSON());
		jsonObject.put("chatHistory", this.chatHistory.toJSON());
		jsonObject.put("roadKingPlayer", roadKingPlayer==null?null:roadKingPlayer.toJSON());
		jsonObject.put("soldierKingPlayer", soldierKingPlayer==null?null:soldierKingPlayer.toJSON());
		jsonObject.put("isGameStarted", this.isGameStarted);
		jsonObject.put("dice", dice==null?null:dice.toJSON());
		return jsonObject;
	}
}
