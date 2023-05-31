package Empoyee.Marketing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Empoyee.InventoryEmployee;
import Order.Product;
import SQL.GlobalConnection;
import User.User;

// I'm working on this class
class Marketer extends User {
    private int persentage; // need to add persentage to constructor and to database

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
        MarketingReport report = new MarketingReport(this.getID(), productId, reportText);

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
    public void makeOffer(int productId, String offerText) throws Exception {
        // Create a new Marketing_Offer object with the employee ID, product ID, and
        // offer text
        MarketingOffer offer = new MarketingOffer(this.getID(), productId, offerText);

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

    public void makeReports(List<Product> products) throws SQLException {
        // Generate reports based on the provided list of products
        for (Product product : products) {
            String report = generateProductReport(product);
            saveReportToDatabase(report);
            sendReportToInventoryManagement(report);
        }
    }

    private String generateProductReport(Product product) throws SQLException {
        // Perform operations and calculations to gather relevant information
        // Create a report with the collected data
        String report = "Product Report:\n";
        report += "Product ID: " + product.getId() + "\n";
        report += "Product Name: " + product.getName() + "\n";
        report += "Product Price: $" + product.getPrice() + "\n";
        // Add more information and analysis specific to the marketing objectives

        return report;
    }

    public void saveReportToDatabase(String report) throws SQLException {
        try (Connection conn = GlobalConnection.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("INSERT INTO Marketing_Reports (report_text) VALUES (?)")) {
            stmt.setString(1, report);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving report to database: " + e.getMessage());
            throw e;
        }
    }

    public void createMarketingOffer(Product product, String offerText, InventoryEmployee employee)
            throws SQLException, Exception {
        // Create a marketing offer for the given product and offer text
        MarketingOffer offer = new MarketingOffer(this.getID(), product.getId(), offerText);
        saveMarketingOfferToDatabase(offer);
        sendOfferToInventoryManagement(offer, employee, this.persentage);
    }

    public double GetNewPrice(MarketingOffer empp) throws SQLException {
        return empp.getDiscout(this.persentage);
    }

    private void saveMarketingOfferToDatabase(MarketingOffer offer) throws Exception {
        try (Connection conn = GlobalConnection.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("INSERT INTO Marketing_Offers (offer_text) VALUES (?)")) {
            stmt.setString(1, offer.getOfferText());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error saving marketing offer to database: " + e.getMessage());
        }
    }

    private String sendOfferToInventoryManagement(MarketingOffer offer, InventoryEmployee inventory, int product_id)
            throws SQLException {
        // Connect to the inventory management module
        double newPrice = offer.getDiscout(this.persentage);
        // Send the offer to the inventory management module
        inventory.setPrice(newPrice, Product.findProductById(product_id));
        return offer.getOfferText();
    }
}
