package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import classes.Hunter;
import classes.Mage;
import classes.Warrior;
import game.*;
import game.Character;

public class GameTests {

	private Game game;
	
	@Before
	public void setUp() {
		game = new Game();
		game.addCharacter(new Mage("Magician", 1, 100));
		game.addCharacter(new Warrior("Mercenary", 1, 100));
		game.addCharacter(new Hunter("Hunter", 1, 100));
	}
	
	//TEST IF selectCharacter IS SELECTED
	@Test
	public void testSelectedCharacter() {
		Character selected = game.selectCharacter();
		
		assertNotNull(selected);
	}
	
	//TEST IF ENEMY CHARACTER IS SELECTED AND IF IT'S NOT SIMILAR TO PLAYER'S CHARACTER
	@Test
	public void testGetRandomOpponent() {
		Character player = game.selectCharacter();
		Character opponent = game.getRandomOpponent(player);
		
		assertNotNull(opponent);
		assertNotEquals(player, opponent);
	}
	
	//TEST IF STATS IS SORTING
	@Test
	public void testGetSortedStats() {
		try {
			game.readStats("resources/results/result.txt");
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		List<Stats> sorted = game.getSortedStats();
		assertEquals("Magician", sorted.get(0).getCharacterName());
		assertEquals("Hunter", sorted.get(1).getCharacterName());
		assertEquals("Mercenray", sorted.get(2).getCharacterName());
		
	}
	

}
