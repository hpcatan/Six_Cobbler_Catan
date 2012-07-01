catan.notices = {
		addAllCatanNotices:function () {
			catan.jws.addCatanNotice("Notice:Room Update",catan.room.controller,catan.room.controller.roomUpdate);
			catan.jws.addCatanNotice("Notice:WaitingRoom Update",catan.waitingRoom,catan.waitingRoom.getServerRoom);
			//catan.jws.addCatanNotice("Room Notice:Game Start",catan.gameRoom,catan.gameRoom.gameStart);
		}
};
