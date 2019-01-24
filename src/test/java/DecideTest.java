//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DecideTest {
    @Test
    public void testLIC8(){
        /*
        The 0th (0,0) and 4th point (30,0) are (A_PTS = 3) points apart, the
        4th (30,0) and the 14th (0,16) point are (B_PTS = 9) points apart. The
        expected return is true, as the required radius to form a circle around
        (0,0), (30,0) and (0,16) is greater than 15.
        */
        Decide d = new Decide();
        d.NUMPOINTS = 17;
        d.X = new double[]{0,1,2,3,30,1,2,3,4,5,6,7,8,9,0,1,10};
        d.Y = new double[]{0,1,2,3,0,1,2,3,4,5,6,7,8,9,16,13,8};
        d.PARAMETERS.RADIUS1=15;
        assertEquals(d.LIC8(), true);

        /*
        The 0th (0,0) and 4th point (10,0) are (A_PTS = 3) points apart, the
        4th (10,0) and the 14th (0,15) point are (B_PTS = 9) points apart. The
        expected return is false, as the required radius to form a circle around
        (0,0), (30,0) and (0,16) is 15, which is not greater than 15.
        */
        d.X = new double[]{0,1,2,3,10,1,2,3,4,5,6,7,8,9,0,1,2};
        d.Y = new double[]{0,1,2,3,0,1,2,3,4,5,6,7,8,9,15,1,2};
        assertEquals(d.LIC8(), false);

        // A_PTS + B_PTS > (NUMPOINTS - 3), expected to be false.
        d.PARAMETERS.A_PTS = 15; d.PARAMETERS.B_PTS = 2;
        assertEquals(d.LIC8(), false);

        // NUMPOINTS < 5, expected to be false.
        d.NUMPOINTS = 4;
        assertEquals(d.LIC8(), false);

        // A_PTS = 0, which is < 1, expected to be false
        d.PARAMETERS.A_PTS = 0;
        assertEquals(d.LIC8(), false);

        // B_PTS = -1, which is < 1, expected to be false
        d.PARAMETERS.B_PTS = -1;
        assertEquals(d.LIC8(), false);
    }

    @Test
    public void testTrue() {
        assertEquals(true, true);
    }
}
