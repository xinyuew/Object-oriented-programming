package edu.cmu.cs.cs214.hw4.core;

/**
 * This class is used to represent the location of tiles
 * @author xinyuewu
 *
 */
public class Location {
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	private int row;
	private int col;
	
	/**
	 * Constructor
	 * @param r is row
	 * @param c is column
	 */
	public Location(int r, int c){
		row = r;
		col = c;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	/**
	 * get row of this location
	 * @return row
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * get column of this location
	 * @return column
	 */
	public int getCol(){
		return col;
	}
	
	/**
	 * set a new row to this location
	 * @param r is new row
	 */
	public void setRow(int r){
		row = r;
	}
	
	/**
	 * set a new column to this location
	 * @param c is new column
	 */
	public void setCol(int c){
		col = c;
	}
}
