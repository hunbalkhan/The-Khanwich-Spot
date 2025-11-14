package com.pluralsight.models;

public class Topping {

    // Base class for toppings.

    private String name;
    private String category;
    private int extraCount;

    // constructor with topping
    public Topping(String name, String category, int extraCount) {
        this.name = name;
        this.category = category;
        this.extraCount = extraCount;
    }

    // constructor without extra topping
    public Topping(String name, String category) {
        this(name, category, 0); // 0 marks no extras, just base portion
    }

    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public int getExtraCount() {
        return extraCount;
    }

    @Override
    public String toString() {
        if (extraCount > 0) {
            return name + " (" + category + ", extra x" + extraCount + ")";
        } else {
            return name + " (" + category + ")";
        }
    }

}
