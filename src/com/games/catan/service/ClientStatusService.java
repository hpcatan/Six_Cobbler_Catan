package com.games.catan.service;

import java.util.Collection;

import com.games.catan.framework.ClientStatus;

public class ClientStatusService extends BaseService{
	public ClientStatus getClientStatusByConnectorId(String connectorId){
		return applicationGameStatus.getClientStatusByConnectorId(connectorId);
	}
	public void deleteClientStatusByConnectorId(String connectorId){
		applicationGameStatus.removeClientStatus(connectorId);
	}
	public Collection<ClientStatus> getAllClientStatus(){
		return applicationGameStatus.getAllClientStatus();
	}
}
