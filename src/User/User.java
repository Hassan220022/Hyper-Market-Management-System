package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import SQL.GlobalConnection;
import User.Password;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings({ "unused", "unchecked" })

public class User {
    private int id;
    protected String username;
    private String name; // TODO: add name to database
    public Password password;
    private UserType type;
    private List<String> actions;

    public User(String username, String password, String type) {
        this.username = username;
        this.password = new Password(password);
        this.type = UserType.getUserType(type);
        this.actions = new ArrayList<String>();

        addAction("Created user");

        // Save the New user to the database
        // Use the global connection object
        try {
            Connection conn = GlobalConnection.getConnection();

            String query = "INSERT INTO users (username, password, class) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, type);// how mesh elmafrood yedi error ashan ashan ana bab3ato String mesh int
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

    protected void updateUsername(String username) {
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

    public int getID() throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT Id FROM Users WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, this.username);
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

    protected String getUsername() throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT username FROM Users WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, this.id);
        ResultSet rs = stmt.executeQuery();
        try {
            if (rs.next()) {
                return rs.getString("username");
            } else {
                return null;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    protected int getType() throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT type FROM Users WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, this.id);
        ResultSet rs = stmt.executeQuery();
        try {
            if (rs.next()) {
                String typeStr = rs.getString("type");
                UserType type = UserType.valueOf(typeStr);
                return type.ordinal();
            } else {
                throw new IllegalArgumentException("Invalid user ID: " + id);
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

    protected void setID(int newID) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "UPDATE users SET id = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, newID);
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
        this.id = newID;
    }

    public void setUsername(String newUsername) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "UPDATE users SET username = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, newUsername);
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
        this.username = newUsername;
    }

    protected boolean employeeExists(String username) {
        try {
            if (getID() != -1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean employeeExists(int ID) {
        try {
            if (getUsername() != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected User getUser(int ID) {
        if (employeeExists(ID)) {
            return this;
        } else {
            return null;
        }
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

    public String getPassword() {
        return password.getHashedPassword();
    }

    protected void setPassword(String password) {
        this.password.setPassword(password);
    }

    public static void main(String[] args) throws SQLException, JsonProcessingException {
        User user = new User("test", "test", "test");
        user.login("test");
        user.updateUsername("test2");
        user.updatePassword("test2");
        user.logout();
        System.out.println(user.getActions());
        System.out.println(user.getActionsJson());
    }

}
