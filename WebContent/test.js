function testBefore() {
	//jws.$("catanGame").style.display = "inline";
}

function testAfter() {
	catanClient.controller.diceController.paint(true);
	var number1 = catanClient.util.catanUtil.getRandomDiceNumber();
	var number2 = catanClient.util.catanUtil.getRandomDiceNumber();
	setTimeout(function() {
		catanClient.controller.diceController.paint(false, number1, number2);
	}, 2000);
	
	// for test
	// jws.$("catanGame").style.display = "inline";

	/*
	 * countDownView.showCountDownTimeForDice();
	 * 
	 * setTimeout(function() {
	 * countDownView.showCountDownTimeForBuildingAndBuyingCard(); }, 5000);
	 * 
	 * setTimeout(function() { countDownView.showCountDownTimeForMarket(); },
	 * 2000);
	 */

	/*
	 * diceView.showDice();
	 * 
	 * var resultDiceNumber = [1, 4]; setTimeout(function() {
	 * diceView.showDice(resultDiceNumber); }, 2000);
	 * 
	 * setTimeout(function() { diceView.showDice(); }, 4000);
	 * 
	 * var resultDiceNumber1 = [2, 5]; setTimeout(function() {
	 * diceView.showDice(resultDiceNumber1); }, 6000);
	 */
}
