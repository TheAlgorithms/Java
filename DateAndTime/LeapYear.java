package DateAndTime;

import java.util.Scanner;

//An algorithm to determine whether a year is a leap year
public class LeapYear {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input a year: ");
        // read input from keyboard
        int year = sc.nextInt();

        // validate and determine a leap year
        if(isLeapYear(year)) { // true
            System.out.println("YES"); // is leap year
        } else {
            System.out.println("NO");
        }
    }

    /*
    A leap year is exactly divisible by 4 except for century years (years ending with 00).
    The century year is a leap year only if it is perfectly divisible by 400.
    * */
    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }
}
