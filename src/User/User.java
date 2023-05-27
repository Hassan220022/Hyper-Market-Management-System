package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SQL.GlobalConnection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


// CREATE TABLE Users (
//   id INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,
//   username VARCHAR(255),
//   hashed_password VARCHAR(255),
//   class VARCHAR(255),
//   actions JSON DEFAULT NULL CHECK (JSON_VALID(actions)) 

// );

@SuppressWarnings({"unused", "unchecked"})

public class User {
    private int id;
    private String username;
    private Password password;
    private List<String> actions;

    public User(String username, String password) {
        this.username = username;
        this.password = new Password(password);
        this.actions = new ArrayList<String>();

        addAction("Created user");

        // Save the New user to the database
        // Use the global connection object
        try {
            Connection conn = GlobalConnection.getConnection();

            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();

            // Get the new user's id from the database
            query = "SELECT userId FROM users WHERE username = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            this.id = rs.getInt("userId");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public boolean login(String password) {
        if (this.password.checkPassword(password)) {
            addAction("Logged in");
            return true;
        } else {
            addAction("Failed login attempt");
            return false;
        }
    
    }

    public void logout() {
        addAction("Logged out");
    }

    public void updateUsername(String username) {
        addAction("Updated username to " + username);
        this.username = username;
        try {
            Connection conn = GlobalConnection.getConnection();
            String query = "UPDATE users SET username = ? WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updatePassword(String password) {
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

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getActions() {
        return actions;
    }

    public String getActionsJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(actions);
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public void setActions(String actionsJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.actions = mapper.readValue(actionsJson, ArrayList.class);
    }

    public String toString() {
        return "User: " + username + " Password: " + password;
    }

    public static void main(String[] args) throws SQLException, JsonProcessingException {
        User user = new User("test", "test");
        user.login("test");
        user.updateUsername("test2");
        user.updatePassword("test2");
        user.logout();
        System.out.println(user.getActions());
        System.out.println(user.getActionsJson());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password.getHashedPassword();
    }

    public void setPassword(String password) {
        this.password.setPassword(password);
    }

}