import java.util.Scanner;

/**
 * This class converts a Binary number to a Decimal number
 * 
 * @author Unknown
 *
 */
class BinaryToDecimal
{
	
	/**
	 * Main Method
	 * 
	 * @param args Command line arguments
	 */
  public static void main(String args[])
  {
    Scanner sc=new Scanner(System.in);
    int binary,binary_copy,d,decimal=0,c=0;
    System.out.print("Binary number: ");
    binary=sc.nextInt();
    binary_copy=binary;
    while(binary_copy!=0)
    {
      d=binary_copy%10;
      decimal+=d*(int)Math.pow(2,c++);
      binary_copy/=10;
    }
    System.out.println("Decimal equivalent:"+decimal);
    sc.close();
  }
}
