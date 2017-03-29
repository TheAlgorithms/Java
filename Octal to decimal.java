import java.util.*;
class Octal_Decimal
{
  public static void main()
  {
    Scanner sc=new Scanner(System.in);
    int n,k,d,s=0,c=0;
    n=sc.nextInt();
    k=n;
    while(k!=0)
    {
      d=k%10;
      s+=d*(int)Math.pow(8,c++);
      k/=10;
    }
    System.out.println("Octal number:"+n);
    System.out.println("Decimal equivalent:"+s);
  }
}
