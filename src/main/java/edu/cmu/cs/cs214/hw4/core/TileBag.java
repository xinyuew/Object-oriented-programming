package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This TileBag class contains all letter tile
 * @author xinyuewu
 *
 */
public class TileBag {
	private ArrayList<LetterTile> letterTiles = new ArrayList<LetterTile>();
	
	/**
	 * constructor for tile bag
	 * at the beginning of the game, should initialize tile bag
	 */
	public TileBag(){
		int count = 0;
		//CHECKSTYLE:OFF
		for(count=0;count<12;count++){
			letterTiles.add(new LetterTile("E",1));
		}
		
		for(count=0;count<9;count++){
			letterTiles.add(new LetterTile("A",1));
			letterTiles.add(new LetterTile("I",1));
		}
		
		for(count=0;count<8;count++){
			letterTiles.add(new LetterTile("O",1));
		}
		
		for(count=0;count<6;count++){
			letterTiles.add(new LetterTile("N",1));
			letterTiles.add(new LetterTile("R",1));
			letterTiles.add(new LetterTile("T",1));
		}
		
		for(count=0;count<4;count++){
			letterTiles.add(new LetterTile("L",1));
			letterTiles.add(new LetterTile("S",1));
			letterTiles.add(new LetterTile("U",1));
			letterTiles.add(new LetterTile("D",2));
		}
		
		for(count=0;count<3;count++){
			letterTiles.add(new LetterTile("G",2));
		}
		
		for(count=0;count<2;count++){
			letterTiles.add(new LetterTile("B",3));
			letterTiles.add(new LetterTile("C",3));
			letterTiles.add(new LetterTile("M",3));
			letterTiles.add(new LetterTile("P",3));
			letterTiles.add(new LetterTile("F",4));
			letterTiles.add(new LetterTile("H",4));
			letterTiles.add(new LetterTile("V",4));
			letterTiles.add(new LetterTile("W",4));
			letterTiles.add(new LetterTile("Y",4));

		}
		letterTiles.add(new LetterTile("K",5));
		letterTiles.add(new LetterTile("J",8));
		letterTiles.add(new LetterTile("X",8));
		letterTiles.add(new LetterTile("Q",10));
		letterTiles.add(new LetterTile("Z",10));
			
		//CHECKSTYLE:ON
	}
	
	/**
	 * check if this tile bag is empty
	 * @return is empty or not
	 */
	public boolean isEmpty(){
		return letterTiles.size() == 0;
	}
	
	/**
	 * get how many tiles are in the tile bag
	 * @return the number of tiles
	 */
	public int getSize(){
		return letterTiles.size();
	}
	
	/**
	 * When this tile is taken by player
	 * remove this tile from tile bag
	 * @param lt is the tile which you want to remove
	 */
	public void removeTile(LetterTile lt){
		letterTiles.remove(lt);
	}
	
	/**
	 * If player exchange tiles, add these tile to the tile bag
	 * @param list is a list of tiles which the player do not need
	 */
	public void addTile(ArrayList<LetterTile> list){
		for (LetterTile lt:list){
			lt.setOwner(null);
			letterTiles.add(lt);
		}	
	}
	
	/**
	 * shuffle this tile bag, in order to get random tile
	 * @param list is a list contains all letter tiles have not been played.
	 */
	public void shuffle(ArrayList<LetterTile> list){
		Collections.shuffle(letterTiles,new Random(System.currentTimeMillis()));
	}
	
	/**
	 * If player want to exchange tiles
	 * get them certain amount tiles randomly chosen from tile bag
	 * @param num is how many tiles player want to exchange
	 * @return a list of tile
	 */
	public ArrayList<LetterTile> getRandomLetterTile(int num){
		ArrayList<LetterTile> tiles = new ArrayList<LetterTile>();
		for (int i=0;i<num;i++){
			Random random = new Random(System.currentTimeMillis());
			int index=random.nextInt(getSize());
			tiles.add(letterTiles.get(index));
			letterTiles.remove(index);
			
		}
		return tiles;
	}
}
