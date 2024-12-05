package com.auth.view;

import com.printDelay;
import java.util.Scanner;

public class LR_View {
    public static void view(){

        LoginExtendSuperV login = new LoginExtendSuperV();
        RegisterExtendSuperV register = new RegisterExtendSuperV();
        try (Scanner scanner = new Scanner(System.in)) {
            int option;
            do{
                printDelay.print(
                        """
                                    \n=== Welcome to TextBased-RPG Game ===
                                    1. Login
                                    2. Register
                                    3. Exit
                                    Choose your option[1/2/3]: """);
                
                option = scanner.nextInt();
                switch (option) {
                    case 1 -> login.viewLogin();
                    case 2 -> register.viewRegister();
                    case 3 -> printDelay.print("Goodbye!");
                    default -> printDelay.print("Invalid option. Please choose again.");
                }
            } while (option !=3);
        }
    }
}
