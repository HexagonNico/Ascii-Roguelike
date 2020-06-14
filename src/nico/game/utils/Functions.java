package nico.game.utils;

import java.util.Random;

import nico.game.Reference;
import nico.game.elements.Armor;
import nico.game.elements.Floor;
import nico.game.elements.Monster;
import nico.game.elements.Weapon;

public class Functions {

	/**Initializes all moving tiles in the floor like player etc.*/
	public static void initMovingTiles() {
		
		Reference.monsters.clear();
		
		for(int y=0;y<Reference.currentFloor.getHeight()-1;y++) {
			for(int x=0;x<Reference.currentFloor.getWidth()-1;x++) {
				switch(Reference.currentFloor.getTile(x, y)) {
				case PLAYER:
					Reference.player.setPos(x, y);
					break;
				case MONSTER:
					Reference.monsters.add(new Monster("Monster", x, y, 10, 2, 2));
				default:
					break;
				}
			}
		}
	}
	
	/**Returns a random number between 1 and n
	 * @param n - The upper bound (inclusive)
	 * @return a random number between 1 and n*/
	public static int getRandomNumber(int n) {
		Random rand = new Random();
		return rand.nextInt(n)+1;
	}
	
	/**Checks if player can move and calls Reference.player.move(Direction) 
	 * @param direction - The direction the player wants to move*/
	public static void handlePlayerMovment(Direction direction) {
		//Gets the tile the player is trying to move on
		Tile tile = null;
		switch(direction) {
		case FOWARD:
			tile = Reference.currentFloor.getTile(Reference.player.getX(), Reference.player.getY()-1);break;
		case LEFT:
			tile = Reference.currentFloor.getTile(Reference.player.getX()-1, Reference.player.getY()); break;
		case BACKWARDS:
			tile = Reference.currentFloor.getTile(Reference.player.getX(), Reference.player.getY()+1); break;
		case RIGHT:
			tile = Reference.currentFloor.getTile(Reference.player.getX()+1, Reference.player.getY()); break;
		}
		
		//Handles the player movement
		switch(tile){
		case NOTHING:
			Reference.player.move(direction);
			message = " ";
			message2 = " ";
			message3 = " ";
			break; //Move the player if it is in front of one of these tiles
		case WALL:
			message = "You ran into a wall!";
			message2 = " ";
			message3 = " ";
			break;
		case STAIRS:
			Reference.player.move(direction);
			Reference.currentFloor = new Floor(Functions.getRandomNumber(Reference.floorCount));
			message = "You went into a new floor!";
			message2 = " ";
			message3 = " ";
			floorsCleared++;
			Functions.initMovingTiles();
			break; //Randomly change floor
		case TRAP:
			Reference.player.move(direction);
			Reference.player.damage(Functions.getRandomNumber(2));
			message = "You ran into a trap and you took damage!";
			message2 = " ";
			message3 = " ";
			break; //Damage the player of 1 or 2 points
		case HP_POTION:
			message = "You found an health potion! Do you want to drink it?";
			message2 = "   [Y] Yes     [N] No";
			message3 = " ";
			decision = PlayerDecision.DRINK_HP_POTION;
			break; //Ask the player if it want to drink the potion
		case GOLD:
			Reference.player.move(direction);
			Reference.player.addGold(Functions.getRandomNumber(3)+2);
			message = "You picked up gold!";
			message2 = " ";
			message3 = " ";
			break; //Add between 3 and 5 gold
		case TREASURE:
			message = "You found a treasure chest! Do you want to open it for 10 gold?";
			message2 = "   [Y] Yes     [N] No";
			message3 = " ";
			decision = PlayerDecision.OPEN_CHEST;
			break; //Ask to open chest
		case KEY:
			Reference.player.move(direction);
			Reference.player.addKey();
			message = "You picked up a small key! What will its use be?";
			message2 = " ";
			message3 = " ";
			break; //Adds a key
		case DOOR:
			message = "This door is loked... Do you want to use a key to open it?";
			message2 = "   [Y] Yes     [N] No";
			message3 = " ";
			decision = PlayerDecision.OPEN_DOOR;
			break; //Ask to open door
		case MONSTER:
			message = "You encountered a monster!";
			message2 = " ";
			message3 = " ";
			Functions.monsterEncounter(direction);
			break; //Handles encounters with monsters
		default:
			message = "Something strange happened...";
			message2 = " ";
			message3 = " ";
			break; //If something glitches out
		}
	}
	
