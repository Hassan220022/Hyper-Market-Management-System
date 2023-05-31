package Empoyee.Marketing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import SQL.GlobalConnection;

public class MarketingOffer {
    private int id;
    private Marketer employee;
    private int productId;
    private String offerText;
    private double discountedPrice; // price after discount

    /// el mafrood khalas

    public MarketingOffer(int employeeId, int productId, String offerText) throws Exception {
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT MAX(id) FROM Marketing_Offers");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt(1) + 1;
            } else {
                this.id = 1;
            }
            stmt = conn.prepareStatement(
                    "INSERT INTO Marketing_Offers (id, employee_id, product_id, offer_text,price) VALUES (?, ?, ?, ?,?)");
            stmt.setInt(1, this.id);
            stmt.setInt(2, employeeId);
            stmt.setInt(3, productId);
            stmt.setString(4, offerText);
            stmt.setDouble(5, discountedPrice);
            stmt.executeUpdate();
            this.employee = (Marketer) employee.searchEmployee(employeeId);// search for employee with this id and
            // return Marketer object --> methode in User class this.productId = productId;
            this.productId = productId;
            this.offerText = offerText;
        } catch (SQLException e) {
            throw new Exception("Error creating marketing report: " + e.getMessage());
        }
    }

    public int getId() throws Exception {
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM Marketing_Offers WHERE id = ?");
            stmt.setInt(1, this.id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Marketing offer not found");
            }
        } catch (SQLException e) {
            throw new Exception("Error getting marketing offer ID: " + e.getMessage());
        }
    }

    public int getEmployeeId() throws SQLException {
        return employee.getID();
    }

    public int getProductId() throws SQLException {
        int productId = -1;
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT product_id FROM Marketing_Offers WHERE id = ?");
            stmt.setInt(1, this.id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                productId = rs.getInt("product_id");
            }
        } catch (SQLException e) {
            throw new RuntimeErrorException(null, "Error getting product ID: " + e.getMessage());
        }
        return productId;
    }

    public String getOfferText() throws SQLException {
        try (Connection conn = GlobalConnection.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT offer_text FROM Marketing_Offers WHERE id = ?")) {
            stmt.setInt(1, this.id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("offer_text");
                } else {
                    throw new SQLException("Marketing offer not found");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving marketing offer text: " + e.getMessage());
            throw e;
        }
    }

    // public Double getDiscount() throws SQLException {
    // double discount = -1;
    // try (Connection conn = GlobalConnection.getConnection();
    // PreparedStatement stmt = conn
    // .prepareStatement("SELECT discount FROM Marketing_Offers WHERE id = ?")) {
    // stmt.setInt(1, this.id);
    // try (ResultSet rs = stmt.executeQuery()) {
    // if (rs.next()) {
    // discount = rs.getDouble("discount");
    // } else {
    // throw new SQLException("Marketing offer not found");
    // }
    // }
    // } catch (SQLException e) {
    // throw new RuntimeErrorException(null, "Error retrieving marketing offer
    // discount: " + e.getMessage());
    // }
    // return discount;
    // }
    // TODO: get price --> for the dicount on the prduct
    // chech if it need some work to be done
    public Double getDiscout(int persentage) throws SQLException {
        // it will get from database the price of the product
        // and then it will calculate the discount
        // and then it will return the discounted price
        double price = -1;
        try (Connection conn = GlobalConnection.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT price FROM Inventory_Products WHERE id = ?")) {
            stmt.setInt(1, this.id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    price = rs.getDouble("price");
                } else {
                    throw new SQLException("Marketing offer not found");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeErrorException(null, "Error retrieving marketing offer price: " + e.getMessage());
        }
        return price - (price * persentage / 100);
    }
}
