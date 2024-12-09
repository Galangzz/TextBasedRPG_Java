package com.model;

import com.view.PrintDelay;

public class Player extends Character{
    private int healAmount;
    WeaponEquipment weapon;
    ArmorEquipment armor;
    
    public Player(String name){
        super(name, 100, 10);
        this.healAmount = 15;
    }

    public void setHealAmount(int healAmount){
        this.healAmount = healAmount;
    }

    public int getHealAmount(){
        return healAmount;
    }

    public void heal(){
        PrintDelay.print(getName() + " heal him self. ");
        setHealth(getHealth() + getHealAmount());
        if(getHealth() > 100){
            setHealth(100);
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

    public void display(){
        PrintDelay.print("\n+++++++++++++++++++++++++++\n");
        PrintDelay.print("Name: " + getName() + "\n");
        if(getHealth() <= 0){
            PrintDelay.print("(Player dead!!!)\n");
        }else{
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
