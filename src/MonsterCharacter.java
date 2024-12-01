class Monster extends Character{
    public Monster(String name){
        super(name, 90, 20);
    }

    @Override
    public void attack(Character opponent){
        System.out.println(getName() + " viciously attacks " + opponent.getName());
        super.attack(opponent);
    }

    @Override
    public void takeDamage(int damage){
        System.out.println(getName() + " roars in pain!");
        super.takeDamage(damage);
    }
}
