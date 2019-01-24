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

    @Test
    public void testLIC3(){
        Decide system = new Decide();
        /*
        Test with area less than AREA1 (200 square units), should be false.
        The triangle points are (0,0), (3,0) and (3,4), which creates a
        triangle with an area of 6 square units. Separated by (E_PTS = 6) and
        (F_PTS = 9) points.
        */
        system.NUMPOINTS = 18;
        system.X = new double[] {0,   1,2,3,4,5,6,   3,   1,2,3,4,5,6,7,8,9,   3};
        system.Y = new double[] {0,   1,2,3,4,5,6,   0,   1,2,3,4,5,6,7,8,9,   4};
        assertEquals(system.LIC10(), false);

        /*
        Test with area more than AREA1, should be true. The triangle is (0,0),
        (21,0) and (21,21) which creates a triangle of 220.5 square units. This
        is greater than AREA1 (220 square units).
        */
        system.NUMPOINTS = 18;
        system.PARAMETERS.AREA1 = 220;
        system.X = new double[] {0,   1,2,3,4,5,6,   21,   1,2,3,4,5,6,7,8,9,   21};
        system.Y = new double[] {0,   1,2,3,4,5,6,   0,   1,2,3,4,5,6,7,8,9,   21};
        assertEquals(system.LIC10(), true);

        // Now the area is higher than that of the triangle, so this should be false.
        system.PARAMETERS.AREA1 = 221;
        assertEquals(system.LIC10(), false);

        // Not enough points, should be false. E_PTS + F_PTS = 6 + 9 = 15 > NUMPOINTS - 3 = 14.
        system.NUMPOINTS = 17;
        system.PARAMETERS.AREA1 = 200;
        system.X = new double[] {0,   1,2,3,4,5,6,   21,   1,2,3,4,5,6,7,8,   21};
        system.Y = new double[] {0,   1,2,3,4,5,6,   0,   1,2,3,4,5,6,7,8,   21};
        assertEquals(system.LIC10(), false);
    }

}
