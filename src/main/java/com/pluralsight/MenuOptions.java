package com.pluralsight;

public class MenuOptions {

    // Sandwich options
    public static class Sandwich {
        public static final String[] sizes = {"4", "8", "12"};
        public static final String[] breadTypes = {"White", "Wheat", "Rye", "Wrap"};
    }

    // Drink options
    public static class Drink {
        public static final String[] sizes = {"Small", "Medium", "Large"};
        public static final String[] flavors = {"Coke", "Fanta", "Sprite", "Mango Lassi", "Dr Pepper", "Fresca", "Water"};
    }

    // Chip options
    public static class Chips {
        public static final String[] flavors = {"Sour Cream & Onion", "BBQ", "Ranch", "Salt & Vinegar", "Salted", "Flamin' Hot"};
    }

    // Topping options
    public static class Toppings {
        public static final String[] meats = {"Turkey", "Ham", "Chicken", "Roast Beef", "Salami", "Bacon", "Steak"};
        public static final String[] cheeses = {"American", "Swiss", "Cheddar", "Provolone"};
        public static final String[] veggies = {"Lettuce", "Peppers", "Onion", "Tomato", "Jalapenos", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"};
        public static final String[] sauces = {"Mayo", "Mustard", "Ranch", "Thousand Island", "Ketchup", "Vinaigrette"};
    }

}