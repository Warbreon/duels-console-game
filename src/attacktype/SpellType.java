package attacktype;

public enum SpellType {
	FIREBALL("Fireball", 20),
	ICE_SHARD("Ice Shard", 15),
	LIGHTNING_BOLT("Lightning Bolt", 25);
	
	private String name;
	private int damage;

	SpellType(String name, int damage) {
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
