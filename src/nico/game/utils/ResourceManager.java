package nico.game.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ResourceManager {

	private static BufferedReader reader;
	
	/**Reads a .txt file containing the floor*/
	public static ArrayList<String> readFloorFile(String fileName) {
		System.out.println("[ResourceManager]: Reading "+fileName);
		
		ArrayList<String> strings = new ArrayList<String>();

		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("[ResourceManager] [ERROR]: file "+fileName+" not found!");
		}
		
		try {
			String str = reader.readLine();
			strings.add(str);
			
			while(str!=null) {
				str = reader.readLine();
				strings.add(str);
			}
			
		} catch(IOException e) {
			System.out.println("[ResourceManager] [ERROR]: IOException");
		}
		
		return strings;
	}
}
