catanClient.controller.landController = {
		
	landModel: null,
	landView: null,

	init: function() {
		this.landModel = catanClient.model.landModel;
		this.landView = catanClient.view.landView;
	},
	
	/*setLogicalVertex: function(colNum, index, x, y) {
		this.landModel.setLogicalVertex = (colNum, index, x, y);
	},*/
	
	paint: function(resourceArray) {
		if (!resourceArray) {
			return;
		}
		
		for (var i = 0; i < resourceArray.length; i++) {
			var resource = resourceArray[i];
			
			if(i >= 0 && i < 3) {	// First column
				this.addToCol(0, i, resource);
			} else if(i >= 3 && i < 7) {	// Second column
				this.addToCol(1, i - 3, resource);
			} else if(i >=7 && i < 12) {	// Third column
				this.addToCol(2, i - 7, resource);
			} else if(i >= 12 && i < 16) {	// Fourth column
				this.addToCol(3, i - 12, resource);
			} else if(i >= 16 && i < 19) {	// Fifth column
				this.addToCol(4, i - 16, resource);
			}
		}
	},
	
	addToCol: function(colNum, index, resource) {
		var resourceY = this.landModel.Y_OFFSET + (((this.landModel.MAX_RESOURCE_IN_COLUMN * this.landModel.Y_RESOURCE_HEIGHT) - this.landModel.COLUMN_COUNTS[colNum] * this.landModel.Y_RESOURCE_HEIGHT) / 2) + ((index + 1) * this.landModel.Y_RESOURCE_HEIGHT); //+ (order * this.BORDER_WIDTH);
		var resourceX = this.landModel.X_OFFSET + ((colNum) * this.landModel.X_RESOURCE_WIDTH); //+ (this.BORDER_WIDTH * colNum);

		var resourceSrc = catanClient.util.catanUtil.getResourceSrcByName(resource[0]);
		var resourceNumber = resource[1];
		
		catanClient.controller.resourceController.paint(resourceX, resourceY, resourceSrc, resourceNumber);
		
		// TODO
		//this.landModel.setLogicalVertex(colNum, index, resourceX, resourceY);
	},
	
};
