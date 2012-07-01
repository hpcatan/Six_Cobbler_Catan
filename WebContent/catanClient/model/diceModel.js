catanClient.model.diceModel = {
	
	// Center dice width & height
	CENTER_DICE_WIDTH: 60,
	CENTER_DICE_HEIGHT: 60,
	
	// The first dice at center with its [x, y]
	CENTER_DICE1_X: 220,
	CENTER_DICE1_Y: 265,
	
	// The second dice at center with its [x, y]
	CENTER_DICE2_X: 290,
	CENTER_DICE2_Y: 265,
	
	// The first dice at corner with its [x, y]
	CORNER_DICE1_X: 8,
	CORNER_DICE1_Y: 40,
	
	// The second dice at corner with its [x, y]
	CORNER_DICE2_X: 37,
	CORNER_DICE2_Y: 40,

	// Corner dice width & height
	CORNER_DICE_WIDTH: 23,
	CORNER_DICE_HEIGHT: 25,
	
	// Center clear area
	CENTER_CLEAR_AREA: {
		X: 220,
		Y: 265,
		WIDTH: 130,
		HEIGHT: 60
	},

	// Corner clear area
	CORNER_CLEAR_AREA: {
		X: 8,
		Y: 40,
		WIDTH: 52,
		HEIGHT: 25
	},
	
	// How much time to show exact dice
	EXACT_DICE_TIME: 700,
	
	// Random dice interval time & its interval id
	RANDOM_INTERVAL_TIME: 200,
	randomIntervalId: 0,
	
	// Canvas context
	brush: null,
	
	// Is random
	isRandom: false,
	
	// Center dice src
	centerDice1Src: "",
	centerDice2Src: "",
	
	// View list
	viewList: [],
	
	// Add listener for repainting
	addRepaintListener: function(view) {
		this.viewList.push(view);
	},
	
	// Remove listener
	removeRepaintListener: function(view) {
		for(var i = 0; i < this.viewList.length; i++) {
			if (this.viewList[i] == view) {
				this.viewList.splice(i, 1);
			}
		}
	},
	
	/**
	 * Notify listener to repaint
	 */
	notifyRepaintListener: function() {
		for(var i = 0; i < this.viewList.length; i++) {
			this.viewList[i].repaint();
		}
	},
	
	/**
	 * Update dice properties with default brush or specify brush
	 * @param isRandom
	 * @param centerDice1Src
	 * @param centerDice2Src
	 * @param brush
	 */
	setDiceProperties: function(isRandom, centerDice1Number, centerDice2Number, brush) {
		if (brush) {
			this.brush = brush;
		} else {
			this.brush = catanClient.util.catanUtil.getTempLandBrush();
		}
		
		if (!isRandom) {
			if (this.randomIntervalId != 0) {
				// Should stop the random interval
				clearInterval(this.randomIntervalId);
			}

			this.centerDice1Src = catanClient.util.catanUtil.getDiceAsSrcByDiceNumber(centerDice1Number);
			this.centerDice2Src = catanClient.util.catanUtil.getDiceAsSrcByDiceNumber(centerDice2Number);
		}
		
		this.isRandom = isRandom;
		this.notifyRepaintListener();
	}
	
};