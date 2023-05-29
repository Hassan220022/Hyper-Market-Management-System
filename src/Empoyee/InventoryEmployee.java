package Empoyee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void addProductToDatabase(Product product) throws ParseException {
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
            stmt.setDouble(4, product.getPrice());
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

    private void removeProductFromDatabase(int productId) // used in methode deleteProduct
    {
        try {
            // Open a connection to the database
            Connection conn = GlobalConnection.getConnection();

            // Prepare a SQL statement to delete a row from the Inventory_Products table
            String sql = "DELETE FROM Inventory_Products WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set the value of the parameter in the SQL statement
            stmt.setInt(1, productId);

            // Execute the SQL statement to delete the row from the table
            stmt.executeUpdate();

            // Close the statement and the connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Handle any errors that occur during the database operation
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) throws ParseException {
        products.add(product);
        addProductToDatabase(product);
    }

    public void deleteProduct(int productId) throws SQLException {
        Product product = findProductById(productId);
        if (product != null) {
            products.remove(product);
            removeProductFromDatabase(productId);
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

    public List<Product> useGetAllProduct() {
        return Product.getAllProducts();
    }

    public Product findProductById(int productId) throws SQLException {
        return Product.findProductById(productId);
    }

    public List<Product> searchProduct(String keyword) throws SQLException {
        return Product.searchProduct(keyword);
    }

    public void setNotificationRange(int range)// malhash 3alaka bel database ashan lahsh altribute in database ethier
                                               // in inventory_Products or in inventory or users
    {
        this.notificationRange = range;
    }

    public void manageDamagedItems(DamagedItem damagedItem) {// TODO: sales class and sales return class
        // Logic to manage damaged items
        System.out.println("Managing damaged item: " + damagedItem);
    }

    public void manageSalesReturn(SalesReturn salesReturn) {
        // Logic to manage sales returns
        System.out.println("Managing sales return: " + salesReturn);
    }
}
