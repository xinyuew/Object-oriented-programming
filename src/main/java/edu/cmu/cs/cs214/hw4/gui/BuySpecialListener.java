package edu.cmu.cs.cs214.hw4.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.cmu.cs.cs214.hw4.core.Game;

/**
 * this is a action listener that react to the event
 * where the user click the buying special tile button to
 * buy a special tile.
 * @author xinyuewu
 *
 */
public class BuySpecialListener implements ActionListener{
	private Game game;
	private String name;
	
	/**
	 * Constructor
	 * @param g is an instance of game
	 * @param n is the special tile's name which player want to buy
	 */
	public BuySpecialListener(Game g,String n){
		game = g;
		name = n;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.buySpecialTile(name);	
	}

}
