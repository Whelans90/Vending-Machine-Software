package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Menu {

	public static VendingMachine vendingMachine1 = new VendingMachine();
	public static Map<String, Item> inventory = vendingMachine1.stockVendingMachine();

	public static void mainOptions() {
		String selection;
		boolean flag = true;

		do {
			System.out.println("Welcome to the Vending Machine. Make a selection below.");
			System.out.println();
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase Item");
			System.out.println("(3) Terminate Vending Machine");

			Scanner userInput = new Scanner(System.in);
			selection = userInput.nextLine();

			switch (selection) {
			case "1":
				displayAllItems();
				break;
			case "2":
				flag = false;
				menuPurchase();
				break;
			case "3":
				vendingMachine1.composeLog();
				vendingMachine1.composeSale();
				exitVendingMachine();
				break;
			default:
				System.out.println();
				System.out.println("INPUT NOT RECOGNIZED. PLEASE TRY AGAIN.");
				System.out.println();
				break;
			}
		} while (flag);
	}// END mainOptions

	public static void menuPurchase() {
		String selection;
		boolean flag = true;

		do {
			System.out.println("Make a selection below");
			System.out.println();
			System.out.println("(1) Feed Money");
			System.out.println("(2) Select Item");
			System.out.println("(3) Finish Transaction");
			System.out.println("(4) Terminate Vending Machine");

			Scanner userInput = new Scanner(System.in);
			selection = userInput.nextLine();

			switch (selection) {
			case "1":
				getMoney();
				break;
			case "2":
				// System.out.println("call select item");

				Scanner item = new Scanner(System.in);
				System.out.println("Enter the item code you would like to purchase: ");
				String itemKey = item.nextLine();
				itemKey = itemKey.toUpperCase();
				vendingMachine1.dispenseItem(itemKey);
				System.out.println("Current Balance: " + vendingMachine1.getBalance());
				break;
			case "3":
				flag = false;

				System.out.println("call finish transaction");
				System.out.println(vendingMachine1.giveChange());

				mainOptions();
				break;
			case "4":
				vendingMachine1.composeSale();
				vendingMachine1.composeLog();
				exitVendingMachine();
				break;
			default:
				System.out.println();
				System.out.println("INPUT NOT RECOGNIZED. PLEASE TRY AGAIN.");
				System.out.println();
				break;
			}
		} while (flag);
	}// END menuPurchase

	public static void exitVendingMachine() {
		System.out.println();
		System.out.println("No more Vending Machine for you.");
		System.exit(0);
	}// END exitVendingMachine

	public static void displayAllItems() {
		File inventoryFile = null;
		inventoryFile = new File("/Users/rwolpert/vm.csv");

		ArrayList<String> displayList = new ArrayList<String>();

		for (String item : inventory.keySet()) {

			displayList.add(item + "\t" + inventory.get(item).getPrice() + "\t" + inventory.get(item).getName()
					+ "\n\t\t\tOnly " + inventory.get(item).getQuantity() + " left. Act now! You are soooo hungry.");
		}

		Collections.sort(displayList);

		for (int i = 0; i < displayList.size(); i++) {
			System.out.println(displayList.get(i));
		}
		System.out.println();
	}// END displayAllItems

	public static double getMoney() {
		double moneyInput;

		System.out.println("Enter an amount in whole dollars: $ ");

		Scanner userInput = new Scanner(System.in);
		moneyInput = Double.parseDouble(userInput.nextLine());
		vendingMachine1.feedMoney(moneyInput);

		System.out.println();
		System.out.println("Current Balance: " + vendingMachine1.getBalance());

		return moneyInput;
	}// END getMoney

}// END class