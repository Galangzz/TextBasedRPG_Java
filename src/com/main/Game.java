package com.main;
import com.PrintDelay;
import java.util.Scanner;

public class Game{
    private Player player;
    private Monster monster;
    private Armor armor;
    private Weapon weapon;
    private final Scanner scanner;

    public Game(){
        scanner = new Scanner(System.in);
    }  

    public void showMenu() {
        while (true) {
            PrintDelay.print("""
                \n=== MENU ===
            1. Created Character
            2. Start Game
            3. Exit
            >> """);
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
                    return;
                }
                default -> PrintDelay.print("Invalid choice. Try again.");
            }
        }
    }

    public void createCharacter() {
        PrintDelay.print("""
        \n=== CREATED CHARACTER ===
        Enter Username:  """);
        String username = scanner.nextLine();
        player = new Player(username);

        choseWeapon();
        choseArmor();

        PrintDelay.print("\nCharacter successfully created:");
        player.display();
    }

    public void start(){
        PrintDelay.print("\n==== Welcome to Text-based RPG! ====\n");
        PrintDelay.print("Player: " + player.getName());
        PrintDelay.print("\n*** Monster appear! ***");
        PrintDelay.print("\nGoblin !!!");
        PrintDelay.print("\nDefeat the monster!\n");
        monster = new Monster("Goblin");
        
        battle();
    }

    private void battle(){
        while (player.isAlive() && monster.isAlive()){
            player.displayStatus();
            monster.displayStatus();

            PrintDelay.print("""
            \nWhat do you want to do?
            1. Attack
            2. Heal
            3. Run
            >>  """);
            int choice = scanner.nextInt();
            PrintDelay.print("");
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
            PrintDelay.print("\nYou win!\n");
            player.display();
        }else{
            PrintDelay.print("\nYou lose!\n");
            player.display();
        }
    }

    private void choseWeapon(){
        Weapon weapon1 = new Weapon("Sword", 20);
        Weapon weapon2 = new Weapon("Spear", 20);
        Weapon weapon3 = new Weapon("Sickle", 20);
        PrintDelay.print("""
            \nPlease Choose a weapon !
            1. Sword
            2. Spear
            3. Sickle
            >> """);
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
        PrintDelay.print("""
            \nPlease chose a Armor!
            1. Steel Armor
            2. Iron Armor
            >>  """);
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
