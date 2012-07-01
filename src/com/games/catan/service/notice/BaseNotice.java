package com.games.catan.service.notice;

import com.games.catan.framework.WebsocketTool;

public abstract class BaseNotice {
	WebsocketTool websocketTool;
	public WebsocketTool getWebsocketTool() {
		return websocketTool;
	}
	public void setWebsocketTool(WebsocketTool websocketTool) {
		this.websocketTool = websocketTool;
	}
}
