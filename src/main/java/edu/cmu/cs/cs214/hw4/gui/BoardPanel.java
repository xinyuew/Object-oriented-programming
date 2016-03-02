package edu.cmu.cs.cs214.hw4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.cmu.cs.cs214.hw4.core.Board;
import edu.cmu.cs.cs214.hw4.core.Game;
import edu.cmu.cs.cs214.hw4.core.LetterTile;
import edu.cmu.cs.cs214.hw4.core.Placement;
import edu.cmu.cs.cs214.hw4.core.SpecialTile;
import edu.cmu.cs.cs214.hw4.core.Square;

/**
 * this is the panel containing all the
 * 15x15 squares in te game.
 * @author xinyuewu
 *
 */
public class BoardPanel extends JPanel {
	private Game game;
	private Board board;
	private ArrayList<ArrayList<Square>> squares;
	private static final int BOARD_SIZE = 15;
	private static final int SQUARE_SIZE = 40;
	private JButton[][] squaresButton;
	private static final int THREE = 3;

	/**
	 * constructor
	 * 
	 * @param g
	 *            is a instance of game
	 */
	public BoardPanel(Game g) {
		game = g;
		board = game.getBoard();
		squares = board.getSquares();
		squaresButton = new JButton[BOARD_SIZE][BOARD_SIZE];
		initialBoard();
	}

	private void initialBoard() {
		add(createBoardPanel());
	}

	private JPanel createBoardPanel() {
		JPanel panel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));

		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				squaresButton[i][j] = new JButton();
				squaresButton[i][j].setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
				squaresButton[i][j].setBorder(BorderFactory
						.createLineBorder(Color.BLACK));
				squaresButton[i][j].setOpaque(true);

				setSquareButtonText(i, j);

				squaresButton[i][j].addActionListener(new SquareListener(game,
						i, j));
				panel.add(squaresButton[i][j]);
			}
		}
		return panel;
	}

	private void setSquareButtonText(int i, int j) {
		squaresButton[i][j].setBackground(squares.get(i).get(j).getColor());
		if (squares.get(i).get(j).getLetterMultiplier() != 1) {
			squaresButton[i][j].setText(squares.get(i).get(j)
					.getLetterMultiplier()
					+ "L");
			if (squares.get(i).get(j).getLetterMultiplier() == THREE) {
				squaresButton[i][j].setForeground(Color.WHITE);
			}
		} else if (squares.get(i).get(j).getWordMultiplier() != 1) {
			squaresButton[i][j].setText(squares.get(i).get(j)
					.getWordMultiplier()
					+ "W");
		} else {
			squaresButton[i][j].setText("");
		}
	}

	/**
	 * update the display of a square using
	 * information in the game board.
	 * @param r row of the square to be updated
	 * @param c column of the square to be updated
	 */
	public void squareChanged(int r, int c) {
		boolean occupied = false;
		LetterTile lt = game.getBoard().getSquares().get(r).get(c)
				.getLetterTile();
		if (lt != null) {
			squaresButton[r][c].setBackground(Color.YELLOW);
			squaresButton[r][c].setForeground(Color.BLACK);
			squaresButton[r][c].setText(lt.getLetter());
		} else {
			squaresButton[r][c].setBackground(Color.YELLOW);
			squaresButton[r][c].setForeground(Color.BLACK);
			ArrayList<Placement> letterPlacements = game.getCurrentPlayer()
					.getMove().getLetterPlacements();
			ArrayList<Placement> specialPlacements = game.getCurrentPlayer()
					.getMove().getSpecialPlacements();
			if (letterPlacements.size() > 0) {
				for (Placement p : letterPlacements) {
					if (p.getLocation().getRow() == r
							&& p.getLocation().getCol() == c) {
						squaresButton[r][c].setText(p.getLetterTile()
								.getLetter());
						occupied = true;
					}
				}
			}
			if (specialPlacements.size() > 0) {
				for (Placement p : specialPlacements) {
					if (p.getLocation().getRow() == r
							&& p.getLocation().getCol() == c) {
						squaresButton[r][c].setText(p.getSpecialTile()
								.getName());
						occupied = true;
					}
				}
			} 
			if(occupied == false){
				setSquareButtonText(r, c);
			}
		}
	}

	/**
	 * after changing player, change the board because this player cannot see
	 * other's special tile
	 */
	public void boardChanged() {
		ArrayList<SpecialTile> specials;
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				specials = game.getBoard().getSquares().get(i).get(j)
						.getSpecialTile();
				if (specials.size() > 0) {
					for (SpecialTile st : specials) {
						if (st.getOwner().equals(game.getCurrentPlayer())) {
							squaresButton[i][j].setText(specials.get(
									specials.size() - 1).getName());
							squaresButton[i][j].setBackground(Color.YELLOW);
							squaresButton[i][j].setForeground(Color.BLACK);
						} else {
							setSquareButtonText(i, j);
						}
					}
				} else {
					setSquareButtonText(i, j);
				}
				LetterTile letter = game.getBoard().getSquares().get(i).get(j)
						.getLetterTile();
				if (letter != null) {
					squaresButton[i][j].setBackground(Color.YELLOW);
					squaresButton[i][j].setForeground(Color.BLACK);
					squaresButton[i][j].setText(letter.getLetter());
				}

			}
		}
	}

}
