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
    public void testComputePUM1(){
        Decide decide = new Decide();
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                decide.LCM[i][j] = "NOTUSED";
            }
        }
        for(int i = 0; i < 15; i++){
            decide.CMV[i] = false;
        }
        decide.computePUM();
        boolean value = true;
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
    public void testComputePUM2(){
        Decide decide = new Decide();
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                decide.LCM[i][j] = "NOTUSED";
            }
        }
        decide.LCM[0][1] = "ANDD";
        decide.LCM[0][2] = "ORR";
        decide.LCM[0][13] = "ANDD";
        decide.LCM[0][14] = "ORR";
        decide.LCM[9][10] = "ANDD";
        decide.LCM[9][11] = "ORR";

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
        value = value && decide.PUM[0][1];
        value = value && decide.PUM[0][2];
        value = value && !decide.PUM[0][13];
        value = value && decide.PUM[0][14];
        value = value && !decide.PUM[9][10];
        value = value && !decide.PUM[9][11];
        assertEquals(value,true);
    }
}
