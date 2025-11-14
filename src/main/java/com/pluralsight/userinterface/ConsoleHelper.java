package com.pluralsight.userinterface;

import java.util.Scanner;

public class ConsoleHelper {

    private static Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt){
        String input = "";
        while (input.isEmpty()) {
            System.out.print(prompt + ": ");
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("❌ Input cannot be empty. Please try again.");
            }
        }
        return input;
    }

    public static int promptForInt(String prompt){
        int result = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt + ": ");

            // Check if next input is an integer
            if (scanner.hasNextInt()) {
                result = scanner.nextInt(); // Read the integer
                scanner.nextLine(); // Clear the newline
                valid = true; // Success!
            }
            else {
                // Clear the bad input
                String badInput = scanner.nextLine();

                // Only show error if they actually typed something
                if (!badInput.trim().isEmpty()) {
                    System.out.println("\n❌ Invalid input. Please enter a number.\n");
                }
                // If they just pressed Enter, silently ask again
            }
        }
        return result;
    }

    public static float promptForFloat(String prompt){
        System.out.print(prompt + ": ");
        float result = scanner.nextFloat();
        scanner.nextLine();
        return result;
    }

    public static long promptForLong(String prompt){
        System.out.print(prompt + ": ");
        long result = scanner.nextLong();
        scanner.nextLine();
        return result;
    }

    public static double promptForDouble(String prompt){
        double result = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt + ": ");  // Keep your $ symbol
            String input = scanner.nextLine().trim(); // Read input as String

            try {
                result = Double.parseDouble(input); // Try converting to double
                valid = true;                        // Success!
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number."); // Error message
            }
        }

        return result;
    }

    public static String centerText(String text, int width) {

        int spaces = width - text.length();
        int left = spaces / 2;
        int right = spaces - left;

        String result = "";
        for (int i = 0; i < left; i++) result += " ";
        result += text;
        for (int i = 0; i < right; i++) result += " ";
        return result;
    }
}