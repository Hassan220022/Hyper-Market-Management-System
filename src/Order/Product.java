package Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import SQL.GlobalConnection;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Date expiryDate;

    public Product(int id, String name, String description, int quantity, double price, Date expiryDate)
            throws SQLException {

        try {
            Connection conn = GlobalConnection.getConnection();
            String query = "INSTER INTO Inventory (id ,name, quantity,price,)";
            PreparedStatement stmt = conn.prepareStatement(query);
            String sql = "INSERT INTO Inventory_Products (name, description, quantity, price, expiry_date) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, description);
            stmt.setDouble(4, price);
            stmt.setInt(5, quantity);
            stmt.setDate(6, new java.sql.Date(expiryDate.getTime()));
            stmt.executeUpdate();

            // Get the new user's id from the database

            query = "SELECT Id FROM Inventory_products WHERE name = ?"; // User must be 'U'must be capitcal
            stmt = conn.prepareStatement(query); // id
            stmt.setString(1, this.name);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            this.id = rs.getInt("userId");
        } catch (Exception e) {
            System.out.println(e);
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public int getId() throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT Id FROM inventory_Products WHERE name = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, this.name);
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

    public void setId(int id) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "UPDATE Inventory_Products SET id = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setInt(2, this.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        this.id = id;
    }

    public String getDescription() throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT name FROM Inventory_products WHERE description = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, this.description);
        ResultSet rs = stmt.executeQuery();
        try {
            if (rs.next()) {
                return rs.getString("description");
            } else {
                return null;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    public void setDescription(String description) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "UPDATE Inventory_Products SET description = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, this.description);
            stmt.setInt(2, this.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        this.description = description;
    }

    public String getName() throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT name FROM Inventory_products WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, this.id);
        ResultSet rs = stmt.executeQuery();
        try {
            if (rs.next()) {
                return rs.getString("name");
            } else {
                return null;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    public void setName(String name) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "UPDATE Inventory_products SET name = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, this.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
    }

    public double getPrice() throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT price FROM Inventory_products WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setDouble(1, this.price);
        ResultSet rs = stmt.executeQuery();
        try {
            if (rs.next()) {
                return rs.getDouble("price");
            } else {
                return 0;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    public void setPrice(double price) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "UPDATE Inventory_products SET price = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, this.price);
            stmt.setInt(2, this.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        this.price = price;
    }

    public int getQuantity() throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT quantity FROM Inventory_products WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, this.quantity);
        ResultSet rs = stmt.executeQuery();
        try {
            if (rs.next()) {
                return rs.getInt("quantity");
            } else {
                return 0;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    public void setQuantity(int quantity) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "UPDATE Inventory_products SET quantity = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, this.quantity);
            stmt.setInt(2, this.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        this.quantity = quantity;
    }

    public Date getExpiryDate() throws SQLException, ParseException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT expiry_date FROM Inventory_Products WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, this.id);
        ResultSet rs = stmt.executeQuery();
        try {
            if (rs.next()) {
                String dateString = rs.getString("expiry_date");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.parse(dateString);
            } else {
                return null;
            }
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
    }

    public void setExpiryDate(Date expiryDate) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "UPDATE Inventory_Products SET expiry_date = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setDate(1, new java.sql.Date(expiryDate.getTime()));
        stmt.setInt(2, this.id);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        this.expiryDate = expiryDate;
    }

    public static List<Product> searchProduct(String keyword) throws SQLException {
        List<Product> searchResults = new ArrayList<>();
        try {
            // Open a connection to the database
            Connection conn = GlobalConnection.getConnection();

            // Prepare a SQL statement to select rows from the Inventory_Products table that
            // contain the search keyword in their name or description
            String sql = "SELECT * FROM Inventory_Products WHERE name LIKE ? OR description LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set the values of the parameters in the SQL statement
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);

            // Execute the SQL statement to select the rows from the table
            ResultSet rs = stmt.executeQuery();

            // Iterate over the result set and create a new Product object for each row
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Date expiryDate = rs.getDate("expiry_date");
                Product product = new Product(id, name, description, quantity, price, expiryDate);
                searchResults.add(product);
            }

            // Close the result set, statement, and connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Handle any errors that occur during the database operation
            e.printStackTrace();
            throw e;
        }
        return searchResults;
    }

    public static Product findProductById(int productId) throws SQLException {
        Product product = null;
        try {
            // Open a connection to the database
            Connection conn = GlobalConnection.getConnection();

            // Prepare a SQL statement to select a row from the Inventory_Products table by
            // ID
            String sql = "SELECT * FROM Inventory_Products WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set the value of the parameter in the SQL statement
            stmt.setInt(1, productId);

            // Execute the SQL statement to select the row from the table
            ResultSet rs = stmt.executeQuery();

            // If a row was found, create a new Product object with the row data
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Date expiryDate = rs.getDate("expiry_date");
                product = new Product(productId, name, description, quantity, price, expiryDate);
            }

            // Close the result set, statement, and connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Handle any errors that occur during the database operation
            e.printStackTrace();
            throw e;
        }
        return product;
    }

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            // Open a connection to the database
            Connection conn = GlobalConnection.getConnection();

            // Prepare a SQL statement to select all rows from the Inventory_Products table
            String sql = "SELECT * FROM Inventory_Products";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Execute the SQL statement to select all rows from the table
            ResultSet rs = stmt.executeQuery();

            // Iterate over the result set and create a new Product object for each row
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Date expiryDate = rs.getDate("expiry_date");
                Product product = new Product(id, name, description, quantity, price, expiryDate);
                products.add(product);
            }

            // Close the result set, statement, and connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            // Handle any errors that occur during the database operation
            e.printStackTrace();
        }
        return products;
    }
}
