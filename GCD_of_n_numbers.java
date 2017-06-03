import java.util.*;
class GCD
{
  public static void main(String args[])throws Exception
  {
    try
    {
       Scanner sc=new Scanner(System.in);
       int n,i,j,gcd=1;
       System.out.println("Enter value of n:");
       n=sc.nextInt();
       int a[]=new int[n];
       System.out.println("Enter the "+ n +" numbers:");
       for(i=0;i<n;i++)
        a[i]=sc.nextInt();
       gcd=a[0];
       j=1;
       while(j<n)
       {
         if(a[j]%gcd==0)
          j++;
        else
         gcd=a[j]%gcd;
       }
       System.out.println("GCD of entered "+ n + " numbers=" + gcd);
    }
    catch(Exception e){
      return;
    }
  }
}
