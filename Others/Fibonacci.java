/**
 * Fibonacci series in Java
 * Program that prints the first N numbers of the Fibonacci series
 * The first number in the series is 1, the second number is 1, and each of the
 * following is the sum of the previous two
 * 
 * @author IryaDev
 */

import java.util.*;

public class Fibonacci{

    public static void main(String[] args){
 
        Scanner sc = new Scanner(System.in);
        int number,fib_1,fib_2,i;
  
        do{
            System.out.print("Enter a number greater than one: ");
            number = sc.nextInt();
        }while(number<=1);
  
        System.out.println("The first " + number + " terms of the Fibonacci series are:");                 

        fib_1=1;
        fib_2=1;

        System.out.print(fib_1 + " ");
        for(i=2;i<=number;i++){
            System.out.print(fib_2 + " ");
            fib_2 = fib_1 + fib_2;
            fib_1 = fib_2 - fib_1;
        }
        System.out.println();
    }
}