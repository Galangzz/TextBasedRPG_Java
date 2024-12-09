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
                4. Exit
                Choose your option[1/2/3]: \0""");
}

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
            2. Update Opponent
            3. Delete Opponent
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

}
