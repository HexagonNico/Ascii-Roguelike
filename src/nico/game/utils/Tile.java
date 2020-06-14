package nico.game.utils;

public enum Tile {

	NOTHING('.'),
	WALL('#'),
	PLAYER('A'),
	STAIRS('^'),
	TRAP(','),
	HP_POTION('p'),
	GOLD('G'),
	TREASURE('T'),
	KEY('!'),
	DOOR('/'),
	MONSTER('M');
	
	private char symbol;
	
	Tile(char symbol) {
		this.symbol=symbol;
	}
	
	public char symbol() {return symbol;}
}
