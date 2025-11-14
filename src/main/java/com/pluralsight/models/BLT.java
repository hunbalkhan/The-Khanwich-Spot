package com.pluralsight.models;

public class BLT extends SignatureSandwich{

    // constructor
    public BLT() {
        super("BLT", "8", "White", true);
        addSignatureToppings();
    }

    // manually adding set toppings
    @Override
    protected void addSignatureToppings() {
        addTopping(new Topping("Bacon", "meat", 0));
        addTopping(new Topping("Cheddar", "cheese", 0));
        addTopping(new Topping("Lettuce", "regular"));
        addTopping(new Topping("Tomato", "regular"));
        addTopping(new Topping("Ranch", "regular"));
    }
}
