class Solution {
    public int numDecodings(String s) {
      
        int dp[]=new int[s.length()+1];
        Arrays.fill(dp,-1);
      return solve(s,0,dp);
      
    }
    static int solve(String s,int i,int dp[]){
      
        if(i>=s.length()) return 1;
        int l=0;
        int r=0;
        if(dp[i]!=-1) return dp[i];
      
        if(i+1<=s.length()){
            String t=s.substring(i,i+1);
            if(check(t)){
            l=solve(s,i+1,dp);
            }
           
        }
      
        if(i+2<=s.length()){
           String t=s.substring(i,i+2);
            if(check(t))
            r=solve(s,i+2,dp); 
        }
      
        return dp[i]=l+r;
    }
  
    static boolean check(String s){
      
        if(s.charAt(0)=='0') return false;
        if(s.length()==1) return true;
        int t=Integer.parseInt(s);
        if(t>=10 && t<=26) return true;
        return false;
      
    }
   
}
