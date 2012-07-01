package com.games.catan.util;

import java.util.Random;

public class ArrayUtil {
	public static void makeArrayRandom(Object[] res) {
		Random random = new Random();
		for(int i=res.length-1;i>0;i--){//make sure location i's value
			int randomLocal = random.nextInt(i+1);
			if(randomLocal!=i){//if true to exchange value
				Object tmp = res[i];
				res[i] = res[randomLocal];
				res[randomLocal] = tmp;
			}
		}
	}
}
