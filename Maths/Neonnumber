import java.util.*;
public class NeonNumber
{
    public static void main(String args[])
    {
        Scanner ob=new Scanner(System.in);
        System.out.println("Enter the number to check neon number or not");
        int num=ob.nextInt();
        int square=num*num;
        int sum=0;
        while(square!=0)//Loop to find the sum of digits.
        {
            int a=square%10;
            sum=sum+a;
            square=square/10;
        }
        if(sum==num)
        {
            System.out.println(num+" is a Neon Number.");
        }
        else
        {
            System.out.println(num+" is not a Neon Number.");
        }
    }

}
