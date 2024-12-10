package com.model;

import com.view.PrintDelay;

public class Monster extends Character{


    public Monster(String name, int hp, int damage){
        super(name, hp, damage);
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
}
