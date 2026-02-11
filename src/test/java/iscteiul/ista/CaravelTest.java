package iscteiul.ista;

import iscteiul.ista.battleship.Caravel;
import iscteiul.ista.battleship.Compass;
import iscteiul.ista.battleship.IPosition;
import iscteiul.ista.battleship.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CaravelTest {

    @Test
    public void testCaravelClassExists() {
        try {
            Class.forName("iscteiul.ista.battleship.Caravel");
        } catch (ClassNotFoundException e) {
            fail("Class iscteiul.ista.battleship.Caravel not found. Make sure Caravel.java is in package iscteiul.ista.battleship");
        }
    }

    @Test
    public void testConstructNorthCreatesVerticalPositions() {
        IPosition origin = new Position(2, 3);
        Caravel c = new Caravel(Compass.NORTH, origin);
        assertEquals(Integer.valueOf(2), c.getSize());
        assertNotNull(c.getPositions());
        assertEquals(2, c.getPositions().size());

        IPosition p0 = c.getPositions().get(0);
        IPosition p1 = c.getPositions().get(1);
        assertEquals(2, p0.getRow());
        assertEquals(3, p0.getColumn());
        assertEquals(3, p1.getRow());
        assertEquals(3, p1.getColumn());

        // bounds
        assertEquals(2, c.getTopMostPos());
        assertEquals(3, c.getBottomMostPos());
        assertEquals(3, c.getLeftMostPos());
        assertEquals(3, c.getRightMostPos());
    }

    @Test
    public void testConstructEastCreatesHorizontalPositions() {
        IPosition origin = new Position(5, 5);
        Caravel c = new Caravel(Compass.EAST, origin);
        assertEquals(Integer.valueOf(2), c.getSize());
        assertEquals(2, c.getPositions().size());

        IPosition p0 = c.getPositions().get(0);
        IPosition p1 = c.getPositions().get(1);
        assertEquals(5, p0.getRow());
        assertEquals(5, p0.getColumn());
        assertEquals(5, p1.getRow());
        assertEquals(6, p1.getColumn());

        // bounds
        assertEquals(5, c.getTopMostPos());
        assertEquals(5, c.getBottomMostPos());
        assertEquals(5, c.getLeftMostPos());
        assertEquals(6, c.getRightMostPos());
    }

    @Test
    public void testConstructorNullBearingThrows() {
        IPosition origin = new Position(0, 0);
        // Ship constructor uses assert and will throw AssertionError when bearing is null
        assertThrows(AssertionError.class, () -> new Caravel(null, origin));
    }

    @Test
    public void testConstructorUnknownBearingThrows() {
        IPosition origin = new Position(1, 1);
        assertThrows(IllegalArgumentException.class, () -> new Caravel(Compass.UNKNOWN, origin));
    }

    @Test
    public void testShootAndStillFloatingBehavior() {
        Position origin = new Position(10, 10);
        Caravel c = new Caravel(Compass.SOUTH, origin);

        // initially still floating
        assertTrue(c.stillFloating());

        // shoot first position
        IPosition first = c.getPositions().get(0);
        c.shoot(first);
        // one hit but one position remains
        assertTrue(c.stillFloating());

        // shoot second position
        IPosition second = c.getPositions().get(1);
        c.shoot(second);
        // now all positions hit => not floating
        assertFalse(c.stillFloating());
    }
}
