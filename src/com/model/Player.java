package com.model;

import com.view.PrintDelay;

public class Player extends Character{

    private int healAmount;
    private String role;
    private WeaponEquipment weapon;
    private ArmorEquipment armor;
    
    public Player(String name, String weapon, int damage_weapon, String armor, int defence_armor, int health, int damage, int defence, int heal_amount, String role, int level){
        super(name, health, damage, level);
        this.healAmount = heal_amount;
        this.role = role;
        this.weapon = new WeaponEquipment(weapon, damage_weapon);
        this.armor = new ArmorEquipment(armor, defence_armor);
    }

    public void setHealAmount(int healAmount){
        this.healAmount = healAmount;
    }

    public int getHealAmount(){
        return healAmount;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public void heal(){
        PrintDelay.print(getName() + " heal him self. ");
        setHealth(getHealth() + getHealAmount());
        if(getHealth() > getMaxHealth()){
            setHealth(getMaxHealth());
        }
        PrintDelay.print("Current Healt: " + getHealth() + "\n");
    }

    public void equipWeapon(WeaponEquipment weapon){
        this.weapon = weapon;
        this.setAttackPower(weapon.getValue() + 10);
    }

    public void equipArmor(ArmorEquipment armor){
        this.armor = armor;
    }


    public void upWeaponDamage(int damage){
        this.weapon.setValue(damage);
    }

    public int getWeaponDamage(){
        return weapon.getValue();
    }

    public void upArmorDefence(int defence){
        this.armor.setValue(defence);
    }

    public int getArmorDefence(){
        return armor.getValue();
    }

    public void display(){
        PrintDelay.print("\n+++++++++++++++++++++++++++\n");
        PrintDelay.print("Name: " + getName() + "\n");
        if(getHealth() <= 0){
            PrintDelay.print("(Player dead!!!)\n");
        }else{
            PrintDelay.print("Level: " + getLevel() + "\n");
            PrintDelay.print("HP: " + getHealth() + "\n");
            weapon.showStatistik();
            armor.showStatistik();
            PrintDelay.print("Total attack: " + getAttackPower() + "\n");
        }
        PrintDelay.print("+++++++++++++++++++++++++++");
        PrintDelay.print("\n");
    }

    @Override
    public void attack(Character opponent){
        PrintDelay.print(getName() + " performs a powerful strike on " + opponent.getName() + "\n");
        super.attack(opponent);
    }

    @Override
    public void takeDamage(int damage){
        if(armor != null){
            int reduceDamage = damage - armor.getValue();
            if(reduceDamage < 0){
                reduceDamage = 0;
            }
            PrintDelay.print(getName() + "'s armor reduces the damage " + armor.getValue() + "\n");
            super.takeDamage(reduceDamage);
        }else{
            super.takeDamage(damage);
        }
    }
}
