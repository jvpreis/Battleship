package iscteiul.ista;

import iscteiul.ista.battleship.Compass;
import iscteiul.ista.battleship.Galleon;
import iscteiul.ista.battleship.IPosition;
import iscteiul.ista.battleship.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GalleonTest {

    @Test
    public void testConstructNorthPattern() {
        Position origin = new Position(2,2);
        Galleon g = new Galleon(Compass.NORTH, origin);
        assertEquals(Integer.valueOf(5), g.getSize());
        assertEquals(5, g.getPositions().size());
        // expected positions: (2,2),(2,3),(2,4),(3,3),(4,3) (based on fillNorth)
        assertEquals(2, g.getPositions().get(0).getRow());
        assertEquals(2, g.getPositions().get(0).getColumn());
        assertEquals(2, g.getPositions().get(1).getRow());
        assertEquals(3, g.getPositions().get(1).getColumn());
        assertEquals(2, g.getPositions().get(2).getRow());
        assertEquals(4, g.getPositions().get(2).getColumn());
        assertEquals(3, g.getPositions().get(3).getRow());
        assertEquals(3, g.getPositions().get(3).getColumn());
        assertEquals(4, g.getPositions().get(4).getRow());
        assertEquals(3, g.getPositions().get(4).getColumn());
    }

    @Test
    public void testConstructEastPattern() {
        Position origin = new Position(0,3);
        Galleon g = new Galleon(Compass.EAST, origin);
        assertEquals(Integer.valueOf(5), g.getSize());
        assertEquals(5, g.getPositions().size());
        // validate a couple of positions to ensure shape roughly correct
        assertEquals(0, g.getPositions().get(0).getRow());
        assertEquals(3, g.getPositions().get(0).getColumn());
        assertEquals(2, g.getPositions().get(4).getRow());
        assertEquals(3, g.getPositions().get(4).getColumn());
    }
}

