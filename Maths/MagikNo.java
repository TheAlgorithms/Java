/*
 * We take an integer X
 * The sum of the digits of X is found recursively till a single digit is obtained.
 * If the single digit integer obtained is equal to 1, then the number X is a Magik number.
 */
 
import java.util.*;

public class MagikNo {
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number to be checked : ");
        int num = sc.nextInt();
        int numBackup = num;
        while(numBackup>9){
            numBackup=sumOf(numBackup);
        }
        if(numBackup==1){
            System.out.println(num+" is a Magik number");
        }else{
            System.out.println(num+" is not a Magik number");
        }
	}
    
    public static int sumOf(int x){
        int sum = 0;
        while(x>0){
            sum+=x%10;
            x/=10;
        }
        return sum;
    }
}

