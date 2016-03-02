package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class contains all placements in one turn
 * 
 * @author xinyuewu
 *
 */
public class Move {
	private ArrayList<Placement> letterPlacements;
	private ArrayList<Placement> specialPlacements;
	private boolean horizontal;
	private boolean vertical;
	private boolean singleLetter;
	
	/**
	 * constructor
	 */
	public Move(){
		letterPlacements = new ArrayList<Placement>();
		specialPlacements = new ArrayList<Placement>();
		horizontal = false;
		vertical = false;
		singleLetter = false;
	}

	/**
	 * get all the placement of letter tile
	 * 
	 * @return letter tile's placements in this turn
	 */
	public ArrayList<Placement> getLetterPlacements() {
		sort();
		return letterPlacements;
		
	}

	/**
	 * get all the placement of special tile
	 * 
	 * @return special tile's placements in this turn
	 */
	public ArrayList<Placement> getSpecialPlacements() {
		return specialPlacements;
	}
	
	/**
	 * set letter tile placements for this move
	 * @param list is a list of letter tile placements
	 */
	public void setLetterPlacements(ArrayList<Placement> list){
		letterPlacements = list;
	}
	
	/**
	 * set special tile placements for this move
	 * @param list is a list of special tile placements
	 */
	public void setSpecialPlacements(ArrayList<Placement> list){
		specialPlacements = list;
	}

	/**
	 * check if all the letter in this turn is collinear
	 * 
	 * @return collinear or not
	 */
	public boolean isCollinear() {
		if (letterPlacements.size() == 1) {
			singleLetter = true;
			return true;
		} else if(letterPlacements.size()>1) {
			boolean sameRow = true;
			boolean sameCol = true;
			int row = letterPlacements.get(0).getLocation().getRow();
			int col = letterPlacements.get(0).getLocation().getCol();
			for (Placement p : letterPlacements) {
				if (p.getLocation().getRow() != row) {		
					sameRow = false;
				}
				if (p.getLocation().getCol() != col) {
					sameCol = false;
				}
			}
			if (sameRow == true){
				horizontal = true;
				return true;
			}
			if (sameCol == true){
				vertical = true;
				return true;
			}
			return false;
		} else {
			return false;
		}
	}
	
	/**
	 * Sort letterplacements by row or by column
	 */
	public void sort(){
		if(letterPlacements.size()>0){
			Collections.sort(letterPlacements, new Comparator<Placement>(){
				@Override
				public int compare(Placement p1, Placement p2){
					if (horizontal == true){
						return p1.getLocation().getCol() - p2.getLocation().getCol();
					} else if(vertical == true){
						return p1.getLocation().getRow() - p2.getLocation().getRow();
					} else {
						return 0;
					}
				}
			});
		}
	}
	
	/**
	 * If all these letter tiles are placed on the same row
	 * @return true if they are on the same row
	 */
	public boolean isHorizontal(){
		return horizontal;
	}
	
	/**
	 * If all these letter tiles are placed on the same column
	 * @return true if they are on the same column 
	 */
	public boolean isVertical(){
		return vertical;
	}
	
	/**
	 * If there is only one letter tile in this turn
	 * @return true if there is only one letter tile
	 */
	public boolean isSingleLetter(){
		return singleLetter;
	}
	
	/**
	 * If these letter tiles are horizontal, get max column
	 * If these letter tiles are vertical, get max row
	 * @return max index
	 */
	public int getMaxIndex(){
		sort();
		if (horizontal){
			return letterPlacements.get(letterPlacements.size()-1).getLocation().getCol();
		} else if (vertical){
			return letterPlacements.get(letterPlacements.size()-1).getLocation().getRow();
		} else {
			return -1;
		}
	}
	
	/**
	 * If these letter tiles are horizontal, get minimum column
	 * If these letter tiles are vertical, get minimum row
	 * @return minimum index
	 */
	public int getMinIndex(){
		sort();
		if (horizontal){
			return letterPlacements.get(0).getLocation().getCol();
		} else if (vertical){

			return letterPlacements.get(0).getLocation().getRow();
		} else {
			return -1;
		}
	}
	
	
	
	/**
	 * After this move is valid, and make the move
	 * Clean this move in order to stroe new placements in next turn
	 */
	public void clear(){
		letterPlacements.clear();
		specialPlacements.clear();
		horizontal = false;
		vertical = false;
		singleLetter = false;
	}
	
	/**
	 * Check if there is a letter tile at this location in this move
	 * @param loc is location
	 * @return true if there is a letter tile at this location in this move
	 */
	public boolean containPlacement(Location loc){
		for(Placement p:letterPlacements){
			if(p.getLocation().equals(loc)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * If this move is empty
	 * @return true if this move is empty
	 */
	public boolean isEmpty(){
		if(letterPlacements.size()>0){
			return false;
		}
		if(specialPlacements.size()>0){
			return false;
		}
		return true;
	}


}
