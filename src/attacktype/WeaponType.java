package attacktype;

public enum WeaponType {
	SWORD("Sword", 12),
	AXE("Axe", 18),
	HAMMER("Hammer", 15);

	private String name;
	private int damage;
	
	WeaponType(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}
	
	@Override
	public String toString() {
		return name;
	}
	

}
