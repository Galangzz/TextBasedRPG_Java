package com.view;

import com.controller.AuthController;
import com.model.SuperV;
import java.util.Scanner;

public class AuthView {
    public void displayMenu(){

        try (Scanner scanner = new Scanner(System.in)) {
            int option;
            do{
                PrintDelay.print(
                        """
                                    \n=== Welcome to TextBased-RPG Game ===
                                    1. Login
                                    2. Register
                                    3. Exit
                                    Choose your option[1/2/3]: """);
                
                option = scanner.nextInt();
                switch (option) {
                    case 1 -> new AuthController().Login();
                    case 2 -> new AuthController().Register();
                    case 3 -> PrintDelay.print("Goodbye!");
                    default -> PrintDelay.print("Invalid option. Please choose again.");
                }
            } while (option !=3);
        }
    }

    public void displayLR(SuperV superV){
        PrintDelay.print(
        """
        \nPlease Enter Your Email!! 
        Email: """);
        superV.setEmail();


        PrintDelay.print(
        """
        \nPlease Enter Your Password!!
        Password: """);
        superV.setPassword();
    }
}
