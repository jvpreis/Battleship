/**
 * 
 */
package battleship;

import java.util.Objects;

public class Position implements IPosition
{
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * @param row  Row position
     * @param column Column position
     */
    public Position(int row, int column)
    {
	this.row = row;
	this.column = column;
	this.isOccupied = false;
	this.isHit = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.IPosition#getRow()
     */
    public int getRow()
    {
	return row;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.IPosition#getColumn()
     */
    public int getColumn()
    {
	return column;
    }


    @Override
    public int hashCode()
    {
	return Objects.hash(column, isHit, isOccupied, row);
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.IPosition#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object otherPosition)
    {
	if (this == otherPosition)
	    return true;
	Position other = null;
	if (otherPosition instanceof IPosition)
	    return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
	else
	    return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.IPosition#isAdjacentTo(battleship.IPosition)
     */
    public boolean isAdjacentTo(IPosition other)
    {
	return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.IPosition#occupy()
     */
    public void occupy()
    {
	isOccupied = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.IPosition#shoot()
     */
    public void shoot()
    {
	isHit = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.IPosition#isOccupied()
     */
    public boolean isOccupied()
    {
	return isOccupied;
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.IPosition#isHit()
     */
    public boolean isHit()
    {
	return isHit;
    }

    @Override
    public String toString()
    {
	return ("Linha = " + row + " Coluna = " + column);
    }

}
