package com.games.catan.pojo;

import org.json.JSONException;
import org.json.JSONObject;

import com.games.catan.exception.CatanLogicException;

public class User extends BasePOJO{
	private String id;
	private String name;
	private String password;
	private Room room;
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void exitRoom() throws CatanLogicException{
		if(room!=null){
			this.room = null;
		}else{
			throw new CatanLogicException("Exception when exit room, the room is "+ room);
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ "]";
	}
	@Override
	public JSONObject toJSON() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", this.id);
		jsonObject.put("name", name);
		jsonObject.put("password", password);
		jsonObject.put("roomId", room==null?null:room.getId());
		return jsonObject;
	}
	
}
