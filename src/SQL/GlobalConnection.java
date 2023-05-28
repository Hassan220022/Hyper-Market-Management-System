package SQL;
import User.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GlobalConnection {
    public static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            // Initialize the global connection object
            String url = "jdbc:mysql://196.221.151.195:3306/project_db";
            String username = "dev";
            String password = "pass";
            conn = DriverManager.getConnection(url, username, password);
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }



    //debug only. remove later.
    public static void main(String[] args) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        System.out.println(conn);




        // add data to test
        String sql = "INSERT INTO Users (username, hashed_password) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "omar");
        stmt.setString(2, "123");
        stmt.executeUpdate();

        // read some data to test

        String sql2 = "SELECT * FROM Users";
        PreparedStatement stmt2 = conn.prepareStatement(sql2);
        ResultSet rs = stmt2.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("username"));
            System.out.println(rs.getString("id"));

        }

        GlobalConnection.closeConnection();
        
    }
}