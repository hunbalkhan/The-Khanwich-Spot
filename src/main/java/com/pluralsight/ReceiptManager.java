package com.pluralsight;

// Formats and prints receipts to console

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
        String filePath = receiptsFolder + File.separator + fileName;

        // try write the receipts to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            writeReceiptContent(bw, order);
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
        bw.write("-------------------------------------\n\n");

        // item selection
        bw.write("-------------------------------------\n");
        bw.write("ITEMS ORDERED:\n");
        bw.write("-------------------------------------\n\n");

        // loop through each item in order
        for (Product item : order.getItems()) {
            // check what type of product it is and write
            if (item instanceof Sandwich) {
                // call method with parameter
                writeSandwichDetails(bw, (Sandwich) item);
            }
            if (item instanceof Drink) {
                writeDrinkDetails(bw, (Drink) item);
            }
            if (item instanceof Chips) {
                writeChipsDetails(bw, (Chips) item);
            }
        }

    }

    // writes sandwich details to receipt
    private static void writeSandwichDetails(BufferedWriter bw, Sandwich sandwich) throws IOException {
        bw.write("SANDWICH (" + sandwich.getSize() + "\"");
        bw.write("  Bread: " + sandwich.getBreadType() + "\n");
        bw.write("  Toasted: " + (sandwich.isToasted() ? "Yes" : "No") + "\n");

        // write toppings in format
        if (!sandwich.getToppings().isEmpty()) {
            bw.write("  Toppings:\n");
            for (Topping topping : sandwich.getToppings()) {
                bw.write("    - " + topping.getName());

                // write extra toppings if applicable
                if (topping.getExtraCount() > 0) {
                    bw.write(" (Extra x" + topping.getExtraCount() + ")");
                }
                bw.write("\n");
            }
        }
        // write price
        bw.write(String.format("  Price: $%.2f\n", sandwich.calculatePrice()));
    }

    // writes drink details to receipt
    private static void writeDrinkDetails(BufferedWriter bw, Drink drink) throws IOException {
        bw.write("DRINK\n");
        bw.write("  Size: " + drink.getSize() + "\n");
        bw.write("  Flavor: " + drink.getFlavor() + "\n");
        bw.write(String.format("  Price: $%.2f\n", drink.calculatePrice()));
    }

    // writes chip details to receipt
    private static void writeChipsDetails(BufferedWriter bw, Chips chips) throws IOException {
        bw.write("CHIPS\n");
        bw.write("  Flavor: " + chips.getFlavor() + "\n");
        bw.write(String.format("  Price: $%.2f\n", chips.calculatePrice()));
    }
}
