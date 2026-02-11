package iscteiul.ista;

import iscteiul.ista.battleship.Position;
import iscteiul.ista.battleship.IPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void testEqualsAndHashCode() {
        Position p1 = new Position(1,2);
        Position p2 = new Position(1,2);
        Position p3 = new Position(2,1);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1, p3);
    }

    @Test
    public void testIsAdjacentTo() {
        IPosition p = new Position(5,5);
        assertTrue(p.isAdjacentTo(new Position(5,5)));
        assertTrue(p.isAdjacentTo(new Position(4,4)));
        assertTrue(p.isAdjacentTo(new Position(6,6)));
        assertFalse(p.isAdjacentTo(new Position(7,7)));
    }

    @Test
    public void testOccupyAndShootFlags() {
        Position p = new Position(3,3);
        assertFalse(p.isOccupied());
        assertFalse(p.isHit());
        p.occupy();
        assertTrue(p.isOccupied());
        p.shoot();
        assertTrue(p.isHit());
    }
}

