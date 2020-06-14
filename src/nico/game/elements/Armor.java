package nico.game.elements;

import nico.game.utils.Functions;

public class Armor {

	public static Armor normal = new Armor(" ", 0);
	public static Armor leather = new Armor("Leather Armor", 1);
	public static Armor bronze = new Armor("Bronze Armor", 2);
	public static Armor medieval = new Armor("Medieval Armor", 3);
	public static Armor misterious = new Armor("Misterious Armor", 4);
	
	private String name;
	private int def;
	
	/**Creates an Armor object
	 * @param name - The name of the armor
	 * @param def - Protection value*/
	public Armor(String name, int def) {
		this.name=name; this.def=def;
	}
	
	/**Getter Method*/
	public int getDef() {return def;}
	/**Getter Method*/
	public String getName() {return name;}
	
	/**Gets a random type of weapon
	 * @return a random Weapon.Type among all the available ones*/
	public static Armor getRandomArmor() {
		int choice = Functions.getRandomNumber(4);
		if(choice == 1) return leather;
		else if(choice == 2) return bronze;
		else if(choice == 3) return medieval;
		else if(choice == 4) return misterious;
		else return normal;
	}
}
