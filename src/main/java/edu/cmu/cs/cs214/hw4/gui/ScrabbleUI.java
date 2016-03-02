package edu.cmu.cs.cs214.hw4.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import edu.cmu.cs.cs214.hw4.core.Game;

/**
 * this class include frame and basic panel
 * @author xinyuewu
 *
 */
public class ScrabbleUI{
	private Game game;
	private final JFrame frame;
	private static final String NAME = "Scrabble Game";
	private static final int NAME_FILED_WIDTH = 20;
	private static final int STATUS_FILED_WIDTH = 30;
	private static final int TEXT_LENGTH_A = 20;
	private static final int TEXT_LENGTH_B = 30;
	private JPanel startPanel;
	private JPanel gamePanel;
	
	/**
	 * constructor
	 */
	public ScrabbleUI(){
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game = new Game();
		startPanel = new JPanel(new BorderLayout());
		frame.setContentPane(startPanel);
		
		JLabel nameLabel = new JLabel("Player's name:");
		startPanel.add(nameLabel,BorderLayout.WEST);
		
		JTextField nameField = new JTextField(TEXT_LENGTH_A);
		nameField.requestFocus();
		startPanel.add(nameField,BorderLayout.CENTER);
		
		JButton addButton = new JButton("Add participant");
		startPanel.add(addButton,BorderLayout.EAST);
		
		JTextField statusField = new JTextField(TEXT_LENGTH_B);
		statusField.setBackground(null);
		statusField.setForeground(Color.RED);
		statusField.setEditable(false);
		startPanel.add(statusField,BorderLayout.NORTH);
		
		ActionListener addParticipantListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				statusField.setText("");
				String name = nameField.getText();
				if(!name.isEmpty()){
					game.addPlayer(name);
					nameField.setText("");
					nameField.requestFocus();
				} else {		
					statusField.setText("Player's name cannot be empty");	
					return;
				}
				
			}
			
		};
		
		addButton.addActionListener(addParticipantListener);
		
		JButton startButton = new JButton("Strat Game");
		startPanel.add(startButton,BorderLayout.SOUTH);
		
		ActionListener startListener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(game.initialGame()){
					startGame();
				} else {
					statusField.setText("Must have 2-4 player");
				}
				
			}
			
		};
		
		startButton.addActionListener(startListener);
		
		//Display window
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	/**
	 * main method, show the frame
	 * @param args is input
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				new ScrabbleUI();
				
			}
			
		});
	}
	
	
	private void startGame(){
		frame.remove(startPanel);
		
		gamePanel = new GamePanel(game);
		frame.setContentPane(gamePanel);
		
		
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
