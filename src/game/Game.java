package game;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import classes.Hunter;
import classes.Mage;
import classes.Warrior;

//GAME LOGIC
public class Game implements GameInterface{
	private List<Character> characterList; //KOMPOZICIJOS RYSIS
	private Map<String, Stats> characterStatsMap; //KOMPOZICIJOS RYSIS
	
	
	public Game() {
		characterList = new ArrayList<>();
		characterStatsMap = new HashMap<>();
	}
	
	@Override
	public void addCharacter(Character character) {
		characterList.add(character);
		characterStatsMap.put(character.name, new Stats(character.name));
		
	}

	//LOAD EXISTING CHARACTERS FROM char_classes.txt
	public void loadCharactersFromFile(String filePath) throws IOException{
		List<String> failoTekstas = Files.readAllLines(Path.of(filePath));
		
		for(String s : failoTekstas) {
			String[] info = s.split("; ");
			String name = info[0];
			int level = Integer.parseInt(info[1]);
			int HP = Integer.parseInt(info[2]);
			
			switch(name) {
			
				case "Magician":
					characterList.add(new Mage(name, level, HP));
					break;
					
				case "Mercenary":
					characterList.add(new Warrior(name, level, HP));
					break;
					
				case "Hunter":
					characterList.add(new Hunter(name, level, HP));
					break;
					
				default:
					System.out.print("Invalid character name: " + name);
					
			}
			
			characterStatsMap.put(name, new Stats(name));
		}
		
	}

	//READ STATS FROM result.txt
	public void readStats(String filePath) throws IOException {
		
		File statsFile = new File(filePath);
		if(!statsFile.exists()) {
			return;
		}
		
		List<String> readStats = Files.readAllLines(Path.of(filePath));
		
		for(String s : readStats) {
			String[] info = s.split(" ");
			String name = info[0];
			int wins = Integer.parseInt(info[2]);
			int loses = Integer.parseInt(info[4]);
			
			Stats stats = characterStatsMap.get(name);
			if(stats != null) {
				stats.setWins(wins);
				stats.setLoses(loses);
			}
			else {
				stats = new Stats(name);
				stats.setWins(wins);
				stats.setLoses(loses);
				characterStatsMap.put(name, stats);
			}
		}
	}
	
	//FIND CHARACTER IN characterList to perform selectCharacter()
	@Override
	public Character findCharacter(int choice) {
	    if(choice >= 0 && choice < characterList.size()) {
	    	return characterList.get(choice);
	    }
	    else {
	    	return null;
	    }
	}
	
	
	//SELECT YOUR CLASS
	public Character selectCharacter() {
		System.out.println("Select your character:");
		for(int i = 0; i < characterList.size(); i++) {
			System.out.println((i+1) + ". " + characterList.get(i).getName());
		}
		
		System.out.print("Your choice: ");
		Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt() - 1;
			
			while(choice < 0 || choice >= characterList.size()) {
				System.out.print("Invalid character choice, enter a valid one: ");
				choice = sc.nextInt() - 1;
			}
			
			Character playerCharacter = findCharacter(choice);
			return playerCharacter;
		
	}
	
	//ENEMY RANDOM CHOICE
	public Character getRandomOpponent(Character chosenCharacter) {
		Random rand = new Random();
		Character opponent;
		
		do {
			opponent = characterList.get(rand.nextInt(characterList.size()));
		} 
		while(opponent == chosenCharacter);
		
		return opponent;
	}
	
	/////////////////////////////////////////BATTLE////////////////////////////////////////////////////
	public void performBattle(Character playerCharacter, Character opponentCharacter) {
			
		opponentCharacter = getRandomOpponent(playerCharacter);
		
		int round = 1;
		
		System.out.println(" ");
		System.out.println("BATTLE STARTS!");
		System.out.println(playerCharacter.name + " vs. " + opponentCharacter.name);
		
		while(playerCharacter.isAlive() && opponentCharacter.isAlive()) {
			
			System.out.println("---------ROUND " + round + "----------");
		
			//PLAYER TURN TO ATTACK
			System.out.println("YOUR TURN:");
			int playerAttackChoice = getPlayerAttackChoice(playerCharacter);
			playerCharacter.performAction(opponentCharacter, playerAttackChoice);
			
			System.out.print("to " + opponentCharacter.name);

			//HEALTH POINTS AFTER PLAYER'S TURN
			if(!opponentCharacter.isAlive()) {opponentCharacter.HP = 0;}
			System.out.println(" ");
			System.out.println(playerCharacter.name + " HP: " + playerCharacter.HP);
			System.out.println(opponentCharacter.name + " HP: " + opponentCharacter.HP);
			System.out.println(" ");
			
			
			//ENEMY TURN TO ATTACK
			if(opponentCharacter.isAlive()) {
			System.out.println("ENEMY TURN:");
			int opponentAttackChoice = getOpponentAttackChoice(opponentCharacter);
			opponentCharacter.performAction(playerCharacter, opponentAttackChoice);
			
			System.out.print("to " + playerCharacter.name);
			
			//HEALTH POINTS AFTER ENEMY'S TURN
			if(!playerCharacter.isAlive()) {playerCharacter.HP = 0;}
			System.out.println(" ");
			System.out.println(playerCharacter.name + " HP: " + playerCharacter.HP);
			System.out.println(opponentCharacter.name + " HP: " + opponentCharacter.HP);	
			System.out.println(" ");
			
			}
		
		round++;
		}
		
		
		//WHO WINS
		Character winner = playerCharacter.isAlive() ? playerCharacter : opponentCharacter;
		Character loser = playerCharacter.isAlive() ? opponentCharacter : playerCharacter;
		
		//rezultatai
		System.out.println(winner.name + " Wins!");
		
		//LVL UP
		winner.level++;
		System.out.println(winner.name + " has leveled up to: " + winner.level + "lvl.");
		System.out.println(" ");
		
		//STATS UPDATE
		Stats winnerStats = characterStatsMap.get(winner.name);
		Stats loserStats = characterStatsMap.get(loser.name);
		
		winnerStats.addWin();
		loserStats.addLose();
		
		System.out.println(winnerStats);
		System.out.println(loserStats);
		
	}
	
