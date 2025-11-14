package com.pluralsight.models;

import com.pluralsight.userinterface.ConsoleHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {

    // Holds multiple Product items (Sandwich, Drink, Chips). Calculates total.

    private String orderId;
    private LocalDateTime dateTime;
    private ArrayList<Product> items;

    public Order() {
        this.orderId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        this.dateTime = LocalDateTime.now();
        this.items = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public ArrayList<Product> getItems() {
        return items;
    }

    // add product to the order
    public void addItem(Product item) {
        items.add(item);
    }

    // calculate price of complete order
    public double calculateTotal() {
        double total = 0;
        for (Product p : items) {
            total += p.calculatePrice(); // uses polymorphism - each product can calculate its own price
        }
        return total;
    }




    // Display order details to the console.
    public void displayOrder() {

        int width = 50; // width for centering console

        // header
        System.out.println(ConsoleHelper.centerText("=".repeat(width), width));
        System.out.println(ConsoleHelper.centerText("\nOrder #" + orderId, width));

        // date and time centered
        System.out.println(ConsoleHelper.centerText(
                "Date/Time: " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd / HH:mm:ss")),
                width));
        System.out.println(ConsoleHelper.centerText("=".repeat(width), width));

        // prints and centres header
        System.out.println("\nItems in order:");

        for (Product p : items) {
            // check if product is a sandwich
           if (p instanceof Sandwich) {
               ((Sandwich) p).displayDetails(width); // call Sandwich displayDetails
           }
            else if (p instanceof Drink) {
                ((Drink) p).displayDetails(width);
           }
            else if (p instanceof Chips) {
               ((Chips) p).displayDetails(width);
           }
        }
        // print & centre total cost of order
        System.out.printf(ConsoleHelper.centerText(
                String.format("Total Price: $%.2f\n", calculateTotal()), width));


    }

//    public String getOrderDetail(){
//        return "all the detail";
//    }

    public boolean isValid() {
        //Stream looks at all the products in the order For this product p, return true if it’s a Sandwich.
        boolean hasSandwich = items.stream().anyMatch( p -> p instanceof Sandwich );
        boolean hasDrinkOrChips = items.stream().anyMatch( p -> p instanceof Drink || p instanceof Chips);
        return hasSandwich || hasDrinkOrChips;


    }

}