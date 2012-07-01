catanClient.view.catanCanvasView = {
		
	init: function(resourceArray) {
		this.loadBackground();
		landView.init(resourceArray);
	},
	
	loadBackground: function() {
		var catanBrush = catanUtil.getCatanCanvas();
		
		var backgroundImage = catanUtil.getBackgroundAsImage();
		backgroundImage.onload = function() {
			catanBrush.drawImage(backgroundImage, 0, 0, CATAN_WIDTH, CATAN_HEIGHT);
		};
	},
		
};
