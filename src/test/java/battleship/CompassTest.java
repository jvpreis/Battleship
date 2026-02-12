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

/**
 * @author fba
 *
 */
class CompassTest
{

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception
    {
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
     * Test method for {@link battleship.Compass#Compass(char)}.
     */
    @Test
    final void testCompass()
    {
	assertEquals('n', Compass.NORTH.getDirection());
	assertEquals('s', Compass.SOUTH.getDirection());
	assertEquals('e', Compass.EAST.getDirection());
	assertEquals('o', Compass.WEST.getDirection());
    }

    /**
     * Test method for {@link battleship.Compass#toString()}.
     */
    @Test
    final void testToString()
    {
	assertEquals("n", Compass.NORTH.toString());
	assertEquals("s", Compass.SOUTH.toString());
	assertEquals("e", Compass.EAST.toString());
	assertEquals("o", Compass.WEST.toString());
    }

}
