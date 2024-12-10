package com.model;

import com.auth.config.DB;
import com.view.AdminView;
import com.view.PrintDelay;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private Monster monster;
    private final Scanner scanner;

    
    public Admin(){
        scanner = new Scanner(System.in);
    }

    public void showAllAccount(){
        // int a = DB.countData("akun");
        List<String[]> data = DB.showAllAccount();
        if(data.isEmpty()){
            PrintDelay.print("\n~~ No account registered ~~\n");
        }else{
            new AdminView().showAllAccount(data);
        }
    }

    public void deleteAccount(){
        new AdminView().deleteAccount();
        String akun = scanner.nextLine().trim();
        int a = DB.cariData(akun);
        if (a != 0) {
            PrintDelay.print("Are you sure you want to delete the " + akun + " account?\n");
            PrintDelay.print("Enter Option (y/n)>> \0");
            char option = scanner.next().charAt(0);
            if (option == 'y' || option =='Y') {
                if(DB.cariChara(a)){
                    if(DB.deleteChara(a)){
                        if(DB.deleteAccount(a)){
                            PrintDelay.print("Account successfully deleted !!!\n");
                        }else{
                            PrintDelay.print("Account failed to delete !!!\n");
                        }
                    }else{
                        PrintDelay.print("Account failed to delete !!!\n");
                    }
                }else{
                    if(DB.deleteAccount(a)){
                        PrintDelay.print("Account successfully deleted !!!\n");
                    }else{
                        PrintDelay.print("Account failed to delete !!!\n");
                    }
                }
            }else {
                PrintDelay.print("Deleted accounts are cancelled!!!\n");
            }
        }else{
            PrintDelay.print("Account not found!!\n");
        }
    }

    public void showAllOpponent(){
        List<String[]> data = DB.showAllOpponent();
        if(data.isEmpty()){
            PrintDelay.print("\n~~ Opponent Data Empty ~~\n");
        }else{
            new AdminView().showAllOpponent(data);
        }
    }

    public void addOpponent(){
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
        if (option == 'y' || option =='Y') {
            if(DB.addOpponent(nama, hp, damage, level)){
                PrintDelay.print("Opponent successfully added !!!\n");
            }else{
                PrintDelay.print("Opponent failed to add !!!\n");
            }
        }else{
            PrintDelay.print("Opponent data is cancelled!!!\n");
        }
    }

    public void deleteOpponent(){
        PrintDelay.print(
            """
            Enter the name of the opponent you want to delete
            >> \0""");
        String name = scanner.nextLine().trim();
        if(DB.cariOpponent(name)){
            if(DB.deleteOpponent(name)){
                PrintDelay.print("Opponent successfully deleted !!!\n");
            }else{
                PrintDelay.print("Opponent failed to delete !!!\n");
            }
        }else{
            PrintDelay.print("Opponent not found !!!\n");
        }
    }
    
}
