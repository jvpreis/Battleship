package iscteiul.ista;

import iscteiul.ista.battleship.Barge;
import iscteiul.ista.battleship.Compass;
import iscteiul.ista.battleship.IPosition;
import iscteiul.ista.battleship.IShip;
import iscteiul.ista.battleship.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BargeTest {

    @Test
    public void testBargeClassExists() {
        try {
            Class.forName("iscteiul.ista.battleship.Barge");
        } catch (ClassNotFoundException e) {
            fail("Class iscteiul.ista.battleship.Barge not found. Make sure Barge.java is in package iscteiul.ista.battleship");
        }
    }

    @Test
    public void testConstructCreatesSinglePositionAndBounds() {
        Position origin = new Position(3, 4);
        Barge b = new Barge(Compass.NORTH, origin);

        assertEquals(Integer.valueOf(1), b.getSize());
        assertNotNull(b.getPositions());
        assertEquals(1, b.getPositions().size());

        IPosition p = b.getPositions().get(0);
        assertEquals(3, p.getRow());
        assertEquals(4, p.getColumn());

        // bounds should all be equal to the single position
        assertEquals(3, b.getTopMostPos());
        assertEquals(3, b.getBottomMostPos());
        assertEquals(4, b.getLeftMostPos());
        assertEquals(4, b.getRightMostPos());
    }

    @Test
    public void testOccupiesAndTooCloseTo() {
        Position origin = new Position(6, 6);
        Barge b = new Barge(Compass.EAST, origin);

        // occupies its own position
        IPosition p = new Position(6, 6);
        assertTrue(b.occupies(p));

        // position adjacent should be considered too close
        IPosition adjacent = new Position(6, 7);
        assertTrue(b.tooCloseTo(adjacent));

        // another ship very close should be too close
        Barge other = new Barge(Compass.SOUTH, new Position(7, 6));
        assertTrue(b.tooCloseTo((IShip) other));
    }

    @Test
    public void testShootAndStillFloatingBehavior() {
        Position origin = new Position(10, 10);
        Barge b = new Barge(Compass.SOUTH, origin);

        // initially still floating
        assertTrue(b.stillFloating());

        // shoot its single position
        IPosition pos = b.getPositions().get(0);
        b.shoot(pos);

        // now it should not be still floating
        assertFalse(b.stillFloating());
    }
}
