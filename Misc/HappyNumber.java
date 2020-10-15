package com.howtodoinjava.java8.example;
 
import java.util.HashSet;
import java.util.Set;
 
public class Main 
{ 
    public static void main(String[] args) {
         
        System.out.println("32 is happy number " + isHappyNumber(32));
        System.out.println("7  is happy number " + isHappyNumber(7));
        System.out.println("28 is happy number " + isHappyNumber(28));
         
        System.out.println("30 is happy number " + isHappyNumber(30));
        System.out.println("9  is happy number " + isHappyNumber(9));
        System.out.println("24 is happy number " + isHappyNumber(24));
    }
     
    static boolean isHappyNumber(int numberToCheck) 
    { 
        Set<Integer> uniqueNumbersEncounterd = new HashSet<Integer>();
        
        //Just to avoid any infinite loop
        while (uniqueNumbersEncounterd.add(numberToCheck))
        {
            int value = 0;
            while (numberToCheck > 0)
            {
                value += Math.pow(numberToCheck % 10, 2);
                numberToCheck /= 10;
            }
            numberToCheck = value;
        }
 
        return numberToCheck == 1;
    } 
} 
