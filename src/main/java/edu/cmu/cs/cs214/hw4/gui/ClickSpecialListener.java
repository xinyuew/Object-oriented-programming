package edu.cmu.cs.cs214.hw4.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.cmu.cs.cs214.hw4.core.Game;

/**
 * this action listener react to the event
 * where a special tile button is clicked
 * @author xinyuewu
 *
 */
public class ClickSpecialListener implements ActionListener{
	private Game game;
	private String name;
	
	/**
	 * constructor
	 * @param g the instance of game
	 * @param n name of special tile
	 */
	public ClickSpecialListener(Game g,String n){
		game = g;
		name = n;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.addTempSpeicalTile(name);
		
	}

}
