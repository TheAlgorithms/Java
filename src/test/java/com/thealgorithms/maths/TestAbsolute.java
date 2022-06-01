import static org.junit.Assert.*;

import org.junit.Test;

public class TestAbsolute {
	@Test
	public void TestAbsoluteValue(){
		assertTrue("Absolute value incorrect!", 1 == AbsoluteValue.getAbsValue(-1));
	}
}