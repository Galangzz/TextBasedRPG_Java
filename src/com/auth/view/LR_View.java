package com.auth.view;

import java.util.Scanner;

public class LR_View {
    public static void view(){

        LoginExtendSuperV login = new LoginExtendSuperV();
        RegisterExtendSuperV register = new RegisterExtendSuperV();
        Scanner scanner = new Scanner(System.in);
        int option;
        do{
            System.out.print(
            """
            \n=== Welcome to TextBased-RPG Game ===
            1. Login
            2. Register
            3. Exit
            Choose your option[1/2/3]: """);
            
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    login.viewLogin();
                    break;
                case 2:
                    register.viewRegister();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        } while (option !=3);
        scanner.close();
    }
}
