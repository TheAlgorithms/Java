
//A spy number is a number where the sum of its digits equals the product of its digits.
// For example, 1124 is a spy number, the sum of its digits is 1+1+2+4=8 and the product of its digits is 1*1*2*4=8.



import java.io.*;
import java.util.*;
public class SpyNumber {
       public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number");
        int n = sc.nextInt();

        int sum = 0;
        int product = 1;
        
        int temp = n;
        while (temp > 0) {
            int rem = temp % 10;
            sum = sum + rem;
            product = product * rem;
            temp = temp / 10;
        }
        if (sum == product) {
            System.out.println(n + " is a spy number");
        } else {
            System.out.println(n + " is not a spy number");
        }


       }}
