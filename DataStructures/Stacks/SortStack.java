package stack;

import java.util.*;

public class SortStack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();	//number of test case
		while (t-- > 0) {
			Stack<Integer> s = new Stack<>();
			int n = sc.nextInt();	//size of array
			while (n-- > 0)
				s.push(sc.nextInt());	//elements in array
			Sort g = new Sort();
			Stack<Integer> a = g.sort(s);
			
			//printing sorted array
			while (!a.empty()) {
				System.out.print(a.peek() + " ");
				a.pop();
			}
			System.out.println();
		}
	}
}
// Sort class to sort a array using stack 
class Sort {
	public Stack<Integer> sort(Stack<Integer> s) {
		
		if (!s.isEmpty()) {
			int temp = s.pop();
			sort(s);
			arrangeStack(s, temp);
		}
		return s;
	}

	private void arrangeStack(Stack<Integer> s, int temp) {
		
		if (s.isEmpty() || temp > s.peek()) {
			s.push(temp);
		} else {
			int element = s.pop();
			arrangeStack(s, temp);
			s.push(element);
		}
	}
}