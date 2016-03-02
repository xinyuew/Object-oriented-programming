package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;

/**
 * This class represent game board It contains all squares
 * 
 * @author xinyuewu
 *
 */
public class Board {
	private ArrayList<ArrayList<Square>> squares;
	private ArrayList<Word> words;
	private ArrayList<Square> squaresWithSpecialTile;
	private static final int ROW = 15;
	private static final int COL = 15;
	private static final int THREE = 3;
	private static final int[] TW = { 0, 7, 14, 105, 119, 210, 217, 224 };
	private static final int[] DW = { 16, 28, 32, 42, 48, 56, 64, 70, 112, 154,
			160, 168, 176, 182, 192, 196, 208 };
	private static final int[] TL = { 20, 24, 76, 80, 84, 88, 136, 140, 144,
			148, 200, 204 };
	private static final int[] DL = { 3, 11, 36, 38, 45, 52, 59, 92, 96, 98,
			102, 108, 116, 122, 126, 128, 132, 165, 172, 179, 186, 188, 213,
			221 };

	/**
	 * constructor
	 */
	public Board() {
		ArrayList<Square> squareRow;
		words = new ArrayList<Word>();
		squares = new ArrayList<ArrayList<Square>>();
		squaresWithSpecialTile = new ArrayList<Square>();
		for (int row = 0; row < ROW; row++) {
			squareRow = new ArrayList<Square>();
			for (int col = 0; col < COL; col++) {
				int index = row * COL + col;
				boolean add = false;
				for (int i : TW) {
					if (index == i) {
						squareRow.add(new Square(1, THREE, row, col));
						add = true;
					}
				}
				for (int i : DW) {
					if (index == i) {
						squareRow.add(new Square(1, 2, row, col));
						add = true;
					}
				}
				for (int i : TL) {
					if (index == i) {
						squareRow.add(new Square(THREE, 1, row, col));
						add = true;
					}
				}
				for (int i : DL) {
					if (index == i) {
						squareRow.add(new Square(2, 1, row, col));
						add = true;
					}
				}
				if (add == false) {
					squareRow.add(new Square(1, 1, row, col));
				}
			}
			squares.add(squareRow);
		}
	}

	/**
	 * Find a square, given the location of this square
	 * 
	 * @param loc
	 *            is location
	 * @return a square which has the same location as parameter
	 */
	public Square getSquareByLocation(Location loc) {
		int row = loc.getRow();
		int col = loc.getCol();
		return squares.get(row).get(col);
	}

	/**
	 * Check if this board is empty
	 * 
	 * @return true if the board is empty
	 */
	public boolean isEmpty() {
		for (int row = 0; row < ROW; row++) {
			for (int col = 0; col < COL; col++) {
				if (squares.get(row).get(col).isEmpty() == false) {
					return false;
				}
			}
		}
		return true;

	}

