package com.techelevator;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class VendingMachine {
	private Map<String, Item> inventory;
	private double balance;
	private double startingBalance;
	private double finalBalance;
	private File load = new File("/Users/swhelan/repos/team4-java-week4-pair-exercises/capstone/vendingmachine.csv");
	private Queue<String> logFile;
	private String transaction;
	private String vendingMachineLog;
	private File log;
	private File sale;
	private Map <String, Integer>  saleList = new HashMap<String, Integer>();

	public VendingMachine() {
		balance = 0;
		logFile = new LinkedList<String>();
	}

	public double getBalance() {
		return balance;
	}

	// ***************** SORT THE INPUT FILE *********
	public Map<String, Item> stockVendingMachine() {
		inventory = new HashMap<String, Item>();
		try (Scanner fileScanner = new Scanner(load)) {

			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] inventoryInformation = new String[4];
				inventoryInformation = line.split("\\|");
				double price = Double.parseDouble(inventoryInformation[2]);

				Item item = new Item(inventoryInformation[0], inventoryInformation[1], price, inventoryInformation[3]);

				try {
					inventory.put(inventoryInformation[0], item);
				} catch (Exception ex) {
					System.out.print("Nothing was added to the map");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.print("There was an error with your sort");
		}
		return inventory;
	}

	public double feedMoney(double cash) {
		startingBalance = balance;
		balance += cash;

		// Writes a Line in the log queue
		transaction = "FEED MONEY";
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String logStamp = (timeStamp + " " + transaction + " $" + startingBalance + " $" + balance);
		logFile.add(logStamp);
		return balance;
	}

	public String dispenseItem(String key) {
		Item item = inventory.get(key);

	//	if (startingBalance - item.getPrice() < 0) {
	//		return "Sorry Need More Money";
		// else {
			if (item.getQuantity() == 0)
				return "Sorry " + item + " is sold out.";

			else {
				// FOR LOG
				transaction = item.getName().toUpperCase();
				startingBalance = balance;
				balance -= item.getPrice();
				item.dispense();

				// FOR SALE REPORT
				finalBalance += item.getPrice();
				saleList.put(item.getName(), item.getNumberSold());

				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				String logStamp = (timeStamp + " " + transaction + " $" + startingBalance + " $" + balance);
				logFile.add(logStamp);

				return item.getName();
		//	}
		}
	}
/**
 * Determines the denomination of coins to return to user
 * @return A string representing the amount of each coin returned
 */
	public String giveChange() {
		int dollars = 0;
		int quarters = 0;
		int dimes = 0;
		int nickels = 0;
		startingBalance = balance;

		if (balance >= 1) {
			dollars = (int) balance; // Truncates to dollar value
			balance -= (double) dollars;
		}
		if (balance > 0) {
			balance *= 100;
			if (balance >= 25) {
				quarters = (int) balance / 25;
				balance -= (double) quarters * 25;
			}
			if (balance > 10) {
				dimes = (int) balance / 10;
				balance -= (double) dimes * 10;
			}
			if (balance >= 5) {
				nickels = (int) balance / 5;
				balance -= (double) nickels * 5;
			}
		}

		balance = 0;

		transaction = "GAVE CHANGE";
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String logStamp = (timeStamp + " " + transaction + " $" + startingBalance + " $" + balance);
		logFile.add(logStamp);

		return "Your change is:\n$" + dollars + "\n" + quarters + "(Q) \n" + dimes + "(D) \n" + nickels + "(N)";

	}
	/**
	 * Generates the final sale log for vending machine
	 */
	public void composeSale() {
		File newDirectory = new File("/Users/swhelan/repos/team4-java-week4-pair-exercises/capstone/");
		sale = new File(newDirectory, "vendingMachineSale.txt");
		
		try {
			sale.createNewFile();
		} catch (IOException e1) {
			System.out.print("File Was Not Made");
		} 		
		
		try (PrintWriter writer = new PrintWriter(sale)) {
			
			
			for (String entry : saleList.keySet()) {
				writer.println(entry + "\t" + saleList.get(entry));
		

			}writer.println(); 
			writer.print(finalBalance);

		} catch (FileNotFoundException e) {
			System.out.print("File Log Was Not Created");
		} 
	}
	
	
	// CREATES THE FINAL LOG FILE USING THE GENERATED QUEUE
	public void composeLog() {
		File newDirectory = new File("/Users/swhelan/repos/team4-java-week4-pair-exercises/capstone/");
		log = new File(newDirectory, "vendingMachineLog.txt");
		
	
		try {
			log.createNewFile();
		} catch (IOException e1) {
			System.out.print("File Was Not Made");
		}

		try (PrintWriter writer = new PrintWriter(log)) {
			for (String entry : logFile) {
				writer.println(entry);
				writer.println();

			}

		} catch (FileNotFoundException e) {
			System.out.print("File Log Was Not Created");
		}
	}

}