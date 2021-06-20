public class Stack <E> {
	
	Link head;
	int cnt;
	
	public Stack() {
		head = create_link(null);
		cnt = 0;
	}
	
	public void clear() {
		head = create_link(null);
		cnt = 0;
	}
	
	public void push(E it) {
		head.next = create_link(it, head.next);
		cnt++;
	}
	
	public E pop() {
		if(head.next == null) return null;
		E it = head.next.element;
		head.next = head.next.next;
		cnt--;
		return it;
	}
	
	public int length() {
		return cnt;
	}
	
	public boolean empty() {
		if(cnt == 0) return true;
		return false;
	}
	
	public E top() {
		if(head.next == null) return null;
		return head.next.element;
	}
	
	public void printStack() {
		Link temp = head;
		while(temp.next != null) {
			if(temp == head) System.out.print(temp.next.element);
			else System.out.print(" " + temp.next.element);
			temp = temp.next;
		}
		System.out.println();
	}
	
	public class Link {
		E element;
		Link next;
		public Link(E it, Link nextval) {
			element = it;
			next = nextval;
		}
		public Link(Link nextval) {
			next = nextval;
		}
	}
	
	public Link create_link(E it, Link nextval) {
		Link n = new Link(it, nextval);
		return n;
	}
	
	public Link create_link(Link nextval) {
		Link n = new Link(nextval);
		return n;
	}
	
}
