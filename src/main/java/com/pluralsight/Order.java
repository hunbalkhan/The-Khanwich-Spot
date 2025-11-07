package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {

    // Holds multiple Product items (Sandwich, Drink, Chips). Calculates total.

    private String orderId;
    private LocalDateTime dateTime;
    private ArrayList<Product> items;

    public Order(String orderId) {
        this.orderId = orderId;
        this.dateTime = LocalDateTime.now();
        this.items = new ArrayList<>();
    }

    // second constructor for flexibility.
    public Order() {
        this.orderId = orderId;
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
        System.out.println("\n=== Order " + orderId + " ===");
                System.out.println("Date/Time: " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd / HH:mm:ss")));
        System.out.println("Items in order:");
        for (Product p : items) {
            System.out.println(" - " + p.toString()); // Calls each product's toString() or display method
        }
        System.out.printf("Total Price: $%.2f\n", calculateTotal());
    }


    // check if an order is valid or not; im going to use it when i create the order to check if its a complete order or not
    // so i dont have extra orders in my csv file thats not even a proper order
    public boolean isValid() {
        //Stream looks at all the products in the order For this product p, return true if it’s a Sandwich.
        boolean hasSandwich = items.stream().anyMatch( p -> p instanceof Sandwich ); //
        boolean hasDrinkOrChips = items.stream().anyMatch( p -> p instanceof Drink || p instanceof Chips);
        return hasSandwich || hasDrinkOrChips;
    }

}
