package battleship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ChatGPT
 *
 */
@DisplayName("Barge class tests")
class BargeTest {

    private Barge barge;

    @BeforeEach
    void setUp() {
        barge = new Barge(Compass.NORTH, new Position(1, 1));
    }

    @Test
    @DisplayName("Test constructor with valid input")
    void testConstructor() {
        assertEquals("Barca", barge.getCategory());
        assertEquals(Compass.NORTH, barge.getBearing());
        assertEquals(1, barge.getPosition().getRow());
        assertEquals(1, barge.getPosition().getColumn());
        assertTrue(barge.stillFloating());
    }

    @Test
    @DisplayName("Test getSize method")
    void testGetSize() {
        assertEquals(1, barge.getSize());
    }

    @Test
    @DisplayName("Test getPositions method")
    void testGetPositions() {
        Iterator<IPosition> positions = barge.getPositions().iterator();
        assertTrue(positions.hasNext());
        IPosition position = positions.next();
        assertEquals(1, position.getRow());
        assertEquals(1, position.getColumn());
        assertFalse(positions.hasNext());
    }

    @Test
    @DisplayName("Test shoot method on an occupied position")
    void testShootOnOccupiedPosition() {
        IPosition position = new Position(1, 1);
        barge.shoot(position);
        assertFalse(barge.stillFloating());
    }

    @Test
    @DisplayName("Test shoot method on an unoccupied position")
    void testShootOnUnoccupiedPosition() {
        IPosition position = new Position(2, 2);
        barge.shoot(position);
        assertTrue(barge.stillFloating());
    }

    @Test
    @DisplayName("Test shoot method with invalid input")
    void testShootWithInvalidInput() {
        Assertions.assertThrows(AssertionError.class, () -> {
            barge.shoot(null);
        });
    }

    @Test
    @DisplayName("Test occupies method with occupied position")
    void testOccupiesWithOccupiedPosition() {
        IPosition position = new Position(1, 1);
        assertTrue(barge.occupies(position));
    }

    @Test
    @DisplayName("Test occupies method with unoccupied position")
    void testOccupiesWithUnoccupiedPosition() {
        IPosition position = new Position(2, 2);
        assertFalse(barge.occupies(position));
    }

    @Test
    @DisplayName("Test tooCloseTo method with overlapping barge")
    void testTooCloseToWithOverlappingBarge() {
        Barge overlappingBarge = new Barge(Compass.EAST, new Position(1, 2));
        assertTrue(barge.tooCloseTo(overlappingBarge));
    }

    @Test
    @DisplayName("Test tooCloseTo method with non-overlapping barge")
    void testTooCloseToWithNonOverlappingBarge() {
        Barge nonOverlappingBarge = new Barge(Compass.EAST, new Position(1, 3));
        assertFalse(barge.tooCloseTo(nonOverlappingBarge));
        }
}
