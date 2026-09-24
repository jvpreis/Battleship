package battleship;

/**
 * Represents the cardinal directions a ship may face in the Battleship game.
 * Each direction is mapped to a single-letter code used throughout the project.
 *
 * @author fba
 */
public enum Compass
{
    /** Direction pointing upward on the board. */
    NORTH('n'),
    /** Direction pointing downward on the board. */
    SOUTH('s'),
    /** Direction pointing to the right on the board. */
    EAST('e'),
    /** Direction pointing to the left on the board. */
    WEST('o'),
    /** Unknown or invalid direction. */
    UNKNOWN('u');

    private final char c;

    /**
     * Creates a compass direction using its letter representation.
     *
     * @param c the single-letter code for the direction
     */
    Compass(char c)
    {
	this.c = c;
    }

    /**
     * Gets the internal character representation of this compass direction.
     *
     * @return the character code associated with this direction
     */
    public char getDirection()
    {
	return c;
    }

    /**
     * Returns the one-character representation of this direction.
     *
     * @return the compass letter for this direction
     */
    @Override
    public String toString()
    {
	return "" + c;
    }

    /**
     * Converts a character to the corresponding compass direction.
     * Unsupported or unknown values map to {@link #UNKNOWN}.
     *
     * @param ch the character to convert; accepted values are {@code 'n'}, {@code 's'}, {@code 'e'}, and {@code 'o'}
     * @return the matching compass direction, or {@link #UNKNOWN} if the value is not recognized
     */
    static Compass charToCompass(char ch)
    {
        Compass bearing;
        switch (ch)
        {
        case 'n':
            bearing = NORTH;
            break;
        case 's':
            bearing = SOUTH;
            break;
        case 'e':
            bearing = EAST;
            break;
        case 'o':
            bearing = WEST;
            break;
        default:
            bearing = UNKNOWN;
        }
    
        return bearing;
    }
}
