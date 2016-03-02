package edu.cmu.cs.cs214.hw4.core;

/**
 * This class is describe a placement in one turn
 * 
 * @author xinyuewu
 *
 */
public class Placement {
	private Location location;
	private LetterTile letterTile;
	private SpecialTile specialTile;

	/**
	 * This constructor is for letter tile placement
	 * 
	 * @param lt
	 *            is the letter tile played in this placement
	 * @param loc
	 *            is location of this placement.
	 */
	public Placement(LetterTile lt, Location loc) {
		location = loc;
		letterTile = lt;
	}

	/**
	 * This constructor is for special tile placement
	 * 
	 * @param st
	 *            is the special tile played in this placement
	 * @param loc
	 *            is the location of this placement
	 */
	public Placement(SpecialTile st, Location loc) {
		specialTile = st;
		location = loc;
	}

	/**
	 * Getter
	 * 
	 * @return the location of this placement
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Getter
	 * 
	 * @return the letter tile of this placement
	 */
	public LetterTile getLetterTile() {
		return letterTile;
	}

	/**
	 * Getter
	 * 
	 * @return the special tile of this placement
	 */
	public SpecialTile getSpecialTile() {
		return specialTile;
	}

	/**
	 * If the player move this tile again from one location to another, set a
	 * new location
	 * 
	 * @param loc is new location
	 */
	public void setLocation(Location loc) {
		location = loc;
	}
}
