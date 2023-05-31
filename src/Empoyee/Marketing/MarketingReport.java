package Empoyee.Marketing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import SQL.GlobalConnection;

//////TODO: Still needs some work
public class MarketingReport {
    private int id;
    private Marketer employee;
    private int productId;
    private String reportText;

    public MarketingReport(int employeeId, int productId, String reportText) throws Exception {

        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT MAX(id) FROM Marketing_Reports");// select Max id
                                                                                                    // from
                                                                                                    // Marketing_Report
                                                                                                    // gets the max id
                                                                                                    // in the table in
                                                                                                    // database

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt(1) + 1;
            } else {
                this.id = 1;
            }
            stmt = conn.prepareStatement(
                    "INSERT INTO Marketing_Reports (id, employee_id, product_id, report_text) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, this.id);
            stmt.setInt(2, employeeId);
            stmt.setInt(3, productId);
            stmt.setString(4, reportText);
            stmt.executeUpdate();
            this.employee = (Marketer) employee.searchEmployee(employeeId);// search for employee with this id and
            // return Marketer object --> methode in User class this.productId = productId;
            this.reportText = reportText;
        } catch (SQLException e) {
            throw new Exception("Error creating marketing report: " + e.getMessage());
        }
    }

    public int getId() throws Exception {
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT id FROM Marketing_Reports WHERE id = ?");
            stmt.setInt(1, this.id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("report is not found");
            }
        } catch (SQLException e) {
            throw new Exception("Error getting marketing report ID: " + e.getMessage());
        }
    }

    public int getEmployeeId() throws SQLException {
        return employee.getID();
    }

    public int getProductId() throws SQLException {
        int productId = -1;
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT product_id FROM Marketing_Reports WHERE id = ?");
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

    public String getReportText() throws SQLException {
        String reportText = null;
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT report_text FROM Marketing_Reports WHERE id = ?");
            stmt.setInt(1, this.id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                reportText = rs.getString("report_text");
            }
        } catch (SQLException e) {
            throw new RuntimeErrorException(null, "Error getting report text: " + e.getMessage());
        }
        return reportText;
    }

    public void setProductId(int productId) throws SQLException {
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Marketing_Reports SET product_id = ? WHERE id = ?");
            stmt.setInt(1, productId);
            stmt.setInt(2, this.id);
            stmt.executeUpdate();
            this.productId = productId;
        } catch (SQLException e) {
            throw new RuntimeErrorException(null, "Error setting product ID: " + e.getMessage());
        }
    }

    public void setReportText(String reportText) throws SQLException {
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Marketing_Reports SET report_text = ? WHERE id = ?");
            stmt.setString(1, this.reportText);
            stmt.setInt(2, this.id);
            stmt.executeUpdate();
            this.reportText = reportText;
        } catch (SQLException e) {
            throw new RuntimeErrorException(null, "Error setting product ID: " + e.getMessage());
        }
    }
}
