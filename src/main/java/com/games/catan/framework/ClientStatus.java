package com.games.catan.framework;

import com.games.catan.framework.flow.Status;
import com.games.catan.pojo.User;

public class ClientStatus {
	private String connectorId;
	private Status status;
	private User user;
	
	public ClientStatus(String connectorId,Status status){
		this.connectorId = connectorId;
		this.status = status;
	}
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConnectorId() {
		return connectorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((connectorId == null) ? 0 : connectorId.hashCode());
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
		ClientStatus other = (ClientStatus) obj;
		if (connectorId == null) {
			if (other.connectorId != null)
				return false;
		} else if (!connectorId.equals(other.connectorId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ClientStatus [connectorId=" + connectorId + ", status="
				+ status + ", user=" + user + "]";
	}
}
