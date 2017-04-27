import java.util.Scanner;

/**
 * This class converts a Decimal number to a Binary number
 * 
 * @author Unknown
 *
 */
class DecimalToBinary
{
	/**
	 * Main Method
	 * 
	 * @param args Command Line Arguments
	 */
  public static void main(String args[])
  {
    Scanner sc=new Scanner(System.in);
    int n,k,s=0,c=0,d;
    System.out.print("Decimal number: ");
    n=sc.nextInt();
    k=n;
    while(k!=0)
    {
      d=k%2;
      s=s+d*(int)Math.pow(10,c++);
      k/=2;
    }//converting decimal to binary
    System.out.println("Binary equivalent:"+s);
    sc.close();
  }
}
