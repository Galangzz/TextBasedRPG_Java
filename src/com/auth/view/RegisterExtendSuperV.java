package com.auth.view;

import com.auth.config.DB;

public class RegisterExtendSuperV extends SuperV {

    public  void viewRegister(){
            
            view();
            String email = getEmail();
            String password = getPassword();
            DB register = new DB();
            int a = DB.cariData(email, password);
            if(a > 0){
                System.out.println("Email already exsist!!");
                
            }else{
                if (register.register(email, password)) {
                    System.out.println("Account successfully registered");
                } else{
                    System.out.println("Failed to register");
                }
            }
    }

}
