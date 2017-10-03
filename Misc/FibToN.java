import java.util.Scanner;

public class FibToN {

	public static void main(String[] args) {
		//take input
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		// print fibonacci sequence less than N
		int first = 0, second = 1;
		//first fibo and second fibonacci are 0 and 1 respectively
		
		while(first <= N){
			//print first fibo 0 then add second fibo into it while updating second as well
			
			System.out.println(first);
			
			int next = first+ second;
			first = second;
			second = next;
		}
	}

}
