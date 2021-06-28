class longest_palindromic_subsequence{


    public static int lps_recursively(String x ,int i , int j ){

        if(i==j)    return 1;
        if( i > j) return 0;
        if(x.charAt(i)==x.charAt(j))
            return lps_recursively(x,i+1,j-1)+2;

        int left_sub=lps_recursively(x,i,j-1);
        int right_sub=lps_recursively(x,i+1,j);
        return Math.max(left_sub,right_sub);
    }
    public static int lps_memoized(String x ,int i , int j,int dp[][] ){

        if(i==j)    return dp[i][j]= 1;
        if( i > j) return dp[i][j]=0;
        if(dp[i][j]!=-1)    return dp[i][j];
        if(x.charAt(i)==x.charAt(j))
            return dp[i][j]= lps_recursively(x,i+1,j-1)+2;

        int left_sub=lps_recursively(x,i,j-1);
        int right_sub=lps_recursively(x,i+1,j);
        return dp[i][j]=Math.max(left_sub,right_sub);
    }
    public static int lps_tab(String x){

        int dp[][]= new int[x.length()][x.length()];

        for(int gap=0;gap<x.length();gap++){

            for(int i=0,j=i+gap ; i<dp.length && j<dp[0].length ;i++,j++){
                if(i==j){
                    dp[i][j]=1;
                }else if(x.charAt(i)==x.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+2;
                }else{
                    dp[i][j]=Math.max(dp[i][j-1] ,dp[i+1][j]);
                }

            }

        }
        return dp[0][x.length()-1];
    }

    public static void main(String args[]){

        // question string expected ans -> 5  ( abcba) 

        String ques= "abcbda";
        // Whenever we use a memoized dp we fill dp with -1 ( not in every question)
        // because at base cases 0 is also one of value which i am trying to place 
        // at particular dp index , if i not fill it with -1 it will still work but it
        //  will cause and TLE error at big string cases 


        int dp[][]= new int[ques.length()][ques.length()];
        for(int i=0;i<dp.length;i++)
            for(int j=0;j<dp[0].length;j++)
                dp[i][j]=-1;


        
        int rec_ans=lps_recursively(ques,0,ques.length()-1);
        int mem_ans=lps_memoized(ques,0,ques.length()-1,dp);
        int tab_ans=lps_tab(ques);
        
        System.out.println("recursive answer :"+rec_ans);
        System.out.println("memoized answer : "+mem_ans);
        System.out.println("tabulated answer : "+tab_ans);


    }


}
