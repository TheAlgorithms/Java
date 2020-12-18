package Programs;
import java.util.Scanner;
public class Pattern_3 {

	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);
		System.out.print("Enter value of n :");
		int n = obj.nextInt();
		for(int i = n ; i > 0 ;i--) {
			System.out.println();
			for(int j = 1 ; j <= i ;j++) {
				System.out.print("$");
			}
		}

	}

}
