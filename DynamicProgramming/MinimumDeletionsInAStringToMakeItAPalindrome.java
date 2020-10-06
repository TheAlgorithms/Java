package DynamicProgramming;

public class MinimumDeletionsInAStringToMakeItAPalindrome {
    public static int LongestPalindromicSubsequence(String X, int n){
        StringBuilder sb = new StringBuilder();
        sb.append(X);
        sb.reverse();
        String Y = sb.toString();

        int[][] t = new int[n+1][n+1];
        for(int i = 0; i < n+1; i++){
            for(int j = 0; j < n+1; j++){
                if(i == 0 || j == 0){
                    t[i][j] = 0;
                }
            }
        }

        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < n+1; j++){
                if(X.charAt(i-1) == Y.charAt(j-1)){
                    t[i][j] = 1 + t[i-1][j-1];
                }
                else{
                    t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
                }
            }
        }
        return X.length() - t[n][n];
    }
    public static void main(String[] args) {
        String X = "agbcba";
        System.out.println(LongestPalindromicSubsequence(X, X.length()));
    }
}

//Output: 1