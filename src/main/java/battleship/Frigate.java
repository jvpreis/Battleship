/**
 * 
 */
package battleship;

/**
 * A frigate occupying four consecutive positions on the board.
 * The bearing determines whether the positions extend along a row or column.
 */
public class Frigate extends Ship
{
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Creates a frigate at the specified position and bearing.
     *
     * @param bearing the direction in which the frigate is placed
     * @param pos the starting position of the frigate
     * @throws IllegalArgumentException if the bearing is not a cardinal direction
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException
    {
	super(Frigate.NAME, bearing, pos);
	switch (bearing)
	{
	case NORTH:
	    for (int r = 0; r < SIZE; r++)
		getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
	    break;
	case SOUTH:
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
	    throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
	}
    }

    /**
     * Returns the number of board positions occupied by this frigate.
     *
     * @return the frigate length, which is four positions
     */
    @Override
    public Integer getSize()
    {
	return Frigate.SIZE;
    }

}
