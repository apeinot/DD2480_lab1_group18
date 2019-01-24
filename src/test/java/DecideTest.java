//Test cases of decide functionnality

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DecideTest {
  
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
