package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;

/**
 * This class represent all the letter tiles a player owns in this turn
 * 
 * @author xinyuewu
 *
 */
public class Rack {
	private ArrayList<LetterTile> letterTilesRack;
	private static final int NUM = 7;
	
	/**
	 * constructor
	 */
	public Rack(){
		letterTilesRack = new ArrayList<LetterTile>();
	}
	
	/**
	 * add a letter tile to this player's rack
	 * @param lt is a letter tile
	 * @return true if add successfully
	 */
	public boolean addLetterTile(LetterTile lt){
		if(letterTilesRack.size()<NUM+1){
			letterTilesRack.add(lt);
			return true;
		}
		return false;
	}
	
	/**
	 * add a list of new tiles to rack
	 * @param list a list of tiles got from tile bag
	 * @return if add success
	 */
	public boolean addTiles(ArrayList<LetterTile> list){
		if (list.size()+letterTilesRack.size() == NUM){
			letterTilesRack.addAll(list);
			return true;
		}
		return false;
	}
	
	/**
	 * remove a list of letter tiles after player has used them
	 * @param list is a list of letter tiles have been used in this turn
	 * @return if remove success
	 */
	public boolean removeTiles(ArrayList<LetterTile> list){
		if(list.size()<letterTilesRack.size() || list.size()==letterTilesRack.size()){
			letterTilesRack.removeAll(list);
			return true;
		}
		return false;
	}
	
	/**
	 * remove a letter tile from rack
	 * @param lt is the letter tile should be removed
	 */
	public void removeTile(LetterTile lt){
		if(letterTilesRack.size()>0 &&  letterTilesRack.contains(lt)){
			letterTilesRack.remove(lt);
		}
	}
	
	/**
	 * get how many tiles are remained in this player's rack
	 * @return number
	 */
	public int getSize(){
		return letterTilesRack.size();
	}
	
	/**
	 * get all the letter tiles in this player's rack
	 * @return a list of letter tile
	 */
	public ArrayList<LetterTile> getLetterTiles(){
		return letterTilesRack;
	}
	
	/**
	 * Check if this letter tile is in this player's rack
	 * @param lt is letter tile
	 * @return true if this player has this letter tile
	 */
	public boolean contains(LetterTile lt){
		for(LetterTile letterTile:letterTilesRack){
			if(letterTile.equals(lt)){
				return true;
			}
		}
		return false;
	}
}
