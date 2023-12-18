
package Main;
import Users.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class Main {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
       Login loginimp=new Login();
    
        if(loginimp.admin_is_exist())
        {
            
            LoadingScreen.start(new admin_registration(), 5);
        }
        else
        {
            
            
            LoadingScreen.start(new user_login(), 5);
        }
    }
    
}
