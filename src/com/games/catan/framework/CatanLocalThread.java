package com.games.catan.framework;

import org.json.JSONException;
import org.json.JSONObject;
import org.jwebsocket.listener.WebSocketServerTokenEvent;
import org.jwebsocket.token.Token;

import com.games.catan.Const.ConstConnectorData;

public class CatanLocalThread {
	private static ThreadLocal<WebSocketServerTokenEvent> aEventThread = new ThreadLocal<WebSocketServerTokenEvent>();
	private static ThreadLocal<Token> aTokenThread = new ThreadLocal<Token>();
	private static ThreadLocal<JSONObject> requestJsonObjectThread = new ThreadLocal<JSONObject>();
	
	public void initLocalThread(WebSocketServerTokenEvent aEvent,Token aToken) throws JSONException{
		aEventThread.remove();
		aTokenThread.remove();
		requestJsonObjectThread.remove();
		
		aEventThread.set(aEvent);
		aTokenThread.set(aToken);
		JSONObject jsonObject = getRequestJSONObject(aToken);
		requestJsonObjectThread.set(jsonObject);
	}
	
	public WebSocketServerTokenEvent getEvent(){
		return aEventThread.get();
	}
	public Token getToken(){
		return aTokenThread.get();
	}
	public JSONObject getRequestJsonObject(){
		return requestJsonObjectThread.get();
	}
	
	private JSONObject getRequestJSONObject(Token aToken) throws JSONException{
		String catanGameString = aToken.getString(ConstConnectorData.CATAN_MESSAGE_NAME);
		JSONObject jsonObject = new JSONObject(catanGameString);
		return jsonObject;
	}
}
