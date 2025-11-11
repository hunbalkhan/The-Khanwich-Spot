package com.pluralsight;

// Formats and prints receipts to console

import java.io.FileWriter;

public class ReceiptManager {

    public static void saveReceipt(Order order) {

        String fileName = "receipts_" + order.getOrderId() + ".txt";








    }



}
