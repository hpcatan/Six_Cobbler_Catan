catan.userLoginout={
		catanLogon:function () {
			userName = catan.page.eUserName.value;
			password = catan.page.ePassword.value;

			catan.page.ePassword.value = "";
			
			var catanLogon = {
				type : "doLogin",
				loginName : userName,
				loginPassword : password
			};
			
			catan.jws.catanRequest(catanLogon,this,catan.userLoginout.catanLoginResponseInvoke);
		},

		catanLoginResponseInvoke:function (responseJson){
			if(responseJson.responseType==0){
				this.catanLoginSuccess();
			}
		},

		catanLoginSuccess:function () {
			var loginDIV = jws.$("loginDIV");
			loginDIV.style.display = 'none';
			
			catan.waitingRoom.getServerRoom();
			
		}

		/*catanLogout:function () {
			var catanLogout = {type:"doLogout"};
			try {
				var lRes = jWebSocketClient.sendJsonString(JSON.stringify(catanLogout));
				receiveStatus = null;
				showInfo(jWebSocketClient.resultToString(lRes));
			} catch (e) {
				showInfo(e);
			}
		}*/
};
