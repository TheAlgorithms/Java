package challengesStacksAndQueues;

import java.util.Scanner;
import java.util.Stack;

public class QueueUsingTwoStacks {

	private Stack<Integer> a;
	private Stack<Integer> b;
	
	public QueueUsingTwoStacks() {
		a = new Stack<>();
		b = new Stack<>();
	}
	public int deque() {
		return a.pop();
	}
	public void enque(int x) {
		while(a.size()!=0) {
			b.push(a.pop());
		}
		a.push(x);
		while(b.size()!=0) {
			a.push(b.pop());
		}
	}
	
	public static void main(String[] args) {
		QueueUsingTwoStacks q = new QueueUsingTwoStacks();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=0;i<n;i++) {
			q.enque(i);
		}
		for(int i=0;i<n;i++) {
			System.out.print(q.deque()+" ");
		}
	}

}
