package happyNumbersProblem;

import java.util.*;
public class HappyNumSeqTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter number: ");
		int n = in.nextInt();
		while(n != 1 && !isSad(n)){
			System.out.print(n+" ");
			n = sumSquares(n);
		}
		if(n == 1) System.out.println("1 Happy number");
		else
			System.out.println("Sad number");
	}
	static int sumSquares(int n){
		int s = 0;
		while(n > 0){
			int r = n%10;
			s = s + r*r;
			n = n/10;
		}
		return s;
	}
	static boolean isSad(int n){
		int cycleNums[] = {4,16,20,37,58,145};
		boolean found = false;
		int j = 0;
		while(j<cycleNums.length && !found)
			if(cycleNums[j] == n)
				found = true;
			else
				j++;
		return found;
	}
}
