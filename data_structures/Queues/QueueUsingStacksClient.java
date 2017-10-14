import java.util.Scanner;

public class QueueUsingStacksClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		QueuesUsingStackEnqueueEff obj = new QueuesUsingStackEnqueueEff();
		obj.enqueue(10);
		obj.enqueue(20);
		obj.enqueue(30);
		obj.enqueue(40);
		obj.enqueue(50);
		obj.display();
		obj.dequeue();
		obj.dequeue();
		obj.enqueue(60);
		obj.display();

	}

}