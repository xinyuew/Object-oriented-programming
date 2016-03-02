package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for testing player class
 * @author xinyuewu
 *
 */
public class PlayerTest {
	private Player player;
	private Rack rack;
	private Game game;
	private static final int RACK = 7;
	
	/**
	 * before each test, set up first
	 */
	@Before
	public void setUp(){
		game = new Game();
		game.addPlayer("1");
		game.addPlayer("2");
		game.initialGame();
		player = game.getCurrentPlayer();
		rack = player.getRack();
	}
	
	/**
	 * test remove tiles from rack
	 */
	@Test
	public void testRemove(){
		ArrayList<LetterTile> tiles = rack.getLetterTiles();
		player.removeTileListFromRack(tiles);
		assertTrue(rack.getSize() == 0);
	}
	
	/**
	 * test refillRack method
	 */
	@Test
	public void testRefillRack(){
		ArrayList<LetterTile> tiles = game.getTileBag().getRandomLetterTile(RACK);
		player.refillRack(tiles);
		assertTrue(rack.getSize() == RACK);
	}
	
	/**
	 * test addLetterPlacement method 
	 */
	@Test
	public void testAddLetter(){
		LetterTile l1 = rack.getLetterTiles().get(0);
		Location loc1 = new Location(1,1);
		Placement p1 = new Placement(l1,loc1);
		player.addLetterPlacement(p1);
		assertTrue(rack.getLetterTiles().size() == RACK-1);
		assertTrue(player.getMove().getLetterPlacements().get(0).equals(p1));
	}
	
	/**
	 * test addSpecialPlacement method
	 */
	@Test
	public void testAddSpecial(){
		SpecialTile s1 = new ReverseTile("Reverse",1);
		Location loc1 = new Location(1,1);
		Placement p1 = new Placement(s1,loc1);
		player.addSpecialPlacement(p1);
		assertTrue(player.getMove().getSpecialPlacements().get(0).equals(p1));
	}

}
