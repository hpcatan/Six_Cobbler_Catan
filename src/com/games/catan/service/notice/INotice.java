package com.games.catan.service.notice;

import org.json.JSONException;

import com.games.catan.exception.CatanLogicException;

public interface INotice {
	public void sendNotice() throws JSONException,CatanLogicException;
}
