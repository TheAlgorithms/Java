package Programs;
import java.util.Scanner;
public class Pattern_8 {
	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);
		System.out.print("Enter Number of Rows :");
		int n = obj.nextInt();
		int number = 1;
		for(int i =1;i<=n;i++){
			for(int j =1;j<=n-i;j++) {
				System.out.print("  ");
			}
			for(int j =1;j<=i;j++) {
				System.out.print(number++ +"  ");
			}
			System.out.println();
		}

	}

}
