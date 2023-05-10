package classes;

import attacktype.BladeType;
import game.Character;

//HUNTER CLASS
public class Hunter extends Character{
	
	public Hunter(String name, int level, int HP) {
		super(name, level, HP);
	}

	@Override
	public void performAction(Character target, int attackChoice) {
		BladeType bladeType = BladeType.values()[attackChoice];
		int damage = bladeType.getDamage();
		target.takeDamage(damage);
		System.out.print("\n" + name + " attacks with " + bladeType + " and deals " + damage + "dmg ");
	}
	
	@Override
	public int getAttackDamage(int attackChoice) {
		return BladeType.values()[attackChoice].getDamage();
	}
	
	@Override
	public String getAttackName(int attackChoice) {
		return BladeType.values()[attackChoice].toString();
	}
	
	@Override
	public void printAttackOptions() {
		for(int i = 0; i < BladeType.values().length; i++) {
			System.out.println((i + 1) + ". " + BladeType.values()[i]);
		}
	}
	
	@Override
	public int getAttackOptionCount() {
		return BladeType.values().length;
	}
	
}
