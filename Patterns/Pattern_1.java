package Programs;
import java.util.Scanner;
public class Pattern_1 {

	public static void main(String[] args) {
		System.out.print("Enter value of n :");
		Scanner obj = new Scanner(System.in);
		int n = obj.nextInt();
		for(int i = 1;i <= n;i++) {
			System.out.println();
			for(int j = 1; j<= n;j++) {
				System.out.print("* ");
			}
		}

	}

}
