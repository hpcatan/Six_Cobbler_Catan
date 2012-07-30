package com.games.catan.pojo;

import org.json.JSONException;
import org.json.JSONObject;

public class BattleHistoryDetail extends BasePOJO {
	private String source;
	private String battleInfo;
	
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBattleInfo() {
		return battleInfo;
	}

	public void setBattleInfo(String battleInfo) {
		this.battleInfo = battleInfo;
	}

	@Override
	public JSONObject toJSON() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("source", source);
		jsonObject.put("battleInfo", this.battleInfo);
		return jsonObject;
	}

}
