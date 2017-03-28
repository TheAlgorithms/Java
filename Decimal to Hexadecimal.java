//Decimal to hexadecimal
import java.util.*;
class Decimal_Hexadecimal
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int n,k,i,d;
        String s=" ";
        int a[]={'A','B','C','D','E','F'};
        n=sc.nextInt();
        k=n;
        while(k!=0)
        {
            d=k%16;
            if(d<10)
                s+=String.valueOf(d);
            else
                s+=a[d-10];
        }
        for(i=s.length()-1;i>=0;i--)
            System.out.print(s.charAt(i));
    }
}
