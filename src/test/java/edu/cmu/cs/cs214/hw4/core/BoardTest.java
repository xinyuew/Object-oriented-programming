package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is test all methods in Board class
 * @author xinyuewu
 *
 */
public class BoardTest {
	private Game game;
	private Board board;
	private TileBag tileBag;
	private SpecialTileStore specialStore;
	private static final int CENTER = 7;
	
	/**
	 * before test, set up
	 */
	@Before
	public void setUp(){
		game = new Game();
		board = game.getBoard();
		tileBag = game.getTileBag();
		specialStore = game.getSpecialTileStore();
	}
	
	/**
	 * Test constructor, if the initial board is correct
	 */
	@Test
	public void testConstructor(){
		
		//CHECKSTYLE:OFF
		Location loc1 = new Location(0,0);
		Square s1 = board.getSquareByLocation(loc1);
		assertTrue(s1.getLetterMultiplier() == 1);
		assertTrue(s1.getWordMultiplier() == 3);
		
		Location loc2 = new Location(14,11);
		Square s2 = board.getSquareByLocation(loc2);
		assertTrue(s2.getLetterMultiplier() == 2);
		assertTrue(s2.getWordMultiplier() == 1);
		
		assertTrue(s2.getLocation().equals(loc2));
		//CHECKSTYLE:ON
	}
	
	/**
	 * Test isEmpty method
	 */
	@Test
	public void testIsEmpty(){
		assertTrue(board.isEmpty());
		Location loc = new Location(0,0);
		LetterTile lt = tileBag.getRandomLetterTile(1).get(0);
		board.getSquareByLocation(loc).addLetterTile(lt);
		assertFalse(board.isEmpty());
	}
	
	/**
	 * test isAdjacentToExistingTile method
	 */
	@Test
	public void testIsAdjacentToExistingTile(){
		//add a letter tile on the board
		Location loc = new Location(CENTER-1,CENTER);
		LetterTile lt = tileBag.getRandomLetterTile(1).get(0);
		board.getSquareByLocation(loc).addLetterTile(lt);
		
		//test new placed letter tiles
		ArrayList<Placement> letterPlacement = new ArrayList<Placement>();
		Move m1 = new Move();
		LetterTile l1 = new LetterTile("E", 1);
		Location loc1 = new Location(CENTER, CENTER);
		Placement p1 = new Placement(l1, loc1);
		LetterTile l2 = new LetterTile("A", 1);
		Location loc2 = new Location(CENTER, CENTER + 1);
		Placement p2 = new Placement(l2, loc2);
		LetterTile l3 = new LetterTile("T", 1);
		Location loc3 = new Location(CENTER, CENTER + 2);
		Placement p3 = new Placement(l3, loc3);


		letterPlacement.add(p1);
		letterPlacement.add(p2);
		letterPlacement.add(p3);
		m1.setLetterPlacements(letterPlacement);
		
		assertTrue(board.isAdjacentToExistingTile(m1));
		letterPlacement.clear();
		
		LetterTile l4 = new LetterTile("E", 1);
		Location loc4 = new Location(0, 0);
		Placement p4 = new Placement(l4, loc4);
		LetterTile l5 = new LetterTile("A", 1);
		Location loc5 = new Location(0, 1);
		Placement p5 = new Placement(l5, loc5);
		
		letterPlacement.add(p4);
		letterPlacement.add(p5);
		
		Move m2 = new Move();
		m2.setLetterPlacements(letterPlacement);
		assertFalse(board.isAdjacentToExistingTile(m2));
		
	}
	
	/**
	 * test isCenter method
	 */
	@Test
	public void testIsCenter(){
		ArrayList<Placement> letterPlacement = new ArrayList<Placement>();
		LetterTile l4 = new LetterTile("E", 1);
		Location loc4 = new Location(0, 0);
		Placement p4 = new Placement(l4, loc4);
		LetterTile l5 = new LetterTile("A", 1);
		Location loc5 = new Location(0, 1);
		Placement p5 = new Placement(l5, loc5);
		
		letterPlacement.add(p4);
		letterPlacement.add(p5);
		
		Move m2 = new Move();
		m2.setLetterPlacements(letterPlacement);
		assertFalse(board.isCenter(m2));
	}
	
