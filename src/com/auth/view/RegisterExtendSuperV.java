package com.auth.view;

import com.auth.config.DB;
import com.PrintDelay;
public class RegisterExtendSuperV extends SuperV {

    public  void viewRegister(){
            
            view();
            String email = getEmail();
            String password = getPassword();
            DB register = new DB();
            int a = DB.cariData(email, password);
            if(a > 0){
                PrintDelay.print("Email already exsist!!\n");
                
            }else{
                if (register.register(email, password)) {
                    PrintDelay.print("Account successfully registered\n");
                } else{
                    PrintDelay.print("Failed to register");
                }
            }
    }

}
