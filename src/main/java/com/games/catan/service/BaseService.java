package com.games.catan.service;

import com.games.catan.framework.ApplicationGameStatus;

public abstract class BaseService {
	ApplicationGameStatus applicationGameStatus;

	public void setApplicationGameStatus(ApplicationGameStatus applicationGameStatus) {
		this.applicationGameStatus = applicationGameStatus;
	}
}
