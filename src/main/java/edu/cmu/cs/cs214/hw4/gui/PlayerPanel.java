package edu.cmu.cs.cs214.hw4.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.cmu.cs.cs214.hw4.core.Game;
import edu.cmu.cs.cs214.hw4.core.LetterTile;
import edu.cmu.cs.cs214.hw4.core.Player;
import edu.cmu.cs.cs214.hw4.core.SpecialTile;

/**
 * this panel is used to display letter tiles and special tiles
 * of current player
 * @author xinyuewu
 *
 */
public class PlayerPanel extends JPanel {
	private Game game;
	private Player currentPlayer;
	private JPanel letterPanel;
	private JPanel specialPanel;
	private List<JButton> letterButtons;
	private JButton boomButton;
	private JButton reverseButton;
	private JButton negativeButton;
	private JButton zeroButton;
	private JButton switchButton;
	private static final int RACK_NUM = 7;

	/**
	 * constructor
	 * 
	 * @param g
	 *            is an instance of game
	 */
	public PlayerPanel(Game g) {
		game = g;
		currentPlayer = game.getCurrentPlayer();
		letterButtons = new ArrayList<JButton>();
		boomButton = new JButton();
		reverseButton = new JButton();
		negativeButton = new JButton();
		zeroButton = new JButton();
		switchButton = new JButton();
		add(createPlayerPanel());
	}

	private JPanel createPlayerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		letterPanel = new JPanel();
		specialPanel = new JPanel();

		JLabel letterLabel = new JLabel("Letter Tiles: ");
		letterPanel.add(letterLabel);
		letterPanel.setLayout(new BoxLayout(letterPanel, BoxLayout.X_AXIS));
		JLabel specialLabel = new JLabel("Special Tiles: ");
		specialPanel.add(specialLabel);
		specialPanel.setLayout(new BoxLayout(specialPanel, BoxLayout.X_AXIS));

		ArrayList<LetterTile> letterTiles = currentPlayer.getRack()
				.getLetterTiles();
		ArrayList<SpecialTile> specialTiles = currentPlayer.getInventory()
				.getSpecialTiles();

		for (int i = 0; i < RACK_NUM; i++) {
			JButton letter = new JButton();
			if (i < letterTiles.size()) {
				int score = letterTiles.get(i).getScore();
				letter.setText(letterTiles.get(i).getLetter() + ": " + score);
			} else {
				letter.setText("");
			}

			letter.addActionListener(new ClickLetterListener(game,i));
			letterButtons.add(letter);

			letterPanel.add(letter);
		}

		calculateInventory(specialTiles);

		boomButton.addActionListener(new ClickSpecialListener(game,"Boom"));
		reverseButton.addActionListener(new ClickSpecialListener(game,"Reverse"));
		negativeButton.addActionListener(new ClickSpecialListener(game,"Negative"));
		zeroButton.addActionListener(new ClickSpecialListener(game,"Zero"));
		switchButton.addActionListener(new ClickSpecialListener(game,"Switch"));
		
		specialPanel.add(boomButton);
		specialPanel.add(reverseButton);
		specialPanel.add(negativeButton);
		specialPanel.add(zeroButton);
		specialPanel.add(switchButton);

		panel.add(letterPanel);
		panel.add(specialPanel);

		return panel;
	}

	private void calculateInventory(ArrayList<SpecialTile> specialTiles) {
		int boom = 0;
		int reverse = 0;
		int negative = 0;
		int zero = 0;
		int switchNum = 0;
		for (SpecialTile st : specialTiles) {
			if (st.getName().equals("Boom")) {
				boom++;
			} else if (st.getName().equals("Reverse")) {
				reverse++;
			} else if (st.getName().equals("Negative")) {
				negative++;
			} else if (st.getName().equals("Zero")) {
				zero++;
			} else if (st.getName().equals("Switch")) {
				switchNum++;
			}
		}
		boomButton.setText("Boom Tile x " + boom);
		if (boom == 0) {
			boomButton.setEnabled(false);
		} else {
			boomButton.setEnabled(true);
		}
		reverseButton.setText("Reverse Tile x " + reverse);
		if (reverse == 0) {
			reverseButton.setEnabled(false);
		} else {
			reverseButton.setEnabled(true);
		}
		negativeButton.setText("Negative Tile x " + negative);
		if (negative == 0) {
			negativeButton.setEnabled(false);
		}else {
			negativeButton.setEnabled(true);
		}
		zeroButton.setText("Zero Tile x " + zero);
		if (zero == 0) {
			zeroButton.setEnabled(false);
		}else {
			zeroButton.setEnabled(true);
		}
		switchButton.setText("Switch Tile x " + switchNum);
		if (switchNum == 0) {
			switchButton.setEnabled(false);
		} else {
			switchButton.setEnabled(true);
		}
	}

	/**
	 * After changing current player, change the rack displayed on the GUI
	 */
	public void rackChanged() {
		for (int i = 0; i < RACK_NUM; i++) {
			int num = game.getCurrentPlayer().getRack().getLetterTiles().size();
			if (i < num) {
				String name = game.getCurrentPlayer().getRack()
						.getLetterTiles().get(i).getLetter();
				int score = game.getCurrentPlayer().getRack().getLetterTiles()
						.get(i).getScore();
				letterButtons.get(i).setText(name + ": " + score);
				letterButtons.get(i).setEnabled(true);
			}else{
				letterButtons.get(i).setText("");
				letterButtons.get(i).setEnabled(false);
			}
		}

	}

	/**
	 * After changing current player, change the inventory displayed on the GUI
	 */
	public void inventoryChanged() {
		ArrayList<SpecialTile> specialTiles = game.getCurrentPlayer()
				.getInventory().getSpecialTiles();
		calculateInventory(specialTiles);
	}

}
