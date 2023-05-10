package classes;

import attacktype.SpellType;
import game.Character;

//MAGE CLASS
public class Mage extends Character{
	
	public Mage(String name, int level, int HP) {
		super(name, level, HP);
	}

	@Override
	public void performAction(Character target, int attackChoice) {
		SpellType spellType = SpellType.values()[attackChoice];
		int damage = spellType.getDamage();
		target.takeDamage(damage);
		System.out.print("\n" + name + " casts " + spellType + " and deals " + damage + "dmg ");
	}
	
	@Override
	public int getAttackDamage(int attackChoice) {
		return SpellType.values()[attackChoice].getDamage();
	}
	
	@Override
	public String getAttackName(int attackChoice) {
		return SpellType.values()[attackChoice].toString();
	}
	
	@Override
	public void printAttackOptions() {
		for(int i = 0; i < SpellType.values().length; i++) {
			System.out.println((i + 1) + ". " + SpellType.values()[i]);
		}
	}
	
	@Override
	public int getAttackOptionCount() {
		return SpellType.values().length;
	}

}