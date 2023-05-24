package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
    private String hashedPassword;

    public Password(String password) {
        this.hashedPassword = hashPassword(password);
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void saveToDatabase(Connection conn, int userId) throws SQLException {
        String query = "UPDATE users SET password = ? WHERE userId = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, hashedPassword);
        stmt.setInt(2, userId);
        stmt.executeUpdate();
    }
}