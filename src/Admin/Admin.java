package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import SQL.GlobalConnection;
import User.User;


public class Admin extends User {
    Admin(String username, String password) {
        super(username, password);
    }

    public void deleteUser(User user) {
        // Delete the user from the database
        // Use the global connection object
        try {
            Connection conn = GlobalConnection.getConnection();

            String query = "DELETE FROM users WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    


    
}
