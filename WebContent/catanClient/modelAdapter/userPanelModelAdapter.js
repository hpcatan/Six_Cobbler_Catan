catanClient.model.userPanel.adapter={
	decodeServerModel:function(serverModel){
		this.clearModel();
		var players = serverModel;
		var userPanel = catanClient.model.userPanel;
		for(var i=0;i<players.length;i++){
			if(players[i]){
				var player = players[i];
				var localUserPanel = userPanel.players[i];
				if(player.user&&player.user.name)
					localUserPanel.name = player.user.name;
				if(player.playerColor=="red")
					localUserPanel.index = catanClient.model.userPanel.USER_INDEX[0];
				else if(player.playerColor=="yellow")
					localUserPanel.index = catanClient.model.userPanel.USER_INDEX[1];
				else if(player.playerColor=="green")
					localUserPanel.index = catanClient.model.userPanel.USER_INDEX[2];
				else if(player.playerColor=="blue")
					localUserPanel.index = catanClient.model.userPanel.USER_INDEX[3];
				if(player.buildingAndCardPoints)
					localUserPanel.score = player.buildingAndCardPoints;
				if(player.roads&&player.roads.length)
					localUserPanel.roadCount = player.roads.length;
				if(player.resourceCards&&player.resourceCards.length)
					localUserPanel.resourceCount = player.resourceCards.length;
			}
		}
	},

	clearModel:function(){
		var players = catanClient.model.userPanel.players;
		for(var i=0;i<players.length;i++){
			var player = players[i];
			player.name = "";
			player.index = "";
			player.score = 0;
			player.roadCount = 0;
			player.resourceCount = 0;
			player.soliderCount = 0;
		}
	}
};