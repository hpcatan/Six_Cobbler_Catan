package com.games.catan.pojo;

import org.json.JSONException;
import org.json.JSONObject;

public class Dice extends BasePOJO {
	
	// The first dice number.
	private int firstNumber;
	
	// The second dice number.
	private int secondNumber;
	
	// Is random dice
	private boolean isRandom;
	
	public Dice(int firstNumber, int secondNumber) {
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.isRandom = false;
	}
	
	public Dice(boolean isRandom) {
		this.isRandom = isRandom;
	}
	
	public int getFirstNumber() {
		return firstNumber;
	}
	
	public int getSecondNumber() {
		return secondNumber;
	}
	
	public boolean isRandom() {
		return isRandom;
	}

	@Override
	public JSONObject toJSON() throws JSONException {
		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("firstNumber", firstNumber);
		responseJsonObject.put("secondNumber", secondNumber);
		responseJsonObject.put("isRandom", isRandom);
		
		return responseJsonObject;
	}

}
