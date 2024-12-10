package com.view;

import java.util.List;


public class AdminView {
    public void adminDisplay(){
        PrintDelay.print(
            """
                \n**==== Dashboard Admin ====**
                1. View Account
                2. Delete Account
                3. Edit Opponent
                4. Edit Weapon
                5. Edit Armor
                6. Exit
                Choose your option[1/2/3]: \0""");
}

    @Deprecated
    public void adminViewAccount(){
        PrintDelay.print(
                """
                \n*==== View Account ====*
                1. Show All Account
                2. Show Info Account
                3. Back
                Choose your option[1/2/3]: \0""");
    }

    public void editOpponent(){
        PrintDelay.print(
            """
            \n*==== Edit Opponent ====*
            1. Show All Opponent
            2. Add Opponent
            3. Delete Opponent
            4. Back
            Choose your option[1/2/3]: \0""");
    }

    public void editWeapon(){
        PrintDelay.print(
        """
            \n*==== Edit Opponent ====*
            1. Show All Weapon
            2. Add Weapon
            3. Delete Weapon
            4. Back
            Choose your option[1/2/3]: \0""");
    }

    
    public void editArmor(){
        PrintDelay.print(
        """
            \n*==== Edit Opponent ====*
            1. Show All Armor
            2. Add Armor
            3. Delete Armor
            4. Back
            Choose your option[1/2/3]: \0""");
    }

    public void showAllAccount(List<String[]> a){
        int number = 1;
        PrintDelay.print("\n ~~ Registered Account ~~\n");
        for(String [] row : a){
            PrintDelay.print(number+ ". " + row[0] + " \n");
            number++;
        }
    }

    public void deleteAccount(){
        PrintDelay.print("Enter the email you want to delete: \0");
    }

    public void showAllOpponent(List<String[]> a){
        int number = 1;
        PrintDelay.print("\n ~~ Opponent ~~\n");
        for(String [] row : a){
            PrintDelay.print(number+ ". " + row[0] + "\t HP:" + row[1] + "\t Damage: " +  row[2] + "\t Level: " +  row[3] + " \n");
            number++;
        }
    }

    public void addOpponent(String name, int hp, int damage, int level){
        PrintDelay.print("-- Opponent Data --\n");
        PrintDelay.print("Opponent Name: " + name + "\n");
        PrintDelay.print("Opponent hp: " + hp + "\n");
        PrintDelay.print("Opponent damage: " + damage + "\n");
        PrintDelay.print("Opponent level: " + level + "\n");
        PrintDelay.print("Do you want to add this data? (y/n) : \0");
    }
}
