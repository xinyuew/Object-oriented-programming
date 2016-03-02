package edu.cmu.cs.cs214.hw4.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.cmu.cs.cs214.hw4.core.Game;

/**
 * this panel shows current player's name and score
 * @author xinyuewu
 *
 */
public class StatusPanel extends JPanel{
	private Game game;
	private JLabel currentPlayerLabel;
	
	/**
	 * constructor 
	 * @param g the game that is running
	 */
	public StatusPanel(Game g){
		game = g;
		add(createStatusPanel());
	}
	
	private JPanel createStatusPanel(){
		JPanel statusPanel = new JPanel();
		currentPlayerLabel = new JLabel();
		currentPlayerLabel.setText("Current Player: "+game.getCurrentPlayer().getName());
		
		statusPanel.add(currentPlayerLabel);
		
		return statusPanel;
	}
	
	/**
	 * called when a new turn is triggered, change
	 * displayed information of current player
	 */
	public void playerChanged(){
		currentPlayerLabel.setText("Current Player: "+game.getCurrentPlayer().getName());
	}
}
