package com.main;

import com.printDelay;

class Monster extends Character{
    public Monster(String name){
        super(name, 90, 20);
    }

    @Override
    public void attack(Character opponent){
        printDelay.print(getName() + " viciously attacks " + opponent.getName() + "\n");
        super.attack(opponent);
    }

    @Override
    public void takeDamage(int damage){
        printDelay.print(getName() + " roars in pain!\n");
        super.takeDamage(damage);
    }
}
