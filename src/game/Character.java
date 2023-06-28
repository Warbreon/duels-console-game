package game;

//CHARACTER CLASS
public abstract class Character {
	protected String name;
	protected int level;
	protected int HP;
	
	public Character(String name, int level, int HP) {
		super();
		this.name = name;
		this.level = level;
		this.HP = HP;
	}
	
	//abstrakt metodai
	public abstract void performAction(Character target, int attackChoice);
	
	public abstract int getAttackDamage(int attackChoice);
	
	public abstract String getAttackName(int attackChoice);
	
	public abstract void printAttackOptions();
	
	public abstract int getAttackOptionCount();
	
	public void takeDamage(int damage) {
		HP -= damage;
	}
	
	public boolean isAlive() {
		return HP > 0;
	}
	
	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}
	
	public int getHP() {
		return HP;
	}
}
