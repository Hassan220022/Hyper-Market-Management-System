package Empoyee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SQL.GlobalConnection;
import User.Password;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings({ "unused", "unchecked" })

public class User {
    private int id;
    private String username;
    private String name; // TODO: add name to database
    private Password password;
    private String email;
    private List<String> actions;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = new Password(password);
        this.email = email;
        this.actions = new ArrayList<String>();

        addAction("Created user");

        // Save the New user to the database
        // Use the global connection object
        try {
            Connection conn = GlobalConnection.getConnection();

            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.executeUpdate();

            // Get the new user's id from the database
            query = "SELECT userId FROM Users WHERE username = ?"; // User must be 'U'must be capitcal
            stmt = conn.prepareStatement(query); // id
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            this.id = rs.getInt("userId");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public boolean isLoggedin(String password) {
        if (this.password.checkPassword(password)) {
            addAction("Is Logged in");
            return true;
        }
        return false;
    }

    public void login(String password_user, String username_user) {
        if (employeeExists(username_user)) {
            try {
                Connection conn = GlobalConnection.getConnection();
                String query = "SELECT password FROM Users WHERE hashed_password = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, BCrypt.hashpw(password_user, BCrypt.gensalt()));
                ResultSet rs = stmt.executeQuery();
                addAction("Logged in");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void logout() {
        addAction("Logged out");
    }

    public void updateUsername(String username) {
        addAction("Updated username to " + username);
        this.username = username;
    }

    protected void updatePassword(String password) {
        this.password = new Password(password);
        try {
            this.password.saveToDatabase(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addAction("Updated password");
    }

    private void addAction(String action) {
        actions.add(action);
        try {
            saveActionsToDatabase(null);
        } catch (JsonProcessingException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveActionsToDatabase(Connection conn) throws SQLException, JsonProcessingException {
        if (conn == null) {
            conn = GlobalConnection.getConnection();
        }
        ObjectMapper mapper = new ObjectMapper();
        String actionsJson = mapper.writeValueAsString(actions);

        String sql = "UPDATE Users SET actions = ? WHERE userId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, actionsJson);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }

    public List<String> getActions(Connection conn) throws SQLException, JsonProcessingException {

        String sql = "SELECT actions FROM Users WHERE userId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String actionsJson = rs.getString("actions");
            ObjectMapper mapper = new ObjectMapper();
            actions = mapper.readValue(actionsJson, ArrayList.class);
        }

        return actions;
    }

    protected boolean employeeExists(String username) {
        try {
            Connection conn = GlobalConnection.getConnection();
            String query = "SELECT username FROM Users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//using getID_data form globalConnection class
    protected boolean employeeExists(int ID) {
        try {
            if (ID == GlobalConnection.getID_data("Users")) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
