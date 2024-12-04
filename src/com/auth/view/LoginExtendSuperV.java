package com.auth.view;

import com.auth.config.DB;
import com.main.Game;
public class LoginExtendSuperV extends SuperV {
    
    public LoginExtendSuperV (){
    }
    
    public void viewLogin(){
        String msg = "";
        do { 
            
            view();
            String email = getEmail();
            String password = getPassword();
            int a = DB.cariData(email, password);
            if (a > 0){
                msg = DB.authAkun(a, email, password);
                System.out.println(msg);
            }else{
                System.out.println("Account is not registered yet");
            }
        } while (!msg.equals("Login Success"));
        //langsung keprogram
        Game game = new Game();
        
        game.start();
    }
}