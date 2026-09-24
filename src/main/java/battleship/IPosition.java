package battleship;

/**
 * Defines the behavior expected from a board position in the Battleship game,
 * including occupancy, adjacency, and shot status.
 *
 * @author fba
 */
public interface IPosition
{
    int getRow();

    int getColumn();

    boolean equals(Object other);

    boolean isAdjacentTo(IPosition other);

    void occupy();

    void shoot();

    boolean isOccupied();

    boolean isHit();
}
