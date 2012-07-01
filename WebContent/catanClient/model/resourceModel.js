catanClient.model.resourceModel = {
	
	// This is the height & width for adding a resource to the view.
	RESOURCE_HEIGHT: 90,
	RESOURCE_WIDTH: 100,
	
	// Resource border
	BORDER_WIDTH: 1,
	
	currentData: function(resourceX, resourceY, resourceSrc, resourceNumber, brush) {
		// Canvas context
		this.brush = brush;

		// This is the [x, y] when trying to add this resource to the view.
		this.resourceX = resourceX;
		this.resourceY = resourceY;
		
		// Resource src * number
		this.resourceSrc = resourceSrc;
		this.resourceNumber = resourceNumber;
		
		this.resourceCircleX = this.resourceX + catanClient.model.resourceModel.RESOURCE_WIDTH / 2;
		this.resourceCircleY = this.resourceY + catanClient.model.resourceModel.RESOURCE_HEIGHT / 2;
		
		var xOff = 10;
		if (this.resourceNumber < 10) {
			xOff = 5;
		}
		this.numberX = this.resourceCircleX - xOff;
		this.numberY = this.resourceCircleY + 6;
	},
	
	// Notified views
	viewList: [],
	
	// Add listener for painting
	addPaintListener: function(view) {
		this.viewList.push(view);
	},
	
	// Remove listener
	removePaintListener: function(view) {
		for(var i = 0; i < this.viewList.length; i++) {
			if (this.viewList[i] == view) {
				this.viewList.splice(i, 1);
			}
		}
	},
	
	// Notify listener to paint
	notifyPaintListener: function(currentData) {
		for(var i = 0; i < this.viewList.length; i++) {
			this.viewList[i].paint(currentData);
		}
	},
	
	// Update model
	setResourceProperties: function(resourceX, resourceY, resourceSrc, resourceNumber, brush) {
		var localBrush = null;
		if (brush) {
			localBrush = brush;
		} else {
			localBrush = catanClient.util.catanUtil.getStaticLandBrush();
		}
		
		var currentData = new this.currentData(resourceX, resourceY, resourceSrc, resourceNumber, localBrush);
		
		this.notifyPaintListener(currentData);
	}
		
};