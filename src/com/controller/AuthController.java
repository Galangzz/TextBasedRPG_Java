package com.controller;

import com.view.AuthView;
import com.model.SuperV;
import com.auth.config.DB;
import com.view.PrintDelay;

public class AuthController {

    private final SuperV superV;
    private final AuthView authView;

    public AuthController() {
        superV = new SuperV();
        authView = new AuthView();
    }

    // Proses login atau registrasi
    public void processAuth() {
        authView.displayMenu();
    }

    public void Login() {
        String msg = "";
        authView.displayLR(superV);

        String email = superV.getEmail();
        String password = superV.getPassword();

        int a = DB.cariData(email, password);
        if (a > 0){
            msg = DB.authAkun(a, email, password);
            PrintDelay.print(msg);
        }else{
            PrintDelay.print("Account is not registered yet");
        }
        
        if (msg.equals("Login Success")){
            PrintDelay.print("\n");

            //langsung keprogram
            GameController game = new GameController();
            game.showMenu();
        }
    }

    public void Register() {
        authView.displayLR(superV);

        String email = superV.getEmail();
        String password = superV.getPassword();

        if (DB.cariData(email, password) > 0) {
            PrintDelay.print("Email already exists!\n");
        } else {
            boolean registered = DB.register(email, password);
            PrintDelay.print(registered ? "Account successfully registered\n" : "Failed to register");
        }
    }
}
