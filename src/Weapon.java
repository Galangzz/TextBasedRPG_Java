class Weapon{
    private String name;
    private int damage;

    Weapon(String name, int damage){
        this.name = name;
        this.damage = damage;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getDamage(){
        return damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    void display(){
        System.out.println("Weapon: " + getName() + ", Damage: " + getDamage());
    }

}
