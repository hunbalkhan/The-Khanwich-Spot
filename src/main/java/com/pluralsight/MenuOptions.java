package com.pluralsight;

public class MenuOptions {

    // Sandwich options
    public static class Sandwich {
        public static final String[] sizes = {"4", "8", "12"};
        public static final double[] sizePrices = {5.50, 7.00, 8.50};

        public static final String[] breadTypes = {"White", "Wheat", "Italian Herbs & Cheese", "Wrap"};
    }

    // Drink options
    public static class Drink {
        public static final String[] sizes = {"Small", "Medium", "Large"};
        public static final double[] sizePrices = {2.00, 2.50, 3.00};

        public static final String[] flavors = {"Coke", "Fanta", "Sprite", "Mango Lassi", "Dr Pepper", "Fresca", "Water"};
    }

    // Chip options
    public static class Chips {
        public static final String[] flavors = {"Sour Cream & Onion", "BBQ", "Ranch", "Salt & Vinegar", "Salted", "Flamin' Hot"};
        public static final double flavorsPrice = 1.50;
    }

    // Topping options
    public static class Toppings {
        public static final String[] meats = {"Turkey", "Ham", "Chicken", "Roast Beef", "Salami", "Bacon", "Steak"};
        // meat prices to display
        public static final double[] meatBasePrices = {1.00, 2.00, 3.00}; // 4", 8", 12"
        public static final double[] meatExtraPrices = {0.50, 1.00, 1.50};

        public static final String[] cheeses = {"American", "Swiss", "Mozzarella", "Cheddar", "Provolone"};
        // cheese prices to display
        public static final double[] cheeseBasePrices = {0.75, 1.50, 2.25};
        public static final double[] cheeseExtraPrices = {0.30, 0.60, 0.90};

        public static final String[] veggies = {"Lettuce", "Peppers", "Onion", "Tomato", "Jalapeño", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"};
        public static final String[] sauces = {"Mayo", "Mustard", "Ranch", "Thousand Island", "Sweet Onion", "Balsamic Glaze", "Garlic Aioli"};
    }
}