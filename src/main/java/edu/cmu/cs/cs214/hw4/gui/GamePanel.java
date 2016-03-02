package edu.cmu.cs.cs214.hw4.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.cmu.cs.cs214.hw4.core.Game;
import edu.cmu.cs.cs214.hw4.core.GameChangeListener;
import edu.cmu.cs.cs214.hw4.core.Player;

/**
 * 
 * @author xinyuewu
 *
 */
public class GamePanel extends JPanel implements GameChangeListener {
	private Game game;
	private ControlPanel controlPanel;
	private BoardPanel boardPanel;
	private PlayerPanel playerPanel;
	private StatusPanel statusPanel;

	/**
	 * constructor
	 * @param g the game that is running
	 */
	public GamePanel(Game g) {
		setLayout(new BorderLayout());
		game = g;
		controlPanel = new ControlPanel(game);
		boardPanel = new BoardPanel(game);
		playerPanel = new PlayerPanel(game);
		statusPanel = new StatusPanel(game);

		add(controlPanel, BorderLayout.EAST);
		add(boardPanel, BorderLayout.CENTER);
		add(playerPanel, BorderLayout.SOUTH);
		add(statusPanel, BorderLayout.NORTH);
		game.addGameChangeListener(this);
	}

	@Override
	public void squareChanged(int x, int y) {
		boardPanel.squareChanged(x, y);

	}

	@Override
	public void playerChanged(Player player) {
		statusPanel.playerChanged();

	}

	@Override
	public void rackChanged() {
		playerPanel.rackChanged();

	}

	@Override
	public void inventoryChanged() {
		playerPanel.inventoryChanged();

	}

	@Override
	public void scoreChanged() {
		controlPanel.scoreChanged();

	}

	@Override
	public void boardChanged() {
		boardPanel.boardChanged();

	}

	@Override
	public void gameEnded(Player winner) {
		JFrame frame = (JFrame) SwingUtilities.getRoot(this);

		if (winner != null) {
			showDialog(frame, "Winner!", winner.getName()
					+ " just won the game!");
		}
		
		//after user click OK button, close the frame window
		System.exit(0);

	}

	private static void showDialog(Component component, String title,
			String message) {
		JOptionPane.showMessageDialog(component, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
}
