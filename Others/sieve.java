import java.util.*;
import java.lang.*;

class sieve
{
public static void main(String args[])throws java.lang.Exception
    {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        
        
        boolean srr[]=new boolean[t+1];
        
        for(int i=2;i<=(int)Math.sqrt(t);i++)
        {
            if(!srr[i])
            {
                for(int k=i*i;k<=t;k+=i)
                    srr[k]=true;
            }
        }
        for(int i=2;i<t+1;i++)
        {if(!srr[i])
            System.out.println(i);
        }
        
    }
}



