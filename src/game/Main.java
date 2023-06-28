package game;

import java.io.IOException;
/*import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import classes.Hunter;
import classes.Mage;
import classes.Warrior;
*/

public class Main {

	public static void main(String[] args) throws IOException {
		
		GameInterface game = new Game();

		game.loadCharactersFromFile("resources/data/char_classes.txt");
		
		game.readStats("resources/results/result.txt");
		
		Character playerCharacter = game.selectCharacter();
		Character opponentCharacter = game.getRandomOpponent(playerCharacter);
		
		game.performBattle(playerCharacter, opponentCharacter);
		
		game.saveResultsToFile("resources/results/result.txt");
		
		game.updateCharacterLevel("resources/data/char_classes.txt");
		
		game.filterWinrate("resources/results/winrates.txt");
		
	}

}



//INTERFACE FOR CHARACTER MANAGEMENT
interface CharacterManagement{
	void addCharacter(Character character);
	
	void loadCharactersFromFile(String filePath) throws IOException;
	
	Character selectCharacter();
	
	Character findCharacter(int choice);
}

//INTERFACE FOR BATTLE
interface BattleInterface{
	
	Character getRandomOpponent(Character playerCharacter);
	
	void performBattle(Character playerCharacter, Character opponentCharacter);
}

//INTERFACE FOR DATA FILES
interface DataInterface{
	void readStats(String filePath) throws IOException;
	
	void updateCharacterLevel(String filePath) throws IOException;
	
	void filterWinrate(String filePath) throws IOException;
	
	void saveResultsToFile(String filePath) throws IOException;
}

//MAIN INTERFACE 
interface GameInterface extends CharacterManagement, BattleInterface, DataInterface{
	
}



