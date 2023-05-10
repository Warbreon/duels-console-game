package classes;

import attacktype.WeaponType;
import game.Character;

//WARRIOR CLASS
public class Warrior extends Character{

	public Warrior(String name, int level, int HP) {
		super(name, level, HP);
	}
	
	@Override
	public void performAction(Character target, int attackChoice) {
		WeaponType weaponType = WeaponType.values()[attackChoice];
		int damage = weaponType.getDamage();
		target.takeDamage(damage);
		System.out.print("\n" + name + " attacks with " + weaponType + " and deals " + damage + "dmg ");
	}
	
	@Override
	public int getAttackDamage(int attackChoice) {
		return WeaponType.values()[attackChoice].getDamage();
	}
	
	@Override
	public String getAttackName(int attackChoice) {
		return WeaponType.values()[attackChoice].toString();
	}
	
	@Override
	public void printAttackOptions() {
		for(int i = 0; i < WeaponType.values().length; i++) {
			System.out.println((i + 1) + ". " + WeaponType.values()[i]);
		}
	}
	
	@Override
	public int getAttackOptionCount() {
		return WeaponType.values().length;
	}
	
}
