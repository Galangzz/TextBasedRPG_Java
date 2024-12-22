package com.model;
import com.view.PrintDelay;

public class Character{
    private String name;
    private int health;
    private int attackPower;
    private int level;


    public Character(String name, int health, int attackPower, int level) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
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

    public int getLevel(){
        return level;
    }   

    public void setLevel(int level){
        this.level = level;
    }


    public boolean isAlive(){
        return health > 0;
    }

    public void attack(Character opponent){
        PrintDelay.print(getName() + " attack " + opponent.getName() + " with damage " + getAttackPower() + "\n");
        opponent.takeDamage(getAttackPower());
    }

    public void takeDamage(int damage){
        setHealth(getHealth() - damage);
        PrintDelay.print(getName() + " suffered damage " + damage + "\n");

        if(getHealth() <= 0){
            PrintDelay.print(getName() + " is dead\n");
        }
    }

    public void displayStatus(){
        PrintDelay.print(getName() + " - Health " + getHealth() + "\n");
    }
}