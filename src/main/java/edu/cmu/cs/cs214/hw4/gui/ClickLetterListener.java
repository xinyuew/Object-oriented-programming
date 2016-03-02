package edu.cmu.cs.cs214.hw4.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.cmu.cs.cs214.hw4.core.Game;

/**
 * this action listener react to 
 * the event where a letter button is clicked
 * @author xinyuewu
 *
 */
public class ClickLetterListener implements ActionListener{
	
	private int index;
	private Game game;
	
	/**
	 * constructor
	 * @param g a instance of the game
	 * @param i index of the letter
	 */
	public ClickLetterListener(Game g,int i){
		index = i;
		game = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.addTempLetterTile(index);	
	}

}
