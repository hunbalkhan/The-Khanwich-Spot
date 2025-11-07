package com.pluralsight;

public class Chips extends Product{

    // Represents a bag of chips (type/flavor).
    private String flavor;

    public Chips(String flavor) {
        super("Chips (" + flavor + ")", 1.50);
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public double calculatePrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Chips - " + flavor + " | Price: $" + String.format("%.2f", calculatePrice());
    }

}
