package com.games.catan.framework;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.games.catan.Const.ConstData;
import com.games.catan.exception.CatanLogicException;
import com.games.catan.framework.flow.CatanFlowManager;
import com.games.catan.framework.flow.Status;
import com.games.catan.pojo.Room;
import com.games.catan.pojo.User;
import com.games.catan.pojo.WaitingRoom;

public class ApplicationGameStatus {
	private Map<String,ClientStatus> connectorIdClientStatusMap = new HashMap<String,ClientStatus>();
	private Room[] rooms = new Room[ConstData.ROOM_COUNT];
	private WaitingRoom waitingRoom;
		
	public ApplicationGameStatus(){
		//initialize room
		for(int i=0;i<rooms.length;i++){
			Room room = new Room();
			room.setId(String.valueOf(i));
			rooms[i] = room;
		}
		
		waitingRoom = new WaitingRoom();
		waitingRoom.setRooms(rooms);
	}
	public WaitingRoom getWaitingRoom(){
		return waitingRoom;
	}
	public void removeClientStatus(String connectorId){
		connectorIdClientStatusMap.remove(connectorId);
	}
	
	public Status getStatusByConnecterId(String connecterId){
		ClientStatus clientStatus = connectorIdClientStatusMap.get(connecterId);
		if(clientStatus==null){
			return null;
		}
		return connectorIdClientStatusMap.get(connecterId).getStatus();
	}
	public ClientStatus getClientStatusByConnectorId(String connecterId){
		return connectorIdClientStatusMap.get(connecterId);
	}
	public void registerConnector(String connectorId) throws CatanLogicException{
		if(connectorIdClientStatusMap.containsKey(connectorId)){
			throw new CatanLogicException("Exception when register connector, connectorId="+connectorId+" is already in connectorIdClientStatusMap="+connectorIdClientStatusMap+". So could not be registered.");
		}
		connectorIdClientStatusMap.put(connectorId, new ClientStatus(connectorId, CatanFlowManager.getFirstStatus()));
	}
	
	public Set<User> getAllUsers(){
		Set<User> userSet = new HashSet<User>();
		Collection<ClientStatus> clientStatusCollection = connectorIdClientStatusMap.values();
		Iterator<ClientStatus> clientStatusIterator = clientStatusCollection.iterator();
		while(clientStatusIterator.hasNext()){
			ClientStatus clientStatus = clientStatusIterator.next();
			User user = clientStatus.getUser();
			if(user!=null){
				userSet.add(user);
			}
		}
		return userSet;
	}
	public Room[] getAllRooms(){
		return rooms;
	}
	public Room getRoomById(String roomId){
		for(int i=0;i<rooms.length;i++){
			if(rooms[i].getId().equals(roomId)){
				return rooms[i];
			}
		}
		return null;
	}
	public User getUserByConnectorId(String connectorId){
		ClientStatus clientStatus = connectorIdClientStatusMap.get(connectorId);
		User user = clientStatus.getUser();
		return user;
	}
	
	public Collection<ClientStatus> getAllClientStatus(){
		return connectorIdClientStatusMap.values();
	}
}
