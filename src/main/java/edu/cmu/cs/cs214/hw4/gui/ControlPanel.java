package edu.cmu.cs.cs214.hw4.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.cmu.cs.cs214.hw4.core.Game;
import edu.cmu.cs.cs214.hw4.core.Player;

/**
 * this panel is located on the right part
 * of game window. it provide buttons for
 * user to choose play, pass or exchange.
 * it also provide buttons to buy special tiles
 * @author xinyuewu
 *
 */
public class ControlPanel extends JPanel{
	private Game game;
	private JPanel scorePanel;
	private JPanel helpPanel;
	private ArrayList<JLabel> scoreLabels;
	private static final int SPECIAL_TILE = 5;
	private static final int CHOICE = 4;
	private final Font titleFont;
	private static final int WORD_SIZE=20;
	
	/**
	 * constructor 
	 * @param g an instance of game
	 */
	public ControlPanel(Game g){
		game = g;
		titleFont = new Font(Font.DIALOG,Font.BOLD,WORD_SIZE);
		scorePanel = new JPanel();
		scoreLabels = new ArrayList<JLabel>();
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(createHelpPanel());
		add(createScorePanel());
		add(createSpecialShopPanel());
		add(createControlButtonPanel());
		
	}
	private JPanel createHelpPanel(){
		helpPanel = new JPanel();
		JButton helpButton = new JButton("HELP");
		helpButton.setForeground(Color.RED);
		helpButton.setFont(new Font(Font.MONOSPACED,Font.BOLD,WORD_SIZE));
		
		ActionListener helpListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				showHelp();		
			}
			
		};
		
		helpButton.addActionListener(helpListener);

		helpPanel.add(helpButton);
		return helpPanel;
	}
	
	private void showHelp(){
		String message;
		message ="Instructions:\n";
		message+="\n";
		message+="-If you want to play tiles on the board, click the letter tile or special tile\n";
		message+="-Then click a location on the board where you would like to play\n";
		message+="-Before you click 'Play' button,'Pass' button or 'Exchange' button\n";
		message+="-You can click 'Retrieve' button at any time to undo all placement in this turn\n";
		message+="-After you confirmed your placement, click 'Play' button\n";
		message+="-If these placements are invalid, it will automatically retrieve all placements\n";
		message+="\n";
		message+="-If you want to exchange some letter tiles, click all the letter tiles you want to exchange\n";
		message+="-If you clicked some letter tiles before but you would like to exchange other tiles instead of these\n";
		message+="-Click 'Retrieve' button to empty all choosed tiles, then re-choose\n";
		message+="\n";
		message+="-Click 'Pass' button to pass this turn\n";
		message+="\n";
		message+="-If all players choose 'Pass', game end\n";
		message+="-If there are less than 7 letter tiles in the tile bag, game end\n";
		JFrame frame = (JFrame) SwingUtilities.getRoot(this);
		showDialog(frame, "Help!", message);
	}
	
	private static void showDialog(Component component, String title,String message) {
		JOptionPane.showMessageDialog(component, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	private JPanel createScorePanel(){
		int playerNum = game.getAllPlayer().size();
		ArrayList<Player> players = game.getAllPlayer();
		scorePanel.setLayout(new GridLayout(playerNum+1,1));
		JLabel scoreBoardLabel = new JLabel();
		scoreBoardLabel.setText("Score Board");
		scoreBoardLabel.setFont(titleFont);
		scoreBoardLabel.setForeground(Color.ORANGE);
		scorePanel.add(scoreBoardLabel);
		
		for(int i=0;i<playerNum;i++){
			JLabel scoreLabel = new JLabel();
			scoreLabel.setText(players.get(i).getName()+": "+players.get(i).getScore());
			scoreLabels.add(scoreLabel);
			scorePanel.add(scoreLabel);
		}
		
		
		
		return scorePanel;
	}
	
	private JPanel createSpecialShopPanel(){
		JPanel specialPanel = new JPanel(new GridLayout(SPECIAL_TILE+1,1));
		JLabel specialLabel = new JLabel("Special Tile shop:");
		specialLabel.setFont(titleFont);
		specialLabel.setForeground(Color.ORANGE);
		JButton boomButton = new JButton("Boom Tile"+": "+game.getSpecialTileStore().getBoomPrice());
		JButton reverseButton = new JButton("Reverse order Tile"+": "+game.getSpecialTileStore().getReversePrice());
		JButton negativeButton = new JButton("Negative score Tile"+": "+game.getSpecialTileStore().getNegativePrice());
		JButton zeroButton = new JButton("Zero score Tile"+": "+game.getSpecialTileStore().getZeroPrice());
		JButton switchButton = new JButton("Score-switch Tile"+": "+game.getSpecialTileStore().getSwitchPrice());
		
		boomButton.addActionListener(new BuySpecialListener(game,"Boom"));
		reverseButton.addActionListener(new BuySpecialListener(game,"Reverse"));
		negativeButton.addActionListener(new BuySpecialListener(game,"Negative"));
		zeroButton.addActionListener(new BuySpecialListener(game,"Zero"));
		switchButton.addActionListener(new BuySpecialListener(game,"Switch"));
		
		
		specialPanel.add(specialLabel);
		specialPanel.add(boomButton);
		specialPanel.add(reverseButton);
		specialPanel.add(negativeButton);
		specialPanel.add(zeroButton);
		specialPanel.add(switchButton);
		
		return specialPanel;
	}
	
	
	private JPanel createControlButtonPanel(){
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(CHOICE+1,1));
		JLabel label = new JLabel("Choose one action:");
		label.setFont(titleFont);
		label.setForeground(Color.ORANGE);
		JButton passButton = new JButton("Pass");
		JButton playButton = new JButton("Play");
		JButton exchangeButton = new JButton("Exchange");
		JButton retrieveButton = new JButton("Retrieve");
		
		//pass action listener
		ActionListener passListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.pass();			
			}
			
		};
		passButton.addActionListener(passListener);
		
		
		//play action listener
		ActionListener playListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.play();
				
			}
			
		};
		playButton.addActionListener(playListener);
		
		ActionListener exchangeListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.exchange(game.getTempLetterTiles());
				
			}
			
		};
		exchangeButton.addActionListener(exchangeListener);
		
		//retrieve all the tiles placed in this turn
		ActionListener retrieveListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.retrieveTiles();
				
			}
			
		};
		retrieveButton.addActionListener(retrieveListener);
		
		
		controlPanel.add(label);
		controlPanel.add(passButton);
		controlPanel.add(playButton);
		controlPanel.add(exchangeButton);
		controlPanel.add(retrieveButton);
		return controlPanel;
	}
	
	
	/**
	 * update the score changed
	 */
	public void scoreChanged(){
		for(int i=0;i<game.getAllPlayer().size();i++){
			String text = game.getAllPlayer().get(i).getName()+": "+game.getAllPlayer().get(i).getScore();
			scoreLabels.get(i).setText(text);
		}
	}

}
