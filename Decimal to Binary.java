#import java.util.*;
class Decimal_Binary
{
  public static void main(String args[])
  {
    Scanner sc=new Scanner(System.in);
    int n,k,s=0,c=0,d;
    n=sc.nextInt();
    k=n;
    while(k!=0)
    {
      d=k%2;
      s=s+d*(int)Math.pow(10,c++);
      k/=2;
    }//converting decimal to binary
    System.out.println("Decimal number:"+n);
    System.out.println("Binary equivalent:"+s);
  }
}
