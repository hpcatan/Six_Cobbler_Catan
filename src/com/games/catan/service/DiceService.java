package com.games.catan.service;

import java.util.Random;

import com.games.catan.pojo.Dice;

public class DiceService extends BaseService {

	/**
	 * Generate two random dice, e.g. (1, 2), (3, 5).
	 * @return Dice
	 */
	public Dice generateDice() {
		Random random = new Random();
		int firstNum = random.nextInt(6) + 1;
		int secondNum = random.nextInt(6) + 1;
		
		Dice dice = new Dice(firstNum, secondNum);
		return dice;
	}
	
	/**
	 * Generate random dice
	 */
	public Dice generateRandomDice() {
		Dice randomDice = new Dice(true);
		return randomDice;
	}
	
}
