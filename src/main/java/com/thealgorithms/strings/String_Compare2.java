/** Author : Siddhant Swarup Mallick
 * Github : https://github.com/siddhant2002
 */

 /** Program description - Showing compareTo function in 2 different ways. In first method using compareTo function to generate the length difference and in the second method compareTo function is used to generate the difference between the ascii codes of the characters of the strings
  */

package com.thealgorithms.strings;
public class String_Compare2 {
    public static void main(String args[])
    {
        // 2 methods are called to execute the functions and return is in integer
        String_Compare2 nm=new String_Compare2();
        String s1="Alpha123";
        String s2="Alpha";
        System.out.println(nm.task(s1,s2));
        s1="Highway";
        s2="Harry";
        System.out.println(nm.task(s1,s2));

        /**
         * OUTPUT :
         * 1st input - 1st string="Alpha123" 2nd string="Alpha"
         * 1st Output - 3
         * 1st approach Time Complexity : O(1)
         * Auxiliary Space Complexity : O(1)
         * 2nd input - 1st string="Highway" 2nd string="Harry"
         * 2nd output - 8
         * 2nd approach Time Complexity : O(1)
         * Auxiliary Space Complexity : O(1)
         * */
    }
    int task(String s1, String s2)
    {
        return s1.compareTo(s2);
        // Here the output will be displayed as per the length between the two strings. So 3 will be displayed
    }
    int task1(String s1, String s2)
    {
        return s1.compareTo(s2);
        // Here the output will be displayed as per the ascii code the characters present in both the strings. So 8 will be the output
    }
}