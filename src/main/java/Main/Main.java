
package Main;
import Users.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class Main {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
       login_register_implemintation impmnt=new login_register_implemintation();
    
        if(impmnt.admin_is_exist())
        {
            
            LoadingScreen.start(new admin_registration(), 50);
        }
        else
        {
            
            
            LoadingScreen.start(new user_login(), 50);
        }
    }
    
}
