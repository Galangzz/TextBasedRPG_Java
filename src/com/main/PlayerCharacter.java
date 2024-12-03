package com.main;
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
        System.out.println(getName() + " heal him self. ");
        setHealth(getHealth() + getHealAmount());
        if(getHealth() > 100){
            setHealth(100);
        }
        System.out.println("Current Healt: " + getHealth() + "\n");
    }

    void equipWeapon(Weapon weapon){
        this.weapon = weapon;
        this.setAttackPower(weapon.getDamage() + 10);
    }

    void equipArmor(Armor armor){
        this.armor = armor;
    }

    void display(){
        System.out.println("\n+++++++++++++++++++++++++++");
        System.out.println("Name: " + getName());
        if(getHealth() <= 0){
            System.out.println("(Player dead!!!)");
        }else{
            System.out.println("HP: " + getHealth());
            weapon.display();
            armor.display();
            System.out.println("Total attack: " + getAttackPower());
        }
        System.out.println("+++++++++++++++++++++++++++");
    }

    @Override
    public void attack(Character opponent){
        System.out.println(getName() + " performs a powerful strike on " + opponent.getName());
        super.attack(opponent);
    }

    @Override
    public void takeDamage(int damage){
        if(armor != null){
            int reduceDamage = damage - armor.getDefencePower();
            if(reduceDamage < 0){
                reduceDamage = 0;
            }
            System.out.println(getName() + "'s armor reduces the damage " + armor.getDefencePower());
            super.takeDamage(reduceDamage);
        }else{
            super.takeDamage(damage);
        }
    }
}
