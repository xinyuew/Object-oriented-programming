package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for Game class And this test actually includes special tile test, word
 * class test, rack class test, special inventory class test, etc. This test
 * nearly include all because nearly all other classes are related to game
 * class. In order to test game class, I must call methohds of other class
 * 
 * @author xinyuewu
 *
 */
public class GameTest {
	private Game game;
	private static final int TILE_BAG = 98;
	private static final int RACK = 7;
	private static final int PLAYER_NUM = 3;
	private static final int CENTER = 7;
	private static final int Y_SCORE = 4;

	/**
	 * Before the test, set up new game first
	 */
	@Before
	public void setUp() {
		game = new Game();
	}

	/**
	 * Test at the start of the game including initialGame() and addPlayer
	 * method
	 */
	@Test
	public void testStartGame() {
		game.addPlayer("1");
		assertTrue(game.getAllPlayer().size() == 1);
		assertFalse(game.initialGame());
		game.addPlayer("2");
		game.addPlayer("3");
		assertTrue(game.getAllPlayer().size() == PLAYER_NUM);
		assertTrue(game.initialGame());
		assertTrue(game.getTileBag().getSize() == TILE_BAG - PLAYER_NUM * RACK);
		assertTrue(game.getCurrentPlayer().equals(game.getAllPlayer().get(0)));
	}

	/**
	 * test pass() method and nextTurn() method
	 */
	@Test
	public void testNextTurn() {
		game.addPlayer("1");
		game.addPlayer("2");
		game.addPlayer("3");
		game.nextTurn();
		assertTrue(game.getCurrentPlayer().equals(game.getAllPlayer().get(1)));
		game.nextTurn();
		assertTrue(game.getCurrentPlayer().equals(game.getAllPlayer().get(2)));
		game.nextTurn();
		assertTrue(game.getCurrentPlayer().equals(game.getAllPlayer().get(0)));
	}

	/**
	 * Test exchange() method
	 */
	@Test
	public void testExchange() {
		game.addPlayer("1");
		game.addPlayer("2");
		game.initialGame();
		ArrayList<LetterTile> exchange = new ArrayList<LetterTile>();
		exchange.add(game.getCurrentPlayer().getRack().getLetterTiles().get(0));
		exchange.add(game.getCurrentPlayer().getRack().getLetterTiles().get(1));
		game.exchange(exchange);
		assertTrue(game.getCurrentPlayer().getRack().getSize() == RACK);
	}

	/**
	 * test isValid method
	 */
	// @Test
	public void testIsValid() {
		game.addPlayer("1");
		game.addPlayer("2");
		game.initialGame();
		LetterTile lt1 = game.getCurrentPlayer().getRack().getLetterTiles()
				.get(0);
		Location loc1 = new Location(1, 1);
		Placement p1 = new Placement(lt1, loc1);
		assertTrue(game.getCurrentPlayer().addLetterPlacement(p1));

		// check the tile has been removed from player's rack
		assertTrue(game.getCurrentPlayer().getRack().getSize() == RACK - 1);

		// check the placement is added to move
		assertTrue(game.getCurrentPlayer().getMove().getLetterPlacements()
				.size() == 1);

		LetterTile lt2 = game.getCurrentPlayer().getRack().getLetterTiles()
				.get(0);
		assertFalse(lt1.equals(lt2));
		Location loc2 = new Location(1, 2);
		Placement p2 = new Placement(lt2, loc2);
		assertTrue(game.getCurrentPlayer().addLetterPlacement(p2));

		assertTrue(game.getCurrentPlayer().getRack().getSize() == RACK - 2);
		assertTrue(game.getCurrentPlayer().getMove().getLetterPlacements()
				.size() == 2);

		// when the board is empty, if do not place a letter tile at the center
		// of the bard. return false
		assertFalse(game.isValid(game.getCurrentPlayer().getMove()));

		// place this tile at the center of the board
		p1.getLocation().setRow(CENTER);
		p1.getLocation().setCol(CENTER);
		p2.getLocation().setRow(CENTER);
		p2.getLocation().setCol(CENTER + 2);

		// not valid because this is not a word in dictionary
		assertFalse(game.isValid(game.getCurrentPlayer().getMove()));

		// Check every method called in isValid
		Move move = game.getCurrentPlayer().getMove();
		Board board = game.getBoard();
		assertTrue(move.isCollinear());
		assertTrue(board.isOnBoard(move));
		assertFalse(board.isOccupied(move));
		assertTrue(board.isEmpty());
		assertTrue(board.isCenter(move));
		assertFalse(board.isConsecutive(move));
		assertTrue(board.isSpecialValid(move));

		// special tile placement is valid
		assertTrue(board.isSpecialValid(move));
		game.getCurrentPlayer().getMove().clear();

	}

