//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DecideTest {

    @Test
    public void testTrue() {
        assertEquals(true, true);
    }

    /**
    Test case for LIC4 function of Decide.java. Expected return is true,
    as there is one point in each quadrant
    */
    @Test
    public void testLIC4_1() {
        Decide d = new Decide();
        d.NUMPOINTS = 4;
        d.PARAMETERS.Q_PTS = 4;
        d.X = new double[]{1.0,-1.0,1.0,-1.0};
        d.Y = new double[]{1.0,1.0,-1.0,-1.0};
        d.PARAMETERS.QUADS=1;
        assertEquals(d.LIC4(), true);
        d.PARAMETERS.QUADS=2;
        assertEquals(d.LIC4(), true);
        d.PARAMETERS.QUADS=3;
        assertEquals(d.LIC4(), true);
    }

    /**
    Test case for LIC4 function of Decide.java. Expected return is true,true,false,
    as there is one point in each quadrant save for one.
    */
    @Test
    public void testLIC4_2() {
        Decide d = new Decide();
        d.NUMPOINTS = 3;
        d.PARAMETERS.Q_PTS = 3;
        d.X = new double[]{1.0,-1.0,1.0};
        d.Y = new double[]{1.0,1.0,-1.0};
        d.PARAMETERS.QUADS=1;
        assertEquals(d.LIC4(), true);
        d.PARAMETERS.QUADS=2;
        assertEquals(d.LIC4(), true);
        d.PARAMETERS.QUADS=3;
        assertEquals(d.LIC4(), false);
    }

    /**
    Test case for LIC4 function of Decide.java. Expected return is false,false,false,false
    as the input data is bad.
    */
    @Test
    public void testLIC4_3() {
        Decide d = new Decide();
        d.NUMPOINTS = 3;
        d.PARAMETERS.Q_PTS = 3;
        d.X = new double[]{1.0,-1.0,1.0};
        d.Y = new double[]{1.0,1.0,-1.0};
        d.PARAMETERS.QUADS=0;
        assertEquals(d.LIC4(), false);
        d.PARAMETERS.QUADS=4;
        assertEquals(d.LIC4(), false);
        d.PARAMETERS.QUADS=3;
        d.PARAMETERS.Q_PTS = 0;
        assertEquals(d.LIC4(), false);
        d.PARAMETERS.Q_PTS = 4;
        assertEquals(d.LIC4(), false);
    }

    /**
    Test case for LIC4 function of Decide.java. Expected return is false,true
    as there is several nodes in one quadrant and one in another.
    */
    @Test
    public void testLIC4_4() {
        Decide d = new Decide();
        d.NUMPOINTS = 10;
        d.X = new double[]{1.0,1.0,1.0,1.0,-1.0,1.0,1.0,1.0,1.0,1.0};
        d.Y = new double[]{1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0};
        d.PARAMETERS.QUADS=2;
        d.PARAMETERS.Q_PTS=2;
        assertEquals(d.LIC4(), false);
        d.PARAMETERS.QUADS=1;
        assertEquals(d.LIC4(), true);
    }

    /**
    Test case for LIC4s quadrant helper function function of Decide.java. Expected return is 0,0,0,1,2,
    as the points are on the axis lines which fall under specific rules, see decide.java.
    */
    @Test
    public void testLIC4QuadrantHelper() {
        Decide d = new Decide();
        assertEquals(d.LIC4QuadrantHelper(0.0,0.0), 0);
        assertEquals(d.LIC4QuadrantHelper(1.0,0.0), 0);
        assertEquals(d.LIC4QuadrantHelper(0.0,1.0), 0);
        assertEquals(d.LIC4QuadrantHelper(-1.0,0.0), 1);
        assertEquals(d.LIC4QuadrantHelper(0.0,-1.0), 2);
    }
}
