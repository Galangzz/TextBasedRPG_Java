package com.model;

import com.view.PrintDelay;

public class Monster extends Character{
    private final int id;
    private final int level;

    public Monster(int id,String name, int hp, int damage, int level) {
        super(name, hp, damage, level);
        this.id = id;
        this.level = level;
    }


    @Override
    public void attack(Character opponent){
        PrintDelay.print(getName() + " viciously attacks " + opponent.getName() + "\n");
        super.attack(opponent);
    }

    @Override
    public void takeDamage(int damage){
        PrintDelay.print(getName() + " roars in pain!\n");
        super.takeDamage(damage);
    }
    
    @Override
    public int getLevel() {
        return level;
    }
    
    public int getId() {
        return id;
    }

    
}
