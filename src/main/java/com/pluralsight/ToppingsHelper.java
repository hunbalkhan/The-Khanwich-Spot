package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class ToppingsHelper {

    // hardcoded arrays to store all available options to choose from for the User

    // breads list

    // Prem toppings listr
    public static final String[] meats = {"Turkey", "Ham", "Chicken", "Roast Beef", "Salami", "Bacon", "Steak"};

    // Reg toppings list


    // below are utility methods to kind of help the display, and collect user selections I'm show im trying to go about it
    public static void displayOptions(String[] options, String title) {
        System.out.println("\n--- " + title + " ---");

        for (int i = 0; i < options.length; i++) {
            // print each option
            System.out.println((i + 1) + ". " + options[i]);
        }
        // "0" lets the user skip if they dont want to anymore toppings
        System.out.println("0. Skip");
    }






    // thinking of letting user choose multiple items from given list i.e meat, cheese, veggies
    // maybe to let user type numbers seperated by spaces, e.g "1 3 5" this would be best for a user as they could see all the options infront of them and then can pick and choose at once.
    // this stops the console from repeating itsself and reprinting the same things. i want it to be minimal and clean, so i though of how
    // we can choose from array lists and can pick multiple by splitting our input
    // then return the list of selected items
    // this in turn will keep my code clean and more object oriented in the user interface and making it almost like it is english i guess is what im trying to do.
    // its really hard but the head banging is paying off. ideas flowing into my head how we can use things in code, even a basic array list is really helpful to me right here
    // i will test with just add sandwich method and see how it goes then push the code. if it bugs which probably will, ill try fixing and making it future proof
    // ill push once completely working with one method, i hope the logic works

    public static List<String> selectMultiple(String[] options, String title) {
        List<String> selected = new ArrayList<>();

        // show available options
        displayOptions(options, title);

        // ask user input
        String input = ConsoleHelper.promptForString("Enter numbers seperated by spaces (0 to skip)");

        // split input by spaces
        String[] parts = input.split(" ");

        // loop trhough each number entered
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

}
