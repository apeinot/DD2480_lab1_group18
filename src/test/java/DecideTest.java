//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DecideTest {
  
    @Test
    public void testTrue() {
        assertEquals(true, true);
    }

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
}
