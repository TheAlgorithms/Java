import static org.junit.Assert.*;
import org.junit.Test;

public class AbecedarianTest {
	@Test
    public void testAbecedarian() {
		Abecedarian abecedarian = new Abecedarian();
		String testString1 = "abcdefg";
		boolean test1 = abecedarian.isAbecedarian(testString1);
		assertEquals(true, test1);
		String testString2 = "fghjkl";
		boolean test2 = abecedarian.isAbecedarian(testString2);
		assertEquals(true, test2);
	}
}
