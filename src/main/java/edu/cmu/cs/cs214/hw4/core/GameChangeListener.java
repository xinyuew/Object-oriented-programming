package edu.cmu.cs.cs214.hw4.core;

/**
 * This class is for game to notify GUI
 * @author xinyuewu
 *
 */
public interface GameChangeListener {

    /**
     * Called when any tile on the board changes.
     *
     * @param x The x coordinate of the updated cell on the board.
     * @param y The y coordinate of the updated cell on the board.
     */
	void squareChanged(int x,int y);
	
    /**
     * Called when the current player changed
     *
     * @param player The new current player.
     */
	void playerChanged(Player player);
	
	
	/**
	 * Called when the player's rack changed
	 */
	void rackChanged();
	
	
	/**
	 * Called when the player's inventory changed
	 */
	void inventoryChanged();
	
	
	/**
	 * Called when player's score has changed
	 */
	void scoreChanged();
	
	/**
	 * Called when player changed, current player cannot see other player's special tile
	 */
	void boardChanged();
	
	/**
	 * Called when game end
	 * @param w is winner
	 */
	void gameEnded(Player w);
}
