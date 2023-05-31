package Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import DatabaseConncection.DatabaseConnection;

public class Password {
    public static DatabaseConnection DB;
    private String hashedPassword;

    public Password(String password) {
        DB = new DatabaseConnection();
        this.hashedPassword = hashPassword(password);
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public int saveToDatabase(int userId) throws SQLException {

        String query = "update employees set password='" + this.hashedPassword + "' where id ='" + userid + "'";
        int r = DB.excuteUpdate(query);
        return r;
    }

    public void setPassword(String password) {
        this.hashedPassword = hashPassword(password);
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public static void main(String[] args) {
        // Create a new Password object with the password "password123"
        Password password = new Password("password123");

        // Print the hashed password
        System.out.println("Hashed password: " + password.getHashedPassword());

        // Check if the password "password123" matches the hashed password
        boolean isMatch = password.checkPassword("password123");
        System.out.println("Password match: " + isMatch);

        // Set a new password and save it to the database for user ID 1
        password.setPassword("newpassword456");
        try {
            int rowsAffected = password.saveToDatabase(1);
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            System.out.println("Error saving password to database: " + ex.getMessage());
        }
    }
}