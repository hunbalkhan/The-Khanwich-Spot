package com.pluralsight;

public abstract class Product {

    // The base class for everything that can be sold (sandwiches, drinks, chips). Will have name + price.

    // Protected can be used in this class and the extended classes
    protected String name;

    //todo: these could be private.

    public Product(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    // Abstract method that forces subclasses to define how their price calculation
    public abstract double calculatePrice();

    @Override
    public String toString() {
        return String.format("%s | $%.2f", name, calculatePrice());
    }
}