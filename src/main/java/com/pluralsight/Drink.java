package com.pluralsight;

public class Drink extends Product{

    // Represents a drink with a size and flavor.

    private String size;
    private String flavor;

    public Drink(String flavor, String size) {
        super(flavor + " (" + size + ")"); // automatically sets name and price, different from before
        this.flavor = flavor;
        this.size = size;
    }

    public String getSize() {
        return size;
    }
    public String getFlavor() {
        return flavor;
    }

//    private static double getPriceBySize(String size) {
//        switch (size.toLowerCase()) {
//            case "small": return 1.50;
//            case "medium": return 2.00;
//            case "large": return 2.50;
//            default: return 0.0;
//        }
//    }

    @Override
    public double calculatePrice() {
        // since price is fixed by size we just need to return price
        return switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.0;
        };
    }

    public void displayDetails(int width) {
        System.out.println(ConsoleHelper.centerText("--- Drink: " + flavor + " (" + size + ") ---", width));
        System.out.printf("Price: $%.2f\n", calculatePrice());
        System.out.println(ConsoleHelper.centerText("-".repeat(width), width));
    }
}
