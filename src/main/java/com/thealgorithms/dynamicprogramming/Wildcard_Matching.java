// ***** PROBLEM STATEMENT *******

// Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).
// The matching should cover the entire input string (not partial).


class Wildcard_Matching {
    public static void main(String[] args) {
        String s = "agbhuagdg";
        String p = "iusfdhbuyvbib";
        System.out.println(isMatch(s,p));
    }
    public static boolean isMatch(String s, String p) {
        if(s.length()==0&&p.length()==0)return true;
        int dp[][] = new int[s.length()][p.length()];
        for(int i = 0;i<dp.length;i++){
            for(int j = 0;j<dp[0].length;j++){
                dp[i][j] = -1;
            }
        }
        return check(s,p,s.length()-1,p.length()-1,dp);
    }
    static boolean check(String s,String p,int i,int j,int dp[][]){
        if(i<0&&j<0)return true;
        if(i>=0&&j<0)return false;
        if(i<0&&j>=0){
            while(j>=0){
                if(p.charAt(j--)!='*')return false;
            }
            return true;
        }
        if(dp[i][j]==-1){
            if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?'){
                if(i==0&&j==0)return true;
                if(check(s,p,i-1,j-1,dp))dp[i][j] = 1;
                else dp[i][j] = 0;
            }
            else if(p.charAt(j)=='*'){
                if(j==0){
                    dp[i][j] = 1;
                    return true;
                }
                if(check(s,p,i-1,j,dp)||check(s,p,i,j-1,dp))dp[i][j] = 1;
                else dp[i][j] = 0;
            }
        }
        return dp[i][j]==1;
    }
}
