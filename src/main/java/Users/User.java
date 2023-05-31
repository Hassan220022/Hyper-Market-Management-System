package Users;

import DatabaseConncection.*;
import java.sql.*;

public class User {

    public static DatabaseConnection DB;
    private static String username;
    private static String password;
    private static int id;
    private int role;

    public User() {
        DB = new DatabaseConnection();
    }

    public User(String classname, String url, String username, String password) {
        DB = new DatabaseConnection();
    }

    public User(String username, String password, int id, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getpassword() {
        return this.password;
    }

    public boolean login(String username, String password) throws SQLException {
        int count = 0;
        String sql = "select count(1) from users where username='" + username + "' and password='" + password + "'";
        ResultSet rs = DB.s.executeQuery(sql);
        while (rs.next()) {
            count = rs.getInt("count(1)");
        }
        if (count == 1) {
            return true;
        }
        return false;
    }

    public void LogOut() {
        System.exit(0);
    }

    public int updateProfile(String username, String password) {
        try {
            return DB.excuteUpdate("update employees set username='" + username + "',password='" + password
                    + "' where id = '" + getId() + "'");
        } catch (Exception e) {
            return 0;
        }

    }

}
