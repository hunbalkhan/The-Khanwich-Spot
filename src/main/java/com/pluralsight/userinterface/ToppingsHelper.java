package com.pluralsight.userinterface;

import java.util.ArrayList;
import java.util.List;

public class ToppingsHelper {

    // below are utility methods to kind of help the display, and collect user selections I'm show im trying to go about it
    public static void displayOptions(String[] options, String title) {
        System.out.println("\n--- " + title + " ---");

        for (int i = 0; i < options.length; i++) {
            // print each option
            System.out.println((i + 1) + ") " + options[i]);
        }
        // "0" lets the user skip if they dont want to anymore toppings
        System.out.println("0) Skip");
    }

    public static List<String> selectMultiple(String[] options, String title) {
        List<String> selected = new ArrayList<>();

        // show available options
        displayOptions(options, title);

        // ask user input
        String input = ConsoleHelper.promptForString("Enter numbers separated by a comma i.e. 1,2,3  (0 to skip)");

        // split input by spaces
        String[] parts = input.split(",");

        // loop through each number entered
        for (String part : parts) {
            try {
                // convert input into index ( -1 because array starts at 0)
                int index = Integer.parseInt(part) - 1;

                // if input is valid (within range), add that item to the selected list
                if (index >= 0 && index < options.length) {
                    selected.add(options[index]);
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input, try again.");
            }

        }
        // return the list of chosen items
        return selected;
    }

    // Let's the user chhoose options that require single answers like bread type
    public static String selectSingle(String[] options, String title) {
        displayOptions(options, title);

        String input = ConsoleHelper.promptForString("Enter your choice");

        try {
            int index = Integer.parseInt(input) - 1;

            if (index >= 0 && index < options.length) {
                return options[index]; // returns selected option
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input, try again.");
        }

        System.out.println("No selection made.");
        return null; // tbd
    }

    public static String selectSingleWithPrices(String[] options, double[] prices, String title) {
        System.out.println("\n--- " + title + " ---");

        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %-20s ($%.2f)\n", i + 1, options[i], prices[i]);
        }
        System.out.println("0. Skip");

        String input = ConsoleHelper.promptForString("Enter your choice");

        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < options.length) {
                return options[index];
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }

        return null;
    }

    public static boolean chooseYesNo(String question) {
        System.out.println("\n" + question);
        System.out.println("1) Yes");
        System.out.println("2) No");

        while (true) {
            int choice = ConsoleHelper.promptForInt("Enter your choice (1 or 2)");
            if (choice == 1) return true;
            if (choice == 2) return false;
            System.out.println("❌ Invalid choice. Please enter 1 for Yes or 2 for No.");
        }
    }

}