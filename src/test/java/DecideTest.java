//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.lang.Math;

public class DecideTest {


    /**
    Test for lic6(). In the first case the furthest point is (1,-2) and the line
    from (-1,3) to (3,2), in which the point is approximately 4.3 units away.
    The second case has the two point 2 and 4 units from the line respectively,
    neither being greater than DIST.
    */
    @Test
    public void testLIC6_1() {
        Decide d = new Decide();
        d.NUMPOINTS = 10;
        d.PARAMETERS.N_PTS = 3;
        d.X = new double[]{0,2,4,1,3,1.5,-2,-1,1,3};
        d.Y = new double[]{0,2,0,2,1,-1,2,3,-2,2};
        d.PARAMETERS.DIST = 3;
        assertEquals(d.lic6(), true);

        d.NUMPOINTS = 4;
        d.PARAMETERS.N_PTS = 4;
        d.X = new double[]{0,3,1,4};
        d.Y = new double[]{0,2,4,0};
        d.PARAMETERS.DIST = 4;
        assertEquals(d.lic6(), false);
    }

    /**
    Test case for when the projection of the furthest point is further than
    either of the two end points. The shortest distance to the line created
    would be 2, while the distance to the end point, which is sough after,
    is sqrt(5)
    */
    @Test
    public void testLIC6_2() {
        Decide d = new Decide();
        d.NUMPOINTS = 3;
        d.PARAMETERS.N_PTS = 3;
        d.X = new double[]{0,-1,4};
        d.Y = new double[]{0,2,0};
        d.PARAMETERS.DIST = 2;
        assertEquals(d.lic6(), true);
    }

    @Test
    public void testTrue() {
        assertEquals(true, true);
    }
}
