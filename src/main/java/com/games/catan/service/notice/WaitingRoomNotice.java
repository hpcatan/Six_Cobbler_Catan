package com.games.catan.service.notice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.Const.ConstConnectorData;
import com.games.catan.exception.CatanLogicException;
import com.games.catan.pojo.User;
import com.games.catan.pojo.WaitingRoom;

public class WaitingRoomNotice extends BaseNotice implements INotice{
	private static final String NOTICE_TYPE = "Notice:WaitingRoom Update";
	private WaitingRoom waitingRoom;
	
	public void setWaitingRoom(WaitingRoom waitingRoom) {
		this.waitingRoom = waitingRoom;
	}

	@Override
	public void sendNotice() throws JSONException, CatanLogicException {
		JSONObject noticeJSONObject = new JSONObject();
		noticeJSONObject.put(ConstConnectorData.RESPONSE_TYPE_NAME, ConstConnectorData.RESPONSE_TYPE_VALUE_NOTICE);
		noticeJSONObject.put(ConstConnectorData.RESPONSE_NOTICE_TYPE_NAME, NOTICE_TYPE);
		
		Set<String> targetConnectorIdSet = new HashSet<String>();
		Set<User> users = waitingRoom.getUsers();
		Iterator<User> userIterator = users.iterator();
		while(userIterator.hasNext()){
			User user = userIterator.next();
			targetConnectorIdSet.add(user.getId());
		}
		websocketTool.sendJsonObject(targetConnectorIdSet, noticeJSONObject);
	}
}
