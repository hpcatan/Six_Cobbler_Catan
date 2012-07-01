package com.games.catan.service;

import java.util.Set;

import com.games.catan.pojo.User;


public class UserService extends BaseService{
	public void userLogout(String connectorId){
		//ApplicationGameStatus.removeClientStatus(connectorId);
	}
	
	public Set<User> getAllUsers() {
		return applicationGameStatus.getAllUsers();
	}
	
	public User getUserByConnectorId(String connectorId){
		return applicationGameStatus.getUserByConnectorId(connectorId);
	}
}