	//ACCORDING TO EARLIER SELECTED CHARACTER, CONSOLE ASKS A PLAYER TO CHOSE A NUMBER OF A GUN TYPE
	private int getPlayerAttackChoice(Character character) {
		System.out.println("Select your attack:");
		
		character.printAttackOptions();
		
		System.out.print("Your choice: ");
		Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt() - 1;

			
			while(choice < 0 || choice >= character.getAttackOptionCount()) {
				System.out.println("Invalid attack choice, enter a valid one:");
				choice = sc.nextInt() - 1;
			}
			
			return choice;
	}
	
	//RANDOM ENEMY ATTACK
	private int getOpponentAttackChoice(Character character) {
		Random rand = new Random();
		return rand.nextInt(character.getAttackOptionCount());
	}
//////////////////////////////////////////////BATTLE ENDS///////////////////////////////////////////////////////
	
	
	//SORT PAGAL WINS
	public List<Stats> getSortedByWinsStats(){
		return characterStatsMap.values().stream()
				.sorted((s1, s2) -> Integer.compare(s2.getWins(), s1.getWins()))
				.collect(Collectors.toList());
	}
	
	//SORT PAGAL WINRATE
	public List<Stats> getSortedByWinrateStats(){
		return characterStatsMap.values().stream()
				.sorted((s1, s2) -> Double.compare(s2.getWinRate(), s1.getWinRate()))
				.collect(Collectors.toList());
	}
	
	//SORT STATS PAGAL WINS AND WINRATE
	public List<Stats> getSortedStats(){
		return characterStatsMap.values().stream()
				.sorted((s1, s2) -> {
					int result = Integer.compare(s2.getWins(), s1.getWins()); //SORT WINS
					
					if(result == 0) { //IF WIN COUNT IS EQUAL
						return Double.compare(s1.getWinRate(), s2.getWinRate()); // SORT WINRATE
					}
					
					return result;
				})
				.collect(Collectors.toList());
					
	}
	
	
	//PRINT WINS/LOSES/WINRATE TO FILE
	public void saveResultsToFile(String filePath) throws IOException{
	
		File file = new File(filePath);
		
		PrintWriter rez = new PrintWriter(file);

		List<Stats> sortedStats = getSortedStats();
		
		for(Stats stats : sortedStats) {
			rez.printf("%s Wins: %d Loses: %d Winrate: %.2f%%%n",
					stats.getCharacterName(), stats.getWins(), stats.getLoses(), stats.getWinRate());
		}
	
		
		rez.close();
		
	}
	
	//UPDATE LEVEL IN char_classes.txt
	public void updateCharacterLevel(String filePath) throws IOException {
		List<String> originalContent = Files.readAllLines(Path.of(filePath));
		List<String> updateContent = new ArrayList<>();
		
		for(int i = 0; i < characterList.size(); i++) {
			Character character = characterList.get(i);
			String[] info = originalContent.get(i).split("; ");
			updateContent.add(character.name + "; " + character.level + "; " + info[2]);
		}
		
		PrintWriter updatedLevel = new PrintWriter(filePath);
		for(String line : updateContent) {
			updatedLevel.println(line);
		}
		
		updatedLevel.close();
	}
	
	//TYPE IN FILE IF WINRATE >= 50%
	public void filterWinrate(String filePath) throws IOException {
		
		PrintWriter rez = new PrintWriter(filePath);
		
		characterStatsMap.values().stream()
					.filter(stats -> stats.getWinRate() >= 50.0)
					.sorted(Comparator.comparing(Stats::getWinRate).reversed()
							.thenComparing(Stats::getWins))
					.forEach(stats -> rez.println(stats.getCharacterName() + " Winrate: " + String.format("%.2f", stats.getWinRate()) + "%"));
		
		rez.close();
	}
	
	//SEARCH character pagal ivestus parametrus
	public List<Character> searchCharacters(String name, int level, int HP){
		return characterList.stream()
				.filter(character -> character.getName().equals(name))
				.filter(character -> character.getLevel() == level)
				.filter(character -> character.getHP() == HP)
				.collect(Collectors.toList());
	}
}

	
