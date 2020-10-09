package Programs;
import java.util.Scanner;
public class Pattern_2 {

	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);//Scanner
		System.out.print("Enter value of n :");
		int n = obj.nextInt();//Taking User Input.
		for(int i =1 ; i <=n ;i++) {
			//Use to jump over next line for next iteration.
			System.out.println();
			for(int j = 1; j <= i ;j++) {
				System.out.print("* ");
			}
		}

	}

}
