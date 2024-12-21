package com.model;

import com.auth.config.DB;
import com.view.AdminView;
import com.view.PrintDelay;
import java.util.List;
import java.util.Scanner;

public class Admin {

    private final Scanner scanner;

    public Admin() {
        scanner = new Scanner(System.in);
    }

    public void showAllAccount() {
        // int a = DB.countData("akun");
        List<String[]> data = DB.showAllAccount();
        if (data.isEmpty()) {
            PrintDelay.print("\n~~ No account registered ~~\n");
        } else {
            new AdminView().showAllAccount(data);
        }
    }

    public void deleteAccount() {
        new AdminView().deleteAccount();
        String akun = scanner.nextLine().trim();
        int a = DB.cariData(akun);
        if (a != 0) {
            PrintDelay.print("Are you sure you want to delete the " + akun + " account?\n");
            PrintDelay.print("Enter Option (y/n)>> \0");
            char option = scanner.next().charAt(0);
            if (option == 'y' || option == 'Y') {
                if (DB.cariChara(a)) {
                    if (DB.deleteChara(a)) {
                        if (DB.deleteAccount(a)) {
                            PrintDelay.print("Account successfully deleted !!!\n");
                        } else {
                            PrintDelay.print("Account failed to delete !!!\n");
                        }
                    } else {
                        PrintDelay.print("Account failed to delete !!!\n");
                    }
                } else {
                    if (DB.deleteAccount(a)) {
                        PrintDelay.print("Account successfully deleted !!!\n");
                    } else {
                        PrintDelay.print("Account failed to delete !!!\n");
                    }
                }
            } else {
                PrintDelay.print("Deleted accounts are cancelled!!!\n");
            }
        } else {
            PrintDelay.print("Account not found!!\n");
        }
    }

    public void showAllOpponent() {
        List<String[]> data = DB.showAllOpponent();
        if (data.isEmpty()) {
            PrintDelay.print("\n~~ Opponent Data Empty ~~\n");
        } else {
            new AdminView().showAllOpponent(data);
        }
    }

    public void addOpponent() {
        PrintDelay.print("\n~Please fill in the following data ~\n\0");
        PrintDelay.print("Enter opponent name >> ");
        String nama = scanner.nextLine().trim();
        PrintDelay.print("Enter opponent hp >> \0");
        int hp = scanner.nextInt();
        PrintDelay.print("Enter opponent damage >> \0");
        int damage = scanner.nextInt();
        PrintDelay.print("Enter opponent level >> \0");
        int level = scanner.nextInt();
        new AdminView().addOpponent(nama, hp, damage, level);
        int option = scanner.next().charAt(0);
        if (option == 'y' || option == 'Y') {
            if (DB.addOpponent(nama, hp, damage, level)) {
                PrintDelay.print("Opponent successfully added !!!\n");
            } else {
                PrintDelay.print("Opponent failed to add !!!\n");
            }
        } else {
            PrintDelay.print("Opponent data is cancelled!!!\n");
        }
    }

    public void deleteOpponent() {
        PrintDelay.print(
                """
            Enter the name of the opponent you want to delete
            >> \0""");
        String name = scanner.nextLine().trim();
        if (DB.cariOpponent(name)) {
            if (DB.deleteOpponent(name)) {
                PrintDelay.print("Opponent successfully deleted !!!\n");
            } else {
                PrintDelay.print("Opponent failed to delete !!!\n");
            }
        } else {
            PrintDelay.print("Opponent not found !!!\n");
        }
    }


    public void showAllWeapon() {
        // int a = DB.countData("akun");
        List<String[]> data = DB.showAllWeapon();
        if (data.isEmpty()) {
            PrintDelay.print("\n~~ Data weapon is empty!! ~~\n");
        } else {
            new AdminView().showAllWeapon(data);
        }
    }

    public void addWeapon() {
        PrintDelay.print("\n~Please fill in the following data ~\n\0");
        PrintDelay.print("Enter weapon name >> ");
        String nama = scanner.nextLine().trim();
        PrintDelay.print("Enter weapon damage >> \0");
        int damage = scanner.nextInt();
        String used = "fighter";
        int option_used;
        do { 
            PrintDelay.print(
            """
            Used For: 
            1. Fighter
            2. Mage
            3. Tank 
            Choose (1/2/3)>> \0""");
            option_used = scanner.nextInt();
            switch (option_used) {
                case 1-> used = "fighter";
                case 2-> used = "mage";
                case 3-> used = "tank";
                default -> PrintDelay.print("Invalid Choice, Please try again!!");
            }
        } while (option_used != 1 && option_used != 2 && option_used !=3);

        new AdminView().addWeapon(nama, damage, used);
        int option = scanner.next().charAt(0);
        if (option == 'y' || option == 'Y') {
            if (DB.addWeapon(nama, damage, used)) {
                PrintDelay.print("Weapon successfully added !!!\n");
            } else {
                PrintDelay.print("Weapon failed to add !!!\n");
            }
        } else {
            PrintDelay.print("Weapon data is cancelled!!!\n");
        }
    }

    public void deleteWeapon() {
        PrintDelay.print(
                """
            Enter the name of the weapon you want to delete
            >> \0""");
        String name = scanner.nextLine().trim();
        if (DB.cariWeapon(name)) {
            if (DB.deleteWeapon(name)) {
                PrintDelay.print("Weapon successfully deleted !!!\n");
            } else {
                PrintDelay.print("Weapon failed to delete !!!\n");
            }
        } else {
            PrintDelay.print("Weapon not found !!!\n");
        }
    }

    public void showAllArmor(){
        List<String[]> data = DB.showAllArmor();
        if (data.isEmpty()) {
            PrintDelay.print("\n~~ Data Armor is empty!! ~~\n");
        } else {
            new AdminView().showAllArmor(data);
        }
    }

    public void addArmor() {
        PrintDelay.print("\n~Please fill in the following data ~\n\0");
        PrintDelay.print("Enter armor name >> ");
        String nama = scanner.nextLine().trim();
        PrintDelay.print("Enter armor defense >> \0");
        int defense = scanner.nextInt();
        String used = "fighter";
        int option_used;
        do { 
            PrintDelay.print(
            """
            Used For: 
            1. Fighter
            2. Mage
            3. Tank 
            Choose (1/2/3)>> \0""");
            option_used = scanner.nextInt();
            switch (option_used) {
                case 1-> used = "fighter";
                case 2-> used = "mage";
                case 3-> used = "tank";
                default -> PrintDelay.print("Invalid Choice, Please try again!!");
            }
        } while (option_used != 1 && option_used != 2 && option_used !=3);

        new AdminView().addArmor(nama, defense, used);
        int option = scanner.next().charAt(0);
        if (option == 'y' || option == 'Y') {
            if (DB.addArmor(nama, defense, used)) {
                PrintDelay.print("Armor successfully added !!!\n");
            } else {
                PrintDelay.print("Armor failed to add !!!\n");
            }
        } else {
            PrintDelay.print("Armor data is cancelled!!!\n");
        }
    }

    public void deleteArmor() {
        PrintDelay.print(
                """
            Enter the name of the armor you want to delete
            >> \0""");
        String name = scanner.nextLine().trim();
        if (DB.cariArmor(name)) {
            if (DB.deleteArmor(name)) {
                PrintDelay.print("Armor successfully deleted !!!\n");
            } else {
                PrintDelay.print("Armor failed to delete !!!\n");
            }
        } else {
            PrintDelay.print("Armor not found !!!\n");
        }
    }

}
