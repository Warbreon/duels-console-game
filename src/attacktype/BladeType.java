package attacktype;

public enum BladeType {
	DAGGER("Dagger", 18),
	CLAWS("Claws", 20),
	FANGS("Fangs", 22);

	private String name;
	private int damage;
	
	BladeType(String name, int damage) {
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
