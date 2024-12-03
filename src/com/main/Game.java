package com.main;
import java.util.Scanner;

class Game{
    private Player player;
    private Monster monster;
    private Armor armor;
    private Weapon weapon;
    private Scanner scanner;

    public Game(){
        scanner = new Scanner(System.in);
    }  

    public void start(){
        System.out.println("\n==== Welcome to Text-based RPG! ====\n");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        player = new Player(username);
        choseWeapon();
        choseArmor();
        player.display();
        System.out.println("\n*** Monster appear! ***");
        System.out.println("Goblin !!!");
        System.out.println("Defeat the monster!\n");
        monster = new Monster("Goblin");
        
        battle();
    }

    private void battle(){
        while (player.isAlive() && monster.isAlive()){
            player.displayStatus();
            monster.displayStatus();

            System.out.println("\nWhat do you want to do?");
            System.out.println("1. Attack");
            System.out.println("2. Heal");
            System.out.println("3. Run");
            System.out.print(">> ");
            int choice = scanner.nextInt();
            System.out.println("");
            switch(choice){
                case 1:
                    player.attack(monster);
                    break;
                case 2:
                    player.heal();
                    break;
                case 3:
                    System.out.println("You run from the monster!");
                    System.out.println("The monster is chasing you!!\n");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    continue;
            }
            if(monster.isAlive()){
                monster.attack(player);
            }
        }

        if(player.isAlive()){
            System.out.println("\nYou win!\n");
        }else{
            System.out.println("\nYou lose!\n");
        }
    }

    private void choseWeapon(){
        Weapon weapon1 = new Weapon("Sword", 20);
        Weapon weapon2 = new Weapon("Spear", 20);
        Weapon weapon3 = new Weapon("Sickle", 20);
        System.out.println("\nPlease Choose a weapon !");
        System.out.println("1. Sword");
        System.out.println("2. Spear");
        System.out.println("3. Sickle");
        System.out.print(">> ");
        int choiceWeapon = scanner.nextInt();
        switch(choiceWeapon){
            case 1:
                weapon = weapon1;
                player.equipWeapon(weapon);
                break;
            case 2:
                weapon = weapon2;
                player.equipWeapon(weapon);
                break;
            case 3:
                weapon = weapon3;
                player.equipWeapon(weapon);
                break;
            default:
                System.out.println("Invalid choice");
                choseWeapon();
        }
    }

    private void choseArmor(){
        Armor armor1 = new Armor("Steel Armor", 10);
        Armor armor2 = new Armor("Iron Armor", 10);
        System.out.println("\nPlease chose a Armor!");
        System.out.println("1. Steel Armor");
        System.out.println("2. Iron Armor");
        System.out.print(">> ");
        int choiceArmor = scanner.nextInt();
        switch(choiceArmor){
            case 1:
                armor = armor1;
                player.equipArmor(armor);
                break;
            case 2:
                armor = armor2;
                player.equipArmor(armor);
                break;
            default:
            System.out.println("Invalid choice");
            choseArmor();
        }
    }
}