	/**Called when the player has to make a decision
	 * @param yn - True for yes, false for no*/
	public static void makeDecision(boolean yn) {
		if(decision == PlayerDecision.NONE) {
			return; //Nothing
		}
		else if(decision == PlayerDecision.DRINK_HP_POTION && yn == true) {
			Reference.player.heal(Functions.getRandomNumber(5)+3);
			message = "You drank the potion and recovered health!";
			message2 = " ";
			message3 = " ";
			Reference.player.move(); //Drink potion
		}
		else if(decision == PlayerDecision.DRINK_HP_POTION && yn == false) {
			message = "I don't need it right now...";
			message2 = " ";
			message3 = " "; //Doesn't drink potion
		}
		else if(decision == PlayerDecision.OPEN_CHEST && yn == true) {
			if(Reference.player.getGold()>=10) {
				Reference.player.takeGold(10);
				message = "You opened the chest!";
				message2 = " ";
				message3 = " "; //Open chest
				
				//Chose weapon or armor
				if(Functions.getRandomNumber(2) == 1) {
					Reference.player.equipWeapon(Weapon.getRandomWeapon());
					message2 = Reference.player.getWeapon().getName()+" equipped!";
				}
				else {
					Reference.player.equipArmor(Armor.getRandomArmor());
					message2 = Reference.player.getArmor().getName()+" equipped!";
				}
				
				Reference.player.move();
				
			}
			else {
				message = "You don't have enough gold...";
				message2 = " ";
				message3 = " ";
			}
		}
		else if(decision == PlayerDecision.OPEN_CHEST && yn == false) {
			message = "I don't want to open it...";
			message2 = " ";
			message3 = " "; //Doesn't open chest
		}
		else if(decision == PlayerDecision.OPEN_DOOR && yn == true) {
			if(Reference.player.getKeys()>0) {
				Reference.player.takeKey();
				Reference.player.move();
				message = "You opened the door!";
				message2 = " ";
				message3 = " "; //Open door
			}
			else {
				message = "You don't have a key...";
				message2 = " ";
				message3 = " ";
			}
		}
		else if(decision == PlayerDecision.OPEN_DOOR && yn == false) {
			message = "Maybe it's not a good idea... Who wonders what's behind.";
			message2 = " ";
			message3 = " "; //Doesn't open door
		}
		
		decision = PlayerDecision.NONE;
	}
	
	/**Moves all the monsters on the floor*/
	public static void moveMonsters() {
		for(int i=0;i<Reference.monsters.size();i++) {
			Reference.monsters.get(i).moveRandom();
		}
	}
	
	/**Makes a monster attack the player and the player attack the monster
	 * @param direction - The position of the monster relative to the player*/
	public static void monsterEncounter(Direction direction) {
		int monsterX=0, monsterY=0;
		
		switch(direction) {
		case FOWARD:
			monsterX = Reference.player.getX(); monsterY = Reference.player.getY()-1; break;
		case LEFT:
			monsterX = Reference.player.getX()-1; monsterY = Reference.player.getY(); break;
		case BACKWARDS:
			monsterX = Reference.player.getX(); monsterY = Reference.player.getY()+1; break;
		case RIGHT:
			monsterX = Reference.player.getX()+1; monsterY = Reference.player.getY(); break;
		}
		
		for(int i=0;i<Reference.monsters.size();i++) {
			if(Reference.monsters.get(i).getX() == monsterX && Reference.monsters.get(i).getY() == monsterY) {
				float playerAttack = Reference.player.getStr()-(Reference.monsters.get(i).getDef()/10)*Reference.player.getStr();
				float monsterAttck = Reference.monsters.get(i).getStr()-(Reference.player.getDef()/10)*Reference.monsters.get(i).getStr();
				Reference.monsters.get(i).damage(playerAttack);
				Reference.player.damage(monsterAttck);
				message2 = "You attacked the monster and left him with "+Reference.monsters.get(i).getHP()+" HP!";
				message3 = "The monster attacked you!";
			}
		}
	}
	
	/**Displays a death message if the player has 0 hp*/
	public static void checkPlayerDeath() {
		if(Reference.player.getHP()<=0) {
			message = "You died!";
			message2 = "You cleared "+floorsCleared+" floors!";
			message3 = "Press any button to continue";
			Reference.player.setDead();
		}
	}
	
	private static String message = " ";
	private static String message2 = " ";
	private static String message3 = " ";
	private static int floorsCleared = 0; 
	/**Returns the message to display on the screen*/
	public static String getMessage() {return message;}
	/**Returns the second message to display on the screen*/
	public static String getMessage2() {return message2;}
	/**Returns the third message to display on the screen*/
	public static String getMessage3() {return message3;}
	
	private static PlayerDecision decision = PlayerDecision.NONE;
	private enum PlayerDecision {
		NONE,
		DRINK_HP_POTION,
		OPEN_CHEST,
		OPEN_DOOR;
	}
}
