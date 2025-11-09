package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class UserInterface {

    private Order currentOrder;

    public void display() {
        System.out.println("---- Welcome to The Khanwich Spot ----");
        System.out.println("         <-===============->          ");

        boolean running = true; // keeps the main menu running

        // main program loop
        while (running) {
            showMainMenu();
            String choice = ConsoleHelper.promptForString("Select an option: ").trim().toLowerCase();

            switch (choice) {
                case "1":
                    startNewOrder();
                    break;
                case "2":
                    System.out.println(" <---  Goodbye!  --->");
                    running = false; // stops loop and exits app
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
                    break;
            }
        }
    }

    private void showMainMenu(){
        System.out.println("\n MAIN MENU");
        System.out.println("1) Start New Order");
        System.out.println("2) Exit");
    }

    // starts new order and opens ordering menu
    private void startNewOrder() {
        currentOrder = new Order();
        boolean ordering = true;

        while (ordering) {
            showOrderMenu();
            String choice = ConsoleHelper.promptForString("Select an option");

            switch (choice) {
                case "1":
                    addSandwich();
                    break;
                case "2":
                    addDrink();
                    break;
                case "3":
                    addChips();
                    break;
                case "4":
                    checkout(); // finish and display order
                    ordering = false; // exit loop
                    break;
                case "0":
                    System.out.println("\nThank you for visiting!");
                    ordering = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again...");
            }
        }
    }

    private void showOrderMenu() {
        System.out.println("\nORDER MENU");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
    }

    private void addSandwich() {
        System.out.println("\n--- ADD SANDWICH ---");

        String size = ConsoleHelper.promptForString("Choose size (4, 8, 12)");
        String bread = ConsoleHelper.promptForString("Choose bread (white, wheat, rye, wrap");
        boolean toasted = ConsoleHelper.promptForString("Toasted? (y/n)").equalsIgnoreCase("y");

        // empty sandwich object
        Sandwich sandwich = new Sandwich("Custom Sandwich", 0, size, bread, toasted, new ArrayList<>());

//        // sandwich is made, now add toppings
//        boolean running = true;
//        while (running) {
//            String toppingName = ConsoleHelper.promptForString("choose from regular list/ Meat list/ cheese list (or 'done' to finish)");
//
//            if (toppingName.equalsIgnoreCase("done")) {
//                running = false; // stops adding toppings
//            }
//            else {
//                // ask what type of topping it is
//                String category = ConsoleHelper.promptForString("Category (meat/cheese/regular");
//                int extraCount = 0;
//
//                // if premium topping, then ask how many extras
//                if (category.equalsIgnoreCase("meat") || category.equalsIgnoreCase("cheese")) {
//                    extraCount = (int) ConsoleHelper.promptForDouble("Quantity of Extra topping (0 for none)");
//                }
//
//                // add the topping to the sandwich
//                sandwich.addTopping(new Topping(toppingName, category, extraCount));
//            }
//        }
//
//        // add the finished complete sandwich to the order now
//        currentOrder.addItem(sandwich);
//        System.out.println("✅ Sandwich added!");

        // above sammich was created now we need to add toppings
        boolean running = true;
        while (running) {
            System.out.println("""
                    \nSelect toppings for your sandwich:
                    1) Meats (premium)
                    2) Cheeses (premium)
                    3) Veggies (regular)
                    4) Sauces (regular)
                    0) Done adding toppings
                    """);

            String choice = ConsoleHelper.promptForString("Enter choice");

            switch (choice) {
                case "1":
                    List<String> selectedMeats = ToppingsHelper.selectMultiple(ToppingsHelper.meats, "Select meats");
                    // loop through each meat inside of the list of options called meats in toppings helper class which is displayed through the method selectMultiple
                    for (String meat : selectedMeats) {
                        // Ask how many portions of meat
                        int extra = (int) ConsoleHelper.promptForDouble("Extra portions of" + meat + " (0 for none)");
                        //add meat to the sandwich
                        sandwich.addTopping(new Topping(meat, "meat", extra));
                    }
                    break;
                case "0": // finish
                    running = false;
                    break;
            }



//  PREV CODE
//            // sandwich is made, now add toppings
//            boolean running = true;
//            while (running) {
//                String toppingName = ConsoleHelper.promptForString("choose from regular list/ Meat list/ cheese list (or 'done' to finish)");
//
//                if (toppingName.equalsIgnoreCase("done")) {
//                    running = false; // stops adding toppings
//                }
//                else {
//                    // ask what type of topping it is
//                    String category = ConsoleHelper.promptForString("Category (meat/cheese/regular");
//                    int extraCount = 0;
//
//                    // if premium topping, then ask how many extras
//                    if (category.equalsIgnoreCase("meat") || category.equalsIgnoreCase("cheese")) {
//                        extraCount = (int) ConsoleHelper.promptForDouble("Quantity of Extra topping (0 for none)");
//                    }
//
//                    // add the topping to the sandwich
//                    sandwich.addTopping(new Topping(toppingName, category, extraCount));
        }
    }

    // handles drink creation
    private void addDrink() {
        System.out.println("\n--- ADD DRINK ---");

// old style without topping helper
        // Ask user for size & flavour
        String size = ConsoleHelper.promptForString("Choose size (small, medium, large)");
        String flavor = ConsoleHelper.promptForString("Enter flavour (Coke/Sprite/Fanta)");

        // create and add drink to order
        currentOrder.addItem(new Drink(flavor, size));
        System.out.println("✅ Drink added!");

        // select size
        String[] drinksSize = {"Small", "Medium", "Large"};


    }

    // handles chip creation
    private void addChips() {
        System.out.println("\n--- ADD CHIP ---");

        // ask user for flavor
        String flavor = ConsoleHelper.promptForString("Enter flavor (BBQ, SALTED)");

        // create and add chips to order
        currentOrder.addItem(new Chips(flavor));
        System.out.println("✅ Chips added!");
    }

    private void checkout() {
        System.out.println("\n--- ORDER SUMMARY ---");
        System.out.println(currentOrder.toString()); // show order using toString
        System.out.printf("TOTAL: $%.2f\n", currentOrder.calculateTotal());
        System.out.println();
        System.out.println("✅ Order complete!");
    }
}
