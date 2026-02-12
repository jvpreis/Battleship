package battleship;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PositionTest {
    private Position position;

    @BeforeEach
    void setUp() throws Exception {
        position = new Position(2, 3);
    }

    @Test
    void testGetPositionRow() {
        assertEquals(2, position.getRow());
    }

    @Test
    void testGetPositionColumn() {
        assertEquals(3, position.getColumn());
    }

    @Test
    void testEquals() {
        Position position2 = new Position(2, 3);
        assertEquals(position2, position);
        assertNotNull(position);
        assertNotEquals(new Position(2, 4), position);
        assertNotEquals(new Position(1, 3), position);
    }

    @Test
    void testHashCode() {
        assertEquals(position.hashCode(), new Position(2, 3).hashCode());
        assertNotEquals(position.hashCode(), new Position(1, 3).hashCode());
    }

    @Test
    void testIsAdjacentTo() {
        assertTrue(position.isAdjacentTo(new Position(1, 2)));
        assertTrue(position.isAdjacentTo(new Position(1, 3)));
        assertTrue(position.isAdjacentTo(new Position(1, 4)));
        assertTrue(position.isAdjacentTo(new Position(2, 2)));
        assertTrue(position.isAdjacentTo(new Position(2, 4)));
        assertTrue(position.isAdjacentTo(new Position(3, 2)));
        assertTrue(position.isAdjacentTo(new Position(3, 3)));
        assertTrue(position.isAdjacentTo(new Position(3, 4)));
        assertFalse(position.isAdjacentTo(new Position(1, 1)));
        assertFalse(position.isAdjacentTo(new Position(4, 4)));
    }

    @Test
    void testOccupy() {
        assertFalse(position.isOccupied());
        position.occupy();
        assertTrue(position.isOccupied());
    }

    @Test
    void testShoot() {
        assertFalse(position.isHit());
        position.shoot();
        assertTrue(position.isHit());
    }

    @Test
    void testIsOccupied() {
        assertFalse(position.isOccupied());
        position.occupy();
        assertTrue(position.isOccupied());
    }

    @Test
    void testIsHit() {
        assertFalse(position.isHit());
        position.shoot();
        assertTrue(position.isHit());
    }

    @Test
    void testToString() {
        assertEquals("Linha = 2 Coluna = 3", position.toString());
    }
}