package edu.cmu.cs.cs214.hw4.core;

/**
 * This class is negative tile class
 * @author xinyuewu
 */
public class NegativeTile extends SpecialTile{

	protected NegativeTile(String n, int p) {
		super(n, p);
		
	}

	@Override
	public void makeEffectBefore(Game g,Square s) {
		g.setScoreMultilier(-1);
		
	}

}
