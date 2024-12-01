class Character{
    private String name;
    private int health;
    private int attackPower;

    public Character(String name, int health, int attackPower){
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getAttackPower(){
        return attackPower;
    }

    public void setAttackPower(int attackPower){
        this.attackPower = attackPower;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public void attack(Character opponent){
        System.out.println(getName() + " attack " + opponent.getName() + " with damage " + getAttackPower());
        opponent.takeDamage(getAttackPower());
    }

    public void takeDamage(int damage){
        setHealth(getHealth() - damage);
        System.out.println(getName() + " suffered damage " + damage + "\n");

        if(getHealth() <= 0){
            System.out.println(getName() + " is dead");
        }
    }

    public void displayStatus(){
        System.out.println(getName() + " - Health " + getHealth());
    }
}