package com.thealgorithms.strings;

import java.util.Scanner;
/**
 * Checks whether a word is Palindrome or not
 *
 * @author Pritam Dash
 */
class PalindromeWord
{
    /**
     * Main method
     *
     * @param args Command line arguments
     */
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a word: ");
        String s=sc.nextLine();
        int len=s.length();
        String s1="",s2="";
        for(int i=0, j=len-1; i<len/2; i++, j--)
        {
            s1+=s.charAt(i);
            s2+=s.charAt(j);
        }
        if(s1.equalsIgnoreCase(s2))
        System.out.println("Palindrome word");
        else
        System.out.println("Not Palindrome word");;
    }
}        