	/**
	 * Test reverseOrder method
	 */
	@Test
	public void testReverseOrder() {
		game.addPlayer("1");
		game.addPlayer("2");
		game.addPlayer("3");
		game.initialGame();
		game.reverseOrder();
		game.nextTurn();
		assertTrue(game.getCurrentPlayer().equals(
				game.getAllPlayer().get(PLAYER_NUM - 1)));
	}

	/**
	 * Test for the move method
	 */
	@Test
	public void testMove() {
		game.addPlayer("1");
		game.addPlayer("2");
		game.addPlayer("3");
		game.initialGame();
		
		//first player play some tiles
		ArrayList<Placement> letterPlacement = new ArrayList<Placement>();
		Move m1 = new Move();
		Board board = game.getBoard();
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

		assertTrue(game.isValid(m1));

		game.move(m1);
		// board is not empty
		assertFalse(board.isEmpty());

		// move is empty
		assertTrue(m1.getLetterPlacements().size() == 0);
		assertTrue(m1.getSpecialPlacements().size() == 0);

		// check this player's score is correct
		// in the move method, it call nextTrun method, so current player is
		// player1
		// Player0 is who gain points
		// CHECKSTYLE:OFF
		assertTrue(game.getAllPlayer().get(0).getScore() == 6);
		// CHECKSTYLE:ON

		letterPlacement.clear();
		m1.clear();

		//second player play some tiles
		Move m2 = new Move();

		LetterTile l4 = new LetterTile("A", 1);
		Location loc4 = new Location(CENTER - 1, CENTER);
		Placement p4 = new Placement(l4, loc4);
		LetterTile l5 = new LetterTile("R", 1);
		Location loc5 = new Location(CENTER + 1, CENTER);
		Placement p5 = new Placement(l5, loc5);
		LetterTile l6 = new LetterTile("Y", Y_SCORE);
		Location loc6 = new Location(CENTER + 2, CENTER);
		Placement p6 = new Placement(l6, loc6);

		letterPlacement.add(p4);
		letterPlacement.add(p5);
		letterPlacement.add(p6);

		m2.setLetterPlacements(letterPlacement);
		
		SpecialTile boom = new BoomTile("Boom",0);
		Location locBoom = new Location(CENTER-1,CENTER+1);
		Placement pBoom = new Placement(boom,locBoom);
		ArrayList<Placement> specialPlacement = new ArrayList<Placement>();
		specialPlacement.add(pBoom);
		m2.setSpecialPlacements(specialPlacement);

		// second player's move is valid
		assertTrue(game.isValid(m2));

		ArrayList<SpecialTile> tiles = new ArrayList<SpecialTile>();
		SpecialTile s1 = new ReverseTile("Reverse", 1);
		SpecialTile s2 = new NegativeTile("Negative", 1);
		tiles.add(s1);
		tiles.add(s2);
		game.getBoard().getSquareByLocation(loc4).addSpecialTileList(tiles);

		game.move(m2);

		// CHECKSTYLE:OFF

		// negative tile make total score negative
		assertTrue(game.getAllPlayer().get(1).getScore() == -14);
		// CHECKSTYLE:ON

		// reverse tile make player's oder reversed
		// before reverse tile, next player should be payer 2, but now it's
		// player 0
		assertTrue(game.getCurrentPlayer().equals(game.getAllPlayer().get(0)));
		
	}

	/**
	 * test buySpecialTile method
	 */
	@Test
	public void testBuySpecialTile() {
		game.addPlayer("1");
		game.addPlayer("2");
		game.initialGame();
		// CHECKSTYLE:OFF
		game.getCurrentPlayer().addScore(100);
		// CHECKSTYLE:ON
		SpecialTile st = game.buySpecialTile("Reverse");
		assertNotNull(st);
	}

}
