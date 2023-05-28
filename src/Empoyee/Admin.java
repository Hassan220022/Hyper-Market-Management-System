package Empoyee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIDefaults;

import SQL.GlobalConnection;
import User.User;

@SuppressWarnings({ "unused" })

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password, "admin");
    }

    public void updateUsernameForEmployee(User user, String newUsername) throws SQLException {
        // check if employee exsists
        try {
            if (employeeExists(user.getID())) {
                user.setUsername(newUsername);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteEmployee(User user) throws SQLException {
        // Check if the employee exists
        if (employeeExists(user.getID())) {
            // Delete the employee from the database
            try {
                Connection conn = GlobalConnection.getConnection();
                String query = "DELETE FROM users WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, user.getID());
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean addEmployee(String Username, String password, String type) throws SQLException {
        try {
            User user = new User(Username, password, type);
            while (!employeeExists(user.getID())) {
                user = new User(Username, password, type);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // isn't finished
    public List<User> listAllEmployees() {
        List<User> employees = new ArrayList<User>();

        try {
            // retrieve all employees from the database
            Connection conn = GlobalConnection.getConnection();
            String query = "SELECT * FROM Users WHERE ";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // create User objects for each employee and add them to the list
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String type = rs.getString("type");
                User employee = new User(username, password, type);// TODO: store in user password with no encryption
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public User searchEmployee(int employeeId) {
        User user = getUser(employeeId);
        if (user != null) {
            return user;
        } else {
            throw new IllegalArgumentException("Invalid user ID: " + employeeId);
        }
    }
}