package com.main;

import com.PrintDelay;

class Armor{
    private int defencePower;
    private String name;

    Armor(String name, int  defencePower){
        this.name = name;
        this.defencePower = defencePower;
    }

    public int getDefencePower(){
        return defencePower;
    }

    public void setDefencePower(int defencePower){
        this.defencePower = defencePower;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    void display(){
        PrintDelay.print("Armor: " + getName() + ", Def: " + getDefencePower() + "\n");
    }
}
