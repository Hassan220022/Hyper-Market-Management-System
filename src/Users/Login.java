package Users;

import Admin.Adminstration;
import DatabaseConncection.DatabaseConnection;
import Inventory.Inventory;
import Marketing.MarketingFrame;
import Seller.MakeOrder;
import java.util.logging.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Login {

    DatabaseConnection dc = new DatabaseConnection();
    User user = new User();

    public boolean admin_is_exist() {
        try {
            int count = 0;
            ResultSet rs = dc.executeQuery("select count(1) from employees where type='Admin'");
            if (rs.next()) {
                count = rs.getInt("count(1)");
            }
            if (count == 0) {
                return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    public void add_admin(String username, String password) throws SQLException {
        String hashedpassword = Password.hashPasswordStatic(password);
        dc.excuteUpdate("insert into employees(username,password,type) values('" + username + "','" + hashedpassword
                + "','Admin')");
        user_login back = new user_login();
        back.setVisible(true);
    }

    public boolean login(String username, String password) {
        try {
            ResultSet rs = dc.executeQuery(
                    "select * from employees where username='" + username + "'");
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (Password.checkPassword(password, hashedPassword)) {
                    switch (rs.getString("type")) {
                        case "Admin":
                            Adminstration admin = new Adminstration();
                            admin.setVisible(true);
                            break;
                        case "Marketing Employee":
                            MarketingFrame marketing = new MarketingFrame();
                            marketing.setVisible(true);
                            break;
                        case "Inventory Employee":
                            Inventory inventory = new Inventory();
                            inventory.setVisible(true);
                            break;
                        case "Seller":
                            MakeOrder seller = new MakeOrder();
                            seller.setVisible(true);
                            break;
                    }
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid password", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return false;
    }

}