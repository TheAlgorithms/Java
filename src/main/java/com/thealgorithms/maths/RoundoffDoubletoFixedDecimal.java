package com.thealgorithms.maths;

import java.math.BigDecimal;
import java.util.Scanner;

public class RoundoffDoubletoFixedDecimal {

    public static void main(String[] args) {

        
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter a decimal Value");
    double x = scanner.nextDouble(); 

    //First we take input from the user
    BigDecimal result;
    System.out.println("Please enter number of places you want to round off the decimal to");


    //Here we ask for number of places the decimal needs to be rounded off to 
    int decimal=  scanner.nextInt() ;//4;
    //The decimal is converted to a string and then finally to a BigDecimal Type and then rounded off
    BigDecimal bd_num = new BigDecimal(Double.toString(x));
    bd_num = bd_num.setScale(decimal, BigDecimal.ROUND_HALF_UP); 
    System.out.printf("Original number: %.7f\n",x);
    System.out.println("Rounded upto 4 decimal: "+bd_num);
}
}