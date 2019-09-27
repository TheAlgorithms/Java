import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeTest {

    @Test
    public void isPalindrome1() {
        Palindrome p = new Palindrome();
        assertEquals(true, p.isPalindrome("@aa^^^^^"));
    }

    @Test
    public void isPalindrome2() {
        Palindrome p = new Palindrome();
        assertEquals(true, p.isPalindrome("123abccba321"));
    }

    @Test
    public void isPalindrome3() {
        Palindrome p = new Palindrome();
        assertEquals(true, p.isPalindrome("@##$##$%$%#$"));
    }

    @Test
    public void isPalindrom4() {
        Palindrome p = new Palindrome();
        assertEquals(true, p.isPalindrome(""));
    }

    @Test
    public void isPalindrome5() {
        Palindrome p = new Palindrome();
        assertEquals(false, p.isPalindrome("fghhge"));
    }
}