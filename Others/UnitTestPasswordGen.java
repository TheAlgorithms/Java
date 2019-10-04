package Others;

import org.junit.Test;
import Others.PasswordGen;
import static org.junit.Assert.*;

public class UnitTestPasswordGen {
	@Test
    public void escapeTestCase0() throws Exception {
        int min_length = 1;
        int max_length = max_length + 3;
        boolean output;
        boolean expected = true;
        int len = PasswordGen.generatePassword(min_length,max_length).length();
        output = (len >= min_length && len <= max_length) ? true : false;

        assertEquals(output,expected);
    }
    @Test
    public void escapeTestCase1() throws Exception {
        int min_length = 8;
        int max_length = min_length;
        boolean output;
        boolean expected = true;
        int len = PasswordGen.generatePassword(min_length,max_length).length();
        output = (len == min_length) ? true : false;

        assertEquals(output,expected);
    }
    @Test
    public void escapeTestCase2() throws Exception {
        int min_length = 1;
        int max_length = min_length - 1;
        boolean output;
        boolean expected = true;
        int len = PasswordGen.generatePassword(min_length,max_length).length();
        output = (len == 0) ? true : false;

        assertEquals(output,expected);
    }
}