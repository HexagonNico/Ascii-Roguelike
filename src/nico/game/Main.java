package nico.game;

import java.util.ArrayList;

import javax.swing.JFrame;

import nico.game.elements.Floor;
import nico.game.elements.Monster;
import nico.game.elements.Player;

import nico.game.gui.GameBoard;

import nico.game.utils.Functions;

public class Main {

	private static JFrame window;
	private static GameBoard gameBoard;
	
	public static void main(String[] args) {
		
		System.out.println("[Main]: Starting...");
		createWindow();
		createGameBoard();
		initGame();
	}
	
	private static void createWindow() {
		System.out.println("[Main]: Creating window");
		window = new JFrame("Ascii Roguelike");
		window.setVisible(true);
		window.setResizable(false);
		window.setBounds(200, 80, Reference.windowWidth, Reference.windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static void createGameBoard() {
		System.out.println("[Main]: Creating GameBoard");
		gameBoard = new GameBoard();
		window.add(gameBoard);
		gameBoard.requestFocusInWindow();
	}
	
	/**Starts a new game!*/
	public static void initGame() {
		//initialization
		Reference.currentFloor = new Floor(0);
		Reference.player = new Player(3, 2);
		Reference.monsters = new ArrayList<Monster>();
		
		Functions.initMovingTiles();
	}

}
