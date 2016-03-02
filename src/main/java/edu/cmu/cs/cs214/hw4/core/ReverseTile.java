package edu.cmu.cs.cs214.hw4.core;

/**
 * This is reverse tile class
 * @author xinyuewu
 *
 */
public class ReverseTile extends SpecialTile{

	protected ReverseTile(String n, int p) {
		super(n, p);
	}

	@Override
	public void makeEffectBefore(Game g,Square s) {
		g.reverseOrder();
		
	}

}
