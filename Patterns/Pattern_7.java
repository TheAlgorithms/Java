package Programs;
import java.util.Scanner;
public class Pattern_7 {
	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);
		System.out.print("Enter Number of rows :");
		int n = obj.nextInt();
		for(int i = n ; i>=1 ; i--) {
			for(int j = 1 ; j <= n-i;j++) {
				System.out.print("  ");
			}
			for(int j = 1 ; j <= i; j++) {
				if(j<10) {
					System.out.print("0"+j+"  ");
				}else {
					System.out.print(j+"  ");
				}
			}
			System.out.println();
		}

	}

}
