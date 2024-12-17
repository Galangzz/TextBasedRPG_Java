package com.view;

import com.model.Monster;
import com.model.Player;
import java.util.List;

public class GameView {

    public void showMenu() {
        PrintDelay.print("""
            \n=== MENU ===
        1. Created Character
        2. Start Game
        3. Exit
        >> \0""");
    }

    public void displayCreation() {
        PrintDelay.print("""
        \n=== CREATED CHARACTER ===
        Enter Username: \0""");
    }

    public void displayRole(){
        PrintDelay.print("""
            \nPlease Select a Role!
            1. Fighter
            2. Mage
            3. Tank
            >> \0""");
    }

    public void displayWeaponChoices(List<String[]> data ) {
        int number = 1;
        PrintDelay.print("\n ~~ Weapon List ~~\n");
        for(String [] row : data){
            PrintDelay.print(number+ ". " + row[0] + " \n");
            number++;
        }
    }

    public void displayArmorChoices(List<String[]> data ) {
        int number = 1;
        PrintDelay.print("\n ~~ Armor List ~~\n");
        for(String [] row : data){
            PrintDelay.print(number+ ". " + row[0] + " \n");
            number++;
        }
    }

    public void displayArmorChoices() {
        PrintDelay.print("""
            \nPlease choose an Armor!
            1. Steel Armor
            2. Iron Armor
            >> \0""");
    }

    public void displayCreationSuccess(Player player) {
        PrintDelay.print("\nCharacter successfully created:");
        player.display();
    }

    public void displayBattle(Player player, Monster monster) {
        PrintDelay.print("\n==== Welcome to Text-based RPG! ====\n");
        PrintDelay.print("Player: " + player.getName());
        PrintDelay.print("\n*** Monster appear! ***");
        PrintDelay.print("\nGoblin !!!");
        PrintDelay.print("\nDefeat the monster!\n");
    }

    public void showBattleOptions() {
        PrintDelay.print("""
            \nWhat do you want to do?
            1. Attack
            2. Heal
            3. Run
            >> \0""");
    }

    public void showVictory(Player player) {
        PrintDelay.print("\nYou win!");
        player.display();
    }

    public void showDefeat(Player player) {
        PrintDelay.print("\nYou lose!");
        player.display();
    }
}
