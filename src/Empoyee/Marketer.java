package Empoyee;

import java.sql.SQLException;
import java.util.*;

import Order.*;
import User.User;

class Marketer extends User {
    public Marketer(String username, String password) {
        super(username, password, "marketer");
    }

    public void makeReport(Product product) throws SQLException {
        // Generate report for the product
        System.out.println("Generating report for product: " + product.getName());

        // Retrieve sales data for the product
        List<Sales> salesData = retrieveSalesData(product);

        // Perform calculations and generate the report
        double totalSales = 0;
        int totalQuantitySold = 0;

        for (Sales sale : salesData) {
            totalSales += sale.getTotalPrice();
            totalQuantitySold += sale.getQuantity();
        }

        // Print the report
        System.out.println("Product Report:");
        System.out.println("Product Name: " + product.getName());
        System.out.println("Total Sales: $" + totalSales);
        System.out.println("Total Quantity Sold: " + totalQuantitySold);
    }

    // Dummy method to retrieve sales data for the product
    private List<Sales> retrieveSalesData(Product product) {
        // Retrieve sales data from the system/database based on the product
        // You can replace this with your actual implementation
        return null;
    }

    // Rest of the class remains the same
}
