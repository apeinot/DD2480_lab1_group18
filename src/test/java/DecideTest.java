//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.lang.Math;

public class DecideTest {

    /**
    Test case for lic1 function of Decide.java. Expected return is true,
    as the required radius to form a circle around (0,0), (30,0), (0,16) is
    greater than 15.
    */
    @Test
    public void testLIC1_1() {
        Decide d = new Decide();
        d.NUMPOINTS = 5;
        d.X = new double[]{0,30,0,1,10};
        d.Y = new double[]{0,0,16,13,8};
        d.PARAMETERS.RADIUS1=15;
        assertEquals(d.lic1(), true);
    }

    /**
    Test case for lic1 function of Decide.java. Expected return is false,
    as no set of points are far enough apart that a circle cannot contain them.
    */
    @Test
    public void testLIC1_2() {
        Decide d = new Decide();
        d.NUMPOINTS = 5;
        d.X = new double[]{17, 17, 22, 13, 0};
        d.Y = new double[]{23, -7, 12, 15, 10};
        d.PARAMETERS.RADIUS1=15;
        assertEquals(d.lic1(), false);

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

    @Test
    public void testLIC0() {
    // Test of the LIC number 0 in a case where the condition should be triggered (true)
	Decide system = new Decide();
	system.NUMPOINTS = 5;
	system.X = new double[] {0, 20, 2, 0, 102};
	system.Y = new double[] {1, 10, 3, 0, 0};
	assertEquals(system.LIC0(), true);


    // Test of the LIC number 0 in a case where the condition should not be triggered (false)

	system.NUMPOINTS = 5;
        system.X = new double[] {0, 20, 2, 0, 12};
        system.Y = new double[] {1, 10, 3, 0, 0};
        assertEquals(system.LIC0(), false);
    }

}
