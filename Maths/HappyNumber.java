package Maths;

public class HappyNumber {

    // Check if a number is happy or not. Refer : https://mathworld.wolfram.com/HappyNumber.html
    public static boolean isHappy(int num)
    {
        int slow=num, fast=num;
        do
        {
            slow = digitSquareSum(slow); // Move one step
            fast = digitSquareSum(digitSquareSum(fast)); // Move 2 steps
        } while(slow != fast);
        return slow == 1; // See if the cycle is stuck on number 1
    }

    public static int digitSquareSum(int num)
    {
        int sum = 0, digit;
        while(num > 0)
        {
            digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args)
    {
        // Testcases
        System.out.println(isHappy(23));
        System.out.println(isHappy(11));
    }
}
