catanClient.view.landView = {
	landController: null,
	
	addLandListener: function(landController) {
		this.landController = landController;
	},
	
	init: function(resourceArray) {
		this.loadMainland(resourceArray);
//		this.initResource2LogicalVertexMapping(resourceArray);
	},
	
	addVillage: function(vertex) {
		// TODO
	},
	
	addCity: function(vertex) {
		// TODO
	},
	
	isBuildingExist: function(vertex) {
		// TODO
	},
	
	addRoad: function(roadIndex) {
		// TODO
	},
	
	isRoadExist: function(roadIndex) {
		// TODO
	},
	
	activateAllActions: function() {
		// TODO
	},
	
	deactivateAllActions: function() {
		// TODO
	},
	
	addPorts: function() {
		// TODO
	}
	
};

/*LandView.prototype.initResource2LogicalVertexMapping = function(resourceArray) {
	for (var i = 0; i < resourceArray.length; i++) {
		var resourceNum = resourceArray[i][1];
		
		if(i >= 0 && i < 3) {	// First column
			this.add2Resource2LogicalVertexArray(resourceNum, i);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 3);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 4);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 7);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 8);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 12);
		} else if(i >= 3 && i < 7) {	// Second column
			this.add2Resource2LogicalVertexArray(resourceNum, i + 4);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 8);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 9);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 13);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 14);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 19);
		} else if(i >=7 && i < 12) {	// Third column
			this.add2Resource2LogicalVertexArray(resourceNum, i + 9);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 14);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 15);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 20);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 21);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 26);
		} else if(i >= 12 && i < 16) {	// Fourth column
			this.add2Resource2LogicalVertexArray(resourceNum, i + 16);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 21);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 22);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 26);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 27);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 31);
		} else if(i >= 16 && i < 19) {	// Fifth column
			this.add2Resource2LogicalVertexArray(resourceNum, i + 23);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 27);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 28);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 31);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 32);
			this.add2Resource2LogicalVertexArray(resourceNum, i + 35);
		}
	}
};

LandView.prototype.add2Resource2LogicalVertexArray = function(resourceNumKey, logicalVertexValue) {
	// changed
	if (!this.landModel.resource2LogicalVertexArray[resourceNumKey]) {
		this.landModel.resource2LogicalVertexArray[resourceNumKey] = new Array();
	}
	
	this.landModel.resource2LogicalVertexArray[resourceNumKey].push(logicalVertexValue);
};*/

/* #0
 * LandView.prototype.bind = function(Method){
    var self = this;

	return (function() {
		return (Method.apply(self, arguments));
	});
};*/