	/**
	 * test if this location is on board and if this square is occupied
	 */
	@Test
	public void testIsOnBoard(){
		//CHECKSTYLE:OFF
		ArrayList<Placement> letterPlacement = new ArrayList<Placement>();
		LetterTile l4 = new LetterTile("E", 1);
		Location loc4 = new Location(18, 0);
		Placement p4 = new Placement(l4, loc4);
		LetterTile l5 = new LetterTile("A", 1);
		Location loc5 = new Location(0, 1);
		Placement p5 = new Placement(l5, loc5);
		
		letterPlacement.add(p4);
		letterPlacement.add(p5);
		
		Move m2 = new Move();
		m2.setLetterPlacements(letterPlacement);
		assertFalse(board.isOnBoard(m2));
		//CHECKSTYLE:ON
	}
	
	/**
	 * Test if this square has been occupied
	 */
	@Test
	public void testIsOccupied(){
		Location loc = new Location(0,0);
		LetterTile lt = tileBag.getRandomLetterTile(1).get(0);
		board.getSquareByLocation(loc).addLetterTile(lt);
		
		ArrayList<Placement> letterPlacement = new ArrayList<Placement>();
		LetterTile l4 = new LetterTile("E", 1);
		Location loc4 = new Location(0, 0);
		Placement p4 = new Placement(l4, loc4);
		LetterTile l5 = new LetterTile("A", 1);
		Location loc5 = new Location(0, 1);
		Placement p5 = new Placement(l5, loc5);
		
		letterPlacement.add(p4);
		letterPlacement.add(p5);
		
		Move m2 = new Move();
		m2.setLetterPlacements(letterPlacement);
		assertTrue(board.isOccupied(m2));
		
		
	}
	
	/**
	 * test if these tiles are consecutive
	 */
	@Test
	public void testIsConsecutive(){
		Location loc1 = new Location(1,1);
		LetterTile lt1 = tileBag.getRandomLetterTile(1).get(0);
		board.getSquareByLocation(loc1).addLetterTile(lt1);
		
		Location loc2 = new Location(1,2);
		LetterTile lt2 = tileBag.getRandomLetterTile(1).get(0);
		board.getSquareByLocation(loc2).addLetterTile(lt2);
		
		ArrayList<Placement> letterPlacement = new ArrayList<Placement>();
		LetterTile l3 = new LetterTile("E", 1);
		Location loc3 = new Location(0, 1);
		Placement p3 = new Placement(l3, loc3);
		LetterTile l4 = new LetterTile("A", 1);
		Location loc4 = new Location(2, 1);
		Placement p4 = new Placement(l4, loc4);
		
		letterPlacement.add(p3);
		letterPlacement.add(p4);
		
		Move m2 = new Move();
		m2.setLetterPlacements(letterPlacement);
		m2.isCollinear();
		
		assertTrue(board.isConsecutive(m2));	
	}
	
