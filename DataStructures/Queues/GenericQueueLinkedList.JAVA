public class Queue<E> {

	public class Link {
		E element;
		Link next;
		public Link(Link nextVal) {
			next = nextVal;
		}
		public Link(E it, Link nextVal) {
			next = nextVal;
			element = it;
		}
	}
	
	public Link create_link (Link nextVal) {
		Link n = new Link(nextVal);
		return n;
	}
	public Link create_link (E it, Link nextVal) {
		Link n = new Link(it, nextVal);
		return n;
	}
	
	Link front;
	Link rear;
	int size;
	
	public Queue() {
		front = rear = create_link(null);
		size = 0;
	}
	public void clear() {
		front = rear = create_link(null);
		size = 0;
	}
	public void enqueue(E it) {
		rear.next = create_link(it, null);
		rear = rear.next;
		size++;
	}
	public E dequeue() {
		if(size == 0) return null;
		E it = front.next.element;
		front.next = front.next.next;
		if(front.next == null) rear = front;
		size--;
		return it;
	}
	public E frontValue() {
		if(front.next == null) return null;
		return front.next.element;
	}
	public int length() {
		return size;
	}
}
