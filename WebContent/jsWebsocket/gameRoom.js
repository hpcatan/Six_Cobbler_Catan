catan.gameRoom={
		roomUpdate:function (catanRec) {
			var room = catanRec.room;
			
			this.show.getChatContext().clearRect(652, 0, 300, 300);
			var players = room.players;
			for(var i=0;i<players.length;i++){
				if(players[i]!=null&&players[i].user!=null){
					this.show.showMessage(players[i].user.name,720,100+i*20);
				}
			}
		}/*,
		gameStart:function(catanRec){
			//alert(JSON.stringify(catanRec.land.resourceAreas));
			catan.gameRoom.show.showMessage("JSON.stringify(catanRec.land.resourceAreas)",652, 450);
		}*/
};
