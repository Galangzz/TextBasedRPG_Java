package com.model;

import com.view.PrintDelay;

public class WeaponEquipment implements Equipment{
    private String name;
    private int damage;

    public WeaponEquipment(String name, int damage){
        this.name = name;
        this.damage = damage;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public int getValue(){
        return damage;
    }

    @Override
    public void setValue(int damage){
        this.damage = damage;
    }

    @Override
    public void showStatistik(){
        PrintDelay.print("Weapon: " + getName() + ", Damage: " + getValue() + "\n");
    }

}
