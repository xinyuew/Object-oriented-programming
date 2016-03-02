package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * test tile bag
 * @author xinyuewu
 *
 */
public class TileBagTest {
	private TileBag tileBag;
	private Game game;
	private static final int RACK = 7;
	
	/**
	 * before each test, set up
	 */
	@Before
	public void setUp(){
		game = new Game();
		tileBag =game.getTileBag();
	}
	
	/**
	 * test get random tile method
	 */
	@Test
	public void testGetRandomTile(){
		ArrayList<LetterTile> tiles1 = new ArrayList<LetterTile>();
		ArrayList<LetterTile> tiles2 = new ArrayList<LetterTile>();
		tiles1 = tileBag.getRandomLetterTile(RACK);
		assertTrue(tiles1.size() == RACK);
		tiles2 = tileBag.getRandomLetterTile(RACK);
		assertFalse(tiles1.equals(tiles2));
	}
}
