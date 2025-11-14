package com.pluralsight.models;

import com.pluralsight.userinterface.ConsoleHelper;

import java.util.ArrayList;

public class Sandwich extends Product {

    // Represents a customizable sandwich. Will include size, bread, toppings, toasted option.

    private String size;
    private String breadType;
    private boolean toasted;
    private ArrayList<Topping> toppings;

    // Constructors
     public Sandwich(String name, String size, String breadType, boolean toasted) {
        super(name);
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
        this.toppings = new ArrayList<>(); // makes this start with an empty list of toppings
    }

    // Getters
    public String getSize() {
        return size;
    }
    public String getBreadType() {
        return breadType;
    }
    public boolean isToasted() {
        return toasted;
    }
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    // Returns base sandwich price depending on size
    private static double getBasePrice(String size) {
        return switch (size) {
            case "4" -> 5.50;
            case "8" -> 7.00;
            case "12" -> 8.50;
            default -> 0.0;
        };
    }

    // topping added to sammich
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    // helper for the meat pricing based on size of sammich and multiple meat add ons
    private double getMeatPrice(String size, int extraMeatCount) {
        double basePrice;
        double extraPrice;

        switch (size) {
            case "4":
                basePrice = 1.00;
                extraPrice = 0.50;
                break;
            case"8":
                basePrice = 2.00;
                extraPrice = 1.00;
                break;
            case "12":
                basePrice = 3.00;
                extraPrice = 1.50;
                break;
            default:
                return 0.0;
        }
        // extra meat add on top of base amount, or just base, works either way
        return basePrice + (extraPrice * extraMeatCount);
        //      1.00     +  (   .50    *     0        )
    }

    // helper for the cheese pricing
    private double getCheesePrice(String size, int extraCheeseCount) {
        double basePrice;
        double extraPrice;

        switch (size) {
            case "4":
                basePrice = 0.75;
                extraPrice = 0.30;
                break;
            case"8":
                basePrice = 1.50;
                extraPrice = 0.60;
                break;
            case "12":
                basePrice = 2.25;
                extraPrice = 0.90;
                break;
            default:
                return 0.0;
        }
        // extra cheese add on top of base amount, or just base, works either way
        return basePrice + (extraPrice * extraCheeseCount);
    }

    @Override
    public double calculatePrice() {
        double total = getBasePrice(size); // start with base sammich price

        for (Topping t : toppings) {
            String category = t.getCategory(); // meat, cheese, regular toppings etc.

            switch (category.toLowerCase()) {
                case "meat" -> total += getMeatPrice(size, t.getExtraCount());
                case "cheese" -> total += getCheesePrice(size, t.getExtraCount());
                // regular toppings/sauces are free
            }
        }
        return total;
    }


    // Display Info to console. keeping this so i can centre
    // Pretty console output
    public void displayDetails(int width) {

        // prints and centres name
        System.out.println(ConsoleHelper.centerText("--- " + name + " ---", width));

        // prints and centres size, bread, toasted
        System.out.println(ConsoleHelper.centerText("Size: " + size + " | Bread: " + breadType + " | Toasted: " + (toasted ? "Yes" : "No"), width));

        // prints and centres toppings header
        System.out.println(ConsoleHelper.centerText("Toppings:",width));

        // loops all toppings and prints & centres each one
        for (Topping t : toppings) {
            // toppings should show name, category and extra count if any
            System.out.println(ConsoleHelper.centerText(" - " + t, width));
        }

        // prints and centres the price
        System.out.printf(ConsoleHelper.centerText(
                String.format("Price: $%.2f\n", calculatePrice()), width));
        // prints a line seperator centered
        System.out.println("-".repeat(width));
    }

    @Override
    public String toString() {
        return String.format("Sandwich (%s\") - Bread: %s, Toasted: %s, Toppings: %s -- $%.2f",
                size, breadType, toasted ? "Yes" : "No", toppings.toString(), calculatePrice());
    }
}
