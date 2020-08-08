import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PalindromeTest {
	
	Palindrome palindrome = new Palindrome();
	String message =  "abccba";
	
	@Test
    public void TestReverseString()
    {
    	String reversedWord = palindrome.reverseString(message);
    	assertEquals(message, reversedWord);
    }
	
	@Test
	public void TestFirstWay()
	{
		assertEquals(true, palindrome.FirstWay(message));
	}
	
	@Test
	public void TestisPalindrome()
	{
		assertEquals(true, palindrome.isPalindrome(message));
	}
	
}
