var catan = {};

catan.page = {
		pageInfo : null,
		eUserName : null,
		ePassword : null,

		showInfo:function(infoStr) {
			this.pageInfo.innerHTML = infoStr;			
		},
		clearInfo:function() {
			this.pageInfo.innerHTML = "";
		},
		showReceiveInfo:function (receiveInfo) {
			var eReceiveInfo = jws.$("receiveInfo");
			eReceiveInfo.innerHTML = eReceiveInfo.innerHTML
					+ "<br />Websocket Recieve:" + receiveInfo;
		}
};

catan.jws = {
	jWebSocketClient : null,
	catanRequestJson : {type:null,responseInvokeMethod:null},
	catanNotices : {notices:[]},
	websocketLogon : function() {
		var lURL = jws.JWS_SERVER_URL;
		catan.page.showInfo("Connecting to " + lURL + " and logging in as '" + catan.page.eUserName.value
				+ "'...");
		var lRes = this.jWebSocketClient
				.logon(
						lURL,
						"catan",
						"",
						{
							// OnOpen callback
							OnOpen : function(aEvent) {
								catan.page.showInfo("<font style='color:#888'>jWebSocket connection established.</font>");
							},
							// OnMessage callback
							OnMessage : function(aEvent, aToken) {
								catan.page.showReceiveInfo(catan.jws.jWebSocketClient.tokenToStream(aToken));
									
								if(!aToken.data)
									return;
								var catanRec = JSON.parse(aToken.data);
								if(catanRec.type&&catanRec.type==catan.jws.catanRequestJson.type){
									catan.jws.catanRequestJson.responseInvokeMethod(catanRec);
								}
								if(catanRec.responseType==2){
									var catanNotices = catan.jws.catanNotices.notices;
									for(var i=0;i<catanNotices.length;i++){
										if(catanNotices[i].noticeType==catanRec.noticeType){
											catanNotices[i].invokeMethod(catanRec);
											break;
										}
									}
								}
							},
							// OnClose callback
							OnClose : function(aEvent) {
								//showInfo("The websocket has been closed.");
							}
						});
		catan.page.showInfo(this.jWebSocketClient.resultToString(lRes));
	},
	addCatanNotice:function (noticeType,originalObject,invokeMethod) {
		var addIndex = this.catanNotices.notices.length;
		//var invokeMethod1 = function(catanRec) {return (invokeMethod.apply(originalObject, catanRec));};
		var invokeMethod1 = this.bindObject(invokeMethod, originalObject);
		this.catanNotices.notices[addIndex] = {noticeType:noticeType,invokeMethod:invokeMethod1};
	},

	catanRequest:function (requestJson,originalObject,responseInvokeMethod){
		try {
			var lRes = this.jWebSocketClient.sendJsonString(JSON.stringify(requestJson));		
			catan.page.showInfo(this.jWebSocketClient.resultToString(lRes));
			
			this.catanRequestJson.type = requestJson.type;
			//this.catanRequestJson.responseInvokeMethod = function(catanRec) {return (responseInvokeMethod.apply(originalObject, catanRec));};
			this.catanRequestJson.responseInvokeMethod = this.bindObject(responseInvokeMethod, originalObject);
		} catch (e) {
			showInfo(e);
		}
	},
	disconnect:function () {
		var catanDisconnect = {type:"disconnect"};
		try {
			var lRes = this.jWebSocketClient.sendJsonString(JSON.stringify(catanDisconnect));
			catan.page.showInfo(this.jWebSocketClient.resultToString(lRes));
		} catch (e) {
			showInfo(e);
		}
	},

	logoff:function () {
		this.disconnect();
		if (this.jWebSocketClient) {
			catan.page.showInfo("Logging off "
					+ (catan.page.eUserName.value != null ? "'" + catan.page.eUserName.value + "'" : "")
					+ " and disconnecting...");
			// the timeout below is optional, if you use it you'll get a good-bye
			// message.
			var lRes = this.jWebSocketClient.close({
				timeout : 3000
			});
			catan.page.showInfo(this.jWebSocketClient.resultToString(lRes));
		}
	},

	initPage:function () {
		catan.page.pageInfo = jws.$("pageInfo");
		catan.page.eUserName = jws.$("userName");
		catan.page.ePassword = jws.$("password");

		if (jws.browserSupportsWebSockets()) {
			this.jWebSocketClient = new jws.jWebSocketJSONClient();
			catan.page.eUserName.focus();
		} else {
			jws.$("userName").setAttribute("disabled", "disabled");
			jws.$("password").setAttribute("disabled", "disabled");

			var lMsg = jws.MSG_WS_NOT_SUPPORTED;
			alert(lMsg);
			catan.page.showInfo(lMsg);
		}
		this.websocketLogon();
		catan.notices.addAllCatanNotices();
	},

	exitPage:function () {
		this.logoff();
	},
	
	bindObject: function(Method, targetObject) {
		return (function() {
			return (Method.apply(targetObject, arguments));
		});
	}
};
