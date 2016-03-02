package edu.cmu.cs.cs214.hw4.core;

/**
 * This class is boom tile class
 * 
 * @author xinyuewu
 *
 */
public class BoomTile extends SpecialTile {
	private static final int RANGE = 3;
	private static final int BOARD_SIZE = 15;

	protected BoomTile(String n, int p) {
		super(n, p);
	}

	@Override
	public void makeEffectBefore(Game g, Square s) {
		Location loc = s.getLocation();
		int row;
		int col;
		for (row = (loc.getRow() - RANGE); row < (loc.getRow() + RANGE + 1); row++) {
			for (col = loc.getCol() - RANGE; col < (loc.getCol() + RANGE + 1); col++) {
				if (row > -1 && row < BOARD_SIZE && col > -1
						&& col < BOARD_SIZE) {
					g.getBoard().getSquareByLocation(new Location(row, col))
							.removeLetterTile();

					if ((row != loc.getRow()) || (col != loc.getCol())) {
						g.getBoard()
								.getSquareByLocation(new Location(row, col))
								.removeSpecialTile();
					}
				}

			}
		}

	}

}
