package edu.cmu.cs.cs214.hw4.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.cmu.cs.cs214.hw4.core.Game;

/**
 * action listener for event where a square is clicked
 * @author xinyuewu
 *
 */
public class SquareListener implements ActionListener{
	
	private int row;
	private int col;
	private Game game;
	
	/**
	 * constructor
	 * @param g the game that is running
	 * @param r row of the square
	 * @param c column of the square
	 */
	public SquareListener(Game g,int r,int c){
		row = r;
		col = c;
		game = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.placeLetterTile(row,col);
		game.placeSpecialTile(row, col);
		
	}

}
