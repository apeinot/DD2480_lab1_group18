//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

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

}
