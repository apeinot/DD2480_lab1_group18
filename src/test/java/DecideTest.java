//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import java.lang.Math;

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
    
    /**
    Test for LIC 13. First case should result in true, as the radius 15 is too small, whereas
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
        d.Y = new double[]{0,27,0,44,70,22,15};
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

    /**
    Test case for the computeFUV function of Decide.java.
    Expected values of the FUV cells are:
    [0]  - false, since PUM[0][1] is false
    [4]  - true, since all PUM cells in the fourth row are true
    [12] - true, since PUV[4] is true
    */
    @Test
    public void testComputeFUV(){
        Decide decide = new Decide();
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){

                decide.PUM[i][j] = true;
            }
        }

        // PUM is a symmetric matrix
        decide.PUM[0][1] = false;
        decide.PUM[1][0] = false;
        decide.PUM[12][13] = false;
        decide.PUM[13][12] = false;
        for(int i = 0; i < 15; i++){
            // set only twelvth entry to false
            if(i != 12){
                decide.PUV[i] = true;
            }
            else{
                decide.PUV[i] = false;
            }
        }
        decide.computeFUV();
        // this row of PUM has a false entry
        assertEquals(decide.FUV[0], false);
        // in this row PUV is false which makes FUV true by definition
        assertEquals(decide.FUV[12], true);
        // all entries in PUM are true and there FUV has to be true
        assertEquals(decide.FUV[4], true);
    }

    /**
    Test case for computePUM function in Decide.java. This test case
    sets all values of LCM to 'NOTUSED'. Therefore, the expected value
    for all cells in PUM is true.
    */
    @Test
    public void testComputePUM1(){
        Decide decide = new Decide();
        // set all cells of LCM to 'NOTUSED'
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                decide.LCM[i][j] = "NOTUSED";
            }
        }
        decide.computePUM();
        boolean value = true;
        // value will only be true if all entries of PUM are true
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(i == j){
                    continue;
                }
                value = value && decide.PUM[i][j];
            }
        }
        assertEquals(value,true);
    }

    /**
    Test case for computePUM function in Decide.java. This test case checks
    the cells of PUM for all possible combination of the operators 'ANDD' and
    'ORR' with the boolean values of the vector CMV. The test case evaluates
    whether the following rules hold:
    true 'ANDD' true - expected value true
    true 'ANDD' false - expected value false
    true 'ORR' true - expected value true
    true 'ORR' false - expected value true
    false 'ANDD' false - expected value false
    false 'ORR' false - expected value false
    */
    @Test
    public void testComputePUM2(){
        Decide decide = new Decide();
        // set all entries of LCM to 'NOTUSED'
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                decide.LCM[i][j] = "NOTUSED";
            }
        }
        // set some values of LCM so that all possible combinations are created
        decide.LCM[0][1] = "ANDD";
        decide.LCM[0][2] = "ORR";
        decide.LCM[0][13] = "ANDD";
        decide.LCM[0][14] = "ORR";
        decide.LCM[9][10] = "ANDD";
        decide.LCM[9][11] = "ORR";

        // set first eight entries of CMV to true, all the others are set to false
        for(int i = 0; i < 15; i++){
            if(i < 8){
                decide.CMV[i] = true;
            }
            else{
                decide.CMV[i] = false;
            }
        }
        decide.computePUM();
        boolean value = true;
        // values is only true if all conditions are met
        value = value && decide.PUM[0][1];
        value = value && decide.PUM[0][2];
        value = value && !decide.PUM[0][13];
        value = value && decide.PUM[0][14];
        value = value && !decide.PUM[9][10];
        value = value && !decide.PUM[9][11];
        assertEquals(value,true);
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
    /**
    Test case for LIC2 function of Decide.java. This test case evaluates
    whether LIC2 returns true if the angle is close to 0 or close to 2*PI
    and whether it returns false if the angle is PI 
    */
    public void testLIC2(){
        Decide decide = new Decide();
        decide.NUMPOINTS = 3;

        // points will give an angle that is close to 0
        decide.X = new double[]{3,0,1};
        decide.Y = new double[]{7,0,5};
        assertEquals(decide.LIC2(),true);

        // points will give an angle that is close to 2*PI
        decide.X = new double[]{1,0,3};
        decide.Y = new double[]{5,0,7};
        assertEquals(decide.LIC2(),true);

        // points will give an angle of PI
        decide.X = new double[]{1,0,-1};
        decide.Y = new double[]{0,0,0};
        assertEquals(decide.LIC2(),false);
    }

    /**
    Test case for computeLAUNCH function of Decide.java. This case evaluates
    two scenarios. First, there is a case where all entries of FUV are true
    and therefore LAUNCH should also be true. In the second case, one entry
    (in this case the fourth entry) of FUV is set to false and therefore
    LAUNCH should alse be false.
    */
    @Test
    public void testComputeLAUNCH(){
        Decide decide = new Decide();

        // test if all entries of FUV are true
        for(int i = 0; i < 15; i++){
            decide.FUV[i] = true;
        }
        decide.computeLAUNCH();
        assertEquals(decide.LAUNCH, true);

        // test if not all entries of FUV are true
        decide.FUV[4] = false;
        decide.computeLAUNCH();
        assertEquals(decide.LAUNCH, false);
    }

    @Test
    public void testLIC5() {
    // Test of the LIC number 5 in a case where the condition should be triggered (true)
        Decide system = new Decide();
        system.NUMPOINTS = 5;
        system.X = new double[] {0, 20, 2, 0, 102};
        system.Y = new double[] {1, 10, 3, 0, 0};
        assertEquals(system.LIC5(), true);


    // Test of the LIC number 5 in a case where the condition should not be triggered (false)

        system.NUMPOINTS = 5;
        system.X = new double[] {0, 20, 30, 40, 50};
        system.Y = new double[] {1, 10, 3, 0, 0};
        assertEquals(system.LIC5(), false);
    }

    /**
    Test cases for LIC number 7. One test where the condition is fullfil and another where it's not
    */
    @Test
    public void testLIC7() {
    // Test of the LIC number 7 in a case where the condition should be triggered 
    // (true with LENGTH1 = 100)
        Decide system = new Decide();
        system.NUMPOINTS = 5;
	    system.PARAMETERS.K_PTS = 3;
        system.X = new double[] {0, 20, 2, 0, 102};
        system.Y = new double[] {1, 10, 3, 0, 0};
        assertEquals(system.LIC7(), true);


    // Test of the LIC number 0 in a case where the condition should not be triggered (false)

        system.NUMPOINTS = 5;
        system.X = new double[] {0, 20, 2, 0, 12};
        system.Y = new double[] {1, 10, 3, 0, 0};
        assertEquals(system.LIC7(), false);
    }

}
