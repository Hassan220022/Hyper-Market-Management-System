
package DatabaseConncection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;


public class DatabaseConnection {
    public static Connection c;
    public Statement s;
    
    public DatabaseConnection() {
        try {
            String url = "jdbc:mysql://196.221.151.195:3306/market_db";
            String username = "dev";
            String password = "pass";
            c = DriverManager.getConnection(url, username, password);
            s = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public ResultSet executeQuery(String query){
        try {
            ResultSet r = s.executeQuery(query);
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public int excuteUpdate(String query){
        try {
            int r = s.executeUpdate(query);
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    //test

    public static void main(String[] args) {
        DatabaseConnection dc = new DatabaseConnection();
        ResultSet rs = dc.executeQuery("select * from employees");
        try {
            while(rs.next()){
                System.out.println(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
