catanClient.controller.userPanelController = {
	getUserIndexArray : function() {
		var userIndexArray = catanClient.model.userPanel.USER_INDEX;

		return userIndexArray;
	},

	updateUserPanel : function(serverUserPanelModel) {
		//  decode serverUserPanelModel to localMode
		catanClient.model.userPanel.adapter.decodeServerModel(serverUserPanelModel);
		//  invoke userPanelView function
		catanClient.view.userPanelView.update();
	}
};
