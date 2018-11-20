package com.techelevator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	Item newItem1;
	Item newItem2;
	Item newItem3;
	Item newItem4;
	
	@Before
	public void setUp() {
		newItem1 = new Item("A1","pringle", 2.00, "Chip");
		newItem2 = new Item("B2", "Sour Patch Kids", 1.50, "Candy");
		newItem3 = new Item("C3", "Soda Pop", 1.25, "Drink");
		newItem4 = new Item("D4", "Bazooka", 0.50, "Gum");
	}

	@After
	public void tearDown() throws Exception {
		newItem1 = null;
		newItem2 = null;
	}

	@Test
	public void testGetPrice() {
		double result = newItem1.getPrice();
		Assert.assertEquals("Testing Chips", 2.00, result, 0);
		
		result = newItem2.getPrice();
		Assert.assertEquals("Testing candy", 1.50, result, 0);
		
		result = newItem3.getPrice();
		Assert.assertEquals("Testing Drink", 1.25, result, 0);
		
	}	
	@Test
	public void testGetSound() {
		String result = newItem1.getSound();
		Assert.assertEquals("Testing Chips", "Crunch Crunch, Yum!", result);
		
		result = newItem2.getSound();
		Assert.assertEquals("Testing candy", "Munch Munch, Yum!", result);
		
		result = newItem3.getSound();
		Assert.assertEquals("Testing Drink", "Glug Glug, Yum!", result);
	
		result = newItem4.getSound();
		Assert.assertEquals("Testing Gum", "Chew Chew, Yum!", result);

		
	}
	@Test
	public void testDispense() {
		int result = newItem1.dispense();
		Assert.assertEquals("Testing Chips", 4, result);
		
		result = newItem1.dispense();
		Assert.assertEquals("Testing Chips 3", 3, result);
		
		result = newItem1.dispense();
		Assert.assertEquals("Testing Chips 2", 2, result);
		
		result = newItem1.dispense();
		Assert.assertEquals("Testing Chips 1", 1, result);
		
		result = newItem1.dispense();
		Assert.assertEquals("Testing Chips 0", 0, result);
	}

}
