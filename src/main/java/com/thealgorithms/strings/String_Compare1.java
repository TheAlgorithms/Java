/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

 /** Program description - To check strings as per object values and string content
  */

  
package com.thealgorithms.strings;
public class String_Compare1 {
    public static void main(String args[])
    {
        // 4 methods are called which are assigned to different sort of task and the return type is in boolean
        String_Compare1 nm=new String_Compare1();
        String s1="Alpha";
        String s2="Alpha";
        String s3=new String(s2);
        System.out.println(nm.task(s1,s2));
        System.out.println(nm.task1(s1,s3));
        System.out.println(nm.task2(s1,s2));
        System.out.println(nm.task3(s1,s3));

        /**
         * OUTPUT :
         * The input is common for all the methods
         * first string ="Alpha" second string ="Alpha" third string ="Alpha"
         * Output: true/false
         * 1st Output - true
         * 1st approach Time Complexity : O(1)
         * Auxiliary Space Complexity : O(1)
         * 2nd output - false
         * 2nd approach Time Complexity : O(1)
         * Auxiliary Space Complexity : O(1)
         * 3rd output - true
         * 3rd approach Time Complexity : O(1)
         * Auxiliary Space Complexity : O(1)
         * 4th output - true
         * 4th approach Time Complexity : O(1)
         * Auxiliary Space Complexity : O(1)
         */
    }
    boolean task(String s1, String s2)
    {
        return s1==s2;
        // Here the answer is true because the object of strings s1 and s2 are same so the object value is checked instead of their content
    }
    boolean task1(String s1, String s2)
    {
        return s1==s2;
        // Here the answer is false because the object of strings s1 and s2 are different so the object value is checked instead of their content
    }
    boolean task2(String s1, String s2)
    {
        return s1.equals(s2);
        // Here the answer is true because the content of the strings are checked irrespective of their objects location
    }
    boolean task3(String s1, String s2)
    {
        return s1.equals(s2);
        // Here the answer is true because the content of the strings are checked irrespective of their objects location
    }
}
