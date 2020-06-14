package nico.game.elements;

import nico.game.Reference;
import nico.game.utils.Direction;
import nico.game.utils.Functions;
import nico.game.utils.Tile;

public class Monster extends Entity {

	private String name;
	
	/**Creates a monster
	 * @param name - Name
	 * @param posX - Position on the x coordinate
	 * @param posY - Position on the y coordinate
	 * @param health - Hp and MaxHp
	 * @param strenght - Attack base value
	 * @param defence - Defence base value*/
	public Monster(String name, int posX, int posY, int health, int strenght, int defence) {
		super(posX, posY, health, strenght, defence);
		this.name=name;
		System.out.println("[Monster]: Creating monster");
	}
	
	/**Moves the monster in a random direction*/
	public void moveRandom() {
		switch(Functions.getRandomNumber(4)) {
		case 1:
			if(Reference.currentFloor.getTile(this.getX(), this.getY()-1) == Tile.NOTHING)
				super.move(Direction.FOWARD);
			else if(Reference.currentFloor.getTile(this.getX(), this.getY()-1) == Tile.PLAYER)
				Functions.monsterEncounter(Direction.FOWARD);
			break;
		case 2:
			if(Reference.currentFloor.getTile(this.getX()-1, this.getY()) == Tile.NOTHING)
				super.move(Direction.LEFT);
			else if(Reference.currentFloor.getTile(this.getX()-1, this.getY()) == Tile.PLAYER)
				Functions.monsterEncounter(Direction.LEFT);
			break;
		case 3:
			if(Reference.currentFloor.getTile(this.getX(), this.getY()+1) == Tile.NOTHING)
				super.move(Direction.BACKWARDS);
			else if(Reference.currentFloor.getTile(this.getX(), this.getY()+1) == Tile.PLAYER)
				Functions.monsterEncounter(Direction.BACKWARDS);
			break;
		case 4:
			if(Reference.currentFloor.getTile(this.getX()+1, this.getY()) == Tile.NOTHING)
				super.move(Direction.RIGHT);
			else if(Reference.currentFloor.getTile(this.getX()+1, this.getY()) == Tile.PLAYER)
				Functions.monsterEncounter(Direction.RIGHT);
			break;
		}
	}
	
	/**Getter Method*/
	public String getName() {return name;}

}
