package com.pluralsight;

public class Drink extends Product{

    // Represents a drink with a size and flavor.

    private String size;
    private String flavor;

    public Drink(String flavor, String size) {
        super(flavor + " (" + size + ")", getPriceBySize(size)); // automatically sets name and price, different from before
        this.flavor = flavor;
        this.size = size;
    }

    public String getSize() {
        return size;
    }
    public String getFlavor() {
        return flavor;
    }

    private static double getPriceBySize(String size) {
        switch (size.toLowerCase()) {
            case "small": return 1.50;
            case "medium": return 2.00;
            case "large": return 2.50;
            default: return 0.0;
        }
    }

    @Override
    public double calculatePrice() {
        // since price is fixed by size we just need to return price
        return price;
    }

    @Override
    public String toString() {
        return "Drink - flavor= " + flavor + ", size= " + size + ", price = $" + getPrice();
    }
}
