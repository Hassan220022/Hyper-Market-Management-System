package Empoyee.Marketing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import Order.*;
import SQL.GlobalConnection;
import User.User;

class Marketer extends User {
    public Marketer(String username, String password) {
        super(username, password, "marketer");
    }

    // public void makeReport(Product product) throws SQLException {
    // // Generate report for the product
    // System.out.println("Generating report for product: " + product.getName());

    // // Retrieve sales data for the product
    // List<Sales> salesData = retrieveSalesData(product);

    // // Perform calculations and generate the report
    // double totalSales = 0;
    // int totalQuantitySold = 0;

    // for (Sales sale : salesData) {
    // totalSales += sale.getTotalPrice();
    // totalQuantitySold += sale.getQuantity();
    // }

    // // Print the report
    // System.out.println("Product Report:");
    // System.out.println("Product Name: " + product.getName());
    // System.out.println("Total Sales: $" + totalSales);
    // System.out.println("Total Quantity Sold: " + totalQuantitySold);
    // }

    // // Dummy method to retrieve sales data for the product
    // private List<Sales> retrieveSalesData(Product product) {
    // // Retrieve sales data from the system/database based on the product
    // // You can replace this with your actual implementation
    // return null;
    // }

    // Users can update their information except ID.
    public void SetUsername(String username) throws SQLException {
        setUsername(username);
    }

    public void SetPassword(String password) throws SQLException {
        setPassword(password);
    }

    // TODO: Still needs some work
    public void makeReport(int productId, String reportText) throws Exception {
        // Create a new Marketing_Report object with the employee ID, product ID, and
        // report text
        Marketing_Report report = new Marketing_Report(this.getID(), productId, reportText);

        // Save the report to the database
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Marketing_Reports (employee_id, product_id, report_text) VALUES (?, ?, ?)");
            stmt.setInt(1, report.getEmployeeId());
            stmt.setInt(2, report.getProductId());
            stmt.setString(3, report.getReportText());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new Exception("Error creating marketing report: " + e.getMessage());
        }
    }

    // TODO: Still needs some work
    public void makeOffer(int productId, String offerText) {
        // Create a new Marketing_Offer object with the employee ID, product ID, and
        // offer text
        Marketing_Offer offer = new Marketing_Offer(this.getID(), productId, offerText);

        // Save the offer to the database
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Marketing_Offers (employee_id, product_id, offer_text) VALUES (?, ?, ?)");
            stmt.setInt(1, offer.getEmployeeId());
            stmt.setInt(2, offer.getProductId());
            stmt.setString(3, offer.getOfferText());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error creating marketing offer: " + e.getMessage());
        }
    }
}
