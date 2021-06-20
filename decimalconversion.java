import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
public class decimalconversion {

	public static void main(String[] args) {
		/*This code can perform binary conversion from normal decimal number to binary numbers like 0 and 1.
		 * This is a  simple coding  to find out bit allotment in system without using any specified class except Scanner class and ArrayList class
		 * which was needy for me.
		 * This contains three "for" loops :--
		 * 1st : to run our code multiple times .
		 * 2nd : to solve values.
		 * 3rd : to print values
		 * 
		 */
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Stack<Long>bn = new Stack();
		//Stack<Long>ln = new Stack();
	//	ArrayList<Long> mn = new ArrayList(); 
	//	mystackfromarraylist list = new mystackfromarraylist();
		

	

		
		
		System.out.println("type your decimal  values");
		long a = 0;
		try {
		long c = sc.nextLong();
		a = c;
		}catch(Exception e) {
			e.printStackTrace();
		}
		long b = 0;

		for(int i = 1;i>0;i++) {
			b = a%2;
			bn.push(b);
			a = a/2;
			if(a == 0 | a == 1) {
				bn.push(a);
				break;
			}
		}System.out.println("binary bits for your number is ");
		for(int i = bn.size();!bn.isEmpty();i--) {
			
		if(bn.size() == i) {
			
			continue;
			
		}else {
			

			System.out.print(bn.pop() + "");
			
		}
			
			
		}
		
			
		
			
		
}

	
}
		
		
		
	