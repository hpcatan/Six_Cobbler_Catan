package com.games.catan.pojo;

import org.json.JSONException;
import org.json.JSONObject;

public class ChatHistoryDetail extends BasePOJO {
	private String source;
	private String chatInfo;
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getChatInfo() {
		return chatInfo;
	}

	public void setChatInfo(String chatInfo) {
		this.chatInfo = chatInfo;
	}

	@Override
	public JSONObject toJSON() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("source", source);
		jsonObject.put("chatInfo", this.chatInfo);
		return jsonObject;
	}

}
