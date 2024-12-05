package com.main;

import com.printDelay;

class Player extends Character{
    private int healAmount;
    Weapon weapon;
    Armor armor;
    
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
        printDelay.print(getName() + " heal him self. ");
        setHealth(getHealth() + getHealAmount());
        if(getHealth() > 100){
            setHealth(100);
        }
        printDelay.print("Current Healt: " + getHealth() + "\n");
    }

    void equipWeapon(Weapon weapon){
        this.weapon = weapon;
        this.setAttackPower(weapon.getDamage() + 10);
    }

    void equipArmor(Armor armor){
        this.armor = armor;
    }

    void display(){
        printDelay.print("\n+++++++++++++++++++++++++++\n");
        printDelay.print("Name: " + getName() + "\n");
        if(getHealth() <= 0){
            printDelay.print("(Player dead!!!)\n");
        }else{
            printDelay.print("HP: " + getHealth() + "\n");
            weapon.display();
            armor.display();
            printDelay.print("Total attack: " + getAttackPower() + "\n");
        }
        printDelay.print("+++++++++++++++++++++++++++");
    }

    @Override
    public void attack(Character opponent){
        printDelay.print(getName() + " performs a powerful strike on " + opponent.getName() + "\n");
        super.attack(opponent);
    }

    @Override
    public void takeDamage(int damage){
        if(armor != null){
            int reduceDamage = damage - armor.getDefencePower();
            if(reduceDamage < 0){
                reduceDamage = 0;
            }
            printDelay.print(getName() + "'s armor reduces the damage " + armor.getDefencePower() + "\n");
            super.takeDamage(reduceDamage);
        }else{
            super.takeDamage(damage);
        }
    }
}
