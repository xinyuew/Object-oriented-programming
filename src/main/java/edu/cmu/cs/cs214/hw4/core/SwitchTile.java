package edu.cmu.cs.cs214.hw4.core;

/**
 * This class represent switch-player tile
 * @author xinyuewu
 *
 */
public class SwitchTile extends SpecialTile{
	
	/**
	 * constructor
	 * @param n is the special tile's name
	 * @param p is the price of this tile
	 */
	public SwitchTile(String n, int p){
		super(n,p);
	}



	@Override
	public void makeEffectBefore(Game g, Square s) {
		// do nothing
		
	}

	@Override
	public void makeEffectAfter(Game g, Square s) {
		int score1 = g.getCurrentPlayer().getScore();
		int score2 = getOwner().getScore();
		getOwner().setScore(score1);
		g.getCurrentPlayer().setScore(score2);
		
	}

}
