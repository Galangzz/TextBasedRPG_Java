package com.main;
import com.printDelay;

class Character{
    private String name;
    private int health;
    private int attackPower;

    public Character(String name, int health, int attackPower){
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getAttackPower(){
        return attackPower;
    }

    public void setAttackPower(int attackPower){
        this.attackPower = attackPower;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public void attack(Character opponent){
        printDelay.print(getName() + " attack " + opponent.getName() + " with damage " + getAttackPower() + "\n");
        opponent.takeDamage(getAttackPower());
    }

    public void takeDamage(int damage){
        setHealth(getHealth() - damage);
        printDelay.print(getName() + " suffered damage " + damage + "\n");

        if(getHealth() <= 0){
            printDelay.print(getName() + " is dead\n");
        }
    }

    public void displayStatus(){
        printDelay.print(getName() + " - Health " + getHealth() + "\n");
    }
}