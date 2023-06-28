package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import game.*;
import game.Character;

public class GameTests {

	private Game game;
	private Stats stats;
	
	@BeforeEach
	public void setUp() throws IOException{
		game = new Game();
		game.loadCharactersFromFile("resources/data/char_classes.txt");
		stats = new Stats("Test character");
	}
	
	//CLEAR TEST INFO
	@AfterEach
	public void tearDown() {
		game = null;
		stats = null;
	}
	
	//TEST IF selectCharacter IS SELECTED
	@Test
	public void testSelectedCharacter() {
		Character selected = game.selectCharacter();
		
		assertTrue(selected instanceof Character);
	}
	
	//TEST IF ENEMY CHARACTER IS SELECTED AND IF IT'S NOT SIMILAR TO PLAYER'S CHARACTER
	@Test
	public void testGetRandomOpponent() {
		Character player = game.selectCharacter();
		Character opponent = game.getRandomOpponent(player);
		
		assertNotEquals(player, opponent);
	}
	
	//TEST IF addWin INCREMENTS WINS AND TOTAL BATTLES
	@Test
	public void testAddWin() {
		int wins = stats.getWins();
		int totalBattles = stats.getTotalBattles();
		
		stats.addWin();
		
		assertEquals(wins + 1, stats.getWins());
		assertEquals(totalBattles + 1, stats.getTotalBattles());
	}
	
	//TEST IF ADDING WINS AND LOSES CALCULATES WINRATE CORRECTLY
	@Test
	public void testGetWinrate() {
		
		stats.addWin();
		stats.addWin();
		stats.addLose();
		
		assertEquals(66.67, stats.getWinRate(), 0.01);
	}
	
	//TEST IF findCharacter() finds character
	@Test
	public void testFindCharacter() {
		Character character = game.findCharacter(1);
		
		assertNotNull(character);
	}
	
	//TEST IF WINNER'S LEVEL dideja BY 1
	@Test
	public void testPerformBattle() {
		Character playerCharacter = game.selectCharacter();
		Character opponentCharacter = game.getRandomOpponent(playerCharacter);
		
		int playerLevelBefore = playerCharacter.getLevel();
		int opponentLevelBefore = opponentCharacter.getLevel();
		
		game.performBattle(playerCharacter, opponentCharacter);
		
		Character winner = playerCharacter.isAlive() ? playerCharacter : opponentCharacter;
		int winnerLevel = winner == playerCharacter ? playerLevelBefore : opponentLevelBefore;
		
		assertEquals(winnerLevel + 1, winner.getLevel());
		
	}
	
	@Test
	@Disabled
	public void testUpdateCharacterLevel() throws IOException{
		
	}
	
	
}
	 

