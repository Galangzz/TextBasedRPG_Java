package com.main;
import com.printDelay;
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
            printDelay.print("""
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
                        printDelay.print("Please create a character first!");
                    } else {
                        start();
                    }
                }
                case 3 -> {
                    printDelay.print("Thank you for playing!");
                    return;
                }
                default -> printDelay.print("Invalid choice. Try again.");
            }
        }
    }

    public void createCharacter() {
        printDelay.print("""
        \n=== CREATED CHARACTER ===
        Enter Username:  """);
        String username = scanner.nextLine();
        player = new Player(username);

        choseWeapon();
        choseArmor();

        printDelay.print("\nCharacter successfully created:");
        player.display();
    }

    public void start(){
        printDelay.print("\n==== Welcome to Text-based RPG! ====\n");
        printDelay.print("Player: " + player.getName());
        printDelay.print("\n*** Monster appear! ***");
        printDelay.print("\nGoblin !!!");
        printDelay.print("\nDefeat the monster!\n");
        monster = new Monster("Goblin");
        
        battle();
    }

    private void battle(){
        while (player.isAlive() && monster.isAlive()){
            player.displayStatus();
            monster.displayStatus();

            printDelay.print("""
            \nWhat do you want to do?
            1. Attack
            2. Heal
            3. Run
            >>  """);
            int choice = scanner.nextInt();
            printDelay.print("");
            switch(choice){
                case 1 -> player.attack(monster);
                case 2 -> player.heal();
                case 3 -> {
                    printDelay.print("You run from the monster!\n");
                    printDelay.print("The monster is chasing you!!\n");
                }
                default -> {
                    printDelay.print("Invalid choice!\n");
                    continue;
                }
            }
            if(monster.isAlive()){
                monster.attack(player);
            }
        }

        if(player.isAlive()){
            printDelay.print("\nYou win!\n");
            player.display();
        }else{
            printDelay.print("\nYou lose!\n");
            player.display();
        }
    }

    private void choseWeapon(){
        Weapon weapon1 = new Weapon("Sword", 20);
        Weapon weapon2 = new Weapon("Spear", 20);
        Weapon weapon3 = new Weapon("Sickle", 20);
        printDelay.print("""
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
                printDelay.print("Invalid choice");
                choseWeapon();
            }
        }
    }

    private void choseArmor(){
        Armor armor1 = new Armor("Steel Armor", 10);
        Armor armor2 = new Armor("Iron Armor", 10);
        printDelay.print("""
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
                printDelay.print("Invalid choice");
                choseArmor();
            }
        }
    }
}
