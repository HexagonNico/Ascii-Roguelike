package nico.game;

import java.util.ArrayList;

import nico.game.elements.Floor;
import nico.game.elements.Monster;
import nico.game.elements.Player;

public class Reference {

	public static final int windowWidth = 1000;
	public static final int windowHeight = 600;
	
	public static final int floorCount = 5;
	
	public static Floor currentFloor;
	public static Player player;
	public static ArrayList<Monster> monsters;
}