	/**
	 * Test getWord, getHorizontalWord and getVerticalWord method
	 * and compute score
	 */
	@Test
	public void testGetWord(){
		game.addPlayer("1");
		game.addPlayer("2");
		game.addPlayer("3");
		game.initialGame();
		ArrayList<Placement> letterPlacement = new ArrayList<Placement>();
		Move m1 = new Move();
		LetterTile l1 = new LetterTile("E", 1);
		Location loc1 = new Location(CENTER, CENTER);
		Placement p1 = new Placement(l1, loc1);
		LetterTile l2 = new LetterTile("A", 1);
		Location loc2 = new Location(CENTER, CENTER + 1);
		Placement p2 = new Placement(l2, loc2);
		LetterTile l3 = new LetterTile("T", 1);
		Location loc3 = new Location(CENTER, CENTER + 2);
		Placement p3 = new Placement(l3, loc3);


		letterPlacement.add(p1);
		letterPlacement.add(p2);
		letterPlacement.add(p3);
		m1.setLetterPlacements(letterPlacement);
		assertTrue(m1.isCollinear());
		
		assertTrue(board.getWords(m1).size() == 1);
		assertTrue(board.getWords(m1).get(0).getContent(m1).equals("EAT"));
		game.move(m1);
		//CHECKSTYLE:OFF
		assertTrue(game.getAllPlayer().get(0).getScore() == 6);
		
		
		LetterTile l7 = new LetterTile("N", 1);
		Location loc7 = new Location(CENTER+1, CENTER + 3);
		board.getSquareByLocation(loc7).addLetterTile(l7);
		
		//add new tiles
		LetterTile l4 = new LetterTile("S", 1);
		Location loc4 = new Location(CENTER-1, CENTER + 2);
		Placement p4 = new Placement(l4, loc4);
		
		LetterTile l5 = new LetterTile("O", 1);
		Location loc5 = new Location(CENTER+1, CENTER + 2);
		Placement p5 = new Placement(l5, loc5);
		
		
		LetterTile l6 = new LetterTile("P", 3);
		Location loc6 = new Location(CENTER+2, CENTER + 2);
		Placement p6 = new Placement(l6, loc6);
		
	
		
		
		Move m2 = new Move();
		letterPlacement.clear();
		letterPlacement.add(p4);
		letterPlacement.add(p5);
		letterPlacement.add(p6);
		
		m2.setLetterPlacements(letterPlacement);
		assertTrue(m2.isCollinear());
		
		assertTrue(board.getWords(m2).size() == 2);
		assertTrue(board.getWords(m2).get(0).getContent(m2).equals("STOP"));
		assertTrue(board.getWords(m2).get(1).getContent(m2).equals("ON"));
		
		assertTrue(game.isValid(m2));
		game.move(m2);
	
		assertTrue(game.getAllPlayer().get(1).getScore() == 14);
		//CHECKSTYLE:ON
	
	}
	
	/**
	 * test isSpecialValid method
	 */
	@Test
	public void testIsSpecialValid(){
		ArrayList<Placement> specialPlcaements = new ArrayList<Placement>();
		SpecialTile s1 = specialStore.newTile("Reverse");
		Location loc1 = new Location(0,0);
		Placement p1 = new Placement(s1,loc1);
		SpecialTile s2 = specialStore.newTile("Negative");
		Location loc2 = new Location(CENTER,CENTER);
		Placement p2 = new Placement(s2,loc2);
		SpecialTile s3 = specialStore.newTile("Zero");
		Location loc3 = new Location(1,2);
		Placement p3 = new Placement(s3,loc3);
		specialPlcaements.add(p1);
		specialPlcaements.add(p2);
		specialPlcaements.add(p3);
		Move m1 = new Move();
		m1.setSpecialPlacements(specialPlcaements);
		
		assertTrue(board.isSpecialValid(m1));
	}
	
	/**
	 * Test placeTile method
	 */
	@Test
	public void testPlaceTile(){
		ArrayList<Placement> letterPlacement = new ArrayList<Placement>();
		Move m1 = new Move();
		LetterTile l1 = new LetterTile("E", 1);
		Location loc1 = new Location(CENTER, CENTER);
		Placement p1 = new Placement(l1, loc1);
		LetterTile l2 = new LetterTile("A", 1);
		Location loc2 = new Location(CENTER, CENTER + 1);
		Placement p2 = new Placement(l2, loc2);
		LetterTile l3 = new LetterTile("T", 1);
		Location loc3 = new Location(CENTER, CENTER + 2);
		Placement p3 = new Placement(l3, loc3);
		
		ArrayList<Placement> specialPlacement = new ArrayList<Placement>();
		SpecialTile s4 = specialStore.newTile("Reverse");
		Location loc4 = new Location(0,0);
		Placement p4 = new Placement(s4,loc4);
		
		letterPlacement.add(p1);
		letterPlacement.add(p2);
		letterPlacement.add(p3);
		specialPlacement.add(p4);
		m1.setLetterPlacements(letterPlacement);
		m1.setSpecialPlacements(specialPlacement);
		
		board.placeTiles(m1);
		
		assertTrue(board.getSquareByLocation(loc1).getLetterTile().equals(l1));
		assertTrue(board.getSquareByLocation(loc2).getLetterTile().equals(l2));
		assertTrue(board.getSquareByLocation(loc3).getLetterTile().equals(l3));
		assertTrue(board.getSquareByLocation(loc4).getSpecialTile().get(0).equals(s4));
		
	}
	


}
