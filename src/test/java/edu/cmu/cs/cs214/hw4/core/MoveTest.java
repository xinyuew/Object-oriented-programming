package edu.cmu.cs.cs214.hw4.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for Move class
 * @author xinyuewu
 *
 */
public class MoveTest {
	private Move move;
	
	/**
	 * before test, set up first
	 */
	@Before
	public void setUp(){
		move = new Move();
		
	}
	
	/**
	 * test isCollinear method, getMaxIndex method and getMinIndex method
	 * and clear method, and containsPlcament method
	 */
	@Test
	public void testCollinear(){
		ArrayList<Placement> letterPlacement = new ArrayList<Placement>();
		LetterTile l1 = new LetterTile("A", 1);
		Location loc1 = new Location(0, 1);
		Placement p1 = new Placement(l1, loc1);
		LetterTile l2 = new LetterTile("B", 1);
		Location loc2 = new Location(1, 1);
		Placement p2 = new Placement(l2, loc2);
		LetterTile l3 = new LetterTile("C", 1);
		Location loc3 = new Location(2, 1);
		Placement p3 = new Placement(l3, loc3);

		letterPlacement.add(p1);
		letterPlacement.add(p2);
		letterPlacement.add(p3);

		move.setLetterPlacements(letterPlacement);
		assertTrue(move.isCollinear());
		
		assertTrue(move.getMinIndex() == 0);
		assertTrue(move.getMaxIndex() == 2);
		
		Location loc4 = new Location(0,0);
		assertFalse(move.containPlacement(loc4));
		assertTrue(move.containPlacement(loc2));
	}
	


}
