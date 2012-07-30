package com.games.catan.pojo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.exception.CatanLogicException;

public class ChatHistory extends BasePOJO {
	private List<ChatHistoryDetail> chatHistoryDetails;
	public ChatHistory(){
		this.setChatHistoryDetails(new ArrayList<ChatHistoryDetail>());
	}
	public List<ChatHistoryDetail> getChatHistoryDetails() {
		return chatHistoryDetails;
	}

	public void setChatHistoryDetails(List<ChatHistoryDetail> chatHistoryDetails) {
		this.chatHistoryDetails = chatHistoryDetails;
	}

	@Override
	public JSONObject toJSON() throws JSONException, CatanLogicException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("chatHistoryDetails", getJSONArray(this.chatHistoryDetails));
		return jsonObject;
	}

}
