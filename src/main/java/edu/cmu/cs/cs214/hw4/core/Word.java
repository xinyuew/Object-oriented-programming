package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;

/**
 * This class represents a word formed in this turn
 * 
 * @author xinyuewu
 *
 */
public class Word {
	private ArrayList<Square> squares;
	private String content;

	/**
	 * Constructor
	 * 
	 * @param s
	 *            is a list of squares which form this word
	 */
	public Word(ArrayList<Square> s) {
		squares = s;
		content = "";
	}

	/**
	 * get word as string format
	 * 
	 * @param move
	 *            is move in this turn
	 * @return string
	 */
	public String getContent(Move move) {
		content = "";
		for (Square s : squares) {
			if (s.getLetterTile() == null) {
				for (Placement p : move.getLetterPlacements()) {
					if (p.getLocation().equals(s.getLocation())) {
						content += p.getLetterTile().getLetter();
					}
				}
			} else {
				content += s.getLetterTile().getLetter();
			}

		}
		return content;
	}

	/**
	 * compute score for this word
	 * 
	 * @return total score
	 */
	public int getScore() {
		int total = 0;
		int wordMultiplier = 1;
		for (Square s : squares) {
			if (s.getLetterTile() != null) {
				total += s.getLetterMultiplier()
						* (s.getLetterTile().getScore());
				wordMultiplier *= s.getWordMultiplier();
			}
		}
		total = total * wordMultiplier;
		return total;
	}

	/**
	 * Clear all words in last turn
	 */
	public void clear() {
		squares.clear();
		content = "";
	}

}
