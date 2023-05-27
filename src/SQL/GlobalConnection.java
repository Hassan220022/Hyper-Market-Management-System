package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GlobalConnection {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            // Initialize the global connection object
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "myuser";
            String password = "mypassword";
            conn = DriverManager.getConnection(url, username, password);
        }
        return conn;
    }
        private static String getQueryForID(String Table) {
        String query;
        switch (Table) {
            case "Employees":
                query = "SELECT Id FROM Employees WHERE username = ?";
                break;
            case "Marketing_Offers":
                query = "SELECT Id FROM Marketing_Offers WHERE username = ?";
                break;
            case "Sales_Orders":
                query = "SELECT Id FROM Sales_Orders WHERE username = ?";
                break;
            case "Inventory_Products":
                query = "SELECT Id FROM Inventory_Products WHERE username = ?";
                break;
            case "Admin":
                query = "SELECT Id FROM Admin WHERE username = ?";
                break;
            case "Marketing_Reports":
                query = "SELECT Id FROM Marketing_Reports WHERE username = ?";
                break;
            default:
                query = "SELECT Id FROM Users WHERE username = ?";
                break;
        }
        return query;
    }
    public static public static int getID_data(String Table) throws SQLException {
        try {
            Connection conn = GlobalConnection.getConnection();
            String query = getQueryForID(Table);
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
