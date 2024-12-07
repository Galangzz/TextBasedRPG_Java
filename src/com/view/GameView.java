package com.view;

import com.model.Monster;
import com.model.Player;

public class GameView {

    public void showMenu() {
        PrintDelay.print("""
            \n=== MENU ===
        1. Created Character
        2. Start Game
        3. Exit
        >> """);
    }

    public void displayCreation() {
        PrintDelay.print("""
        \n=== CREATED CHARACTER ===
        Enter Username:  """);
    }

    public void displayWeaponChoices() {
        PrintDelay.print("""
            \nPlease Choose a weapon!
            1. Sword
            2. Spear
            3. Sickle
            >> """);
    }

    public void displayArmorChoices() {
        PrintDelay.print("""
            \nPlease choose an Armor!
            1. Steel Armor
            2. Iron Armor
            >>  """);
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
            >>  """);
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
