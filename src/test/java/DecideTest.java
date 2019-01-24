//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;
import org.junit.Test;

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
    }

    /**
    Test for LIC 1. First case should result in true, as the radius 15 is too small, whereas
    20 is enough to cover a set of three points.
    */
    @Test
    public void testLIC13() {
        Decide d = new Decide();
        d.NUMPOINTS = 7;
        d.X = new double[]{0,14,30,29,13,40,2};
        d.Y = new double[]{0,27,0,44,16,22,15};
        d.PARAMETERS.A_PTS = 1;
        d.PARAMETERS.B_PTS = 1;
        d.PARAMETERS.RADIUS1=15;
        d.PARAMETERS.RADIUS2=20;
        assertEquals(d.lic13(), true);

        d.NUMPOINTS = 7;
        d.X = new double[]{0,-10,30,29,13,40,2};
        d.Y = new double[]{0,27,0,44,16,22,15};
        d.PARAMETERS.A_PTS = 1;
        d.PARAMETERS.B_PTS = 1;
        d.PARAMETERS.RADIUS1=15;
        d.PARAMETERS.RADIUS2=20;
        assertEquals(d.lic13(), false);
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
