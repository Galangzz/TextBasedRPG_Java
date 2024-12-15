package com.model;

import com.view.PrintDelay;

public class ArmorEquipment implements Equipment {

    private int defencePower;
    private String name;

    public ArmorEquipment(String name, int defencePower) {
        this.name = name;
        this.defencePower = defencePower;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getValue() {
        return defencePower;
    }

    @Override
    public void setValue(int defencePower) {
        this.defencePower = defencePower;
    }

    @Override
    public void showStatistik() {
        PrintDelay.print("Armor: " + getName() + ", Def: " + getValue() + "\n");
    }
}
