package com.techelevator;


public class Item {
private String name;
private double price;
private String category;
private String sound;
private int quantity;
private int numberSold;

public Item (String location, String name, double price, String category) {
    this.name = name;
    this.category = category;
    this.price = price;
    quantity = 5;
    numberSold = 0;

}
public String getName() {
    return name;        
}

public double getPrice() {
    return price;
}
public String getCategory() {
    return category;
}
public int getNumberSold() {
	return numberSold;
}

public String getSound() {
    if (category.equalsIgnoreCase("Drink")) {
        sound = "Glug Glug, Yum!";
    }
    if (category.equalsIgnoreCase("Gum")) {
        sound = "Chew Chew, Yum!";
    }
    if (category.equalsIgnoreCase("Candy")) {
         sound = "Munch Munch, Yum!";
    }
    if (category.equalsIgnoreCase("Chip")) {
         sound = "Crunch Crunch, Yum!";
    }
    
    return sound;
}
public int getQuantity() {
    return quantity;
}
public int dispense() {
    quantity -= 1;
    numberSold += 1;
    return quantity;
}
}