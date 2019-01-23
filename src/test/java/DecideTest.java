//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DecideTest {

    @Test
    public void testTrue() {
        assertEquals(true, true);
    }

    @Test
    public void testLIC3(){
        Decide system = new Decide();
        // Test with fewer than 3 points which can't produce a triangle (false)
        system.NUMPOINTS = 2;
        system.X = new double[] {0, 3};
        system.Y = new double[] {3, 4};
        assertEquals(system.LIC3(), false);

        // Test with area less than AREA1 (200 square units), should be false.
        system.NUMPOINTS = 3;
        system.X = new double[] {0, 3, 3};
        system.Y = new double[] {0, 0, 4};
        assertEquals(system.LIC3(), false);

        // Test with area more than AREA1, should be true.
        system.NUMPOINTS = 4;
        system.X = new double[] {-1, 0, 21, 21};
        system.Y = new double[] {-1, 0, 0, 21};
        assertEquals(system.LIC3(), true);

        // NUMPOINTS doesn't correspond with the actual number of points, should be false.
        system.NUMPOINTS = 3;
        system.X = new double[] {-1, 0, 21, 21};
        system.Y = new double[] {-1, 0, 0, 21};
        assertEquals(system.LIC3(), false);
    }
}
