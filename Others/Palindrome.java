package Others;

class Palindrome {

    private String reverseString(String x) { // *helper method
        StringBuilder output = new StringBuilder(x);
        return output.reverse().toString();
    }

    public boolean FirstWay(String x) { // *palindrome method, returns true if palindrome
        if (x == null || x.length() <= 1)
            return true;
        return x.equalsIgnoreCase(reverseString(x));
    }

    public boolean SecondWay(String x) {
        if (x.length() == 0 || x.length() == 1)
            return true;

        if (x.charAt(0) != x.charAt(x.length() - 1))
            return false;

        return SecondWay(x.substring(1, x.length() - 1));
    }

    /**
     * This method ignores all non-alphanumeric characters and case runs in O(n)
     * where n is the length of s
     * 
     * @param s String to check
     * @return true if s is palindrome else false
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().trim();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c))
                sb.append(c);
        }
        s = sb.toString();
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--))
                return false;

        }
        return true;
    }
}
