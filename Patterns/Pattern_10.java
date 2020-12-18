package Programs;
import java.util.Scanner;
public class Pattern_10 {

	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);
		System.out.print("Enter the value of n :");
		int n = obj.nextInt();
		System.out.println("# ");
		for(int i = 0 ; i<=n-2 ;i++) {
			System.out.print("# ");
			for(int j = 1 ; j<=i ;j++) {
				System.out.print("  ");
			}
			System.out.print("# ");
			System.out.println();
		}
		for(int g = 1; g<=n+1 ;g++) {
			System.out.print("# ");
		}

	} 

}
