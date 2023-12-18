package Users;

import DatabaseConncection.*;
public class User {

    public static DatabaseConnection DB;
    private static String username;
    private static Password password;
    private static int id;
    public User() {
        DB = new DatabaseConnection();
    }

    public User(String classname, String url, String username, String password) {
        DB = new DatabaseConnection();
    }

    public User(String username, String password, int id, int role) {
        User.id = id;
        User.username = username;
        User.password = new Password(password);
    }

    public void setId(int id) {
        User.id = id;
    }

    public void setUsername(String username) {
        User.username = username;
    }

    public void setUserPassword(String password) {
        User.password.setPassword(password);
    }

    public int getId() {
        return User.id;
    }

    public String getUsername() {
        return User.username;
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
            int a = DB.excuteUpdate("update employees set username= \"" + username + "\",password=\"" + hashedpassword
                    + "\" where id = " + getId());
            System.out.println(a);
            return a;
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