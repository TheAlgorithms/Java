// Java Code for Palindrome Partitioning Problem 
// https://www.youtube.com/watch?v=szKVpQtBHh8&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=36
// https://www.geeksforgeeks.org/palindrome-partitioning-dp-17/

class Palindrome_Partitioning_Recur {
    static int count =0;
    static int min =Integer.MAX_VALUE ;

    static boolean isPalindrome(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=s.charAt((s.length())-1-i)) return false;
        }
        return true;
    }
    static int minPalPartition(String s,int i,int j){
        if(i >=j || isPalindrome(s.substring(i,j+1))) return 0;

        for(int k=i;k<j;k++){
            count = (1 + minPalPartition(s, i, k) + minPalPartition(s, k + 1, j)); 
        }
        return Math.min(min,count);
    }
    public static void main(String[] args) {
       String str = "geek";
       int n = str.length();

       System.out.println("Min cuts needed for Palindrome Partitioning is: "+minPalPartition(str,0,n-1));
    }
}
