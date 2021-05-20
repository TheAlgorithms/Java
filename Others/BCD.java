import java.util.*;

public class BCD{

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
		//defining Stack object for operation
		Stack<Long>MyStack = new Stack();
	

//Giving decimal value to system for calulation of binary values
	

		
		System.out.println();
		System.out.println("type your decimal  values");
		long temp = 0;
		try {
		long ip = sc.nextLong();
		temp = ip;
		}catch(Exception e) {
			e.printStackTrace();
		}\\
		\\Value of ip will be given to temp variable to perform operations
			\\Dummy will get binary values for decimal values given by user and further transformed to stack
		long dummy = 0;
\\This loop will calculate all the binary values for decimal value given by user using Scanner
		for(int i = 1;i>0;i++) {
			dummy = temp%2;
			MyStack.push(dummy);
			temp = temp/2;
			if(temp == 0 | temp == 1) {
				MyStack.push(a);
				break;
			}
		}System.out.println("binary bits for your number is ");
		\\This loop will print all values present in the stack by first come last serve mannner
		for(int i = MyStack.size();!MyStack.isEmpty();i--) {
			
		if(MyStack.size() == i) {
			
			continue;
			
		}else {
			

			System.out.print(MyStack.pop() + "");
			
		}
			
			
		}
		
