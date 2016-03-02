package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;

/**
 * This class represent player
 * 
 * @author xinyuewu
 *
 */
public class Player {
	private Move move;
	private String name;
	private Rack rack;
	private int score;
	private SpecialTileInventory inventory;

	/**
	 * constructor
	 * 
	 * @param n
	 *            is this player's name
	 */
	public Player(String n) {
		name = n;
		score = 0;
		move = new Move();
		rack = new Rack();
		inventory = new SpecialTileInventory();
	}

	/**
	 * refill player's rack after he played some letter tiles
	 * 
	 * @param newTiles
	 *            is new tiles generate from tile bag
	 */
	public void refillRack(ArrayList<LetterTile> newTiles) {
		rack.addTiles(newTiles);
	}

	/**
	 * remove a list of letter tiles from player's rack
	 * 
	 * @param tiles
	 *            a list of letter tiles need to be removed
	 */
	public void removeTileListFromRack(ArrayList<LetterTile> tiles) {
		rack.removeTiles(tiles);
	}


	/**
	 * If the player play a letter tile, add this tile to the list
	 * 
	 * @param p
	 *            is a letter tile placement
	 * @return true if add letter placement successfully
	 */
	public boolean addLetterPlacement(Placement p) {
		if (rack.contains(p.getLetterTile())) {
			move.getLetterPlacements().add(p);
			rack.removeTile(p.getLetterTile());
			return true;
		}
		return false;
	}

	/**
	 * If the player play a special tile, add this tile to the list
	 * 
	 * @param p
	 *            is a special tile placement
	 */
	public void addSpecialPlacement(Placement p) {
		move.getSpecialPlacements().add(p);
		inventory.removeSpecialTile(p.getSpecialTile());
	}
	
	/**
	 * After this turn, clear this player's move
	 */
	public void clearMove(){
		move.clear();
	}
	
	/**
	 * add a new special tile to this player's inventory
	 * @param st is special tile
	 */
	public void addSpecialTile(SpecialTile st){
		inventory.addSpecialTile(st);
	}


	/**
	 * get this player's move
	 * 
	 * @return move
	 */
	public Move getMove() {
		return move;
	}

	/**
	 * add score to this player
	 * 
	 * @param s
	 *            is score gained in this turn
	 */
	public void addScore(int s) {
		score += s;
	}

	/**
	 * get this player's current score
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * set this player's score
	 * @param s is score
	 */
	public void setScore(int s){
		score = s;
	}

	/**
	 * get this player's rack
	 * 
	 * @return rack
	 */
	public Rack getRack() {
		return rack;
	}
	
	/**
	 * get this player's special tile inventory
	 * @return inventory
	 */
	public SpecialTileInventory getInventory(){
		return inventory;
	}
	
	/**
	 * get this player's name
	 * @return name
	 */
	public String getName(){
		return name;
	}

}
