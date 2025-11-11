package com.pluralsight;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {

    private Order currentOrder;

    public void display() {
        System.out.println("\n---- Welcome to The Khanwich Spot ----");


        boolean running = true; // keeps the main menu running

        // main program loop
        while (running) {
            showMainMenu();

            int choice = ConsoleHelper.promptForInt("Select an option");
            switch (choice) {
                case 1 -> startNewOrder();
                case 2 -> {
                    System.out.println("\n--- Thank you for visiting The Khanwich Spot! Have a great day! ---");
                    running = false; // stops loop and exits app
                }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private void showMainMenu(){
        System.out.println("""
            
            ╔════════════════════════════╗
            ║         MAIN MENU          ║
            ╚════════════════════════════╝
            1) Start New Order
            2) Exit
            """);
    }

    // starts new order and opens ordering menu
    private void startNewOrder() {
        currentOrder = new Order(); // create fresh order
        boolean ordering = true;

        while (ordering) {
            showOrderMenu();
            int choice = ConsoleHelper.promptForInt("Select an option");

            switch (choice) {
                case 1 -> addSandwich();
                case 2 -> addDrink();
                case 3 -> addChips();
                case 4 -> {
                    checkout(); // finish and display order
                    ordering = false; // exit loop
                }
                case 0 -> {
                    System.out.println("\n❌ Order Cancelled. Thank you for visiting!");
                    ordering = false;
                }
                default ->
                    System.out.println("❌ Invalid choice. Try again...");
            }
        }
    }

    private void showOrderMenu() {
        System.out.println("""
            
            ╔════════════════════════════╗
            ║        ORDER MENU          ║
            ╚════════════════════════════╝
            1) Add Sandwich
            2) Add Drink
            3) Add Chips
            4) Checkout
            0) Cancel Order
            """);
    }

    private void addSandwich() {
        System.out.println("""
                
                ╔════════════════════════════╗
                ║        ADD SANDWICH        ║
                ╚════════════════════════════╝
                """);

        String size = ToppingsHelper.selectSingle(MenuOptions.Sandwich.sizes, "Choose Sandwich Size (inch)");
        String bread = ToppingsHelper.selectSingle(MenuOptions.Sandwich.breadTypes, "Choose Bread Type");
        boolean toasted = ToppingsHelper.chooseYesNo("Would you like it toasted?");

        // empty sandwich object
        Sandwich sandwich = new Sandwich("Custom Sandwich", 0, size, bread, toasted, new ArrayList<>());

        // above sammich was created now we need to add toppings
        boolean running = true;
        while (running) {
            System.out.println("""
                    
                    ╔═══════════════════════════════════╗
                    ║         TOPPING SELECTION         ║
                    ╚═══════════════════════════════════╝
                    1) Meats (premium)
                    2) Cheeses (premium)
                    3) Veggies
                    4) Sauces
                    0) Done adding toppings
                    """);

            int choice = ConsoleHelper.promptForInt("Enter choice");

            switch (choice) {
                case 1 -> addMeats(sandwich);
                case 2 -> addCheeses(sandwich);
                case 3 -> addVeggies(sandwich);
                case 4 -> addSauces(sandwich);
                case 0 -> running = false;
                default -> System.out.println("❌ Invalid choice.");
            }
        }
        currentOrder.addItem(sandwich);
        System.out.printf("\n✅ Sandwich added to your order! \nPrice: $%.2f\n", sandwich.calculatePrice());
    }

    // helper to add meats to a sandwich
    private void addMeats(Sandwich sandwich) {
        // lets user pick multiple meats from list
        List <String> selectedMeats = ToppingsHelper.selectMultiple(MenuOptions.Toppings.meats, "Select Meats");


        // loop through each meat they picked
        for (String meat : selectedMeats) {
            // asking if they want any extra portions
            int extra = ConsoleHelper.promptForInt("Extra portions of" + meat + "? (0 for standard)");
            // add this meat to the sandwich with extra count
            sandwich.addTopping(new Topping(meat, "meat", extra));
        }
        System.out.println("✅ Meats added!");
    }

    private void addCheeses(Sandwich sandwich) {
        List<String> selectedCheese = ToppingsHelper.selectMultiple(MenuOptions.Toppings.cheeses, "Select Cheese");
        for (String cheese : selectedCheese) {
            int extra = ConsoleHelper.promptForInt("Extra portions of " + cheese + "? (0 for standard)");
            sandwich.addTopping(new Topping(cheese, "cheese", extra));
        }
        System.out.println("✅ Cheese added!");
    }

    private void addVeggies(Sandwich sandwich) {
        List<String> selectedVeg = ToppingsHelper.selectMultiple(MenuOptions.Toppings.veggies, "Select Veggies");
        for (String veg : selectedVeg) {
            sandwich.addTopping(new Topping(veg, "regular"));
        }
        System.out.println("✅ Veggies added!");
    }

    private void addSauces(Sandwich sandwich) {
        List<String> selectedSauces = ToppingsHelper.selectMultiple(MenuOptions.Toppings.sauces, "Select Sauces");
        for (String sauce : selectedSauces) {
            sandwich.addTopping(new Topping(sauce, "regular"));
        }
    }

    // handles drink creation
    private void addDrink() {
        System.out.println("\n--- ADD DRINK ---");

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
        System.out.println("""
            
            ╔════════════════════════════════════╗
            ║            ORDER SUMMARY           ║
            ╚════════════════════════════════════╝
            """);

        if (currentOrder.getItems().isEmpty()) {
            System.out.println("❌ Your order is empty! Please add items first.");
            return; // Exit early
        }

        currentOrder.displayOrder(); // show order

        System.out.printf("TOTAL: $%.2f\n", currentOrder.calculateTotal());
        System.out.println();
        System.out.println("✅ Order complete!");
    }
}