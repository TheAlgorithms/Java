package Programs;
import java.util.Scanner;
public class Pattern_6 {

	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);
		System.out.print("Enter Number of Rows :");
		int n = obj.nextInt();
		for(int i =1;i<=n;i++){
			for(int j =1;j<=n-i;j++) {
				System.out.print("  ");
			}
			for(int j =1;j<=i;j++) {
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
