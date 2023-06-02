package Users;

import DatabaseConncection.*;
import java.sql.*;

public class User {

    public static DatabaseConnection DB;
    private static String username;
    private static Password password;
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
        this.password = new Password(password);
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserPassword(String password) {
        this.password.setPassword(password);
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getpassword() {
        return password.getHashedPassword();
    }

    public void LogOut() {
        System.exit(0);
    }

    public int updateProfile(String username, String password) {
        String hashedpassword = Password.hashPasswordStatic(password);
        try {
            return DB.excuteUpdate("update employees set username='" + username + "',password='" + hashedpassword
                    + "' where id = '" + getId() + "'");
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateProfileUserName(String username)// update only username
    {
        try {
            return DB.excuteUpdate("update employees set username='" + username + "' where id = '" + getId() + "'");
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateProfilePassword(String password) {
        String hashedpassword = Password.hashPasswordStatic(password);
        try {
            return DB.excuteUpdate(
                    "update employees set password='" + hashedpassword + "' where id = '" + getId() + "'");
        } catch (Exception e) {
            return 0;
        }
    }
}