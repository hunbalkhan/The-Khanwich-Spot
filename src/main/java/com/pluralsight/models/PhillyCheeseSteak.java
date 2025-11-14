package com.pluralsight.models;

public class PhillyCheeseSteak extends SignatureSandwich {

    // Philly Cheese Steak always has the same base configuration
    public PhillyCheeseSteak() {
        super("Philly Cheese Steak", "8", "White", true);
        addSignatureToppings();
    }

    @Override
    protected void addSignatureToppings() {
        // Add signature toppings for Philly Cheese Steak
        addTopping(new Topping("Steak", "meat", 0));
        addTopping(new Topping("American", "cheese", 0));
        addTopping(new Topping("Peppers", "regular"));
        addTopping(new Topping("Mayo", "regular"));
    }
}