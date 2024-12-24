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

    public void displayCharacterNameCreated(List<String[]> data ) {
        int number = 1;
        PrintDelay.print("\n ~~ Character List ~~\n");
        for(String [] row : data){
            PrintDelay.print(number+ ". " + row[0] +" | (" + row[9] + ") | Level: " + row[10] + " \n");
            number++;
        }
        PrintDelay.print(">> \0");
    }

    public void displayCharacterStatsCreated(String[] characterStats) {
        PrintDelay.print("\n ~~ Character Stats ~~\n");
        PrintDelay.print("Name: " + characterStats[0] + "\n");
        PrintDelay.print("Weapon: " + characterStats[1] + "\n");
        PrintDelay.print("Weapon Damage: " + characterStats[2] + "\n");
        PrintDelay.print("Armor: " + characterStats[3] + "\n");
        PrintDelay.print("Armor Defense: " + characterStats[4] + "\n");
        PrintDelay.print("HP: " + characterStats[5] + "\n");
        PrintDelay.print("Character Damage: " + characterStats[6] + "\n");
        PrintDelay.print("Character Defense: " + characterStats[7] + "\n");
        PrintDelay.print("Heal Amount: " + characterStats[8] + "\n");
        PrintDelay.print("Role: " + characterStats[9] + "\n");
        PrintDelay.print("Level: " + characterStats[10] + "\n");
    }

    @Deprecated
    @SuppressWarnings("karatan bro")
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
        PrintDelay.print("* Stage: " + player.getLevel() + " *\n");
        PrintDelay.print("Player: " + player.getName());
        PrintDelay.print("\n*** Monster appear! ***");
        PrintDelay.print("\n" + monster.getName());
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
        PrintDelay.print("\nLevel up !!!\n");
        PrintDelay.print(
            """
            HP Restored
            Weapon damage increased
            Armor defense strengthened
            Player strength increased\n""");
        player.display();
    }

    public void showDefeat(Player player) {
        PrintDelay.print("\nYou lose!");
        player.display();
    }

    public void resumeStage() {
        PrintDelay.print("\nYou have defeated the monster and have reached the next stage.\n");
        PrintDelay.print("\nWhat do you want to do?");
        PrintDelay.print("\n1. Continue");
        PrintDelay.print("\n2. Exit");
        PrintDelay.print("\n>> \0");
    }
    public void restartGameDefeat() {
        // PrintDelay.print("\nGame Over!");
        PrintDelay.print("\nDo you want to play again?");
        PrintDelay.print("\n1. Yes");
        PrintDelay.print("\n2. No");
        PrintDelay.print("\n>> \0");
    }
}
