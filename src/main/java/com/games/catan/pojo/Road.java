package com.games.catan.pojo;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.pojo.Player.PlayerColor;

public class Road extends BasePOJO {
	private PlayerColor playerColor;
	private Player player;
	
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
		return jsonObject;
	}

}
