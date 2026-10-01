package battleship;

/**
 * Represents a barge in the Battleship game.
 * <p>
 * A barge occupies exactly one board position.
 */
public class Barge extends Ship
{
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Creates a barge with the specified bearing and position.
     *
     * @param bearing the bearing of the barge
     * @param pos the upper-left position of the barge
     */
    public Barge(Compass bearing, IPosition pos)
    {
	super(Barge.NAME, bearing, pos);
	getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the number of positions occupied by the barge.
     *
     * @return the size of the barge, always {@code 1}
     */
    @Override
    public Integer getSize()
    {
	return SIZE;
    }

}
