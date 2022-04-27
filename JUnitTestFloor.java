package assignent3;

import org.junit.Test;
import com.thealgorithms.maths.Floor;
import static org.junit.Assert.assertEquals;

public class JUnitTestFloor {
	
	@Test
	public void case1() { // Test if input is null
		double i = (Double) null;
		assertEquals(Math.floor(i), Floor.floor(i), 0);
	}
	
	@Test
	public void case2() { // Test if epsilon = 0
		double i = 3.1291;
		assertEquals(Math.floor(i), Floor.floor(i), 0);
	}
	
	@Test
	public void case3() { // Test if epsilon = 0.5
		double i = 3.1291;
		assertEquals(Math.floor(i), Floor.floor(i), 0.5);
	}
	
	@Test
	public void case4() { // Test negative input
		double i = -3.1291;
		assertEquals(Math.floor(i), Floor.floor(i), 0);
	}
}
