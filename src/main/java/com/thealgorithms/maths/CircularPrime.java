import java.util.Scanner;
class CircularPrime
{
    public static boolean isPrime(int num) 
    {
        int c = 0;
        for (int i = 1; i <= num; i++) 
        {
            if (num % i == 0) 
            {
                c++;
            }
        }
        if(c==2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static int gdc(int num) 
    {
        int c = 0;
        while (num != 0) 
        {
            c++;
            num /= 10;
        }
        return c;
    }
    public static void main() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("ENTER THE NUMBER : ");
        int n = sc.nextInt();
        if (n <= 0) 
        {
            System.out.println("INVALID INPUT");
            return;
        }
        boolean isCircularPrime = true;
        if (isPrime(n)==true) 
        {
            System.out.println(n);
            int dc = gdc(n);
            int divisor = (int)(Math.pow(10,dc - 1));
            int n2 = n;
            for (int i = 1; i < dc; i++) 
            {
                int t1 = n2 / divisor;
                int t2 = n2 % divisor;
                n2 = t2 * 10 + t1;
                System.out.println(n2);
                if (isPrime(n2)==false) 
                {
                    isCircularPrime = false;
                    break;
                }
            }
        }
        else 
        {
            isCircularPrime = false;
        }
        if (isCircularPrime==true) 
        {
            System.out.println(n + " IS A CIRCULAR PRIME.");
        }
        else 
        {
            System.out.println(n + " IS NOT A CIRCULAR PRIME.");
        }
    }
}