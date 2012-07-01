package com.games.catan.pojo;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.pojo.Player.PlayerColor;

public class DevelopCard extends BasePOJO {
	public static enum DevelopCardType{soldier,monopolize,bumper,road,point};
	private DevelopCardType developCardType;
	private PlayerColor playerColor;
	private Player player;
	
	public DevelopCardType getDevelopCardType() {
		return developCardType;
	}

	public void setDevelopCardType(DevelopCardType developCardType) {
		this.developCardType = developCardType;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(PlayerColor playerColor) {
		this.playerColor = playerColor;
	}

	@Override
	public JSONObject toJSON() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("playerColor", playerColor);
		//jsonObject.put("player", player);
		jsonObject.put("developCardType", developCardType);
		return jsonObject;
	}

}
