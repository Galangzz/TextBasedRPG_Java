package com.controller;

import com.model.Armor;
import com.model.Monster;
import com.model.Player;
import com.model.Weapon;
import com.view.GameView;
import com.view.PrintDelay;
import java.util.Scanner;

public class GameController{
    private Player player;
    private Monster monster;
    private Armor armor;
    private Weapon weapon;
    private final GameView view;
    private final Scanner scanner;

    public GameController(){
        view = new GameView();
        scanner = new Scanner(System.in);
    }  

    public void showMenu() {
        while (true) {
            view.showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1 -> createCharacter();
                case 2 -> {
                    if (player == null) {
                        PrintDelay.print("Please create a character first!");
                    } else {
                        start();
                    }
                }
                case 3 -> {
                    PrintDelay.print("Thank you for playing!");
                    PrintDelay.print("\n");
                    return;
                }
                default -> PrintDelay.print("Invalid choice. Try again.");
            }
        }
    }

    public void createCharacter() {
        view.displayCreation();
        String username = scanner.nextLine();
        player = new Player(username);

        choseWeapon();
        choseArmor();

        view.displayCreationSuccess(player);
    }

    public void start(){
        view.displayBattle(player, monster);
        monster = new Monster("Goblin");
        battle();
    }

    private void battle(){
        while (player.isAlive() && monster.isAlive()){
            player.displayStatus();
            monster.displayStatus();
            view.showBattleOptions();
            int choice = scanner.nextInt();
            switch(choice){
                case 1 -> player.attack(monster);
                case 2 -> player.heal();
                case 3 -> {
                    PrintDelay.print("You run from the monster!\n");
                    PrintDelay.print("The monster is chasing you!!\n");
                }
                default -> {
                    PrintDelay.print("Invalid choice!\n");
                    continue;
                }
            }
            if(monster.isAlive()){
                monster.attack(player);
            }
        }

        if(player.isAlive()){
            view.showVictory(player);
        }else{
            view.showDefeat(player);
        }
    }

    private void choseWeapon(){
        Weapon weapon1 = new Weapon("Sword", 20);
        Weapon weapon2 = new Weapon("Spear", 20);
        Weapon weapon3 = new Weapon("Sickle", 20);
        view.displayWeaponChoices();
        int choiceWeapon = scanner.nextInt();
        switch(choiceWeapon){
            case 1 -> {
                weapon = weapon1;
                player.equipWeapon(weapon);
            }
            case 2 -> {
                weapon = weapon2;
                player.equipWeapon(weapon);
            }
            case 3 -> {
                weapon = weapon3;
                player.equipWeapon(weapon);
            }
            default -> {
                PrintDelay.print("Invalid choice");
                choseWeapon();
            }
        }
    }

    private void choseArmor(){
        Armor armor1 = new Armor("Steel Armor", 10);
        Armor armor2 = new Armor("Iron Armor", 10);
        view.displayArmorChoices();
        int choiceArmor = scanner.nextInt();
        switch(choiceArmor){
            case 1 -> {
                armor = armor1;
                player.equipArmor(armor);
            }
            case 2 -> {
                armor = armor2;
                player.equipArmor(armor);
            }
            default -> {
                PrintDelay.print("Invalid choice");
                choseArmor();
            }
        }
    }
}