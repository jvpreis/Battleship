package iscteiul.ista.battleship;


/**
 * Represents a Barge used in the Battleship game.
 * <p>
 * A Barge is a ship with a fixed size of 1 that occupies a single board
 * position. This class provides the constructor to create a Barge with a
 * specific bearing (orientation) and a reference position. Equality and
 * other ship behaviours are inherited from {@code Ship}.
 * <p>
 * Usage example:
 * <pre>{@code
 * Barge b = new Barge(Compass.EAST, position);
 * }</pre>
 * Thread-safety: instances are not thread-safe unless externally synchronized.
 *
 * @author jvprs
 * @since 1.0
 */
public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Creates a new Barge with the given bearing and position.
     * The created Barge will occupy a single cell at the provided position.
     *
     * @param bearing the barge bearing (must not be {@code null})
     * @param pos     the upper-left position of the barge (must not be {@code null})
     * @throws NullPointerException if {@code bearing} or {@code pos} is {@code null}
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the size of the Barge. Always returns {@code 1}.
     *
     * @return the size of the Barge
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
