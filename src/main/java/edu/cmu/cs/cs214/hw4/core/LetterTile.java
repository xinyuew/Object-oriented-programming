package edu.cmu.cs.cs214.hw4.core;

/**
 * This class is letter tile class
 * @author xinyuewu
 *
 */
public class LetterTile {
	private String letter;
	private int score;
	private Player owner;
	
	/**
	 * constructor
	 * @param l is the letter on the tile
	 * @param s is the score of this tile
	 */
	public LetterTile(String l,int s){
		letter = l;
		score = s;
		owner = null;
	}
	
	/**
	 * get the score of this letter tile
	 * @return score
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * get the letter on this tile
	 * @return letter
	 */
	public String getLetter(){
		return letter;
	}
	
	/**
	 * get the owner of this tile
	 * @return player
	 */
	public Player getOwner(){
		return owner;
	}
	
	/**
	 * set the owner of this letter tile
	 * @param p is the player who own this tile
	 */
	public void setOwner(Player p){
		owner = p;
	}
}
