import static org.junit.Assert.*;
import org.junit.Test;

public class TestVolume {
	@Test
	public void testVolumePyramid() {
		assertTrue("Failure", 2 == Volume.volumePyramid(2, 3));
	}
}