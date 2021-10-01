
/*
In this code you need to find the number of unique characters
Example : String = "aabbacde"
Output: 5 because only a,b,c,d,e are the charaters present in this String.
Constraints: String contains only small case character of English.
*/

import java.util.Scanner;
public class Solution {

    static int numberOfChar(String s) {
        int ar[] = new int[26];
        int ans = 0;
        for(int i=0;i<s.length();i++){
            int ind = (int)s.charAt(i)-97;
            ar[ind]++;
            if(ar[ind]==1)
                ans++;
        }
        return ans;
        }

    public static void main(String[] args) {
    
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        scan.close();
        int ret = numberOfChar(s);
        System.out.println(ret);
    }
}
