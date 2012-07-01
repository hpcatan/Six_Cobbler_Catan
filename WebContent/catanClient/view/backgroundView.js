catanClient.view.backgroundView = {
	
	// Background model
	backgroundModel: null,
	
	// Set model for this view
	setModel: function(model) {
		if (this.backgroundModel != null) {
			this.backgroundModel.removePaintListener(this);
		}
		
		this.backgroundModel = model;
		if (this.backgroundModel != null) {
			this.backgroundModel.addPaintListener(this);
		}
	},
	
	// Paint
	paint: function() {
		var brush = this.backgroundModel.brush;
		var backgroundImage = catanClient.util.catanUtil.getImage(this.backgroundModel.backgroundSrc);
		var width = this.backgroundModel.WIDTH;
		var height = this.backgroundModel.HEIGHT;
		
		backgroundImage.onload = function() {
			brush.drawImage(backgroundImage, 0, 0, width, height);
		};
	},

};
