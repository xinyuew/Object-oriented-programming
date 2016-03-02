package edu.cmu.cs.cs214.hw4.core;

/**
 * Special Tile class is an abstract class, there are several sub class extends
 * this abstract class. This class contains some basic information for special
 * tiles.
 */
public abstract class SpecialTile {
	private String name;
	private int price;
	private Player owner;
	
	/**
	 * The constructor of specialTile
	 * @param n is the special tile's name
	 * @param p is the special tile's price
	 * @param p is the special tile's owner
	 */
	protected SpecialTile(String n, int p){
		name = n;
		price = p;
	}

	/**
	 * After triggered special tile, this tile will make some effect to game before calculate score.
	 * 
	 * @param g
	 *            is Game class
	 * @param s is the square which contains this special tile
	 */
	public abstract void makeEffectBefore(Game g,Square s);
	
	/**
	 * This tile will make effect to the game after calculate score
	 * @param g is an instance of game
	 * @param s is the square which contains this special tile
	 */
	public void makeEffectAfter(Game g,Square s){
		//do nothing`
	};
	
	/**
	 * Getter
	 * @return this special tile's name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Getter
	 * @return this special tile's price
	 */
	public int getPrice(){
		return price;
	}
	
	/**
	 * Getter
	 * @return this special tile's owner
	 */
	public Player getOwner(){
		return owner;
	}
	
	/**
	 * set owner for this special tile
	 * @param p is owner
	 */
	public void setOwner(Player p){
		owner = p;
	}
	


}
