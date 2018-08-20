import java.util.Scanner;

/**
 * Converts any Binary number to an Octal Number
 * 
 * @author Zachary Jones
 *
 */
public class BinaryToOctal {
	
	/**
	 * Main method
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
        int n,k,d,s=0,c=0;
        System.out.print("Binary number: ");
        n=sc.nextInt();
        k=n;

        while(k!=0) {
            d=k%10;
            s+=d*(int)Math.pow(2,c++);
            k/=10;
        }
        
        k = s;
        s = 0;
        c = 0;
        n = k;
        
        while(k != 0) {
            d = k%8;
            s += d*(int)Math.pow(10, c++);
            k /= 8;
        }
                
        System.out.println("Octal equivalent:"+s);
        sc.close();

}
