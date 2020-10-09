public class Palindrome {

    private static void printStatusOfPalindrome(String str) {
        if (isPalindrome(str)) {
            System.out.println("\"" + str + "\" is a Palindrome!");
        } else {
            System.out.println("\"" + str + "\" is not a Palindrome!");
        }
    }

    private static boolean isPalindrome(String str) {
        int n = str.length();
        for (int i = 0; i < n / 2; i++)
            if (str.charAt(i) != str.charAt(n - i - 1))
                return false;
        return true;
    }

    public static void main(String[] args) {
        printStatusOfPalindrome("redivider");
        printStatusOfPalindrome("phone");
    }
}