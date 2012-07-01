package com.games.catan.service;

import java.util.Set;

import com.games.catan.pojo.User;
import com.games.catan.pojo.WaitingRoom;

public class WaitingRoomService extends BaseService{
	public void addUserInWaitingRoom(User user){
		applicationGameStatus.getWaitingRoom().getUsers().add(user);
	}
	public WaitingRoom getWaitingRoom(){
		return applicationGameStatus.getWaitingRoom();
	}
	public boolean isUserInWaitingRoom(User user){
		WaitingRoom waitingRoom =  applicationGameStatus.getWaitingRoom();
		Set<User> users = waitingRoom.getUsers();
		return users.contains(user);
	}
	public void deleteUserFromWaitingRoom(User user) {
		WaitingRoom waitingRoom =  applicationGameStatus.getWaitingRoom();
		Set<User> users = waitingRoom.getUsers();
		users.remove(user);
	}
	/***********************************************************************/
	private UserService userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
