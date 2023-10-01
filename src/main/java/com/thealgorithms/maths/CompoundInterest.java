package com.thealgorithms.maths;
 
import java.util.*;

class CompoundInterest {
   double monthlyInterestRate, cdvalue, deposit, apy;
   int maturityPeriod;

   void Compute() {
        System.out.print("Enter the Initial Deposit Amount: ");
        try (Scanner input = new Scanner(System.in)) {
           deposit = input.nextDouble();
           System.out.print("Enter the Annual Percentage Yield: ");

           apy = input.nextDouble();
           System.out.print("Enter the Maturity Period (No. of Months): ");

           maturityPeriod = input.nextInt();
        }
       monthlyInterestRate = deposit * (.0001 * apy);
       System.out.println("The Monthly Interest Rate is: " + monthlyInterestRate);
       double cdvalue;
       System.out.println("Month"
           + "\t"
           + "CD Value");
       for (int i = 1; i <= maturityPeriod; i++) {
           cdvalue = (deposit + deposit * apy / 1200);
           deposit = cdvalue;
           System.out.println(i + "\t" + cdvalue);
       }
    }
    public static void main(String[] args) {
       CompoundInterest c = new CompoundInterest();
       c.Compute();
       System.out.print("");
    }
}
