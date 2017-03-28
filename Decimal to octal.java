#import java.util.*;
class Decimal_Octal
{
  public static void main()
  {
    Scanner sc=new Scanner(System.in);
    int n,k,d,s=0,c=0;
    n=sc.nextInt();
    k=n;
    while(k!=0)
    {
      d=k%8;
      s+=d*(int)Math.pow(10,c++);
      k/=8;
    }
    System.out.println("Decimal number:"+n);
    System.out.println("Octal equivalent:"+s);
  }
}
