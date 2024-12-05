package com.main;

import com.PrintDelay;

class Monster extends Character{
    public Monster(String name){
        super(name, 90, 20);
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
