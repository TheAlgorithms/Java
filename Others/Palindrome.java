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
}
