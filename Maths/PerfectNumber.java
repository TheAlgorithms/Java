/*A number is a perfect number if is equal to sum of its proper divisors, that is, sum of its positive divisors excluding the number itself.
  example- 6 is a perfect number as sum of its factors i.e 1+2+3=6 i.e the number itself */

import java.util.Scanner;

public class PerfectNumber
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int Number,Sum=0,i;
        System.out.println("Enter a number to check if it is perfect number or not : ");
        Number=sc.nextInt();
        for(i = 1 ; i < Number ; i++)            //used to sum the factors excluding itself.
        {
            if(Number % i == 0)
                Sum = Sum + i ;
        }
        if(Sum == Number)
            System.out.println(Number+" is a Perfect Number.") ;
        else
            System.out.println(Number+" is not the Perfect Number.") ;
    }
}
