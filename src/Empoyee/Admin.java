package Empoyee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIDefaults;

import SQL.GlobalConnection;
import User.Password;
import User.User;
import User.UserType;

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
    // public boolean updatePassword(User user, String newPassword) throws
    // SQLException {--------->>>>>>eb2a e3malha ashan mesh 3aref am3malha ezay

    // // check if employee exsists
    // try {
    // if (employeeExists(user.getID())) {
    // setPassword(newPassword);
    // saveToDatabase(user.getID());
    // return true;
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return false;
    // }
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
                User employee = new User(username, password, type);// check if this is correct
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    // methode 1
    public User searchEmployee(int employeeId) {
        User user = getUser(employeeId);
        if (user != null) {
            return user;
        } else {
            throw new IllegalArgumentException("Invalid user ID: " + employeeId);
        }
    }

    // methode 2
    public User searchEmployee_methode2(int id) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT * FROM Users WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        try {
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                int typeStr = rs.getInt("type");
                return new User(username, password, UserType.getUserTypeString(typeStr));
            } else {
                return null;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }
}
