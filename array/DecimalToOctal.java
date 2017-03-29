package array;

import array.security.ProjectAlgorithmsScanner;

class DecimalToOctal
{
  public static void main(String args[])
  {
    int n,k,d,s=0,c=0;
    n = ProjectAlgorithmsScanner.getInteger();
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
