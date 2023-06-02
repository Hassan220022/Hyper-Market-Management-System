
package Main;

import Users.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class Main {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        Login impmnt = new Login();

        if (impmnt.admin_is_exist()) {

            LoadingScreen.start(new admin_registration(), 10);
        } else {

            LoadingScreen.start(new user_login(), 10);
        }
    }

}
