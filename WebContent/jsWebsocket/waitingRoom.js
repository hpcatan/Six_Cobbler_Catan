catan.waitingRoom = {
		getServerRoom:function () {
			var queryRoom = {
				type : "queryRoomInfo"
			};
			catan.jws.catanRequest(queryRoom,this,catan.waitingRoom.getServerRoomResponseInvoke);
		},
		getServerRoomResponseInvoke:function (responseJson) {
			if(responseJson.responseType == 0){
				this.getServerRoomSuccess(responseJson);
			}
		},
		getServerRoomSuccess:function (catanRec) {
			var roomListDIV = jws.$("roomListDIV");
			roomListDIV.style.display = 'inline';

			var roomHtmlBegin = "<table align=\"center\" border=\"1\"><tr><th>RoomID</th><th>DESC</th><th>JOIN</th></tr>";
			var roomHtmlEnd = "</table>";
			var rooms = catanRec.waitingRoom.rooms;
			for(var i=0;i<rooms.length;i++){
				var room = rooms[i];
				var users = room.users;
				var roomPlayerSum = users.length;
				var roomUserCount = 0;
				for(var j=0;j<users.length;j++){
					if(users[j]!=null){
						roomUserCount++;
					}
				}
				var enterLink = "ENTER";
				if(roomUserCount<roomPlayerSum){
					enterLink = "<a href=\"javascript:catan.waitingRoom.doSelectRoom("+room.roomId+");\">ENTER</a>";
				}
				var aRoomHtml = "<tr><td>"+room.roomId+"</td><td>"+roomUserCount+"/"+roomPlayerSum+"</td><td>"+enterLink+"</td></tr>";
				roomHtmlBegin = roomHtmlBegin+aRoomHtml;
			}
			var roomHtml = roomHtmlBegin+roomHtmlEnd;
			roomListDIV.innerHTML = roomHtml;
		},

		doSelectRoom:function (roomId) {
			var selectRoom = {type:"doSelectRoom",roomId:roomId};
			catan.jws.catanRequest(selectRoom,this,catan.waitingRoom.doSelectRoomResponseInvoke);
		},
		doSelectRoomResponseInvoke:function (responseJson){
			if(responseJson.responseType == 0){
				this.doSelectRoomSuccess(responseJson.roomId);
			}
		},
		doSelectRoomSuccess:function (roomId) {
			var loginAndRoomDIV = jws.$("loginAndRoomDIV");
			loginAndRoomDIV.style.display = 'none';
			
			var catanGameDiv = jws.$("catanGame");
			catanGameDiv.style.display = 'inline';
			
			catan.gameRoom.show.showMessage("welcome join room:"+roomId,652, 400);
		}
};