	/**
	 * check if all the letter tiles played in this turn has at least on tile is
	 * adjacent to existing letter tiles on the board.
	 * 
	 * @param m
	 *            is Move of this turn
	 * @return true or false
	 */
	public boolean isAdjacentToExistingTile(Move m) {

		// if there is only one placement
		// find this tile has neighbor or not
		ArrayList<Placement> letterPlacements = m.getLetterPlacements();
		for (Placement p : letterPlacements) {
			if (isNotEmptyAround(p.getLocation())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Given a certain location, check north,south,west and east direction if
	 * there is a letter tile in one of these location, return true
	 * 
	 * @param loc
	 *            is location
	 * @return true or false
	 */
	private boolean isNotEmptyAround(Location loc) {
		ArrayList<Square> around = new ArrayList<Square>();
		if (loc.getRow() > 0) {
			around.add(squares.get(loc.getRow() - 1).get(loc.getCol()));
		}
		if (loc.getRow() < ROW - 1) {
			around.add(squares.get(loc.getRow() + 1).get(loc.getCol()));
		}
		if (loc.getCol() > 0) {
			around.add(squares.get(loc.getRow()).get(loc.getCol() - 1));
		}
		if (loc.getCol() < COL - 1) {
			around.add(squares.get(loc.getRow()).get(loc.getCol() + 1));
		}
		if (around.size() > 0) {
			for (Square s : around) {
				if (s.isNoLetterTile() == false) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Get the center of this board
	 * 
	 * @return a location
	 */
	private Location getCenter() {
		Location center = new Location(ROW / 2, COL / 2);
		return center;
	}

	/**
	 * In the first turn of the game Player much play one letter tile at the
	 * center location of the board
	 * 
	 * @param m
	 *            is Move
	 * @return if there is a tile at center or not
	 */
	public boolean isCenter(Move m) {
		ArrayList<Placement> letterPlacements = m.getLetterPlacements();
		for (Placement p : letterPlacements) {
			if (getCenter().equals(p.getLocation())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check all the locations where player want to play letter tiles are not
	 * occupied by other letter tiles
	 * 
	 * @param m
	 *            is move in this turn
	 * @return true if been occupied, false for not occupied
	 */
	public boolean isOccupied(Move m) {
		ArrayList<Placement> letterPlacements = m.getLetterPlacements();
		for (Placement p : letterPlacements) {
			if (getSquareByLocation(p.getLocation()).isNoLetterTile() == false) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check all the locations where player want to play letter tiles are on the
	 * board
	 * 
	 * @param m
	 *            is move in this turn
	 * @return true if all locations are on board
	 */
	public boolean isOnBoard(Move m) {
		ArrayList<Placement> letterPlacements = m.getLetterPlacements();
		for (Placement p : letterPlacements) {
			int row = p.getLocation().getRow();
			int col = p.getLocation().getCol();
			if (row < 0 || row > ROW - 1 || col < 0 || col > COL - 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * letter tiles played in this turn, and letter tiles which are already on
	 * the board and have the same row or same column as tiles in this turn, all
	 * these tiles should be consecutive
	 * 
	 * @param m
	 *            is move in this turn
	 * @return true if these tiles are consecutive
	 */
	public boolean isConsecutive(Move m) {
		ArrayList<Placement> letterPlacements = m.getLetterPlacements();
		int max = m.getMaxIndex();
		int min = m.getMinIndex();
		int num = 0;
		for (int i = min; i < max + 1; i++) {
			if (m.isHorizontal()) {
				int row = letterPlacements.get(0).getLocation().getRow();
				if (squares.get(row).get(i).isNoLetterTile() == false) {
					num++;
				}
			} else if (m.isVertical()) {
				int col = letterPlacements.get(0).getLocation().getCol();
				if (squares.get(i).get(col).isNoLetterTile() == false) {
					num++;
				}
			} else if (m.isSingleLetter()) {
				return true;
			}
		}
		if (num + letterPlacements.size() == max - min + 1) {
			return true;
		}
		return false;
	}

	/**
	 * Given move in this turn, get all the word formed by letter tiles in this
	 * turn
	 * 
	 * @param m
	 *            is move in this turn
	 * @return all words
	 */
	public ArrayList<Word> getWords(Move m) {
		words.clear();
		ArrayList<Placement> letterPlacements = m.getLetterPlacements();

		// these movements are horizontal
		// not include single tile condition
		// each tiles has same row
		if (m.isHorizontal()) {
			ArrayList<Square> word = new ArrayList<Square>();
			int row = letterPlacements.get(0).getLocation().getRow();

			// find horizontal word first
			int i = m.getMinIndex() - 1;
			int min = m.getMinIndex();
			while (i > -1) {
				if (squares.get(row).get(i).isNoLetterTile() == false) {
					min = i;
					i--;
				} else {
					break;
				}
			}

			i = m.getMaxIndex() + 1;
			int max = m.getMaxIndex();
			while (i < COL) {
				if (squares.get(row).get(i).isNoLetterTile() == false) {
					max = i;
					i++;
				} else {
					break;
				}
			}
			for (i = min; i < max + 1; i++) {
				word.add(squares.get(row).get(i));
			}
			Word horizontalWord = new Word(word);
			words.add(horizontalWord);

			// find all vertical words
			for (Placement p : letterPlacements) {
				Word w = getVerticalWord(m, p);
				if (w != null) {
					words.add(w);
				}
			}

			// if these movements are vertical
			// each tile has same column
		} else if (m.isVertical()) {
			ArrayList<Square> word = new ArrayList<Square>();
			int col = letterPlacements.get(0).getLocation().getCol();

			// find vertical word first
			int i = m.getMinIndex() - 1;
			int min = m.getMinIndex();
			while (i > -1) {
				if (squares.get(i).get(col).isNoLetterTile() == false) {
					min = i;
					i--;
				} else {
					break;
				}
			}

			i = m.getMaxIndex() + 1;
			int max = m.getMaxIndex();
			while (i < ROW) {
				if (squares.get(i).get(col).isNoLetterTile() == false) {
					max = i;
					i++;
				} else {
					break;
				}
			}
			for (i = min; i < max + 1; i++) {
				word.add(squares.get(i).get(col));
			}
			Word verticalWord = new Word(word);
			words.add(verticalWord);

			// find all horizontal words
			for (Placement p : letterPlacements) {
				Word w = getHorizontalWord(m, p);

				// if w == null, this tile has no neighbor at horizontal
				// direction
				if (w != null) {
					words.add(w);
				}
			}

			// consider single tile condition
			// this tile is adjacent to existing letter tiles
		} else if (m.isSingleLetter()) {
			Placement p = letterPlacements.get(0);
			Word w;
			w = getHorizontalWord(m, p);
			if (w != null) {
				words.add(w);
			}
			w = getVerticalWord(m, p);
			if (w != null) {
				words.add(w);
			}

		}
		return words;

	}

	/**
	 * Given a placement, get horizontal direction word for this placement
	 * 
	 * @param move
	 *            is move in this turn
	 * @param p
	 *            is placement
	 * @return word
	 */
	public Word getHorizontalWord(Move move, Placement p) {
		ArrayList<Square> horizontalSquares = new ArrayList<Square>();
		int row = p.getLocation().getRow();
		int col = p.getLocation().getCol();
		int max;
		int min;
		int index = col;
		while (index < COL
				&& ((squares.get(row).get(index).isNoLetterTile() == false) || (move
						.containPlacement(new Location(row, index))))) {
			index++;
		}
		max = index - 1;
		index = col;
		while (index > 0
				&& ((squares.get(row).get(index).isNoLetterTile() == false) || (move
						.containPlacement(new Location(row, index))))) {
			index--;
		}
		min = index + 1;
		if (max == min) {
			return null;
		}
		for (index = min; index < max + 1; index++) {
			horizontalSquares.add(squares.get(row).get(index));
		}
		Word horizontalWord = new Word(horizontalSquares);

		return horizontalWord;

	}

	/**
	 * Given a placement, get vertical direction word for this placement
	 * 
	 * @param move
	 *            is move in this turn
	 * @param p
	 *            is placement
	 * @return word
	 */
	public Word getVerticalWord(Move move, Placement p) {
		ArrayList<Square> verticalSquares = new ArrayList<Square>();
		int row = p.getLocation().getRow();
		int col = p.getLocation().getCol();
		int max;
		int min;
		int index = row;
		while (index < ROW
				&& ((squares.get(index).get(col).isNoLetterTile() == false) || (move
						.containPlacement(new Location(index, col))))) {
			index++;
		}
		max = index - 1;
		index = row;
		while (index > -1
				&& ((squares.get(index).get(col).isNoLetterTile() == false) || (move
						.containPlacement(new Location(index, col))))) {
			index--;
		}
		min = index + 1;
		if (max == min) {
			return null;
		}
		for (index = min; index < max + 1; index++) {
			verticalSquares.add(squares.get(index).get(col));
		}
		Word verticalWord = new Word(verticalSquares);
		return verticalWord;
	}

	/**
	 * Check if placements of special tiles are all valid
	 * 
	 * @param m
	 *            is move in this turn
	 * @return true if special tiles are valid
	 */
	public boolean isSpecialValid(Move m) {
		ArrayList<Placement> specialPlcaements = m.getSpecialPlacements();
		if (specialPlcaements.size() == 0) {
			return true;
		}
		for (Placement p : specialPlcaements) {
			Location loc = p.getLocation();
			if (loc.getRow() > ROW - 1 || loc.getRow() < 0
					|| loc.getCol() > COL - 1 || loc.getCol() < 0) {
				return false;
			}
			if (getSquareByLocation(loc).isNoLetterTile() == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Place all the tiles in this turn on the board
	 * 
	 * @param m
	 *            is move in this turn
	 */
	public void placeTiles(Move m) {
		ArrayList<Placement> specialPlcaements = m.getSpecialPlacements();
		ArrayList<Placement> letterPlcaements = m.getLetterPlacements();
		for (Placement p : letterPlcaements) {
			int row = p.getLocation().getRow();
			int col = p.getLocation().getCol();
			squares.get(row).get(col).addLetterTile(p.getLetterTile());
			if (squares.get(row).get(col).getSpecialTile().size() > 0) {
				squaresWithSpecialTile.add(squares.get(row).get(col));
			}
		}
		for (Placement p : specialPlcaements) {
			int row = p.getLocation().getRow();
			int col = p.getLocation().getCol();
			squares.get(row).get(col).addSpecialTile(p.getSpecialTile());
		}
	}

	/**
	 * Compute score in this turn
	 * 
	 * @return score
	 */
	public int computeScore() {
		int total = 0;
		for (Word w : words) {
			total += w.getScore();
		}
		return total;
	}

	/**
	 * Trigger all the special tiles in this turn
	 * 
	 * @param g
	 *            is game
	 * @param m
	 *            is move in this turn
	 */
	public void trigerSpecialTileBefore(Game g, Move m) {
		for (Square s : squaresWithSpecialTile) {
			ArrayList<SpecialTile> specialTiles = s.getSpecialTile();
			if (specialTiles != null && specialTiles.size()>0) {
				for (SpecialTile st : specialTiles) {
					st.makeEffectBefore(g, s);
				}  
			}
			//s.getSpecialTile().clear();
		}
		
		//squaresWithSpecialTile.clear();
	}
	
	/**
	 * trigger all the special tile's effect after calculate score
	 * @param g is an instance of game class
	 * @param m is the move in this turn
	 */
	public void trigerSpecialTileAfter(Game g,Move m){
		for (Square s : squaresWithSpecialTile) {
			ArrayList<SpecialTile> specialTiles = s.getSpecialTile();
			if (specialTiles != null && specialTiles.size()>0) {
				for (SpecialTile st : specialTiles) {
					st.makeEffectAfter(g, s);
				}
			}
			s.getSpecialTile().clear();
		}
		
		squaresWithSpecialTile.clear();
	}

	/**
	 * At the end of this turn clear all the words in this turn
	 */
	public void clearWord() {
		for (Word w : words) {
			w.clear();
		}
		words.clear();

	}

	/**
	 * return the square list
	 * 
	 * @return all the squares in the board
	 */
	public ArrayList<ArrayList<Square>> getSquares() {
		return squares;
	}
}
