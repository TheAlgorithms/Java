public class longestPalindromicSubstring {
    public static String getLPS(String s)
    {
        if(s.length() == 0)
            return "";

        // If string has same character, no computations needed.
        if(s.replace(String.valueOf(s.charAt(0)), "").length() == 0)
            return s;

        String current = s.substring(0, 1);
        for(int i = 1; i < s.length(); i++)
        {
            // Try to expand the palindrome using the ith character
            String odd = getLPSFrom(s, i-1, i+1); // Considers centre
            String even = getLPSFrom(s, i-1, i); // Considers no centre
            //Comparator<String> strlenComp = (a, b) -> Integer.compare(a.length(), b.length());
            //int longest=strlenComp.compare(odd,even);
            String longest = (odd.length()>even.length()) ? odd : even;
            if(longest.length() > current.length())
                current = longest;
        }
        return current;
    }

    // Check whether a substring starting from left to right index is a palindrome or not
    public static String getLPSFrom(String s, int left, int right)
    {
        while(left >= 0&&right < s.length())
        {
            if(s.charAt(left) != s.charAt(right))
                break;
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }

    public static void main(String[] args)
    {
        // Testcases
        assert getLPS("abcbadefef").equals("abcba");
        assert getLPS("saeedogeeseseegodabsds").equals("dogeeseseegod");
        assert getLPS("uncopyrightable").equals("u");
    }
}
