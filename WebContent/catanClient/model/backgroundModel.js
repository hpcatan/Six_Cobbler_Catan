catanClient.model.backgroundModel = {
	
	// From [x, y] to draw the background
	START_X: 0,
	START_Y: 0,
	
	// Width & height
	WIDTH: 960,
	HEIGHT: 630,
	
	// Canvas context
	brush: null,
	
	// Background image src
	backgroundSrc: null,
	
	// View list
	viewList: [],
	
	/**
	 * Add listener for painting
	 * @param view
	 */
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
	notifyPaintListener: function() {
		for(var i = 0; i < this.viewList.length; i++) {
			this.viewList[i].paint();
		}
	},
	
	/**
	 * Update model properties, if not set brush this model will use its default brush
	 * @param backgroundSrc
	 * @param brush
	 */
	setBackgroundProperties: function(backgroundSrc, brush) {
		if (brush) {
			this.brush = brush;
		} else {
			this.brush = catanClient.util.catanUtil.getCatanBrush();
		}
		
		if (backgroundSrc) {
			this.backgroundSrc = backgroundSrc;
		} else {
			this.backgroundSrc = catanClient.util.catanUtil.BACKGROUND_SRC;
		}
		
		this.notifyPaintListener();
	}
		
};