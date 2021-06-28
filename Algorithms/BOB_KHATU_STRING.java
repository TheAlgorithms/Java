/*Bob and Khatu both love the string. Bob has a string S and Khatu has a string T. They want to make both string S and T to anagrams of each other. Khatu can apply two operations to convert string T to anagram of string S which are given below:
1.) Delete one character from the string T.
2.) Add one character from the string S.

Khatu can apply above both operation as many times he want. Find the minimum number of operations required to convert string T so that both T and S will become anagram of each other.

Input:

First line of input contains number of test cases T. Each test case contains two lines. First line contains string S and second line contains string T.

Output:

For each test case print the minimum number of operations required to convert string T to anagram of string S.

Constraints:

1 ≤ T ≤ 10
1 ≤ |S|,|T| ≤ 105

SAMPLE INPUT:
4
abc
cba
abd
acb
talentpad
talepdapd
code
road

SAMPLE OUTPUT:
0
2
4
4
*/

/*
JAVA SOLUTION:
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
//import for Scanner and other utility classes
import java.util.*;
 
 
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
 
class TestClass {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int n=s.nextInt();
        while(n-->0)
        {
            
        String a=s.next();
        String b=s.next();
        int arr[]=new int[26];
        
        for(int i=0;i<a.length();i++)
        {
        arr[a.charAt(i)-97]++;
        }
         for(int i=0;i<b.length();i++)
        {
        arr[b.charAt(i)-97]--;
        }
        
        int count=0;
        for(int i:arr)
        {
        count+=Math.abs(i);
        }
        System.out.println(count);
        }
 
    }
}
*/

Solution:
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
class TestClass {
    public static void main(String args[] ) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());                
        for(int i =0; i< testCase; i++)
        {
            int output = 0;
            int [] hash_s = new int[26];
            //int [] hash_l = new int[26];
            String s = br.readLine();
            String l = br.readLine();
            for(char c: s.toCharArray())
            {
                hash_s[c - 'a']++;
            }
            for(char c: l.toCharArray())
            {
                hash_s[c - 'a']--;
            }
            for(int j =0; j<26;j++)
            {
                output = output + Math.abs(hash_s[j]);
            }
            System.out.println(output);
        }
 
        
 
    }
}