package com.games.catan.pojo;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class WaitingRoom extends BasePOJO{	
	private Room[] rooms;
	private Set<User> users;
	public WaitingRoom(){
		users = new HashSet<User>();
	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Room[] getRooms() {
		return rooms;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}

	@Override
	public JSONObject toJSON() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		JSONArray roomJsonArray = new JSONArray();
		for(Room room:rooms){
			JSONObject roomJson = new JSONObject();
			roomJson.put("roomId", room.getId());
			JSONArray usersJson = new JSONArray();
			for(Player player:room.getPlayers()){
				if(player!=null&&player.getUser()!=null)
					usersJson.put(player.getUser().toJSON());
				else{
					User user = null;
					usersJson.put(user);
				}
			}
			roomJson.put("users", usersJson);
			roomJson.put("roomId", room.getId());
			roomJsonArray.put(roomJson);
		}
		jsonObject.put("rooms", roomJsonArray);
		return jsonObject;
	}
	
}
