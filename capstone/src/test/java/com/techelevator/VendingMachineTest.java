package com.techelevator;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class VendingMachineTest {
VendingMachine vendingMachine1;
Map <String,Item> inventory;
VendingMachine vendingMachine2;
VendingMachine vendingMachine3;
VendingMachine vendingMachine4;

	@Before
	public void setUp() {
		
		vendingMachine1 = new VendingMachine();
		vendingMachine1.feedMoney(20);
		inventory = vendingMachine1.stockVendingMachine();
		
		vendingMachine2 = new VendingMachine();
		vendingMachine2.feedMoney(20.50);
		
		vendingMachine3 = new VendingMachine();
		vendingMachine3.feedMoney(5.65);
		
		vendingMachine4 = new VendingMachine();
		vendingMachine4.feedMoney(0.30);
	}
	@After
	public void tearDown() {
		vendingMachine1 = null;
		vendingMachine2 = null;
		vendingMachine3 = null;
		vendingMachine4 = null;
	}
	@Test
	public void test_FeedMoney() {
		double result = vendingMachine1.feedMoney(10);
		assertEquals("Thirty Dollar Test", 30, result, 0);
	}
	@Test
	public void test_GetChange20() {
		String result = vendingMachine1.giveChange();
		assertEquals("Twenty Dollar Test", "Your change is:\n$" + 20 + "\n" 
				+ 0 + "(Q) \n" + 0 + "(D) \n" + 0 + "(N)", result);
		}
		@Test
		public void test_GetChange_Quarter() {	
		String result = vendingMachine2.giveChange();
		assertEquals("Quarter test", "Your change is:\n$" + 20 + "\n" 
				+ 2 + "(Q) \n" + 0 + "(D) \n" + 0 + "(N)", result);
		} @Test
		public void test_GetChange_NickelAndDime() {
		String result = vendingMachine3.giveChange();
		assertEquals("Nickel and Dime Test", "Your change is:\n$" + 5 + "\n" 
				+ 2 + "(Q) \n" + 1 + "(D) \n" + 1 + "(N)", result);
		}
		@Test
		public void test_GetChange_ANickel() {
		String result = vendingMachine4.giveChange();
		assertEquals("Only Nickel Test", "Your change is:\n$" + 0 + "\n" 
				+ 1 + "(Q) \n" + 0 + "(D) \n" + 1 + "(N)", result);
		}
		@Test
		public void test_Dispense() {	
		String result = vendingMachine1.dispenseItem("A1");
		assertEquals("Dispense A1 5", inventory.get("A1").getName(), result);
		
		result = vendingMachine1.dispenseItem("A1");
		assertEquals("Dispense A1 4", inventory.get("A1").getName(), result);
		
		result = vendingMachine1.dispenseItem("A1");
		assertEquals("Dispense A1 3", inventory.get("A1").getName(), result);
		
		result = vendingMachine1.dispenseItem("A1");
		assertEquals("Dispense A1 2", inventory.get("A1").getName(), result);
		
		result = vendingMachine1.dispenseItem("A1");
		assertEquals("Dispense A1 2", inventory.get("A1").getName(), result);
		
		result = vendingMachine1.dispenseItem("A1");
		assertEquals("Dispense A1 empty", "Sorry " + inventory.get("A1") + " is sold out.", result);
		
		}
	}

