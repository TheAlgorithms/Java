
package gcd;

public class Gcd 
{
    public static int c;
    public int num1,num2;
    public static int gcd(int a,int b)
    {
        if(b>a)
        {
            c=b;
            b=a;
            a=c;
        }
        while (b!=0)
        {
            int temp=a%b;
            a=b;
            b=temp;
        }
        return a;
    }

    
    public static void main(String[] args) 
    {
        int num1=4;
        int num2=50;
      System.out.println( gcd(num1,num2));
    }
    
}
