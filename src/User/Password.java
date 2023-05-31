package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import SQL.GlobalConnection;

public class Password {
    private String hashedPassword;

    public Password(String password) {
        this.hashedPassword = hashPassword(password);
    }

    public Password(User user) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT password FROM users WHERE userId = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getUsername());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        this.hashedPassword = rs.getString("hashed_password");
    }

    public boolean checkPassword(String password){
        return BCrypt.checkpw(password, hashedPassword);
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void saveToDatabase(int userId) throws SQLException {
        // Use the global connection object
        Connection conn = GlobalConnection.getConnection();
    
        String query = "UPDATE users SET password = ? WHERE userId = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, hashedPassword);
        stmt.setInt(2, userId);
        stmt.executeUpdate();
    }

    public void setPassword(String password) {
        this.hashedPassword = hashPassword(password);
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}