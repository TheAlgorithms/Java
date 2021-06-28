package DynamicProgramming;
import java.util.Scanner;
/**
*	Given a non-empty string containing only digits, determine the 
*   total number of ways to decode it.
* 
*	A message containing letters from A-Z is being encoded to 
*	numbers using the following mapping:
*
*   'A' -> 1
*	'B' -> 2
*	...
*	'Z' -> 26
*
*  Example
*  Input: "226"
*  Output: 3
*  Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*
* @author Mohd Asif (https://github.com/asif536)
*
**/
public class DecodeWays{
	/**
     * @param str The input String
     * @return Number of way to decode the String str
     */
	public int numberOfDecodings(String str) {
        int n=str.length();
        int dp[]=new int[n+1];
        dp[0]=1;
        dp[1]=str.charAt(0)=='0'?0:1;
        for(int i=2;i<=n;i++)
        {
            int last=str.charAt(i-1)-'0';
            int prev=str.charAt(i-2)-'0';
            int num=prev*10+last;
            if(num==0 || last==0&&prev>=3){
                return 0;
            }else if(last==0){
                dp[i]=dp[i-2];
            }else if(prev!=0&&num<27){
                dp[i]=dp[i-1]+dp[i-2];
            }else{
                dp[i]=dp[i-1];
            }
        }
        return dp[n];
    }
      /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	String str=sc.next(); // Enter the String
    	System.out.println(numberOfDecodings(str));
    	sc.close();
    }
}