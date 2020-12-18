package Programs;

import java.util.Scanner;

public class Pattern_11 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter value of n :");
		int n = sc.nextInt();
		
		for(int i = n;i>=0;i--) {
			System.out.println();
			for(int s =1;s<=n-i;s++) {
				System.out.print("  ");
			}
			for(int j = 1 ;j<=i;j++) {
				System.out.print("* ");
			}
		}
		System.out.println();

	}

}
