package com.games.catan.service.notice;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.Const.ConstConnectorData;
import com.games.catan.exception.CatanLogicException;
import com.games.catan.pojo.Player;
import com.games.catan.pojo.Room;

public class RoomNotice extends BaseNotice implements INotice{
	private Room room;
	private static final String NOTICE_TYPE = "Notice:Room Update";
	public void setRoom(Room room) throws JSONException, CatanLogicException{
		this.room = room;
	}
	
	private void sentJsonInRoom(Room room, JSONObject noticeJSONObject) {
		Set<String> targetConnectorIdSet = new HashSet<String>();
		for(Player player:room.getPlayers()){
			if(player!=null&&player.getUser()!=null){
				String targetConnectorId = player.getUser().getId();
				targetConnectorIdSet.add(targetConnectorId);
			}
		}
		websocketTool.sendJsonObject(targetConnectorIdSet, noticeJSONObject);
	}

	@Override
	public void sendNotice() throws JSONException,CatanLogicException {
		JSONObject noticeJSONObject = new JSONObject();
		noticeJSONObject.put(ConstConnectorData.RESPONSE_TYPE_NAME, ConstConnectorData.RESPONSE_TYPE_VALUE_NOTICE);
		noticeJSONObject.put(ConstConnectorData.RESPONSE_NOTICE_TYPE_NAME, NOTICE_TYPE);
		noticeJSONObject.put("room", room.toJSON());
		
		sentJsonInRoom(room, noticeJSONObject);
	}
}
