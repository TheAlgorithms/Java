package main.java.com.thealgorithms.maths;

import java.util.Scanner;

public class Factors {

    public static void findFactor(int n) {
        for(int i=1; i <= n; i++) {
            if(n % i == 0)
                System.out.print(i+"\t");
        }
    }
    public static void main(String[] args) {

        // declare variable
        int number = 0;

        // create Scanner class object
        Scanner scan = new Scanner(System.in);

        // take input
        System.out.print("Enter a number: ");
        number = scan.nextInt();

        System.out.print("The  number chosen is " +number);
        System.out.print("The factors of the chosen number are:");
        findFactor(number);
    


    }

}


