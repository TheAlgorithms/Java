package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class JosephusProblem {

	static int Josephus(int n,int k) {
		Queue<Integer> queue=new LinkedList<>();
		for(int i=1;i<=n;i++) {
			queue.offer(i);
		}
		while(queue.size()>1) {
			for(int i=0;i<k-1;i++) {
				queue.offer(queue.poll());//removing the top and enqueuing it in queue
			}
			queue.poll();
		}
		return queue.peek();	
	}

}
