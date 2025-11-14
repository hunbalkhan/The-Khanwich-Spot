package com.pluralsight.data;

// Formats and prints receipts to console

import com.pluralsight.userinterface.MenuOptions;
import com.pluralsight.models.Order;
import com.pluralsight.models.Topping;
import com.pluralsight.models.Chips;
import com.pluralsight.models.Drink;
import com.pluralsight.models.Product;
import com.pluralsight.models.Sandwich;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {

    // the folder where al receipts will be saved
    private static final String receiptsFolder = "receipts";


    // Creates the receipts folder if it doesn't exist
    private static void createReceiptFolderIfNeeded() {
        File folder = new File(receiptsFolder);

        if (!folder.exists()) {
            boolean created = folder.mkdir();

            if (created) {
                System.out.println("Created receipts folder.");
            }
        }
    }


    // saves an order as a receipt file
    public static void saveReceipt(Order order) {
        // makes sure a folder is there, if not it creates one
        createReceiptFolderIfNeeded();

        String fileName = order.getOrderId() + ".txt";
        String filePath = receiptsFolder + File.separator + fileName; // i.e receipts/20251113-143025.txt

        // try write the receipts to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            writeReceiptContent(bw, order);
            System.out.println("✅ Receipt saved successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error saving receipt: " + e.getMessage());;
        }
    }

    // writes out actual receipt to file
    private static void writeReceiptContent(BufferedWriter bw, Order order) throws IOException {

        // header
        bw.write("=====================================\n");
        bw.write("         THE  KHANWICH  SPOT\n");
        bw.write("          CUSTOMER  RECEIPT\n");
        bw.write("=====================================\n\n");

        // order info
        bw.write("Order ID:  " + order.getOrderId() + "\n");
        bw.write("Date/Time: " + order.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss")) + "\n");
        bw.write("-------------------------------------\n");

        // item section
        bw.write("ITEMS ORDERED:\n\n");

        // loop through each item in order
        for (Product item : order.getItems()) {
            // check what type of product it is and write
            if (item instanceof Sandwich) {
                // cast to sandwich, and write sandwich details
                writeSandwichDetails(bw, (Sandwich) item);
            }
            if (item instanceof Drink) {
                writeDrinkDetails(bw, (Drink) item);
            }
            if (item instanceof Chips) {
                writeChipsDetails(bw, (Chips) item);
            }
            bw.write("\n");
        }

        // total section
        bw.write("-------------------------------------\n");
        bw.write(String.format("TOTAL: $%.2f\n", order.calculateTotal()));
        bw.write("-------------------------------------\n\n");

        // footer
        bw.write("       Thank you for your order!     \n");
        bw.write("          Please come again!         \n");
        bw.write("-------------------------------------\n");
    }

    // writes details about sandwich to receipt
    private static void writeSandwichDetails(BufferedWriter bw, Sandwich sandwich) throws IOException {
        bw.write("SANDWICH (" + sandwich.getSize() + "\")\n");
        bw.write("  Bread: " + sandwich.getBreadType() + (sandwich.isToasted() ? " (Toasted)" : "") + "\n");

        //base price
        double basePrice = getBaseSandwichPrice(sandwich.getSize());
        bw.write(String.format("  Base Price:  ........... +$%.2f\n", basePrice));

        // write toppings in format
        if (!sandwich.getToppings().isEmpty()) {
            bw.write("  Toppings:\n");
            for (Topping topping : sandwich.getToppings()) {
                double toppingCost = getToppingCost(topping, sandwich.getSize());
                bw.write("    - " + topping.getName());

                // write extra toppings if applicable
                if (topping.getExtraCount() > 0) {
                    bw.write(" (Extra x" + topping.getExtraCount() + ")");
                }

                // show cost prem/free
                if (toppingCost > 0) {
                    bw.write(String.format(" ........... +$%.2f", toppingCost ));
                }
                else {
                    bw.write(" ........... FREE");
                }
                bw.write("\n");
            }
        }
        // write price
        bw.write("-------------------------------------\n");
        bw.write(String.format("  Price: $%.2f\n\n", sandwich.calculatePrice()));
    }

    // Helper to get base sandwich price
    private static double getBaseSandwichPrice(String size) {

        if (size.equals("4")) {
            return 5.50;
        } else if (size.equals("8")) {
            return 7.00;
        } else if (size.equals("12")) {
            return 8.50;
        } else {
            return 0.0; // Invalid size
        }
    }

    private static double getToppingCost(Topping topping, String size) {
        String category = topping.getCategory().toLowerCase();

        // Figure out which size index we need (0, 1, 2)
        int sizeIndex = 0; // default is small 4"

        if (size.equals("8")) {
            sizeIndex = 1;
        } else if (size.equals("12")) {
            sizeIndex = 2;
        }

        // Calculate cost based on topping category
        if (category.equals("meats")) {

            double baseMeat = MenuOptions.Toppings.meatBasePrices[sizeIndex];
            double extraMeat = MenuOptions.Toppings.meatExtraPrices[sizeIndex];

            return baseMeat + (extraMeat * topping.getExtraCount());
        }
        else if (category.equals("cheese")) {

            double baseCheese = MenuOptions.Toppings.cheeseBasePrices[sizeIndex];
            double extraCheesePrice = MenuOptions.Toppings.cheeseExtraPrices[sizeIndex];

            return baseCheese + (extraCheesePrice * topping.getExtraCount());
        }
        else {
            return 0.0; // regular toppings
        }
    }

    // writes drink details to receipt
    private static void writeDrinkDetails(BufferedWriter bw, Drink drink) throws IOException {
        bw.write("DRINK\n");
        bw.write("  Size: " + drink.getSize() + "\n");
        bw.write("  Flavor: " + drink.getFlavor() + "\n");
        bw.write(String.format("  Price: $%.2f\n\n", drink.calculatePrice()));
    }

    // writes chip details to receipt
    private static void writeChipsDetails(BufferedWriter bw, Chips chips) throws IOException {
        bw.write("CHIPS\n");
        bw.write("  Flavor: " + chips.getFlavor() + "\n");
        bw.write(String.format("  Price: $%.2f\n", chips.calculatePrice()));
    }
}
