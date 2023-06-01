package Users;

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

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static String hashPasswordStatic(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public int saveToDatabase(int userId) throws SQLException {

        String query = "update employees set password='" + this.hashedPassword + "' where id ='" + userId + "'";
        int r = DB.excuteUpdate(query);
        return r;
    }

    public void setPassword(String password) {
        this.hashedPassword = hashPassword(password);
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}