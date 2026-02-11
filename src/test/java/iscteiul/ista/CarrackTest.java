package iscteiul.ista;

import iscteiul.ista.battleship.Carrack;
import iscteiul.ista.battleship.Compass;
import iscteiul.ista.battleship.IPosition;
import iscteiul.ista.battleship.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarrackTest {

    @Test
    public void testConstructNorthCreatesVerticalPositions() {
        Position origin = new Position(1,1);
        Carrack c = new Carrack(Compass.NORTH, origin);
        assertEquals(Integer.valueOf(3), c.getSize());
        assertEquals(3, c.getPositions().size());
        IPosition p0 = c.getPositions().get(0);
        IPosition p1 = c.getPositions().get(1);
        IPosition p2 = c.getPositions().get(2);
        assertEquals(1, p0.getRow());
        assertEquals(1, p0.getColumn());
        assertEquals(2, p1.getRow());
        assertEquals(1, p1.getColumn());
        assertEquals(3, p2.getRow());
        assertEquals(1, p2.getColumn());
    }

    @Test
    public void testConstructEastCreatesHorizontalPositions() {
        Position origin = new Position(2,2);
        Carrack c = new Carrack(Compass.EAST, origin);
        assertEquals(Integer.valueOf(3), c.getSize());
        assertEquals(3, c.getPositions().size());
        IPosition p0 = c.getPositions().get(0);
        IPosition p1 = c.getPositions().get(1);
        IPosition p2 = c.getPositions().get(2);
        assertEquals(2, p0.getRow());
        assertEquals(2, p0.getColumn());
        assertEquals(2, p1.getRow());
        assertEquals(3, p1.getColumn());
        assertEquals(2, p2.getRow());
        assertEquals(4, p2.getColumn());
    }
}

