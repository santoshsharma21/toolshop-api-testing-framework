/**
 * 
 */
package com.toolshop.utilities;

import com.github.javafaker.Faker;

/**
 * 
 */
public class FakerUtils {
	// faker instance
	private static Faker faker;
	
	// return random data
	public static String getRandomData(RandomDataConstant randomData) {
		faker = new Faker();
		switch(randomData) {
		case NAME:
			return faker.company().name();
		case SLUG:
			return faker.commerce().productName();
		default:
			return "data not available";
		}
	}
}