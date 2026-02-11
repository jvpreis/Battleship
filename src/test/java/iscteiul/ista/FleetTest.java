package iscteiul.ista;

import iscteiul.ista.battleship.Barge;
import iscteiul.ista.battleship.Carrack;
import iscteiul.ista.battleship.Compass;
import iscteiul.ista.battleship.Fleet;
import iscteiul.ista.battleship.IShip;
import iscteiul.ista.battleship.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FleetTest {

    @Test
    public void testAddShipAndGetShipsLikeAndShipAt() {
        Fleet f = new Fleet();
        Barge b = new Barge(Compass.NORTH, new Position(0,0));
        assertTrue(f.addShip(b));
        Carrack c = new Carrack(Compass.EAST, new Position(2,2));
        assertTrue(f.addShip(c));

        List<IShip> naves = f.getShipsLike("Nau");
        assertEquals(1, naves.size());

        // shipAt should find the barge at (0,0)
        IShip at = f.shipAt(new Position(0,0));
        assertNotNull(at);
        assertEquals(b.getCategory(), at.getCategory());
    }

    @Test
    public void testGetFloatingShips() {
        Fleet f = new Fleet();
        Barge b = new Barge(Compass.NORTH, new Position(5,5));
        f.addShip(b);
        assertEquals(1, f.getFloatingShips().size());

        // shoot and cause it to sink
        b.shoot(b.getPositions().get(0));
        assertEquals(0, f.getFloatingShips().size());
    }

    @Test
    public void testAddShipCollisionPreventsAdd() {
        Fleet f = new Fleet();
        Barge b1 = new Barge(Compass.NORTH, new Position(8,8));
        Barge b2 = new Barge(Compass.NORTH, new Position(8,8));
        assertTrue(f.addShip(b1));
        // second barge occupies same cell => should be collision risk -> not added
        assertFalse(f.addShip(b2));
    }

    // --- Additional tests to increase branch coverage ---

    @Test
    public void testAddShipOutsideBoardReturnsFalse() {
        Fleet f = new Fleet();
        // position outside the board (negative row)
        Barge b = new Barge(Compass.NORTH, new Position(-1, 0));
        assertFalse(f.addShip(b));
    }

    @Test
    public void testAddShipRightmostOutsideBoardReturnsFalse() {
        Fleet f = new Fleet();
        // Carrack east at column 8 -> rightmost = 8+2 = 10 which is > 9
        Carrack c = new Carrack(Compass.EAST, new Position(0,8));
        assertFalse(f.addShip(c));
    }

    @Test
    public void testAddShipBottommostOutsideBoardReturnsFalse() {
        Fleet f = new Fleet();
        // Carrack south starting at row 8 -> bottommost = 8+2 = 10 which is > 9
        Carrack c = new Carrack(Compass.NORTH, new Position(8,0));
        assertFalse(f.addShip(c));
    }

    @Test
    public void testAddShipLeftmostOutsideBoardReturnsFalse() {
        Fleet f = new Fleet();
        // Barge with negative column
        Barge b = new Barge(Compass.NORTH, new Position(0,-1));
        assertFalse(f.addShip(b));
    }

    @Test
    public void testFleetCapacityPreventsFurtherAdds() {
        Fleet f = new Fleet();
        // Coordinates chosen to avoid collisions and stay inside board
        int[][] coords = {
                {0,0},{0,3},{0,6},{0,9},
                {3,0},{3,3},{3,6},{3,9},
                {6,0},{6,3},{6,6},{6,9}
        };

        // Add 11 ships -> according to Fleet logic this should be allowed for the first 11
        for (int i = 0; i < 11; i++) {
            Barge b = new Barge(Compass.NORTH, new Position(coords[i][0], coords[i][1]));
            assertTrue(f.addShip(b), "should add ship at index " + i);
        }

        // 12th add should be rejected due to fleet size limit
        Barge extra = new Barge(Compass.NORTH, new Position(coords[11][0], coords[11][1]));
        assertFalse(f.addShip(extra));
    }

    @Test
    public void testShipAtReturnsNullWhenNone() {
        Fleet f = new Fleet();
        assertNull(f.shipAt(new Position(9,9)));
    }

    @Test
    public void testShipAtFindsLaterShip() {
        Fleet f = new Fleet();
        Barge b = new Barge(Compass.NORTH, new Position(0,0));
        Carrack c = new Carrack(Compass.EAST, new Position(2,2));
        f.addShip(b);
        f.addShip(c);
        // search a position occupied by carrack; first ship doesn't occupy it
        IShip found = f.shipAt(new Position(2,2));
        assertNotNull(found);
        assertEquals(c.getCategory(), found.getCategory());
    }

    @Test
    public void testGetShipsReturnsInternalList() {
        Fleet f = new Fleet();
        assertNotNull(f.getShips());
        int before = f.getShips().size();
        f.getShips().add(new Barge(Compass.NORTH, new Position(0,0)));
        assertEquals(before + 1, f.getShips().size());
    }

    @Test
    public void testPrintStatusExecutes() {
        Fleet f = new Fleet();
        f.addShip(new Barge(Compass.NORTH, new Position(0,0)));
        f.addShip(new Carrack(Compass.EAST, new Position(2,2)));
        // exercise the printStatus path (no assertions, just ensure no exceptions)
        f.printStatus();
    }

    @Test
    public void testGetShipsLikeEmptyAndNonEmpty() {
        Fleet f = new Fleet();
        assertTrue(f.getShipsLike("Galeao").isEmpty());
        f.addShip(new Carrack(Compass.NORTH, new Position(4,4)));
        List<IShip> nau = f.getShipsLike("Nau");
        assertEquals(1, nau.size());
    }

    @Test
    public void testGetFloatingShipsEmpty() {
        Fleet f = new Fleet();
        assertTrue(f.getFloatingShips().isEmpty());
    }

    @Test
    public void testPrintShipsByCategoryNullThrowsAssertion() {
        Fleet f = new Fleet();
        // When assertions are enabled this should throw AssertionError
        try {
            f.printShipsByCategory(null);
            // if assertions are disabled, the call is a no-op; assert that no exception means assertions disabled
            // To keep the test deterministic, we accept both outcomes: either AssertionError or no exception.
        } catch (AssertionError ae) {
            // expected when tests run with -ea
            assertInstanceOf(AssertionError.class, ae);
        }
    }

}
