/**
 * 
 */
package battleship;

public class Carrack extends Ship
{
    private static final int SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * @param bearing
     * @param pos
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException
    {
	super(Carrack.NAME, bearing, pos);
	switch (bearing)
	{
	case NORTH, SOUTH:
	    for (int r = 0; r < SIZE; r++)
		positions.add(new Position(pos.getRow() + r, pos.getColumn()));
	    break;
	case EAST, WEST:
	    for (int c = 0; c < SIZE; c++)
		positions.add(new Position(pos.getRow(), pos.getColumn() + c));
	    break;
	default:
	    throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see battleship.Ship#getSize()
     */
    @Override
    public int getSize()
    {
	return Carrack.SIZE;
    }

}
