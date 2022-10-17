package com.thealgorithms.conversions;

import java.util.Scanner;
/**
 * Converts any Octal Number to a Decimal Number
 *
 * @author Pritam Dash
 */
class OctalToDecimal
{
    /**
     * This method converts an octal number to a decimal number.
     *
     * @param st The octal number
     * @param l The length of the whole-number part of the Double type octal number
     * @return The decimal number
     */

    static String convert(String st, int l)
    {
        long x=0;
        double y=0.0;
        String st1;
        for(int i=0; i<st.length(); i++)
        {
            String st2 =Character.toString(st.charAt(i));
            int a=l-i-1;
            if(a>=0)
                x+=Integer.parseInt(st2)*(int)Math.pow(8,a);
            else
                y+=Integer.parseInt(st2)*Math.pow(8,a);
        }
        if(st.length()==l)
            st1=Long.toString(x);
        else
        {
            double z=x+y;
            st1=Double.toString(z);
        }
        return st1;
    }

    /**
     * Main method
     *
     * @param args Command line arguments
     */

    public static void main(String args[])
    {
        String s="",s1="",s2="";
        Scanner sc = new Scanner(System.in);
        System.out.print("Octal number: ");
        String oct = sc.nextLine();
        int f=0;
        if(oct.charAt(0) == '-')
            f=1;
        if(oct.contains(".")==true)
        {
            s=oct.substring(f,oct.indexOf('.'));
            s1=s+oct.substring(oct.indexOf('.')+1);
            s2=convert(s1,s.length());
        }
        else
            s2=convert(oct.substring(f),oct.length());
        if(f==1)
            System.out.println("Decimal equivalent: -" +s2);
        else
            System.out.println("Decimal equivalent: " +s2);
        sc.close();
    }
}
