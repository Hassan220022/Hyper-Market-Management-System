package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class GlobalConnection {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            // Initialize the global connection object
            String url = "jdbc:mysql://192.168.1.99:3306/project_db";
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

    // debug only. remove later.
    public static void main(String[] args) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        // System.out.println(conn);

        // add data to test
        // String sql = "INSERT INTO Users (username, hashed_password) VALUES (?, ?)";
        // PreparedStatement stmt = conn.prepareStatement(sql);
        // stmt.setString(1, "omar");
        // stmt.setString(2, "123");
        // stmt.executeUpdate();

        // // read some data to test

        String sql2 = "SELECT * FROM Users";
        PreparedStatement stmt2 = conn.prepareStatement(sql2);
        ResultSet rs = stmt2.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("username"));
            System.out.println(rs.getString("id"));

        }
        System.out.println("done");
        System.out.println("\n\n");
        System.out.println(GlobalConnection.getID("Users", "omar"));
        System.out.println(GlobalConnection.getUsername("Users", 4));
        // GlobalConnection.closeConnection();

    }

    protected static int getID(String tableName, String username) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT Id FROM " + tableName + " WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
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

    protected static String getUsername(String tableName, int id) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT username FROM " + tableName + " WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
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

    protected static String getHashedPassword(String tableName, int id) throws SQLException {
        Connection conn = GlobalConnection.getConnection();
        String query = "SELECT password FROM " + tableName + " WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        try {
            if (rs.next()) {
                return rs.getString("password");
            } else {
                return null;
            }
        } finally {
            rs.close();
            stmt.close();
        }
    }

}
