package edu.cmu.cs.cs214.hw4.core;

import java.awt.Color;
import java.util.ArrayList;

/**
 * This class represent a single square on the board
 * @author xinyuewu
 *
 */
public class Square {
	private LetterTile letterTile;
	private ArrayList<SpecialTile> specialTiles;
	private int letterMultiplier;
	private int wordMultiplier;
	private Location location;
	private static final int THREE = 3;
	
	/**
	 * Constructor
	 * @param lm is letter multiplier
	 * @param wm is word multiplier
	 * @param row is row of this square
	 * @param col is column of this square
	 */
	public Square(int lm,int wm,int row,int col){
		letterTile = null;
		specialTiles = new ArrayList<SpecialTile>();
		letterMultiplier = lm;
		wordMultiplier = wm;
		location = new Location(row,col);
	}
	
	/**
	 * Check if this square is an empty square
	 * @return true is this square is empty
	 */
	public boolean isEmpty(){
		return letterTile == null && specialTiles.size() == 0;
	}
	
	/**
	 * Check if this square has letter tile
	 * @return true if there is no letter tile
	 */
	public boolean isNoLetterTile(){
		return letterTile == null;
	}
	
	/**
	 * Check if this square has special tiles
	 * @return true if there is no special tiles
	 */
	public boolean isNoSpecialTiles(){
		return specialTiles.size() == 0;
	}
	
	/**
	 * remove all special tiles after triggered
	 */
	public void removeSpecialTile(){
		specialTiles.clear();
	}
	
	/**
	 * remove the letter tile when there is boom tile
	 */
	public void removeLetterTile(){
		letterTile = null;
	}
	
	/**
	 * if player play many special tiles on this square
	 * add these tiles to the special tile list
	 * @param list is a list of special tile played in this turn
	 */
	public void addSpecialTileList(ArrayList<SpecialTile> list){
		specialTiles.addAll(list);
	}
	
	/**
	 * if player play a letter tiles on this square
	 * add this tile to the square
	 * @param lt is the letter tile played in this turn
	 */
	public void addLetterTile(LetterTile lt){
		letterTile = lt;
	}
	
	/**
	 * add a new special tile to this square
	 * @param st is special tile
	 */
	public void addSpecialTile(SpecialTile st){
		specialTiles.add(st);
	}
	
	/**
	 * get this square's location
	 * @return location
	 */
	public Location getLocation(){
		return location;
	}
	
	/**
	 * get this square's letter multiplier
	 * @return letter multiplier
	 */
	public int getLetterMultiplier(){
		return letterMultiplier;
	}
	/**
	 * get this square's word multiplier
	 * @return word multiplier
	 */
	public int getWordMultiplier(){
		return wordMultiplier;
	}
	
	/**
	 * get the letter tile in this square
	 * If there is no letter tile, return null
	 * @return letter tile
	 */
	public LetterTile getLetterTile(){
		return letterTile;	
	}
	
	/**
	 * get all special tiles on this square
	 * @return a list of special tiles
	 */
	public ArrayList<SpecialTile> getSpecialTile(){
		return specialTiles;
	}
	
	/**
	 * get this square's color
	 * @return color
	 */
	public Color getColor(){
		if(letterMultiplier == 2){
			return Color.CYAN;
		} else if (letterMultiplier == THREE){
			return Color.BLUE;
		} else if (wordMultiplier == 2){
			return Color.PINK;
		} else if (wordMultiplier == THREE){
			return Color.RED;
		}
		return Color.WHITE;
	}
}
