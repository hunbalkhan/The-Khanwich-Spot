package com.pluralsight.models;

import com.pluralsight.userinterface.ConsoleHelper;

public class Chips extends Product {

    // Represents a bag of chips (type/flavor).
    private String flavor;

    public Chips(String flavor) {
        super("Chips (" + flavor + ")");
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public double calculatePrice() {
        return 1.50;
    }

    public void displayDetails(int width) {
        System.out.println(ConsoleHelper.centerText("--- Chips: " + flavor + " ---", width));
        System.out.printf("Price: $%.2f\n", calculatePrice());
        System.out.println(ConsoleHelper.centerText("-".repeat(width), width));
    }
}
