catanClient.view.diceView = {
		
	// Dice model
	diceModel: null,
	
	// Set model for this view
	setModel: function(model) {
		if (this.diceModel != null) {
			this.diceModel.removeReaintListener(this);
		}
		
		this.diceModel = model;
		if (this.diceModel != null) {
			this.diceModel.addRepaintListener(this);
		}
	},
	
	// Repaint dice for random or exact dice
	repaint: function() {
		this.clearPrivate();
		
		var self = this;
		if(this.diceModel.isRandom) {
			this.diceModel.randomIntervalId = setInterval(function() { self.paintRandomDicePrivate(); }, this.diceModel.RANDOM_INTERVAL_TIME);
		} else {
			this.paintExactDicePrivate();
		}
	},
	
	paintExactDicePrivate: function() {
		var firstDiceImg = catanClient.util.catanUtil.getImage(this.diceModel.centerDice1Src);
		var secondDiceImg = catanClient.util.catanUtil.getImage(this.diceModel.centerDice2Src);
		
		var self = this;
		firstDiceImg.onload = function() {
			secondDiceImg.onload = function() {
				self.diceModel.brush.drawImage(firstDiceImg, self.diceModel.CENTER_DICE1_X, self.diceModel.CENTER_DICE1_Y, self.diceModel.CENTER_DICE_WIDTH, self.diceModel.CENTER_DICE_HEIGHT);
				self.diceModel.brush.drawImage(secondDiceImg, self.diceModel.CENTER_DICE2_X, self.diceModel.CENTER_DICE2_Y, self.diceModel.CENTER_DICE_WIDTH, self.diceModel.CENTER_DICE_HEIGHT);
				
				setTimeout(function() {
					self.clearCenterAreaPrivate();
					self.diceModel.brush.drawImage(firstDiceImg, self.diceModel.CORNER_DICE1_X, self.diceModel.CORNER_DICE1_Y, self.diceModel.CORNER_DICE_WIDTH, self.diceModel.CORNER_DICE_HEIGHT);
					self.diceModel.brush.drawImage(secondDiceImg, self.diceModel.CORNER_DICE2_X, self.diceModel.CORNER_DICE2_Y, self.diceModel.CORNER_DICE_WIDTH, self.diceModel.CORNER_DICE_HEIGHT);
				}, self.diceModel.EXACT_DICE_TIME);
			};
		};
	},
	
	paintRandomDicePrivate: function() {
		var firstDiceNumber = catanClient.util.catanUtil.getRandomDiceNumber();
		var secondDiceNumber = catanClient.util.catanUtil.getRandomDiceNumber();
		
		var firstDiceImg = catanClient.util.catanUtil.getDiceAsImageByDiceNumber(firstDiceNumber);
		var secondDiceImg = catanClient.util.catanUtil.getDiceAsImageByDiceNumber(secondDiceNumber);
		
		var self = this;
		firstDiceImg.onload = function() {
			secondDiceImg.onload = function() {
				self.diceModel.brush.drawImage(firstDiceImg, self.diceModel.CENTER_DICE1_X, self.diceModel.CENTER_DICE1_Y, self.diceModel.CENTER_DICE_WIDTH, self.diceModel.CENTER_DICE_HEIGHT);
				self.diceModel.brush.drawImage(secondDiceImg, self.diceModel.CENTER_DICE2_X, self.diceModel.CENTER_DICE2_Y, self.diceModel.CENTER_DICE_WIDTH, self.diceModel.CENTER_DICE_HEIGHT);
			};
		};
	},
	
	clearPrivate: function() {
		this.clearCenterAreaPrivate();
		this.clearCornerAreaPrivate();
	},
	
	clearCornerAreaPrivate: function() {
		var x = this.diceModel.CORNER_CLEAR_AREA.X;
		var y = this.diceModel.CORNER_CLEAR_AREA.Y;
		var width = this.diceModel.CORNER_CLEAR_AREA.WIDTH;
		var height = this.diceModel.CORNER_CLEAR_AREA.HEIGHT;
		
		this.diceModel.brush.clearRect(x, y, width, height);
	},
	
	clearCenterAreaPrivate: function() {
		var x = this.diceModel.CENTER_CLEAR_AREA.X;
		var y = this.diceModel.CENTER_CLEAR_AREA.Y;
		var width = this.diceModel.CENTER_CLEAR_AREA.WIDTH;
		var height = this.diceModel.CENTER_CLEAR_AREA.HEIGHT;
		
		this.diceModel.brush.clearRect(x, y, width, height);
	},

};
