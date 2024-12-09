package com.controller;

import com.model.Admin;
import com.view.AdminView;
import com.view.PrintDelay;
import java.util.Scanner;

public class AdminController {

    private final AdminView view;
    private final Scanner scanner;

    public AdminController(){
        view = new AdminView();
        scanner = new Scanner(System.in);
    }

    public void adminDisplay(){
        while(true){
            view.adminDisplay();
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> adminViewAccount();
                case 2 -> PrintDelay.print("Delete Account\n");
                case 3 -> editOpponent();
                case 4 -> {
                    PrintDelay.print("Have a Nice Day!\n");
                    return;
                }
                default -> PrintDelay.print("Invalid option. Please choose again.");
            }
        }
    }

    public void adminViewAccount(){
        while (true) {
            view.adminViewAccount();
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> new Admin().showAllAccount();
                case 2 -> PrintDelay.print("Delete Account\n");
                case 3 -> {return;}
                default -> PrintDelay.print("Invalid option. Please choose again.");
            }
        }
    }

    public void editOpponent(){
        while (true) {
            view.editOpponent();
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> PrintDelay.print("Show All Opponent\n");
                case 2 -> PrintDelay.print("Update Opponent\n");
                case 3 -> PrintDelay.print("Delete Opponenet\n");
                case 4 -> {return;}
                default -> PrintDelay.print("Invalid option. Please choose again.");
            }
        }
    }


}
