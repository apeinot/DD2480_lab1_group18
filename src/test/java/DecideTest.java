//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.lang.Math;

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
    Test for LIC9. Should return false as input data is bad
    */
    @Test
    public void testLIC9_1(){
        Decide d = new Decide();
        d.NUMPOINTS = 4;
        d.PARAMETERS.C_PTS = 1;
        d.PARAMETERS.D_PTS = 1;
        d.PARAMETERS.AREA1=10;
        assertEquals(d.LIC9(), false); //NUMPOINTS < 5.
        d.NUMPOINTS = 5;
        d.PARAMETERS.C_PTS = 0;
        assertEquals(d.LIC9(), false);//1 ≤ C_PTS
        d.PARAMETERS.C_PTS = 1;
        d.PARAMETERS.D_PTS = 0;
        assertEquals(d.LIC9(), false);//1 ≤ D_PTS
        d.PARAMETERS.D_PTS = 1;
        d.PARAMETERS.D_PTS = 2;
        assertEquals(d.LIC9(), false);//E PTS+F PTS ≤ NUMPOINTS−3
    }

    /**
    Test for LIC9. Expected result true and false since espilon changes to large number
    */
    @Test
    public void testLIC9_2(){
        Decide d = new Decide();
        d.NUMPOINTS = 5;
        d.PARAMETERS.C_PTS = 1;
        d.PARAMETERS.D_PTS = 1;
        d.PARAMETERS.EPSILON = 1;
        d.X = new double[]{0,0,0,0,10};
        d.Y = new double[]{10,0,0,0,0};
        assertEquals(d.LIC9(), true);
        d.PARAMETERS.EPSILON = 10;
        assertEquals(d.LIC9(), false);
    }

    /**
    Test for LIC9. Expected result true,false,true since C_PTS and D_PTS change accordingly
    */
    @Test
    public void testLIC9_3(){
        Decide d = new Decide();
        d.NUMPOINTS = 10;
        d.PARAMETERS.C_PTS = 2;
        d.PARAMETERS.D_PTS = 1;
        d.PARAMETERS.EPSILON = 1;
        d.X = new double[]{1,0,1,1,0,1,10,1,1,1};
        d.Y = new double[]{1,10,1,1,0,1,0,1,1,1};
        assertEquals(d.LIC9(), true);
        d.PARAMETERS.C_PTS = 1;
        d.PARAMETERS.D_PTS = 2;
        d.X = new double[]{1,0,1,0,1,1,10,1,1,1};
        d.Y = new double[]{1,10,1,0,1,1,0,1,1,1};
        assertEquals(d.LIC9(), true);
    }

    /**
    Test for LIC9. Expected result true as angle is PI radians and epsilon is zero
    */
    @Test
    public void testLIC9_4(){
        Decide d = new Decide();
        d.NUMPOINTS = 5;
        d.PARAMETERS.C_PTS = 1;
        d.PARAMETERS.D_PTS = 1;
        d.PARAMETERS.EPSILON = 0;
        d.X = new double[]{0,0,10,0,20};
        d.Y = new double[]{0,0,0,0,0};
        assertEquals(d.LIC9(), true);
    }

    /**
    Test the method pointDist
    */
    @Test
    public void testPointDist(){
        Decide d = new Decide();
        d.X = new double[]{0,1};
        d.Y = new double[]{0,0};
        assertEquals(d.PointDist(0,1), 1, 0.0001);
        d.X = new double[]{0,1};
        d.Y = new double[]{0,1};
        assertEquals(d.PointDist(0,1), Math.sqrt(2), 0.0001);
        d.X = new double[]{0,5,45};
        d.Y = new double[]{0,6,0};
        assertEquals(d.PointDist(0,2), 45, 0.0001);
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

    /**
    Test for LIC14. Should return false as input data is bad
    */
    @Test
    public void testLIC14_1(){
        Decide d = new Decide();
        d.NUMPOINTS = 4;
        d.PARAMETERS.E_PTS = 1;
        d.PARAMETERS.F_PTS = 1;
        d.PARAMETERS.AREA1=10;
        d.PARAMETERS.AREA2=10;
        assertEquals(d.LIC14(), false); //Violates 5 ≤ NUMPOINTS.
        d.NUMPOINTS = 5;
        d.PARAMETERS.E_PTS = 0;
        assertEquals(d.LIC14(), false);//Violates 1 ≤ E_PTS
        d.PARAMETERS.E_PTS = 1;
        d.PARAMETERS.F_PTS = 0;
        assertEquals(d.LIC14(), false);//Violates 1 ≤ F_PTS
        d.PARAMETERS.F_PTS = 2;
        assertEquals(d.LIC14(), false);//Violates E PTS+F PTS ≤ NUMPOINTS−3
        d.PARAMETERS.F_PTS = 1;
        d.PARAMETERS.AREA2 = -1;
        assertEquals(d.LIC14(), false);//Violates 0 ≤ AREA2
    }

    /**
    Test for LIC14 should return false, true, false as area 1 and 2 changes accordingly
    */
    @Test
    public void testLIC14_2(){
        Decide d = new Decide();
        d.NUMPOINTS = 5;
        d.X = new double[]{0, 0, 10, 0, 10};//A triangle of area 50
        d.Y = new double[]{0, 0, 0, 0, 10};
        d.PARAMETERS.E_PTS = 1;
        d.PARAMETERS.F_PTS = 1;
        d.PARAMETERS.AREA1=49;
        d.PARAMETERS.AREA2=50;
        assertEquals(d.LIC14(), false); //No triangle is *smaller* than 50.
        d.PARAMETERS.AREA2=51;
        assertEquals(d.LIC14(), true);
        d.PARAMETERS.AREA1=50;
        assertEquals(d.LIC14(), false); //No triangle is *greater* than 50*
    }

    /**
    Test for LIC14 should return true, false, testTrue as we edit area 1 and 2 accordingly.
    */
    @Test
    public void testLIC14_3(){
        Decide d = new Decide();
        d.NUMPOINTS = 6;
        d.X = new double[]{0, 0, 5, 2, 5, 2};//One 5x5 right triangle one 2x2 right triangle
        d.Y = new double[]{0, 0, 0, 0, 5, 2};
        d.PARAMETERS.E_PTS = 1;
        d.PARAMETERS.F_PTS = 1;
        d.PARAMETERS.AREA1=12; //5x5/2 should be bigger
        d.PARAMETERS.AREA2=3; //2x2/2 should be smaller
        assertEquals(d.LIC14(), true);
        d.X = new double[]{0, 0, 5, 2, 5, 2};//One 5x5 right triangle one 2x3 right triangle
        d.Y = new double[]{0, 0, 0, 0, 5, 3};
        assertEquals(d.LIC14(), false); //2x3/2 = 3 which is not smaller than 3
        d.PARAMETERS.AREA2=4; //2x3/2 should be smaller
        assertEquals(d.LIC14(), true); //2x3/2 = 3 which is not smaller than 3
    }

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
    public void testLIC10(){
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

    /**
    Test for LIC10. Should return false as input data is bad
    */
    @Test
    public void testLIC10_1(){
        Decide d = new Decide();
        d.NUMPOINTS = 4;
        d.PARAMETERS.E_PTS = 1;
        d.PARAMETERS.F_PTS = 1;
        d.PARAMETERS.AREA1=10;
        assertEquals(d.LIC10(), false); //NUMPOINTS < 5.
        d.NUMPOINTS = 5;
        d.PARAMETERS.E_PTS = 0;
        assertEquals(d.LIC10(), false);//1 ≤ E_PTS
        d.PARAMETERS.E_PTS = 1;
        d.PARAMETERS.F_PTS = 0;
        assertEquals(d.LIC10(), false);//1 ≤ F_PTS
        d.PARAMETERS.F_PTS = 1;
        d.PARAMETERS.F_PTS = 2;
        assertEquals(d.LIC10(), false);//E PTS+F PTS ≤ NUMPOINTS−3
    }

    /**
    Test for LIC10. Should return true and false since area will be 50
    */
    @Test
    public void testLIC10_2(){
        Decide d = new Decide();
        d.NUMPOINTS = 5;
        d.X = new double[]{0, 0, 10, 0, 10};
        d.Y = new double[]{0, 0, 0, 0, 10};
        d.PARAMETERS.E_PTS = 1;
        d.PARAMETERS.F_PTS = 1;
        d.PARAMETERS.AREA1=49;
        assertEquals(d.LIC10(), true);
        d.PARAMETERS.AREA1=50;
        assertEquals(d.LIC10(), false);
    }

    /**
    Test for LIC10. Should return true,false,true since E_PTS and F_PTS change
    accordingly.
    */
    @Test
    public void testLIC10_3(){
        Decide d = new Decide();
        d.NUMPOINTS = 6;
        d.X = new double[]{0, 0, 0, 10, 0, 10};
        d.Y = new double[]{0, 0, 0, 0, 0, 10};
        d.PARAMETERS.E_PTS = 2;
        d.PARAMETERS.F_PTS = 1;
        d.PARAMETERS.AREA1=49;
        assertEquals(d.LIC10(), true);
        d.PARAMETERS.E_PTS = 1;
        d.PARAMETERS.F_PTS = 2;
        assertEquals(d.LIC10(), false);
        d.X = new double[]{0, 0, 10, 0, 0, 10};
        d.Y = new double[]{0, 0, 0, 0, 0, 10};
        assertEquals(d.LIC10(), true);
    }

    /**
    Test for LIC10. Should return true (case close to the previous one).
    */
    @Test
    public void testLIC10_4(){
        Decide d = new Decide();
        d.NUMPOINTS = 10;
        d.X = new double[]{0, 0, 0, 0, 0, 0, 0, 10, 0, 10};
        d.Y = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 10};
        d.PARAMETERS.E_PTS = 2;
        d.PARAMETERS.F_PTS = 1;
        d.PARAMETERS.AREA1 = 49;
        assertEquals(d.LIC10(), true);
    }

    /**
    Test case for LIC 11. First case should result in true. Other cases should result in false
    */
    @Test
    public void testLIC11() {
    // Test of the LIC number 11 in a case where the condition should be triggered (true)
        Decide system = new Decide();
	system.PARAMETERS.G_PTS = 3;
        system.NUMPOINTS = 5;
        system.X = new double[] {200, 20, 2, 0, 102};
        system.Y = new double[] {1, 10, 3, 0, 0};
        assertEquals(system.LIC11(), true);


    // Test of the LIC number 11 in a case where the condition should not be triggered (false)
        system.NUMPOINTS = 5;
        system.X = new double[] {0, 20, 30, 40, 50};
        system.Y = new double[] {1, 10, 3, 0, 0};
        assertEquals(system.LIC11(), false);

    // Test of the LIC number 11 in a case where NUMPOINTS < 3 (false)

        system.NUMPOINTS = 3;
        system.X = new double[] {0, 20, 30};
        system.Y = new double[] {1, 10, 3};
        assertEquals(system.LIC11(), false);
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
    Test cases for LIC number 12. One test where the condition is fullfil and two where it's not
    */
    @Test
    public void testLIC12() {
    // Test of the LIC number 12 in a case where the condition should be triggered 
    // (true with LENGTH1 = 100  and LENGTH2 = 150)
        Decide system = new Decide();
        system.NUMPOINTS = 5;
	system.PARAMETERS.K_PTS = 3;
        system.X = new double[] {0, 20, 2, 0, 102};
        system.Y = new double[] {0, 10, 3, 1, 0};
        assertEquals(system.LIC12(), true);


    // Test of the LIC number 12 in a case where the condition should not be triggered 
    // (false because 12 is not greater than LENGTH1)

        system.NUMPOINTS = 5;
        system.X = new double[] {0, 20, 2, 0, 12};
        system.Y = new double[] {0, 10, 3, 1, 0};
        assertEquals(system.LIC12(), false);

    // Test of the LIC number 12 in a case where the condition should not be triggered 
    // (false because 1200 is not smaller than LENGTH2)

        system.NUMPOINTS = 5;
        system.X = new double[] {0, 20, 2, 0, 1200};
        system.Y = new double[] {0, 10, 3, 1, 0};
        assertEquals(system.LIC12(), false);

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


    /**
    Test case for decide function of Decide.java. This case evaluates the result of
    the LAUNCH value after the execution of the decide function. The first case
    should set the value LAUNCH to true and the second test case to false. The last
    test case should be trivially true.
    */
    @Test
    public void testDecide(){
        Decide decide = new Decide();
        decide.NUMPOINTS = 3;
        decide.X = new double[]{1,0,3};
        decide.Y = new double[]{5,0,7};

        // set everything to 'NOTUSED' besides cells (2,3) and (3,2)
        for(int i = 0; i < 15; i++){
            for(int j = 0; j< 15; j++){
                if(i != j){
                    decide.LCM[i][j] = "NOTUSED";
                }
            }
        }
        decide.LCM[2][3] = "ORR";
        decide.LCM[3][2] = "ORR";

        for(int i = 0; i < 15; i++){
            if(i != 2){
                decide.PUV[i] = false;
            }
            else{
                decide.PUV[i] = true;
            }
        }

        // first test case should lead to LAUNCH being set to true
        decide.decide();
        //assertEquals(decide.LAUNCH, true);

        // second test case should lead to LAUNCH being set to true
        decide.LCM[2][3] = "ANDD";
        decide.LCM[3][2] = "ANDD";
        decide.decide();
        //assertEquals(decide.LAUNCH, false);


        // first and second test case MISSING

        // set values so that LAUNCH will be true
        for(int i = 0; i < 15; i++){
            for(int j = 0; j< 15; j++){
                if(i != j){
                    decide.LCM[i][j] = "NOTUSED";
                }
            }
        }
        for(int i = 0; i < 15; i++){
            decide.PUV[i] = false;
        }
        decide.decide();
        assertEquals(decide.LAUNCH, true);
    }
    
    /**
    Test case for computeCMV function of Decide.java. This case evaluates whether
    the CMV vector is correctly set to true or false.
    */
    @Test
    public void testComputeCMV(){
        Decide system = new Decide();
        system.NUMPOINTS = 8;
	    system.PARAMETERS.K_PTS = 3;
        system.X = new double[] {0, 20, 2, 0, 102, 1, 0, 3};
        system.Y = new double[] {1, 10, 3, 0, 0, 5, 0, 7};
        system.computeCMV();
        // Test of the LIC number 7 in a case where the condition should be triggered 
        // (true with LENGTH1 = 100; because the distance is greater obviously)
        assertEquals(system.CMV[7], true);
        // x value of second point is greater than the x value of the third point
        assertEquals(system.CMV[5], true);
        // last three points give an angle close to 2*PI
        assertEquals(system.CMV[2], true);
        system.PARAMETERS.K_PTS = 7;
        system.computeCMV();
        // first and last point do not have a distance greater than LENGTH1 = 100
        assertEquals(system.CMV[12], false);
    }

}
