catanClient.model.landModel = {
	
	// Column resource count
	COLUMN_COUNTS: {0:3, 1:4, 2:5, 3:4, 4:3},
		
	// For catan game mainland
	X_OFFSET: 50,
	Y_OFFSET: -40,
	
	// Max resources in one column
	MAX_RESOURCE_IN_COLUMN: 5,
	
	// For calculate x coordinate and y coordinate
	Y_RESOURCE_HEIGHT: 100,
	X_RESOURCE_WIDTH: 90,
	
	// Resource border width
	BORDER_WIDTH: catanClient.model.resourceModel.BORDER_WIDTH,
	
	QUARTER_RESOURCE_WIDTH: catanClient.model.resourceModel.RESOURCE_WIDTH / 4,
	HALF_RESOURCE_WIDTH: catanClient.model.resourceModel.RESOURCE_WIDTH / 2,
	THIRD_RESOURCE_WIDTH: catanClient.model.resourceModel.RESOURCE_WIDTH * 0.75,
	
	HALF_RESOURCE_HEIGHT: this.HEX_IMAGE_HEIGHT / 2,
		
	// Start from 1, {1: [1, 4], 2: [1, 5], 3: [2, 5], 4: [2, 6], 5: [3, 6], 6: [3, 7], 7: [4, 8], 8: [5, 9], 9: [6, 10], 10: [7, 11], 11: [8, 12], 12: [8, 13], 13: [9, 13], 14: [9, 14], 15: [10, 14], 16: [10, 15], 17: [11, 15], 18: [11, 16], 19: [12, 17], 20: [13, 18], 21: [14, 19], 22: [15, 20], 23: [16, 21], 24: [17, 22], 25: [17, 23], 26: [18, 23], 27: [18, 24], 28: [19, 24], 29: [19, 25], 30: [20, 25], 31: [20, 26], 32: [21, 26], 33: [21, 27], 34: [22, 28], 35: [23, 29], 36: [24, 30], 37: [25, 31], 38: [26, 32], 39: [27, 33], 40: [28, 34], 41: [29, 34], 42: [29, 35], 43: [30, 35], 44: [30, 36], 45: [31, 36], 46: [31, 37], 47: [32, 37], 48: [32, 38], 49: [33, 38], 50: [34, 39], 51: [35, 40], 52: [36, 41], 53: [37, 42], 54: [38, 43], 55: [39, 44], 56: [40, 44], 57: [40, 45], 58: [41, 45], 59: [41, 46], 60: [42, 46], 61: [42, 47], 62: [43, 47], 63: [44, 48], 64: [45, 49], 65: [46, 50], 66: [47, 51], 67: [48, 52], 68: [49, 52], 69: [49, 53], 70: [50, 53], 71: [50, 54], 72: [51, 54]},
	road2VertexMap: {0: [0, 3], 1: [0, 4], 2: [1, 4], 3: [1, 5], 4: [2, 5], 5: [2, 6], 6: [3, 7], 7: [4, 8], 8: [5, 9], 9: [6, 10], 10: [7, 11], 11: [7, 12], 12: [8, 12], 13: [8, 13], 14: [9, 13], 15: [9, 14], 16: [10, 14], 17: [10, 15], 18: [11, 16], 19: [12, 17], 20: [13, 18], 21: [14, 19], 22: [15, 20], 23: [16, 21], 24: [16, 22], 25: [17, 22], 26: [17, 23], 27: [18, 23], 28: [18, 24], 29: [19, 24], 30: [19, 25], 31: [20, 25], 32: [20, 26], 33: [21, 27], 34: [22, 28], 35: [23, 29], 36: [24, 30], 37: [25, 31], 38: [26, 32], 39: [27, 33], 40: [28, 33], 41: [28, 34], 42: [29, 34], 43: [29, 35], 44: [30, 35], 45: [30, 36], 46: [31, 36], 47: [31, 37], 48: [32, 37], 49: [33, 38], 50: [34, 39], 51: [35, 40], 52: [36, 41], 53: [37, 42], 54: [38, 43], 55: [39, 43], 56: [39, 44], 57: [40, 44], 58: [40, 45], 59: [41, 45], 60: [41, 46], 61: [42, 46], 62: [43, 47], 63: [44, 48], 64: [45, 49], 65: [46, 50], 66: [47, 51], 67: [48, 51], 68: [48, 52], 69: [49, 52], 70: [49, 53], 71: [50, 53]},

	// Key[logical index] : value[absolute coordinate]
	logicalVertexMap: [],
	
	// Key[resource num] : value[logical vertex array]
	resource2LogicalVertexArray: [],

	// Hex height & width
//	HEX_IMAGE_HEIGHT: Y_HEX_HEIGHT - 10,
//	HEX_IMAGE_WIDTH: X_HEX_WIDTH + 10,

	setLogicalVertex: function(colNum, index, x, y) {
		// Add vertex coordinates to LandModel.logicalVertexMap
		switch(colNum) {
			case 0:
				this.addVertexFromAbsolute2Logical(index, x - 25, y + 26);
				this.addVertexFromAbsolute2Logical(index + 3, x, y - 24);
				this.addVertexFromAbsolute2Logical(index + 7, x + 66, y - 24);
				this.addVertexFromAbsolute2Logical(index + 12, x + 95, y + 26);
				
				if (index == 2) {
					this.addVertexFromAbsolute2Logical(index + 4, x, y + 75);
					this.addVertexFromAbsolute2Logical(index + 8, x + 66, y + 75);
				}
				break;
			case 1:
				if (index == 0) {
					this.addVertexFromAbsolute2Logical(index + 11, x, y - 24);
				}

				this.addVertexFromAbsolute2Logical(index + 16, x + 66, y - 24);
				this.addVertexFromAbsolute2Logical(index + 22, x + 95, y + 26);
				
				if(index == 3) {
					this.addVertexFromAbsolute2Logical(index + 12, x, y + 75);
					this.addVertexFromAbsolute2Logical(index + 17, x + 66, y + 75);
				}
				break;
			case 2:
				if (index == 0) {
					this.addVertexFromAbsolute2Logical(index + 21, x, y - 20);
				}

				this.addVertexFromAbsolute2Logical(index + 27, x + 66, y - 24);
				this.addVertexFromAbsolute2Logical(index + 33, x + 95, y + 26);
				
				if(index == 4) {
					this.addVertexFromAbsolute2Logical(index + 22, x, y + 75);
					this.addVertexFromAbsolute2Logical(index + 28, x + 66, y + 75);
				}
				break;
			case 3:
				this.addVertexFromAbsolute2Logical(index + 38, x + 66, y - 24);
				this.addVertexFromAbsolute2Logical(index + 43, x + 95, y + 26);
				
				if(index == 3) {
					this.addVertexFromAbsolute2Logical(index + 39, x + 66, y + 75);
				}
				break;
			case 4:
				this.addVertexFromAbsolute2Logical(index + 47, x + 66, y - 24);
				this.addVertexFromAbsolute2Logical(index + 51, x + 95, y + 26);
				
				if (index == 2) {
					this.addVertexFromAbsolute2Logical(index + 48, x + 66, y + 75);
				}
				break;
		}
	},
	
	addVertexFromAbsolute2Logical: function(index, x, y) {
		var coordinate = new Vertex();
		coordinate.x = x;
		coordinate.y = y;
		this.logicalVertexMap[index] = coordinate;
		
		// TODO test
		var dynamicLandBrush = catanUtil.getDynamicLandCanvas();
		var image = new Image();
		if (index % 10 == 0) {
			image.src = "image/2_0_city_green.png";
			image.onload = function() {
				dynamicLandBrush.drawImage(image, x, y, 30, 30);
			};
		} else if (index % 10 == 1) {
			image.src = "image/2_1_city_yellow.png";
			image.onload = function() {
				dynamicLandBrush.drawImage(image, x, y, 30, 30);
			};
		} else if (index % 10 == 2) {
			image.src = "image/2_2_city_red.png";
			image.onload = function() {
				dynamicLandBrush.drawImage(image, x, y, 30, 30);
			};
		} else if (index % 10 == 3) {
			image.src = "image/2_3_city_blue.png";
			image.onload = function() {
				dynamicLandBrush.drawImage(image, x, y, 30, 30);
			};
		}
	}
		
};
