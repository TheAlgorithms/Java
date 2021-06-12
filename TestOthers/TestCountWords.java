package TestOthers;

import CountWords;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * I create the 3 test cases:
 * Test 1: a string that have no numbers or potential non-alphanumeric characters.
 * Test 2: a string that contains number.
 * Test 3: a string that contains number and potential non-alphanumeric characters.
 */

@SuppressWarnings("InstantiationOfUtilityClass")
public class TestCountWords {
    int [] testText1 = "Hello earth";
    int [] testText2 = "Hello earth 505";
    int [] testText3 = "Hello earth 505, this is Java!";

    CountWords test = new CountWords();

    @Test
    public void testCoWo() {
        assertEquals(2, CountWords.testCoWo(testText1));
        assertEquals(3, CountWords.testCoWo(testText2));
        assertEquals(11, CountWords.testCoWo(testText3));
    }
}
