catanClient.model.cityModel = {
	
	viewList: [],
	playColor: "",
	x: -1,
	y: -1,
	cityImg: null,
	
	addRepaintListener: function(view) {
		this.viewList.push(view);
	},
	
	removeRepaintListener: function(view) {
		for(var i = 0; i < this.viewList.length; i++) {
			if (this.viewList[i] == view) {
				this.viewList.splice(i, 1);
			}
		}
	},
	
	notifyRepaintListener: function() {
		for(view in this.viewList) {
			view.repaint();
		}
	},
	
	updateModel: function(playColor, x, y) {
		this.playColor = playColor;
		this.x = x;
		this.y = y;
		this.cityImg = this.retrieveCityImgByPlayColor(playColor);
		
		this.notifyRepaintListener();
	},
	
	retrieveCityImgByPlayColor: function(playColor) {
		var playerColor = catanClient.model.playerModel.PLAYER_COLOR;
		var catanUtil = catanClient.util.catanUtil;
		var citySrc = catanClient.util.catanUtil.CITY_SRC;
		
		if (playerColor.RED == playColor) {
			catanUtil.getCityAsImage(citySrc.RED);
		} else if (playerColor.BLUE == playColor) {
			catanUtil.getCityAsImage(citySrc.BLUE);
		} else if (playerColor.YELLOW == playColor) {
			catanUtil.getCityAsImage(citySrc.YELLOW);
		} else if (playerColor.GREEN == playColor) {
			catanUtil.getCityAsImage(citySrc.GREEN);
		}
	}
	
};