package com.games.catan.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.games.catan.exception.CatanLogicException;
import com.games.catan.framework.WebsocketTool;
import com.games.catan.pojo.Player;
import com.games.catan.pojo.Room;
import com.games.catan.pojo.User;
import com.games.catan.service.notice.INotice;

public abstract class BaseOperate {
	/**
	 * after a game client operation come in, create a class which implements this interface to deal with it.
	 * @param aEvent
	 * @param aToken
	 * @return
	 * @throws JSONException
	 * @throws CatanLogicException
	 */
	public abstract void process() throws JSONException, CatanLogicException;
	
	WebsocketTool websocketTool;
	public WebsocketTool getWebsocketTool() {
		return websocketTool;
	}
	public void setWebsocketTool(WebsocketTool websocketTool) {
		this.websocketTool = websocketTool;
	}
	
	Map<String,String> connectorIdNextStatusIdMap = new HashMap<String, String>();
	public Map<String, String> getConnectorIdNextStatusIdMap() {
		return connectorIdNextStatusIdMap;
	}
	void addNextStatusIdByCurrentUser(String nextStatusId){
		this.connectorIdNextStatusIdMap.put(this.websocketTool.getCurrentConnectorId(), nextStatusId);
	}
	void addNextStatusIdByConnectorId(String nextStatusId,String connectorId){
		this.connectorIdNextStatusIdMap.put(connectorId, nextStatusId);
	}
	void addNextStatusIdByRoomUsers(String nextStatusId,Room room){
		Player[] players = room.getPlayers();
		for(Player player:players){
			if(player!=null&&player.getUser()!=null){
				User user = player.getUser();
				this.addNextStatusIdByConnectorId(nextStatusId, user.getId());
			}
		}
	}	
	List<INotice> delayNotices = new ArrayList<INotice>();
	void addDelayNotice(INotice notice){
		delayNotices.add(notice);
	}
	public List<INotice> getAllDelayNotices(){
		return delayNotices;
	}
}
