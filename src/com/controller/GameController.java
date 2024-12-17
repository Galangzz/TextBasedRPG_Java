package com.controller;

import com.auth.config.DB;
import com.model.ArmorEquipment;
import com.model.Monster;
import com.model.Player;
import com.model.WeaponEquipment;
import com.view.GameView;
import com.view.PrintDelay;
import java.util.List;
import java.util.Scanner;

public class GameController{
    private Player player;
    private Monster monster;
    private ArmorEquipment armor;
    private WeaponEquipment weapon;
    private final GameView view;
    private final Scanner scanner;

    public GameController(){
        view = new GameView();
        scanner = new Scanner(System.in);
    }  

    public void showMenu(int a) {
        while (true) {
            view.showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1 -> createCharacter(a);
                case 2 -> {
                    if (player == null) {
                        PrintDelay.print("Please create a character first!\n");
                    } else {
                        start();
                    }
                }
                case 3 -> {
                    PrintDelay.print("Thank you for playing!");
                    PrintDelay.print("\n");
                    return;
                }
                default -> PrintDelay.print("Invalid choice. Try again.\n");
            }
        }
    }

    public void createCharacter(int idAccount) {
        view.displayCreation();
        String username = scanner.nextLine();
        String role = chooseRole();
        int idWeapon = choseWeapon(role);
        int idArmor = choseArmor(role);

        if(DB.addCharacter(idAccount, idWeapon, idArmor, username, role)){
            PrintDelay.print("Character Successfully Created");
        }else{
            PrintDelay.print("Failed to create character");
        }

        // view.displayCreationSuccess(player);
    }

    public void start(){
        view.displayBattle(player, monster);
        monster = new Monster("Goblin", 90, 20);
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

    private String chooseRole(){
        int option_Role;
        String role = "fighter";
        do { 
            
            view.displayRole();
            option_Role = scanner.nextInt();
            switch(option_Role){
                case 1 -> role = "fighter";
                case 2 -> role = "mage";
                case 3 -> role = "tank";
                default -> PrintDelay.print("Invalid choice !!\n");
            }
        } while (option_Role != 1 && option_Role != 2 && option_Role != 3); 
        return role;
    }

    private int choseWeapon(String used){
        List<String[]> data = DB.showAllWeaponRole(used);
        if(!data.isEmpty()){
            int jumlah = DB.countWeaponRole(used);
            int choiceWeapon;
            do{

                view.displayWeaponChoices(data);
                PrintDelay.print("Choose >> \0");
                choiceWeapon = scanner.nextInt();
                
                if(choiceWeapon > 0 && choiceWeapon <= jumlah){
                    String[] weapon = data.get(choiceWeapon - 1);
                    int idWeapon = Integer.parseInt(weapon[1]);
                    return idWeapon;
                }else{
                    PrintDelay.print("Invalid Choice !!!\n");
                }
            }while(choiceWeapon <= 0 || choiceWeapon > jumlah);
            
        }else{
            PrintDelay.print("No weapon available for this role");
        }
        return 0;
        
    }

    private int choseArmor(String used){
        List<String[]> data = DB.showAllArmorRole(used);
        if(!data.isEmpty()){
            int jumlah = DB.countArmorRole(used);
            int choiceArmor;
            do{

                view.displayArmorChoices(data);
                PrintDelay.print("Choose >> \0");
                choiceArmor = scanner.nextInt();
                
                if(choiceArmor > 0 && choiceArmor <= jumlah){
                    String[] armor = data.get(choiceArmor - 1);
                    int idArmor = Integer.parseInt(armor[1]);
                    return idArmor;
                }else{
                    PrintDelay.print("Invalid Choice !!!\n");
                }
            }while(choiceArmor <= 0 || choiceArmor > jumlah);
            
        }else{
            PrintDelay.print("No armor available for this role");
        }
        return 0;
        
    }


}
