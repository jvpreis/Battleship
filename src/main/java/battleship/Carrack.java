/**
 * 
 */
package battleship;

public class Carrack extends Ship
{
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * @param bearing The bearing of ships of interest
     * @param pos The position of ships of interest
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException
    {
	super(Carrack.NAME, bearing, pos);
	switch (bearing)
	{
	case SOUTH:
	    for (int r = 0; r < SIZE; r++)
		getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
	    break;
	case NORTH:    
	    for (int r = 0; r < SIZE; r++)
		getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
	    break;
	case EAST:
	    for (int c = 0; c < SIZE; c++)
		getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
	    break;
	case WEST:
	    for (int c = 0; c < SIZE; c++)
		getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
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
    public Integer getSize()
    {
	return Carrack.SIZE;
    }

}
