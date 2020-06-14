package nico.game.elements;

import nico.game.utils.Functions;

public class Weapon {

	public static Weapon hand = new Weapon(" ", 0);
	public static Weapon stick = new Weapon("Wooden Stick", 1);
	public static Weapon shortSword = new Weapon("Short Sword", 2);
	public static Weapon axe = new Weapon("Axe", 3);
	public static Weapon greatSword = new Weapon("Great Sword", 4);
	
	private String name;
	private int dmg;
	
	/**Creates a Weapon object
	 * @param name - The name of the weapon
	 * @param atk - Attack damage value*/
	public Weapon(String name, int dmg) {
		this.name=name;
		this.dmg=dmg;
	}
	
	/**Getter Method*/
	public int getDmg() {return dmg;}
	/**Getter Method*/
	public String getName() {return name;}
	
	/**Gets a random type of weapon
	 * @return a random Weapon.Type among all the available ones*/
	public static Weapon getRandomWeapon() {
		int choice = Functions.getRandomNumber(4);
		if(choice == 1) return stick;
		else if(choice == 2) return shortSword;
		else if(choice == 3) return axe;
		else if(choice == 4) return greatSword;
		else return hand;
	}
}
