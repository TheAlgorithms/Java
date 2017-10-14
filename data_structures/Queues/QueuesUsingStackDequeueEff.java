
public class QueuesUsingStackDequeueEff {
	DynamicStack sPrimary;
	DynamicStack sSecondary;

	public QueuesUsingStackDequeueEff() throws Exception {
		// TODO Auto-generated constructor stub
		sPrimary = new DynamicStack();
		sSecondary = new DynamicStack();
	}

	public int size() {
		return this.sPrimary.size();
	}

	public boolean isEmpty() {
		if (this.sPrimary.size() == 0)
			return true;
		else
			return false;

	}

	public void enqueue(int item) throws Exception {
		this.sPrimary.push(item);

	}

	public int dequeue() throws Exception {
		if (this.sPrimary.size() == 0)
			throw new Exception("Queue is empty");
		RevStack(sPrimary, new DynamicStack(), 0);
		int pop = sPrimary.pop();
		RevStack(sPrimary, new DynamicStack(), 0);
		return pop;

	}

	public int front() throws Exception {
		if (this.sPrimary.size() == 0)
			throw new Exception("Queue is empty");

		RevStack(sPrimary, new DynamicStack(), 0);
		int rv = sPrimary.pop();
		RevStack(sPrimary, new DynamicStack(), 0);
		return rv;

	}

	public void display() throws Exception {
		
		sPrimary.display();
	}

	public static void RevStack(DynamicStack stack, DynamicStack helper, int index) throws Exception {

		if (stack.isEmpty())
			return;
		int item = stack.pop();
		RevStack(stack, helper, index + 1);
		helper.push(item);
		if (index == 0) {
			while (!helper.isEmpty()) {
				stack.push(helper.pop());
			}
		}
	}
}
