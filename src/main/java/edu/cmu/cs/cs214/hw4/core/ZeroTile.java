package edu.cmu.cs.cs214.hw4.core;

/**
 * This class is zero tile class
 * @author xinyuewu
 *
 */
public class ZeroTile extends SpecialTile{

	protected ZeroTile(String n, int p) {
		super(n, p);
	}

	@Override
	public void makeEffectBefore(Game g,Square s) {
		g.setScoreMultilier(0);
		
	}

}
