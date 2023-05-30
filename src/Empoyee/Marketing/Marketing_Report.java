package Empoyee.Marketing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import SQL.GlobalConnection;

//////TODO: Still needs some work
public class Marketing_Report {
    private int id;
    private Marketer employee;
    private int productId;
    private String reportText;

    public Marketing_Report(int id, int employeeId, int productId, String reportText) throws Exception {
        int EID = -1;
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Marketing_Reports WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id");

                EID = rs.getInt("employee_id"); // get employee id from database

                this.productId = rs.getInt("product_id");
                this.reportText = rs.getString("report_text");
            } else {
                stmt = conn.prepareStatement(
                        "INSERT INTO Marketing_Reports (id, employee_id, product_id, report_text) VALUES (?, ?, ?, ?)");
                stmt.setInt(1, id);
                stmt.setInt(2, employeeId);
                stmt.setInt(3, productId);
                stmt.setString(4, reportText);
                stmt.executeUpdate();
                this.id = id;
                this.employee = (Marketer) employee.searchEmployee(EID);// search for employee with this id and return
                                                                        // Marketer object --> methode in User class
                this.productId = productId;
                this.reportText = reportText;
            }
        } catch (SQLException e) {
            throw new Exception("Error creating marketing report: " + e.getMessage());
        }
    }

    public int getId() throws Exception {

        int id = -1;
        try {
            Connection conn = GlobalConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT id FROM Marketing_Reports WHERE employee_id = ? AND product_id = ? AND report_text = ?");
            stmt.setInt(1, this.employee.getID());
            stmt.setInt(2, this.productId);
            stmt.setString(3, this.reportText);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new Exception("Error getting marketing report ID: " + e.getMessage());
        }
        return id;
    }

    public int getEmployeeId() throws SQLException {
        return employee.getID();
    }

    public int getProductId() {
        return productId;
    }

    public String getReportText() {
        return reportText;
    }
}
