catanClient.util.catanUtil = {

	// Resource images
	RESOURCE_SRC: {
		BRICK: "image/resource_brick.png",
		DESERT: "image/resource_desert.png",
		FOREST: "image/resource_forest.png",
		GRAIN:  "image/resource_grain.png",
		ORE: "image/resource_ore.png",
		SHEEP: "image/resource_sheep.png"
	},
		
	// Village images
	VILLAGE_SRC: {
		BLUE: "image/village_blue.png",
	    GREEN: "image/village_green.png",
	    RED: "image/village_red.png",
	    YELLOW: "image/village_yellow.png"
	},
	
	// City images
	CITY_SRC: {
		BLUE: "image/city_blue.png",
		GREEN: "image/city_green.png",
	    RED: "image/city_red.png",
	    YELLOW: "image/city_yellow.png",
	},
	
	// Road images
	ROAD_SRC: {
		BLUE: "image/road_blue.png",
		GREEN: "image/road_green.png",
        RED: "image/road_red.png",
        YELLOW: "image/road_yellow.png",
	},

	// Background image
	BACKGROUND_SRC: "image/background.jpg",
	
	// Canvas ids
	CANVAS_ID: {
		CATAN_CANVAS_ID: "catanCanvasId",
		STATIC_LAND_CANVAS_ID: "staticLandCanvasId",
		DYNAMIC_LAND_CANVAS_ID: "dynamicLandCanvasId",
		TEMP_CANVAS_ID: "tempCanvasId",
		USER_PANEL_CANVAS_ID: "userPanelCanvasId",
		CONTROL_PANEL_CANVAS_ID: "controlPanelCanvasId",
		RESOURCE_PANEL_CANVAS_ID: "resourcePanelCanvasId",
	},
	
	// Canvas context
	catanBrush: null,
	staticLandBrush: null,
	dynamicLandBrush: null,
	tempBrush: null,
	userPanelBrush: null,
	controlPanelBrush: null,
	resourcePanelBrush: null,
	
	// Dice image prefix
	DICE_SRC: {
		ONE: "image/dice1.png",
		TWO: "image/dice2.png",
		THREE: "image/dice3.png",
		FOUR: "image/dice4.png",
		FIVE: "image/dice5.png",
		SIX: "image/dice6.png"
	},
	
	// Count down long image & short image
	COUNT_DOWN_LONG_SRC: "image/countDownLong.png",
	COUNT_DOWN_SHORT_SRC: "image/countDownShort.png",
	
	// Dice animation
//	DICE_ANIMATION_GIF: this.IMAGE_PATH + "diceAnimation.gif",
	
	getImage: function(imageSrc) {
		if (!imageSrc) {
			return null;
		}
		
		var image = new Image();
		image.src = imageSrc;
		
		return image;
	},
	
	getCountDownLongAsImage: function() {
		return this.getImage(this.COUNT_DOWN_LONG_SRC);
	},

	getCountDownShortAsImage: function() {
		return this.getImage(this.COUNT_DOWN_SHORT_SRC);
	},
	
	// Get dice image from dice number
	getDiceAsImageByDiceNumber: function(diceNumber) {
		if (diceNumber < 1 || diceNumber > 6) {
			return null;
		}
		
		switch(diceNumber) {
			case 1:
				return this.getImage(this.DICE_SRC.ONE);
			case 2:
				return this.getImage(this.DICE_SRC.TWO);
			case 3:
				return this.getImage(this.DICE_SRC.THREE);
			case 4:
				return this.getImage(this.DICE_SRC.FOUR);
			case 5:
				return this.getImage(this.DICE_SRC.FIVE);
			case 6:
				return this.getImage(this.DICE_SRC.SIX);
		}
	},
	
	// Get dice src from dice number
	getDiceAsSrcByDiceNumber: function(diceNumber) {
		if (diceNumber < 1 || diceNumber > 6) {
			return null;
		}
		
		switch(diceNumber) {
			case 1:
				return this.DICE_SRC.ONE;
			case 2:
				return this.DICE_SRC.TWO;
			case 3:
				return this.DICE_SRC.THREE;
			case 4:
				return this.DICE_SRC.FOUR;
			case 5:
				return this.DICE_SRC.FIVE;
			case 6:
				return this.DICE_SRC.SIX;
		}
	},
	
	getRandomDiceNumber: function() {
		return Math.floor(Math.random() * 6 + 1);
	},
	
	getCatanBrush: function() {
		if (this.catanBrush == null) {
			var catanCanvas = document.getElementById(this.CANVAS_ID.CATAN_CANVAS_ID);
			this.catanBrush = catanCanvas.getContext("2d");
		}
		
		return this.catanBrush;
	},
	
	getStaticLandBrush: function() {
		if (this.staticLandBrush == null) {
			var staticLandCanvas = document.getElementById(this.CANVAS_ID.STATIC_LAND_CANVAS_ID);
			this.staticLandBrush = staticLandCanvas.getContext("2d");
		}
		
		return this.staticLandBrush;
	},
	checkNotNegativeInteger:function(integer){
		/*if(/[^\d]/.test(integer)||integer<0){
			false;
		}*/
		return true;
	},
	getDynamicLandBrush: function() {
		if (this.dynamicLandBrush == null) {
			var dynamicLandCanvas = document.getElementById(this.CANVAS_ID.DYNAMIC_LAND_CANVAS_ID);
			this.dynamicLandBrush = dynamicLandCanvas.getContext("2d");
		}
		
		return this.dynamicLandBrush;
	},
	
	getTempLandBrush: function() {
		if (this.tempBrush == null) {
			var tempCanvas = document.getElementById(this.CANVAS_ID.TEMP_CANVAS_ID);
			this.tempBrush = tempCanvas.getContext("2d");
		}
		
		return this.tempBrush;
	},
	
	getUserPanelBrush: function() {
		if (this.userPanelBrush == null) {
			var userPanelCanvas = document.getElementById(this.CANVAS_ID.USER_PANEL_CANVAS_ID);
			this.userPanelBrush = userPanelCanvas.getContext("2d");
		}
		
		return this.userPanelBrush;
	},
	
	getControlPanelBrush: function() {
		if (this.controlPanelBrush == null) {
			var controlPanelCanvas = document.getElementById(this.CANVAS_ID.CONTROL_PANEL_CANVAS_ID);
			this.controlPanelBrush = controlPanelCanvas.getContext("2d");
		}
		
		return this.controlPanelBrush;
	},

	getResourcePanelBrush: function() {
		if (this.resourcePanelBrush == null) {
			var resourcePanelCanvas = document.getElementById(this.CANVAS_ID.RESOURCE_PANEL_CANVAS_ID);
			this.resourcePanelBrush = resourcePanelCanvas.getContext("2d");
		}
		
		return this.resourcePanelBrush;
	},
	
	getResourceSrcByName: function(resourceName) {
		if ("brick" == resourceName) {
			return this.RESOURCE_SRC.BRICK;
		} else if ("desert" == resourceName) {
			return this.RESOURCE_SRC.DESERT;
		} else if ("forest" == resourceName) {
			return this.RESOURCE_SRC.FOREST;
		} else if ("grain" == resourceName) {
			return this.RESOURCE_SRC.GRAIN;
		} else if ("ore" == resourceName) {
			return this.RESOURCE_SRC.ORE;
		} else if ("sheep" == resourceName) {
			return this.RESOURCE_SRC.SHEEP;
		}
	}
	
};
