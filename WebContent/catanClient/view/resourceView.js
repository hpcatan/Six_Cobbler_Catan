catanClient.view.resourceView = {
	
	// Resource model
	resourceModel: null,
	
	// Set model for this view
	setModel: function(model) {
		if (this.resourceModel != null) {
			this.resourceModel.removePaintListener(this);
		}
		
		this.resourceModel = model;
		if (this.resourceModel != null) {
			this.resourceModel.addPaintListener(this);
		}
	},
		
	paint: function(currentData) {
		var resourceImage = catanClient.util.catanUtil.getImage(currentData.resourceSrc);
		
		var self = this;
		resourceImage.onload = function() {
			self.paintResourcePrivate(resourceImage, currentData);
			self.paintResourceNumberPrivate(currentData);
		};
	},
	
	paintResourcePrivate: function(resourceImage, currentData) {
		currentData.brush.drawImage(resourceImage, currentData.resourceX, currentData.resourceY,
				this.resourceModel.RESOURCE_WIDTH, this.resourceModel.RESOURCE_HEIGHT);
	},
	
	paintResourceNumberPrivate: function(currentData) {
		var brush = currentData.brush;
		
		var x = currentData.resourceCircleX;
		var y = currentData.resourceCircleY;
		var number = currentData.resourceNumber;
		
		brush.beginPath();
		var radius = 15;
		brush.arc(x, y, radius, 0, 2 * Math.PI, false);
		brush.fillStyle = "#EEE8AA";
		brush.fill();
		/*landBrush.lineWidth = 0;
		landBrush.strokeStyle = "black";
		landBrush.stroke();*/
		
		brush.font = "15pt Times New Roman bold";
		brush.fillStyle = "black"; // stroke color
		
		brush.fillText(number, currentData.numberX, currentData.numberY);
	}
		
};