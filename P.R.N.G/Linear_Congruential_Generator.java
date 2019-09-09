import java.util.Scanner;
/**
 * LCG is an Pseudo Random Number Genarating algorithm
 * This algorithm is used by languages like Java.
 * The random numbers are calculated with a discontinuous piecewise linear equation.
 * 
 *
 * @author Abhinandan Pal (https://github.com/Abhinandan-Pal)
 *
 */
public class Linear_Congruential_Generator
{
    static double x;// x stores the current random number used to generate the next.
    int m = (int) Math.pow(2,32);// modulus
    int a = 1664525;// multiplier
    int c = 1013904223;// increment
    double nextRand()
    {
        x = (a*x + c)%m;
        return x/2147483647;
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a Seed: ");
        // seed refers to the first number based on which the random numbers are to beformed.
        x = sc.nextInt();
        Linear_Congruential_Generator object1 = new Linear_Congruential_Generator();
        System.out.print("Number of Random number to Print: ");
        int n = sc.nextInt();
        for(int i =0;i<n;i++)
        {
            System.out.println(object1.nextRand());
        }
    }
}
