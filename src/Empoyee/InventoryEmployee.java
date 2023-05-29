package Empoyee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import java.util.*;

import Order.*;
import SQL.GlobalConnection;
import User.User;

class InventoryEmployee extends User {
    private List<Product> products;
    private int notificationRange;

    public InventoryEmployee(String username, String password) {
        super(username, password, "inventory");
        this.products = new ArrayList<>();
        this.notificationRange = 5;
    }

    public static void addProductToDatabase(Product product) {
        try {
            // Open a connection to the database
            Connection conn = GlobalConnection.getConnection();

            // Prepare a SQL statement to insert a new row into the Inventory_Products table
            String sql = "INSERT INTO Inventory_Products (name, description, quantity, price, expiry_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set the values of the parameters in the SQL statement
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getQuantity());
            stmt.setBigDecimal(4, product.getPrice());
            stmt.setDate(5, new java.sql.Date(product.getExpiryDate().getTime()));
            

            // Execute the SQL statement to insert the new row into the table
            stmt.executeUpdate();

            // Close the statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Handle any errors that occur during the database operation
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(int productId) throws SQLException {
        Product product = findProductById(productId);
        if (product != null) {
            products.remove(product);
        }
    }

    public void updateProduct(Product product) throws SQLException, ParseException {
        Product existingProduct = findProductById(product.getId());
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setExpiryDate(product.getExpiryDate());
        }
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product findProductById(int productId) throws SQLException {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public List<Product> searchProduct(String keyword) throws SQLException {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().contains(keyword)) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    public void setNotificationRange(int range) {
        this.notificationRange = range;
    }

    public void manageDamagedItems(DamagedItem damagedItem) {
        // Logic to manage damaged items
        System.out.println("Managing damaged item: " + damagedItem);
    }

    public void manageSalesReturn(SalesReturn salesReturn) {
        // Logic to manage sales returns
        System.out.println("Managing sales return: " + salesReturn);
    }
}
