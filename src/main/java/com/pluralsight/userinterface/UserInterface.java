package com.pluralsight.userinterface;


import com.pluralsight.data.ReceiptManager;
import com.pluralsight.models.*;

import java.util.List;

public class UserInterface {

    private Order currentOrder;

    public void display() {
        System.out.println("\n---- Welcome to The Khanwich Spot ----");


        boolean running = true; // keeps the main menu running
        while (running) {
            showMainMenu();

            // error handling in console helper
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
                case 2 -> addSignatureSandwich();
                case 3 -> addDrink();
                case 4 -> addChips();
                case 5 -> {
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
            2) Add Signature Sandwich
            3) Add Drink
            4) Add Chips
            5) Checkout
            0) Cancel Order
            """);
    }

    private void addSandwich() {
        System.out.println("\n═════════    ADD SANDWICH    ════════");
        System.out.println("\n--- Sandwich Sizes ---");

        // show sizes with prices, loop until valid selection
        String size = null;
        while (size == null) {
            System.out.println("\n");
            for (int i = 0; i < MenuOptions.Sandwich.sizes.length; i++) {
                System.out.printf("%d) %s\" - $%.2f (base price)\n",
                        i + 1,
                        MenuOptions.Sandwich.sizes[i],
                        MenuOptions.Sandwich.sizePrices[i]);
            }
            System.out.println("0) Cancel");

            int sizeChoice = ConsoleHelper.promptForInt("Select size") - 1;

            //check if user wants to cancel
            if (sizeChoice == -1) {
                System.out.println("❌ Sandwich creation cancelled.");
                return; // exit
            }

            // check if choice is valid
            if (sizeChoice >= 0 && sizeChoice < MenuOptions.Sandwich.sizes.length) {
                size = MenuOptions.Sandwich.sizes[sizeChoice]; // Valid choice!
            } else {
                // Invalid choice - loop will repeat
                System.out.println("❌ Invalid choice. Please try again.");
            }
        }

        String bread = getSelectionOrCancel(MenuOptions.Sandwich.breadTypes, "Choose Bread Type");
        if (bread == null) return;

        // ask if toasted
        boolean toasted = ToppingsHelper.chooseYesNo("Would you like it toasted?");

        // empty sandwich object
        Sandwich sandwich = new Sandwich("Custom Sandwich", size, bread, toasted);

        //show running subtotal
        System.out.printf("\nCurrent sandwich price: $%.2f\n", sandwich.calculatePrice());

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
                case 1 -> {
                    addMeats(sandwich);
                    System.out.printf("Current sandwich price: $%.2f\n", sandwich.calculatePrice());
                }
                case 2 -> {
                    addCheeses(sandwich);
                    System.out.printf("Current sandwich price: $%.2f\n", sandwich.calculatePrice());
                }
                case 3 -> {
                    addVeggies(sandwich);
                    System.out.printf("Current sandwich price: $%.2f (veggies are FREE!)\n", sandwich.calculatePrice());
                }
                case 4 -> {
                    addSauces(sandwich);
                    System.out.printf("Current sandwich price: $%.2f (sauces are FREE!)\n", sandwich.calculatePrice());
                }
                case 0 -> running = false;
                default -> System.out.println("❌ Invalid choice.");
            }
        }
        currentOrder.addItem(sandwich);
        System.out.printf("\n✅%s Sandwich added to your order! Final Price: $%.2f\n", sandwich.getName(),sandwich.calculatePrice());
        System.out.printf("Order total so far: $%.2f\n", currentOrder.calculateTotal());
    }


    private void addSignatureSandwich() {
        System.out.println("\n═══  ADD SIGNATURE SANDWICH  ════");

        System.out.println("""
                1) BLT - $10.50
                   8" White bread (Toasted)
                   Bacon, Chedder, Lettuce, Tomato, Ranch
                
                2) Philly Cheese Steak - $11.00
                   8" White Bread (Toasted)
                   Steak, American Cheese, Peppers, Mayo
                   
                0) Cancel
                """);

        int choice = ConsoleHelper.promptForInt("Select a signature sandwich");

        Sandwich sandwich = null;

        switch (choice) {
            case 1 -> sandwich = new BLT();
            case 2 -> sandwich = new PhillyCheeseSteak();
            case 0 -> {
                System.out.println("❌ Signature Sandwich selection cancelled.");
                return;
            }
            default -> {
                System.out.println("❌ Invalid choice.");
                return;
            }
        }

        System.out.printf("\n✅%s Sandwich added to your order! Final Price: $%.2f\n", sandwich.getName(), sandwich.calculatePrice());

        boolean customize = ToppingsHelper.chooseYesNo("Would you like to customize this sandwich?");

        if (customize) {
            // Allow customization using the same topping menu
            boolean running = true;

            while (running) {
                System.out.println("""
                    
                    ╔═══════════════════════════════════╗
                    ║         CUSTOMIZE SANDWICH        ║
                    ╚═══════════════════════════════════╝
                    1) Add Extra Meats (premium)
                    2) Add Extra Cheese (premium)
                    3) Add More Veggies (FREE)
                    4) Add More Sauces (FREE)
                    0) Done customizing
                    """);

                int customChoice = ConsoleHelper.promptForInt("Enter choice");

                switch (customChoice) {
                    case 1 -> {
                        addMeats(sandwich);
                        System.out.printf("Current sandwich price: $%.2f\n", sandwich.calculatePrice());
                    }
                    case 2 -> {
                        addCheeses(sandwich);
                        System.out.printf("Current sandwich price: $%.2f\n", sandwich.calculatePrice());
                    }
                    case 3 -> {
                        addVeggies(sandwich);
                        System.out.printf("Current sandwich price: $%.2f\n", sandwich.calculatePrice());
                    }
                    case 4 -> {
                        addSauces(sandwich);
                        System.out.printf("Current sandwich price: $%.2f\n", sandwich.calculatePrice());
                    }
                    case 0 -> running = false;
                    default -> System.out.println("❌ Invalid choice. Please try again.");
                }
            }
        }

        currentOrder.addItem(sandwich);
        System.out.printf("\n✅%s added to your order! Final Price: $%.2f\n", sandwich.getName(), sandwich.calculatePrice());
        System.out.printf("Order total so far: $%.2f\n", currentOrder.calculateTotal());
    }


    // helper to reduce code, method adds any type of topping to a sandwich
    private void addToppings(Sandwich sandwich, String[] options, String title, String category, boolean allowExtra) {
        // user can select multiple items from given list
        List <String> selectedItems = ToppingsHelper.selectMultiple(options, title);

        // check if already selected
        if (selectedItems.isEmpty()) {
            System.out.println("No " + title.toLowerCase() + " selected.");
            return;
        }

        // loop through user selected items
        for (String item : selectedItems) {
            // makes extras 0 by default
            int extra = 0;

            // if toppings allows extras then ask
            if (allowExtra) {
                extra = ConsoleHelper.promptForInt("Extra portions of " + item + "?  (0 for standard)");
            }

            // create a topping object and add it to sandwich, category tells price calculator if it is premium or not
            sandwich.addTopping(new Topping(item, category, extra));
        }
        System.out.println("✅ " + title + " added!");
    }

    // these are wrapper methods that call generic helper, making addSandwich easier to read
    private void addMeats(Sandwich sandwich) {

//        // lets user pick multiple meats from list
//        List <String> selectedMeats = ToppingsHelper.selectMultiple(MenuOptions.Toppings.meats, "Select Meats");
//
//        // check if user selected meats
//        if (selectedMeats.isEmpty()) {
//            System.out.println("No meats selected.");
//            return; // Exit method early
//        }
//
//        // loop through each meat they picked
//        for (String meat : selectedMeats) {
//            // asking if they want any extra portions
//            int extra = ConsoleHelper.promptForInt("Extra portions of " + meat + "? (0 for standard)");
//            // add this meat to the sandwich with extra count
//            sandwich.addTopping(new Topping(meat, "meat", extra));
//        }
//        System.out.println("✅ Meats added!");
// OLD CODE ^^^^^^

        // adds toppings, from the list of meats, set title as meats, in category meat, set true if extra is an option
        addToppings(sandwich, MenuOptions.Toppings.meats, "Meats", "meat", true);

    }

    private void addCheeses(Sandwich sandwich) {
//        List<String> selectedCheese = ToppingsHelper.selectMultiple(MenuOptions.Toppings.cheeses, "Select Cheese");
//        for (String cheese : selectedCheese) {
//            int extra = ConsoleHelper.promptForInt("Extra portions of " + cheese + "? (0 for standard)");
//            sandwich.addTopping(new Topping(cheese, "cheese", extra));
//        }
//        System.out.println("✅ Cheese added!");
// OLD CODE ^^^^

        addToppings(sandwich, MenuOptions.Toppings.cheeses, "Cheese", "cheese", true);

    }

    private void addVeggies(Sandwich sandwich) {
        addToppings(sandwich, MenuOptions.Toppings.veggies, "Veggies", "regular", false);
    }

    private void addSauces(Sandwich sandwich) {
        addToppings(sandwich, MenuOptions.Toppings.sauces, "Sauces", "regular", false);
    }



    // returns the selected item, or null if user explicitly cancels (chooses 0)
    private String getSelectionOrCancel(String[] options, String title) {

        // let user select one item from options
        String selection = ToppingsHelper.selectSingle(options, title);

        // check if user cancelled
        if (selection == null) {
            System.out.println("❌ Selection cancelled.");
        }
        // return selection
        return selection;
    }

    // handles drink creation
    private void addDrink() {
        System.out.println("\n═════════     ADD DRINK      ════════");

//        // Ask user for size & flavour
//        String size = ToppingsHelper.selectSingle(MenuOptions.Drink.sizes, "Choose Drink Size");
//        String flavor = ToppingsHelper.selectSingle(MenuOptions.Drink.flavors,"Enter flavour");
//
//        // create and add drink to order
//        currentOrder.addItem(new Drink(flavor, size));
//        System.out.println("✅ Drink added!");
// OLD CODE ^^^\

        // get drink flavor using built in error handling
        String size = getSelectionOrCancel(MenuOptions.Drink.sizes, "Choose Drink Size");
        if (size == null) return; // exit if cancelled

        String flavor = getSelectionOrCancel(MenuOptions.Drink.flavors, "Choose Drink flavor");
        if (flavor == null) return; // exit if cancelled

        Drink drink = new Drink(flavor, size);
        currentOrder.addItem(drink);

        System.out.printf("✅ Drink added! Price: $%.2f\n", drink.calculatePrice());
    }


    // handles chip creation
    private void addChips() {
        System.out.println("\n═════════      ADD CHIPS     ════════");

        String flavor = getSelectionOrCancel(MenuOptions.Chips.flavors, "Choose Chips flavor");
        if (flavor == null) return;

        Chips chips = new Chips(flavor);
        currentOrder.addItem(chips);

        System.out.printf("✅ Chips added! Price: $%.2f\n", chips.calculatePrice());
    }

    // CHECKOUT
    private void checkout() {
        System.out.println("""
            
            ╔════════════════════════════════════╗
            ║            ORDER SUMMARY           ║
            ╚════════════════════════════════════╝
            """);

        if (currentOrder.getItems().isEmpty()) {
            System.out.println("❌ Your order is empty! Please add items first.");
            return; // Exit early, no saving
        }

        if (!currentOrder.isValid()) {
            System.out.println("""
                    
                    ❌ Invalid Order!
                    You must order at least ONE sandwich OR at least ONE drink/chips.
                    Please add more items before checking out.
                    """);
            return;
        }

        currentOrder.displayOrder(); // show order

        System.out.printf("\nTOTAL: $%.2f\n", currentOrder.calculateTotal());

        // ask if user wants to save the receipt to file
        boolean saveReceipt = ToppingsHelper.chooseYesNo("Would you like to save this receipt?");

        if (saveReceipt) {
            ReceiptManager.saveReceipt(currentOrder);
        }

        System.out.println("✅ Order complete! Thank you for your order!");
    }
}