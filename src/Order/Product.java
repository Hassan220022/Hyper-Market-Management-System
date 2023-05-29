package Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import SQL.GlobalConnection;
import User.UserType;

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

}
