package nico.game.elements;

import nico.game.utils.Direction;

public class Entity {

	protected int posX;
	protected int posY;
	
	protected float health;
	protected int maxHealth;
	
	protected int strenght;
	protected int defence;
	
	
	/**Creates a moving tile object
	 * @param posX - Position on the x coordinate
	 * @param posY - Position on the y coordinate
	 * @param health - Hp and MaxHp
	 * @param strenght - Attack base value
	 * @param defence - Defence base value*/
	protected Entity(int posX, int posY, int health, int strenght, int defence) {
		this.setPos(posX, posY);
		this.health=health; this.maxHealth=health;
		this.strenght=strenght; this.defence=defence;
	}
	
	/**Setter Method
	 * @param posX - Position on the x coordinate
	 * @param posY - Position on the y coordinate*/
	public void setPos(int posX, int posY) {
		this.posX=posX; this.posY=posY;
	}
	
	/**Getter Method*/
	public int getX() {return posX;}
	
	/**Getter Method*/
	public int getY() {return posY;}
	
	/**Getter Method*/
	public float getHP() {return health;}
	
	/**Getter Method*/
	public int getMaxHP() {return maxHealth;}
	
	/**Getter Method*/
	public int getStr() {return strenght;}
	
	/**Getter Method*/
	public int getDef() {return defence;}
	
	/**Reduces health
	 * @param amount - The amount of health to take from the tile*/
	public void damage(float amount) {
		this.health-=amount;
	}
	
	/**Restores health
	 * @param amount - The amount of health to give to the tile*/
	public void heal(int amount) {
		this.health+=amount;
		if(health>maxHealth) health=maxHealth;
	}
	
	/**Changes the position of the tile
	 * @param dir - Determines the new position of the tile*/
	protected void move(Direction dir) {
		switch(dir) {
		case FOWARD:
			this.posY--; break;
		case LEFT:
			this.posX--; break;
		case BACKWARDS:
			this.posY++; break;
		case RIGHT:
			this.posX++; break;
		}
	}
}
