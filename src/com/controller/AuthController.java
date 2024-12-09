package com.controller;

import com.auth.config.DB;
import com.model.SuperV;
import com.view.AuthView;
import com.view.PrintDelay;

public class AuthController {

    private final SuperV superV;
    private final AuthView authView;

    public AuthController() {
        superV = new SuperV();
        authView = new AuthView();
    }

    // Proses login
    public void processAuth() {
        authView.displayMenu();
    }

    public void Login() {
        String role;
        authView.displayLR(superV);
        if (superV.getEmail().contains(" ")) {
            PrintDelay.print("Make sure your email typing is correct\n");
        }else{
            String email = superV.getEmail();
            String password = superV.getPassword();
            
            int a = DB.cariData(email);
            if (a > 0){
                role = DB.authAkun(a, email, password);
            }else{
                role = "Account is not registered yet";
                
            }
            switch (role) {
                case "player"-> {
                    PrintDelay.print("\n--- Welcome ---\n");
                    new GameController().showMenu();
                }
                case "admin" -> {
                    PrintDelay.print("\n * Hello Admin * \n");
                    new AdminController().adminDisplay();
                }
                default->{
                    PrintDelay.print(role);
                    PrintDelay.print("\n");
                    // throw new AssertionError(msg);
                }
            }
        }
    }
        

    //Proses Register
    public void Register() {
        authView.displayLR(superV);
        if (superV.getEmail().contains(" ")) {
            PrintDelay.print("Make sure your email typing is correct\n");
        }else{
            String email = superV.getEmail();
            String password = superV.getPassword();
            
            if (DB.cariData(email) > 0) {
                PrintDelay.print("Email already exists!\n");
            } else {
                boolean registered = DB.register(email, password);
                PrintDelay.print(registered ? "Account successfully registered\n" : "Failed to register\n");
            }
        }
    }
}
