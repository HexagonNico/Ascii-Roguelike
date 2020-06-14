package nico.game.elements;

import nico.game.utils.Direction;

public class Player extends Entity {
	
	private Direction facing;

	private int gold;
	private int keys;
	
	private Weapon equippedWeapon;
	private Armor equippedArmor;
	
	private boolean alive;
	
	/**Calls super(int, int) and prints a message to the console
	 * @param posX - Position on the x coordinate
	 * @param posY - Position on the y coordinate*/
	public Player(int posX, int posY) {
		super(posX, posY, 20, 1, 1);
		this.gold=0;
		this.equippedWeapon = Weapon.hand;
		this.equippedArmor = Armor.normal;
		this.alive=true;
		System.out.println("[Player]: Created player");
	}
	
	@Override
	public void move(Direction dir) {
		super.move(dir);
	}
	
	/**Moves the player in the direction it's facing*/
	public void move() {
		this.move(facing);
	}
	
	/**Setter Method*/
	public void setFacing(Direction dir) {this.facing=dir;}
	
	/**Getter Method*/
	public int getGold() {return gold;}
	
	/**Adds gold to the player
	 * @param amount - The amount of gold to add*/
	public void addGold(int amount) {gold+=amount;}
	
	/**Removes gold from the player
	 * @param amount - The amount of gold to add*/
	public void takeGold(int amount) {gold-=amount;}
	
	/**Getter Method*/
	public int getKeys() {return keys;}
	
	/**Adds 1 key to the player*/
	public void addKey() {keys++;}
	
	/**Removes 1 key from the player*/
	public void takeKey() {if(keys>0) keys--;}
	
	/**Setter Method - Gives a weapon to the player
	 * @param weapon - The weapon to equip*/
	public void equipWeapon(Weapon weapon) {
		this.equippedWeapon = weapon;
		this.strenght=1;
		this.strenght+=this.equippedWeapon.getDmg();
	}
	
	/**Setter Method - Gives an armor to the player
	 * @param armor - The armor to equip*/
	public void equipArmor(Armor armor) {
		this.equippedArmor = armor;
		this.defence=1;
		this.defence+=this.equippedArmor.getDef();
	}
	
	/**Getter Method*/
	public Weapon getWeapon() {return equippedWeapon;} 
	/**Getter Method*/
	public Armor getArmor() {return equippedArmor;}
	
	/**Getter Method
	 * @return true if the player is alive*/
	public boolean isAlive() {return alive;}
	/**Sets isAlive() to false*/
	public void setDead() {this.alive=false;}
}