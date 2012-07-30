package com.games.catan.pojo;

import org.json.JSONException;
import org.json.JSONObject;

public class SoldierInLand extends BasePOJO {
	private ResourceArea captureResourceArea;

	public ResourceArea getCaptureResourceArea() {
		return captureResourceArea;
	}
	public void setCaptureResourceArea(ResourceArea captureResourceArea) {
		this.captureResourceArea = captureResourceArea;
	}

	@Override
	public JSONObject toJSON() throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

}
