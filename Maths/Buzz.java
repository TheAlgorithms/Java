package Maths;

import java.util.Scanner;

public class Buzz {
    public static boolean checkBuzz(int n)
    {
        if (n%10==7 || n%7==0) //A Buzz number is a number that is divisible by 7 or ends with 7
        return true;
        else 
        return false;
    }
    public static void main(String[] args) {
        char choice = 'N';
        Scanner sc = new Scanner(System.in);
        int number;
        do {
            System.out.println("Enter a number");
            number = sc.nextInt();
            if (checkBuzz(number))
                System.out.println("The number " + number + " is a Buzz number.");
            else
                System.out.println("The number " + number + " is not a Buzz number.");
            System.out.println("Do you want to continue? Enter Y else enter any character: ");
            choice = sc.next().charAt(0);
        } while (choice == 'Y' || choice == 'y'); //A loop to take numbers and check if they are Buzz numbers
        sc.close();
    }
}
