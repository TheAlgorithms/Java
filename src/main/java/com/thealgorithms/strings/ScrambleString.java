class Solution {
    Boolean dp[][][];
    char[] s1, s2;
    int n;
    public boolean isScramble(String s1, String s2) {
        this.s1=s1.toCharArray();
        this.s2=s2.toCharArray();
        n=s1.length();
        dp=new Boolean[n][n][n+1];
        return f(0, 0, n);
    }

    private boolean f(int i, int j, int len) {
        if(len==1) return dp[i][j][len]=s1[i]==s2[j];
        if(dp[i][j][len]!=null) return dp[i][j][len];

        for(int k=1; k<len; k++) {
            if(f(i, j, k) && f(i+k, j+k, len-k))
                return dp[i][j][len]=true;
            else if(f(i, j+len-k, k) && f(i+k, j, len-k))
                return dp[i][j][len]=true;
        }
        return dp[i][j][len]=false;
    }
}
