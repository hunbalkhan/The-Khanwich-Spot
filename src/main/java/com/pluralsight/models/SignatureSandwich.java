package com.pluralsight.models;


public abstract class SignatureSandwich extends Sandwich {

    // constructor - with predefined size, bread, toppings
    public SignatureSandwich(String name, String size, String breadType, boolean toasted) {
        super(name, size, breadType, toasted);
    }

    //adding a method where each signature sandwich chooses toppings
    protected abstract void addSignatureToppings();

}
