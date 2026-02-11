package iscteiul.ista;

import iscteiul.ista.battleship.Compass;
import iscteiul.ista.battleship.Frigate;
import iscteiul.ista.battleship.IPosition;
import iscteiul.ista.battleship.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FrigateTest {

    @Test
    public void testConstructNorthCreatesVerticalPositions() {
        Position origin = new Position(0,0);
        Frigate f = new Frigate(Compass.NORTH, origin);
        assertEquals(Integer.valueOf(4), f.getSize());
        assertEquals(4, f.getPositions().size());
        for (int i = 0; i < 4; i++) {
            IPosition p = f.getPositions().get(i);
            assertEquals(i, p.getRow());
            assertEquals(0, p.getColumn());
        }
    }

    @Test
    public void testConstructEastCreatesHorizontalPositions() {
        Position origin = new Position(3,3);
        Frigate f = new Frigate(Compass.EAST, origin);
        assertEquals(Integer.valueOf(4), f.getSize());
        assertEquals(4, f.getPositions().size());
        for (int i = 0; i < 4; i++) {
            IPosition p = f.getPositions().get(i);
            assertEquals(3, p.getRow());
            assertEquals(3 + i, p.getColumn());
        }
    }
}

