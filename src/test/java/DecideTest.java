//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class DecideTest {
  
    @Test
    public void testTrue() {
        assertEquals(true, true);
    }

    @Test
    /**
    Test case for computePUM function in Decide.java. This test case
    sets all values of LCM to 'NOTUSED'. Therefore, the expected value
    for all cells in PUM is true.
    */
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

    @Test
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
}
