catanClient.controller.diceController = {
	
	// Dice model
	diceModel: null,
	
	// Dice view
	diceView: null,
	
	init: function() {
		this.diceModel = catanClient.model.diceModel;
		this.diceView = catanClient.view.diceView;
		
		this.diceView.setModel(this.diceModel);
	},
	
	/**
	 * Paint dice
	 * @param isRandom
	 * @param centerDice1Src
	 * @param centerDice2Src
	 * @param brush
	 */
	paint: function(isRandom, centerDice1Number, centerDice2Number, brush) {
		this.diceModel.setDiceProperties(isRandom, centerDice1Number, centerDice2Number, brush);
	},
	
	paintFromJSON: function(diceJSON) {
		var isRandom = diceJSON.isRandom;
		var centerDice1Number = diceJSON.firstNumber;
		var centerDice2Number = diceJSON.secondNumber;
		
		this.paint(isRandom, centerDice1Number, centerDice2Number);
	}
	
};