class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        int positive = 0;
        int negative = 0;

        if (i<n && s.charAt(i) == '+') {
            positive++; 
            i++;
        }

        if (i<n && s.charAt(i) == '-') {
            negative++; 
            i++;
        }

        double ans = 0;

        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            ans = ans * 10 + (s.charAt(i) - '0'); 
            i++;
        }

        if (negative > 0) { 
            ans = -ans;
        }
        if (positive > 0 && negative > 0) { 
            return 0;
        }

        int INT_MAX = (int) Math.pow(2, 31) ;
        int INT_MIN = (int) Math.pow(-2, 31);

        if (ans > INT_MAX) { 
            ans = INT_MAX;
        }

        if (ans < INT_MIN) { 
            ans = INT_MIN;
        }

        return (int) ans;
    }
}
