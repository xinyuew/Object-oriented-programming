package edu.cmu.cs.cs214.hw4.core;

/**
 * This class is response for generate special tile
 * @author xinyuewu
 *
 */
public class SpecialTileStore {
	private static final int BOOM_PRICE = 20;
	private static final int REVERSE_PRICE = 5;
	private static final int NEGATIVE_PRICE = 15;
	private static final int ZERO_PRICE = 10;
	private static final int SWITCH_PRICE = 30;

	/**
	 * buy special tile from special tile store
	 * @param n is the tile name
	 * @return special tile
	 */
	public SpecialTile newTile(String n) {
		SpecialTile st;
		if (n.equals("Boom")) {
			st = new BoomTile(n, BOOM_PRICE);
		} else if (n.equals("Reverse")) {
			st = new ReverseTile(n, REVERSE_PRICE);
		} else if (n.equals("Negative")) {
			st = new NegativeTile(n, NEGATIVE_PRICE);
		} else if (n.equals("Zero")) {
			st = new ZeroTile(n, ZERO_PRICE);
		} else if (n.equals("Switch")) {
			st = new SwitchTile(n, SWITCH_PRICE);
		} else {
			st = null;
		}
		return st;
	}
	
	/**
	 * get the price of boom tile
	 * @return boom tile's price
	 */
	public int getBoomPrice(){
		return BOOM_PRICE;
	}
	
	/**
	 * get the price of reverse tile
	 * @return reverse tile's price
	 */
	public int getReversePrice(){
		return REVERSE_PRICE;
	}
	
	/**
	 * get the price of negative tile
	 * @return negative tile's price
	 */
	public int getNegativePrice(){
		return NEGATIVE_PRICE;
	}
	
	/**
	 * get the price of zero tile
	 * @return zero tile's price
	 */
	public int getZeroPrice(){
		return ZERO_PRICE;
	}
	
	/**
	 * get the price of switch tile
	 * @return switch tile's price
	 */
	public int getSwitchPrice(){
		return SWITCH_PRICE;
	}

}
