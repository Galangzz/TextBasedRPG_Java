package com.controller;

import com.auth.config.DB;
import com.model.Monster;
import com.model.Player;
import com.view.GameView;
import com.view.PrintDelay;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController {

    private static final Logger logger = Logger.getLogger(DB.class.getName());

    private Player player;
    private Monster monster;
    private final Random random;
    private final GameView view;
    private final Scanner scanner;

    public GameController() {
        view = new GameView();
        scanner = new Scanner(System.in);
        random = new Random();

    }

    public void showMenu(int a) {
        while (true) {
            view.showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 ->
                    createCharacter(a);
                case 2 ->
                    chooseCharacter(a);
                case 3 -> {
                    PrintDelay.print("Thank you for playing!");
                    PrintDelay.print("\n");
                    return;
                }
                default ->
                    PrintDelay.print("Invalid choice. Try again.\n");
            }
        }
    }

    public void createCharacter(int idAccount) {
        view.displayCreation();
        String username = scanner.nextLine();
        String role = chooseRole();
        int idWeapon = choseWeapon(role);
        int idArmor = choseArmor(role);
        int damage = 5;
        int defense = 0;
        int heal_amount = 0;

        try {
            switch (role) {
                case "fighter" -> {
                    damage += DB.cariWeaponByID(idWeapon);
                    defense += DB.cariArmorByID(idArmor);
                    heal_amount = 10;
                }
                case "mage" -> {
                    damage += DB.cariWeaponByID(idWeapon);
                    defense += DB.cariArmorByID(idArmor);
                    heal_amount = 30;
                }
                case "tank" -> {
                    damage += DB.cariWeaponByID(idWeapon);
                    defense += DB.cariArmorByID(idArmor);
                    heal_amount = 15;
                }
                default ->
                    throw new AssertionError();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error created character", idAccount);

        }

        if (DB.addCharacter(idAccount, idWeapon, idArmor, username, damage, defense, heal_amount, role)) {
            PrintDelay.print("Character Successfully Created");
        } else {
            PrintDelay.print("Failed to create character");
        }
    }

    //choose character
    public void chooseCharacter(int idAccount) {
        PrintDelay.print("Choose your character\n");
        List<String[]> stats = DB.showCharacterStatsCreated(idAccount);
        if (!stats.isEmpty()) {
            view.displayCharacterNameCreated(stats);
            int choice;
            do {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice > 0 && choice <= stats.size()) {
                    int i = choice - 1;
                    String[] statsCharacter = stats.get(i);
                    view.displayCharacterStatsCreated(statsCharacter);
                    PrintDelay.print("Are you sure you want to choose that character? (Y/N)\n");
                    PrintDelay.print(">> \0");
                    String confirm = scanner.nextLine().toUpperCase();
                    if (confirm.equals("Y")) {
                        String name = statsCharacter[0];
                        String weaponPlayer = statsCharacter[1];
                        int damage_weapon = Integer.parseInt(statsCharacter[2]);
                        String armorPlayer = statsCharacter[3];
                        int defence_armor = Integer.parseInt(statsCharacter[4]);
                        int health = Integer.parseInt(statsCharacter[5]);
                        int damage = Integer.parseInt(statsCharacter[6]);
                        int defense = Integer.parseInt(statsCharacter[7]);
                        int heal_amount = Integer.parseInt(statsCharacter[8]);
                        String role = statsCharacter[9];
                        int level = Integer.parseInt(statsCharacter[10]);

                        switch (level) {
                            case 1 -> {
                                damage_weapon += 0;
                                defence_armor += 0;
                            }
                            case 2 -> {
                                damage_weapon += 10;
                                defence_armor += 10;
                            }
                            case 3 -> {
                                damage_weapon += 20;
                                defence_armor += 20;
                            }
                            default ->
                                throw new AssertionError();
                        }

                        player = new Player(name, weaponPlayer, damage_weapon, armorPlayer, defence_armor, health, damage, defense, heal_amount, role, level);

                        if (statsCharacter[11] != null) {
                            int idMonster = Integer.parseInt(statsCharacter[11]);
                            if (!generateMonsterId(idMonster, level)) {
                                generateRandomMonster(level);
                            }
                        } else {
                            generateRandomMonster(level);
                        }
                        start();
                    } else {
                        PrintDelay.print("Choose another character\n");
                    }

                } else {
                    PrintDelay.print("Invalid choice");

                }
            } while (choice <= 0 && choice > stats.size());
        } else {
            PrintDelay.print("You don't have any character");
            PrintDelay.print("Please create a character first");
        }
    }

    public boolean generateMonsterId(int id, int level) {
        try {
            String[] monsterData = DB.getMonsterDataID(id);
            if (monsterData != null) {
                int idMonster = Integer.parseInt(monsterData[0]);
                String name = monsterData[1];
                int hp = Integer.parseInt(monsterData[2]);
                int damage = Integer.parseInt(monsterData[3]);
                int levelMonster = Integer.parseInt(monsterData[4]);
                if (levelMonster != level) {
                    return false;
                }
                monster = new Monster(idMonster, name, hp, damage, levelMonster);
                return true;
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Error generate monster By Id", id);
        }
        return false;
    }

    public boolean generateRandomMonster(int level) {
        try {
            List<String[]> monsterDataList = DB.getMonsterData(level);
            if (!monsterDataList.isEmpty()) {
                int randomIndex = random.nextInt(monsterDataList.size());
                String[] monsterData = monsterDataList.get(randomIndex);
                int id = Integer.parseInt(monsterData[0]);
                String name = monsterData[1];
                int hp = Integer.parseInt(monsterData[2]);
                int damage = Integer.parseInt(monsterData[3]);
                int levelMonster = Integer.parseInt(monsterData[4]);
                monster = new Monster(id, name, hp, damage, levelMonster);
                return true;
            }
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Error generate random monster", level);
        }
        return false;
    }

    public void start() {
        view.displayBattle(player, monster);
        battle();
    }

    private void battle() {
        while (player.isAlive() && monster.isAlive()) {
            player.displayStatus();
            monster.displayStatus();
            view.showBattleOptions();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 ->
                    player.attack(monster);
                case 2 ->
                    player.heal();
                case 3 -> {
                    PrintDelay.print("You run from the monster!\n");
                    PrintDelay.print("The monster is chasing you!!\n");
                }
                default -> {
                    PrintDelay.print("Invalid choice!\n");
                    continue;
                }
            }

            if (monster.isAlive()) {
                monster.attack(player);
            }
        }

        if (player.isAlive()) {
            levelUp();
        } else {
            view.showDefeat(player);
            restartGameDefeat();
        }
    }

    public void resumeStage() {
        view.resumeStage();
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                generateRandomMonster(player.getLevel());
                start();
            }
            case 2 -> {
                if (DB.updateChara(player.getName(), player.getLevel(), player.getAttackPower(), player.getArmorDefence(), monster.getId())) {
                    PrintDelay.print("\n");
                }else {
                    PrintDelay.print("Failed to update character\n");
                }
            }
            default -> {
                PrintDelay.print("Invalid choice!\n");
                resumeStage();
            }
        }
    }

    public void restartGameDefeat() {
        view.restartGameDefeat();
        int choice = scanner.nextInt();
        switch (choice) {
            //cek cek
            case 1 ->
                start();
            case 2 -> {

            }
            default -> {
                PrintDelay.print("Invalid choice!\n");
                restartGameDefeat();
            }
        }

    }

    private void levelUp() {
        player.setHealth(100);
        if (player.getLevel() < 3) {

            player.setLevel(player.getLevel() + 1);
            switch (player.getLevel()) {
                case 2 -> {

                    player.upWeaponDamage(player.getWeaponDamage() + 10);
                    player.upArmorDefence(player.getArmorDefence() + 10);
                    player.setAttackPower(player.getAttackPower() + 10);
                    view.showVictory(player);
                    resumeStage();
                }
                case 3 -> {
                    player.upWeaponDamage(player.getWeaponDamage() + 10);
                    player.upArmorDefence(player.getArmorDefence() + 10);
                    player.setAttackPower(player.getAttackPower() + 10);
                    view.showVictory(player);
                    resumeStage();
                }
                default ->
                    throw new AssertionError();
            }
            
        } else {
            player.display();
                if (DB.updateChara(player.getName(), player.getLevel(), player.getAttackPower(), player.getArmorDefence(), monster.getId())) {

                    PrintDelay.print("\nYou have reached level 3!\n");
                    PrintDelay.print("Congratulations!\n");
                    PrintDelay.print("You won the game!\n");
                    PrintDelay.print("Thank you for playing\nThis is the highest level for now.\n");
                }
        }
    }

    private String chooseRole() {
        int option_Role;
        String role = "fighter";
        do {

            view.displayRole();
            option_Role = scanner.nextInt();
            switch (option_Role) {
                case 1 ->
                    role = "fighter";
                case 2 ->
                    role = "mage";
                case 3 ->
                    role = "tank";
                default ->
                    PrintDelay.print("Invalid choice !!\n");
            }
        } while (option_Role != 1 && option_Role != 2 && option_Role != 3);
        return role;
    }

    private int choseWeapon(String used) {
        List<String[]> data = DB.showAllWeaponRole(used);
        if (!data.isEmpty()) {
            int jumlah = DB.countWeaponRole(used);
            int choiceWeapon;
            do {

                view.displayWeaponChoices(data);
                PrintDelay.print("Choose >> \0");
                choiceWeapon = scanner.nextInt();

                if (choiceWeapon > 0 && choiceWeapon <= jumlah) {
                    String[] weapon = data.get(choiceWeapon - 1);
                    int idWeapon = Integer.parseInt(weapon[1]);
                    return idWeapon;
                } else {
                    PrintDelay.print("Invalid Choice !!!\n");
                }
            } while (choiceWeapon <= 0 || choiceWeapon > jumlah);

        } else {
            PrintDelay.print("No weapon available for this role");
        }
        return 0;

    }

    private int choseArmor(String used) {
        List<String[]> data = DB.showAllArmorRole(used);
        if (!data.isEmpty()) {
            int jumlah = DB.countArmorRole(used);
            int choiceArmor;
            do {

                view.displayArmorChoices(data);
                PrintDelay.print("Choose >> \0");
                choiceArmor = scanner.nextInt();

                if (choiceArmor > 0 && choiceArmor <= jumlah) {
                    String[] armor = data.get(choiceArmor - 1);
                    int idArmor = Integer.parseInt(armor[1]);
                    return idArmor;
                } else {
                    PrintDelay.print("Invalid Choice !!!\n");
                }
            } while (choiceArmor <= 0 || choiceArmor > jumlah);

        } else {
            PrintDelay.print("No armor available for this role");
        }
        return 0;

    }

}
