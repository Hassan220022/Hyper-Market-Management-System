package Empoyee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SQL.GlobalConnection;
import User.User;

@SuppressWarnings({ "unused" })

public class Admin extends User {

    public Admin(String username, String password, String email) {
        super(username, password, email);
    }

    public void updateUsernameForEmployee(int employeeId, String newUsername) {
        // Check if the employee exists
        if (employeeExists(employeeId)) {
            // Update the employee's username
            try {
                Connection conn = GlobalConnection.getConnection();
                String query = "UPDATE Employees SET username = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, newUsername);
                stmt.setInt(2, employeeId);
                stmt.executeUpdate();

                System.out.println("Employee username updated successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Employee with ID " + employeeId + " does not exist.");
        }
    }

    public void deleteEmployee(int employeeId) {
        // Check if the employee exists
        if (employeeExists(employeeId)) {
            // Delete the employee from the database
            try {
                Connection conn = GlobalConnection.getConnection();
                String query = "DELETE FROM Employees WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, employeeId);
                stmt.executeUpdate();

                System.out.println("Employee deleted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Employee with ID " + employeeId + " does not exist.");
        }
    }

    public void listAllEmployees() {
        try {
            Connection conn = GlobalConnection.getConnection();
            String query = "SELECT id, username, type FROM Employees";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Employee List:");
            System.out.println("ID\t\tUsername\t\tType");
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String type = rs.getString("type");
                System.out.println(id + "\t\t" + username + "\t\t" + type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchEmployee(int employeeId) {
        // Check if the employee exists
        if (employeeExists(employeeId)) {
            // Retrieve and display the employee's details
            try {
                Connection conn = GlobalConnection.getConnection();
                String query = "SELECT id, username, type FROM Employees WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, employeeId);
                ResultSet rs = stmt.executeQuery();

                System.out.println("Employee Details:");
                System.out.println("ID\t\tUsername\t\tType");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String type = rs.getString("type");
                    System.out.println(id + "\t\t" + username + "\t\t" + type);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Employee with ID " + employeeId + " does not exist.");
        }
    }

    protected boolean employeeExists(int employeeId) {
        try {
            Connection conn = GlobalConnection.getConnection();
            String query = "SELECT id FROM Employees WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
