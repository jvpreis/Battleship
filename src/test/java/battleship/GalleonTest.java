/**
 * 
 */
package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleship.Compass;
import battleship.Galleon;
import battleship.Position;

/**
 * @author fba
 *
 */
class GalleonTest
{
    static Galleon gN, gS, gE, gW;

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception
    {
	gN = new Galleon(Compass.NORTH, new Position(5, 5));
	gS = new Galleon(Compass.SOUTH, new Position(5, 5));
	gE = new Galleon(Compass.EAST, new Position(5, 5));
	gW = new Galleon(Compass.WEST, new Position(5, 5));
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception
    {
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception
    {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception
    {
    }

    /**
     * Test method for {@link battleship.Galleon#getSize()}.
     */
    @Test
    final void testGetSize()
    {
	assertEquals(5, gN.getSize());
	assertEquals(5, gS.getSize());
	assertEquals(5, gE.getSize());
	assertEquals(5, gW.getSize());
    }

    /**
     * Test method for
     * {@link battleship.Galleon#Galleon(battleship.Compass, battleship.IPosition)}.
     */
    @Test
    final void testGalleon()
    {
	assertNotNull(gN);
	assertEquals(Compass.NORTH, gN.getBearing());
	assertEquals(5, gN.getTopMostPos());

	assertNotNull(gS);
	assertEquals(Compass.SOUTH, gS.getBearing());
	assertEquals(7, gS.getBottomMostPos());

	assertNotNull(gE);
	assertEquals(Compass.EAST, gE.getBearing());
	assertEquals(5, gE.getRightMostPos());

	assertNotNull(gW);
	assertEquals(Compass.WEST, gW.getBearing());
	assertEquals(5, gW.getLeftMostPos());
    }

    /**
     * Test method for
     * {@link battleship.Galleon#Galleon(battleship.Compass, battleship.IPosition)}.
     */
    @Test
    final void testGalleonIllegalArgumentException()
    {
	assertThrows(IllegalArgumentException.class, () -> new Galleon(Compass.UNKNOWN, new Position(0, 0)));
    }

    /**
     * Test method for
     * {@link battleship.Galleon#Galleon(battleship.Compass, battleship.IPosition)}.
     */
    @Test
    final void testGalleonNullPointerException()
    {
	assertThrows(NullPointerException.class, () -> new Galleon(null, new Position(0, 0)));
	assertThrows(NullPointerException.class, () -> new Galleon(null, null));
    }

    /**
     * Test method for {@link java.lang.Object#toString()}.
     */
    @Test
    final void testToString()
    {
	assertEquals("[Galeao n Linha = 5 Coluna = 5]", gN.toString());
	assertEquals("[Galeao s Linha = 5 Coluna = 5]", gS.toString());
	assertEquals("[Galeao e Linha = 5 Coluna = 5]", gE.toString());
	assertEquals("[Galeao o Linha = 5 Coluna = 5]", gW.toString());
    }

}
