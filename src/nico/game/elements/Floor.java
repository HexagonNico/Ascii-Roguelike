package nico.game.elements;


import java.util.ArrayList;

import nico.game.Reference;
import nico.game.utils.Tile;
import nico.game.utils.ResourceManager;

public class Floor {

	private ArrayList<ArrayList<Tile>> tiles;
	private boolean firstFloor;
	
	/**Creates a floor
	 * @param floorNumber - The ordinal number of this floor in the file*/
	public Floor(int floorNumber) {
		System.out.println("[Floor]: Creating floor"+floorNumber);
		
		tiles = new ArrayList<ArrayList<Tile>>();
		
		ArrayList<String> strs = ResourceManager.readFloorFile("src/nico/resources/floors/floor"+floorNumber+".txt");
		
		for(int i=0;i<strs.size()-1;i++) {
			char[] charray = strs.get(i).toCharArray();
			tiles.add(new ArrayList<Tile>());
			for(int j=0;j<charray.length;j++) {
				switch(charray[j]) {
				case '.':
					tiles.get(i).add(Tile.NOTHING); break;
				case '#':
					tiles.get(i).add(Tile.WALL); break;
				case 'A':
					tiles.get(i).add(Tile.PLAYER); break;
				case '^':
					tiles.get(i).add(Tile.STAIRS); break;
				case ',':
					tiles.get(i).add(Tile.TRAP); break;
				case 'p':
					tiles.get(i).add(Tile.HP_POTION); break;
				case 'G':
					tiles.get(i).add(Tile.GOLD); break;
				case 'T':
					tiles.get(i).add(Tile.TREASURE); break;
				case '!':
					tiles.get(i).add(Tile.KEY); break;
				case '/':
					tiles.get(i).add(Tile.DOOR); break;
				case 'M':
					tiles.get(i).add(Tile.MONSTER); break;
				}
			}
		}
		
		if(floorNumber == 0) firstFloor = true;
		else firstFloor = false;
	}
	
	/**Gets the size of the floor on the y coordinate*/
	public int getHeight() {
		return tiles.size();
	}
	
	/**Gets the size of the floor on the x coordinate*/
	public int getWidth() {
		return tiles.get(0).size();
	}
	
	/**Returns one tile of the floor
	 * @param x - The x coordinate of the tile
	 * @param y - The y coordinate of the tile*/
	public Tile getTile(int x, int y) {return tiles.get(y).get(x);}
	
	/**Returns one tile of the floor
	 * @param x - The x coordinate of the tile
	 * @param y - The y coordinate of the tile*/
	public char getTileChar(int x, int y) {return tiles.get(y).get(x).symbol();}
	
	/**Updates the position of the player*/
	public void updatePlayerPos() {
		//Deletes old pos
		for(int i=0;i<this.getHeight();i++) {
			for(int j=0;j<this.getWidth();j++) {
				if(tiles.get(i).get(j) == Tile.PLAYER)
					tiles.get(i).set(j, Tile.NOTHING);
			}
		}
		//Sets new pos
		tiles.get(Reference.player.getY()).set(Reference.player.getX(), Tile.PLAYER);
	}
	
	/**Updates the position of the monsters*/
	public void updateMonstersPos() {
		//Deletes old pos
		for(int i=0;i<this.getHeight();i++) {
			for(int j=0;j<this.getWidth();j++) {
				if(tiles.get(i).get(j) == Tile.MONSTER)
					tiles.get(i).set(j, Tile.NOTHING);
			}
		}
		//Sets new pos
		for(int i=0;i<Reference.monsters.size();i++) {
			if(Reference.monsters.get(i).getHP()<=0)
				Reference.monsters.remove(i);
			else
				tiles.get(Reference.monsters.get(i).getY()).set(Reference.monsters.get(i).getX(), Tile.MONSTER);
		}
			
	}
	
	/**Getter Method
	 * @return true if this is floor0 */
	public boolean isFirstFloor() {return firstFloor;}
}
