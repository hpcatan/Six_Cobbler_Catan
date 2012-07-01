catan.room = {};
catan.room.controller={
	roomUpdate: function(catanRec){
		var room = catanRec.room;
		var players = room.players;
		// TODO invoke playerController to update player GUI
		catanClient.controller.userPanelController.updateUserPanel(players);
		
		var battleHistory = room.battleHistory;
		//TODO invoke battleHistory controller to update battleHistory GUI
		
		var chatHistory = room.chatHistory;
		//TODO invoke chatHistory controller to update chatHistory GUI.
		
		var land = room.land;
		var SoldierInResourceAreaIndex = room.SoldierInResourceAreaIndex;
		var resourceAreas = room.resourceAreas;
		var seaports = room.seaports;
		//TODO invoke land controller to update land GUI.
		
		
	}
};