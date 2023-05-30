package Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import SQL.GlobalConnection;
import User.User;

public class Order {
    private int id;
    private User customer;
    private List<Product> products;
    private double totalPrice;
    private Date orderDate;
    private String status; // show you the status of the order
                           // if it is live or cancelled

    public Order(int id, int customerId, int type) throws SQLException {///
        this.id = id;
        this.products = new ArrayList<>();
        this.totalPrice = 0.0;
        customer = customer.searchEmployee(customerId);
        this.status = "live";
        this.orderDate = new Date();
    }

    public Order(int id, int customerId) throws SQLException {// todo; add to database
        this.id = id;
        this.products = new ArrayList<>();
        this.customer = customer.searchEmployee(customerId);// meaning is go find user with id customerID and return
                                                            // object and save it in customer
        this.totalPrice = 0.0;
        this.orderDate = new Date();
        // try {
        // Connection conn = GlobalConnection.getConnection();
        // String sql = "SELECT * FROM Sales_Orders WHERE Id = ? AND customerID = ?";
        // PreparedStatement stmt = conn.prepareStatement(sql);
        // stmt.setInt(1, id);
        // stmt.setInt(2, customerId);
        // ResultSet rs = stmt.executeQuery();

        // if (rs.next()) {
        // this.id = rs.getInt("Id");
        // customer = customer.searchEmployee(rs.getInt("customerID"));
        // if (customer == null) {
        // throw new SQLException("Customer not found");
        // }
        // this.productId = rs.getInt("product_id");
        // this.quantity = rs.getInt("quantity");
        // this.totalPrice = rs.getDouble("price");
        // this.orderDate rs.getString("order_date");
        // this.status = rs.getString("status");
        // } else {
        // throw new SQLException("Order not found");
        // }
        // } catch (SQLException e) {
        // System.out.println("Error creating order: " + e.getMessage());
        // throw e;
        // } finally {
        // if (rs != null) {
        // rs.close();
        // }
        // if (stmt != null) {
        // stmt.close();
        // }
        // if (conn != null) {
        // conn.close();
        // }
        // }
    }

    public int getId() throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT Id FROM Sales_Orders WHERE customerID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, this.customer.getID());
        ResultSet rs = stmt.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("Id");
            } else {
                return -1;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    public int getCustomerId() throws SQLException {
        return customer.getID();
    }

    public List<Product> GetProducts() {// todo: add to database
        return products;

        // List<Product> products = new ArrayList<>();
        // try {
        // Connection conn = GlobalConnection.getConnection();
        // String sql = "SELECT * FROM Sales_Orders_Products WHERE order_id = ?";
        // PreparedStatement stmt = conn.prepareStatement(sql);
        // stmt.setInt(1, this.id);
        // ResultSet rs = stmt.executeQuery();

        // while (rs.next()) {
        // int productId = rs.getInt("product_id");
        // int quantity = rs.getInt("quantity");
        // double price = rs.getDouble("price");
        // Product product = new Product(productId, quantity, price);
        // products.add(product);
        // }
        // } catch (SQLException e) {
        // System.out.println("Error getting products: " + e.getMessage());
        // throw e;
        // } finally {
        // if (rs != null) {
        // rs.close();
        // }
        // if (stmt != null) {
        // stmt.close();
        // }
        // if (conn != null) {
        // conn.close();
        // }
        // }
    }

    public double getTotalPrice() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double price = -1;
        try {
            conn = GlobalConnection.getConnection();
            String sql = "SELECT price FROM Sales_Orders WHERE Id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (SQLException e) {
            System.out.println("Error getting total price: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return price;
    }

    public void addProduct(Product product) throws SQLException { // TODO:check if it correct
                                                                  // TODO: with database
        if (this.status == "live") {// check if the order is live
                                    // it will add the new product to database and if not
                                    // it will create new order
            try {
                Connection conn = GlobalConnection.getConnection();
                String sql = "INSERT INTO Sales_Orders (order_id, product_id) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.setInt(2, product.getId());
                stmt.executeUpdate();
                products.add(product);
                totalPrice += product.getPrice();
            } catch (SQLException e) {
                System.out.println("Error adding product to order: " + e.getMessage());
                throw e;
            }
        } else {
            new Order(this.id, this.customer.getID());
        }
    }

    public void removeProduct(Product product) throws SQLException { // TODO: implement database
        if (products != null) {// there is products in the order if true it will remove the product
                               // if false it will cancel the order
            if (products.remove(product)) {
                totalPrice -= product.getPrice();
                System.out.println("Product removed from the order: " + product.getName());
            } else {
                System.out.println("Product not found in the order: " + product.getName());
            }
        } else {
            cancelOrder();
        }
        // remove a product from the order

    }

    private void remove(Order order) {
        order.remove(order);
    }

    public boolean cancelOrder() throws SQLException {
        boolean success = false;

        try {
            Connection conn = GlobalConnection.getConnection();
            String sql = "UPDATE Sales_Orders SET status = ? WHERE Id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Cancelled");
            stmt.setInt(2, this.id);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) { // if the sales table was updated
                this.status = "Cancelled";
                success = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error cancelling order: " + e.getMessage());
        }
        products.removeAll(products);// remove all products from the order arraylist
        orderDate.remove(orderDate);// remove the order date
        this.remove(this);
        return success;
    }
    // @Override
    // public String toString() {
    // return "Order ID: " + id + ", Customer ID: " + customerId + ", Total Price:
    // $" + totalPrice;
    // }

}