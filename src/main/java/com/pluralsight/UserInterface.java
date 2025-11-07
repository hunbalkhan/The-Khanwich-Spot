package com.pluralsight;

import java.util.ArrayList;

public class UserInterface {

    private Order currentOrder;

    public void display() {
        System.out.println("---- Welcome to The Khanwich Spot ----");
        System.out.println("         <-===============->          ");

        boolean running = true;
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

    private void startNewOrder() {
        currentOrder = new Order();
        //System.out.println("\nStarting new order");

        boolean ordering = true;

        while (ordering) {
            showOrderMenu();
            String choice = ConsoleHelper.promptForString("Select an option: ");
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
        String bread = ConsoleHelper.promptForString("Choose bread (white");
        boolean toasted = ConsoleHelper.promptForString("Toasted? (y/n)").equalsIgnoreCase("y");

        Sandwich sandwich = new Sandwich("Custom Sandwich", 0, size, bread, toasted, new ArrayList<>());

        // sandwich is made, now add toppings




    }



}
