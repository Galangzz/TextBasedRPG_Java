package com.controller;

import com.model.Admin;
import com.view.AdminView;
import com.view.PrintDelay;
import java.util.Scanner;

public class AdminController {

    private final AdminView view;
    private final Scanner scanner;
    private final Admin admin;

    public AdminController(){
        admin = new Admin();
        view = new AdminView();
        scanner = new Scanner(System.in);
    }

    public void adminDisplay(){
        while(true){
            view.adminDisplay();
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> admin.showAllAccount();
                case 2 -> admin.deleteAccount();
                case 3 -> editOpponent();
                case 4 -> editWeapon();
                case 5 -> editArmor();
                case 6 -> {
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
                case 1 -> admin.showAllAccount();
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
                case 1 -> admin.showAllOpponent();
                case 2 -> admin.addOpponent();
                case 3 -> admin.deleteOpponent();
                case 4 -> {return;}
                default -> PrintDelay.print("Invalid option. Please choose again.");
            }
        }
    }

    public void editWeapon(){
        while (true) {
            view.editWeapon();
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> admin.showAllWeapon();
                case 2 -> admin.addWeapon();
                case 3 -> admin.deleteWeapon();
                case 4 -> {return;}
                default -> PrintDelay.print("Invalid option. Please choose again.");
            }
        }
    }

    public void editArmor(){
        while (true) {
            view.editArmor();
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> admin.showAllArmor();
                case 2 -> admin.addArmor();
                case 3 -> admin.deleteArmor();
                case 4 -> {return;}
                default -> PrintDelay.print("Invalid option. Please choose again.");
            }
        }
    }


}
