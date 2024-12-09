package com.model;

interface Equipment {

    // mengambil dan set name pada weapon atau armor
    String getName();
    void setName(String name);

    //mengambil dan set damage pada weapon atau defPower pada armor
    int getValue();
    void setValue(int value);

    //menampilkan statistik dari armor atau weapon(nama, damage atau def)
    public void showStatistik();

}
