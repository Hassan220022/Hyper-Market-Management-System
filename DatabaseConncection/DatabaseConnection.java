
package DatabaseConncection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import dotenv.Dotenv;

public class DatabaseConnection {
    public static Connection con;
    public Statement stmnt;

    public DatabaseConnection() {
        try {
            Dotenv dotenv = Dotenv.load();
            String url = dotenv.get("URL");
            String username = dotenv.get("SQLUSER");
            String password = dotenv.get("SQLPASSWORD");

            System.out.println(url);
            System.out.println(username);
            System.out.println(password);

            con = DriverManager.getConnection(url, username, password);
            stmnt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);

            // Try again with different credentials
            try {
                Dotenv dotenv = Dotenv.load();

                String url2 = dotenv.get("URL2");
                String username2 = dotenv.get("SQLUSER2");
                String password2 = dotenv.get("SQLPASSWORD2");

                System.out.println(url2);
                System.out.println(username2);
                System.out.println(password2);

                con = DriverManager.getConnection(url2, username2, password2);
                stmnt = con.createStatement();
            } catch (SQLException ex2) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex2);
            }
        }

    }

    public ResultSet executeQuery(String query) {
        try {
            ResultSet r = stmnt.executeQuery(query);
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int excuteUpdate(String query) throws SQLException {
        try {
            int r = stmnt.executeUpdate(query);
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    // test

    public static void main(String[] args) {
        DatabaseConnection dc = new DatabaseConnection();
        ResultSet rs = dc.executeQuery("select * from employees");
        try {
            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
