import java.util.Stack;

/**
 * This implements Queue using two Stacks.
 *
 * Big O Runtime:
 *      insert(): O(1)
 *      remove(): O(1) amortized
 *      isEmpty(): O(1)
 *
 * A queue data structure functions the same as a real world queue.
 * The elements that are added first are the first to be removed.
 * New elements are added to the back/rear of the queue.
 *
 * @author sahilb2
 *
 */
class QueueUsingTwoStacks {

    // Stack to keep track of elements inserted into the queue
    private Stack inStack;
    // Stack to keep track of elements to be removed next in queue
    private Stack outStack;

    /**
	 * Constructor
	 */
    public QueueUsingTwoStacks() {
        this.inStack = new Stack();
        this.outStack = new Stack();
    }

    /**
     * Inserts an element at the rear of the queue
     *
     * @param x element to be added
     */
    public void insert(Object x) {
        // Insert element into inStack
        this.inStack.push(x);
    }

    /**
     * Remove an element from the front of the queue
     *
     * @return the new front of the queue
     */
    public Object remove() {
        if(this.outStack.isEmpty()) {
            // Move all elements from inStack to outStack (preserving the order)
            while(!this.inStack.isEmpty()) {
                this.outStack.push( this.inStack.pop() );
            }
        }
        return this.outStack.pop();
    }

    /**
     * Returns true if the queue is empty
     *
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return (this.inStack.isEmpty() && this.outStack.isEmpty());
    }

}
