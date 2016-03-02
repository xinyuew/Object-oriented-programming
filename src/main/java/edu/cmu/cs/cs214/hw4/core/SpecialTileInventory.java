package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;

/**
 * This class represent player's inventory which stores special tiles
 * @author xinyuewu
 *
 */
public class SpecialTileInventory {
	private ArrayList<SpecialTile> specialTiles;
	
	/**
	 * constructor
	 */
	public SpecialTileInventory(){
		specialTiles = new ArrayList<SpecialTile>();
	}
	
	/**
	 * add a new special tile for current player
	 * @param st is special tile
	 */
	public void addSpecialTile(SpecialTile st){
		specialTiles.add(st);
	}
	
	/**
	 * remove a used special tile from current player
	 * @param st is used special tile
	 */
	public void removeSpecialTile(SpecialTile st){
		specialTiles.remove(st);
	}
	
	/**
	 * get this player's special tiles
	 * @return this player's special tiles
	 */
	public ArrayList<SpecialTile> getSpecialTiles(){
		return specialTiles;
	}
	

}
