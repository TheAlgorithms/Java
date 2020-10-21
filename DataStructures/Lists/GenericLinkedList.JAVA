public class List <E> {
	
	Link head;
	Link tail;
	Link curr;
	int cnt;
	
	public List() {
		head = tail = curr = create_link(null);
		cnt = 0;
	}
	
	public void clear() {
		curr = tail = head = create_link(null);
		cnt = 0;
	}
	
	public void insert(E it) {
		curr.next = create_link(it, curr.next);
		if(tail == curr) tail = curr.next;
		cnt++;
	}
	
	public void append(E it) {
		tail.next = create_link(it, null);
		tail = tail.next;
		cnt++;
	}
	
	public E remove() {
		if(curr.next == null) return null;
		E it = curr.next.element;
		if(tail == curr.next) tail = curr;
		curr.next = curr.next.next;
		cnt--;
		return it;
	}
	
	public void moveToStart() {
		curr = head;
	}
	
	public void moveToEnd() {
		curr = tail;
	}
	
	public void prev() {
		if(curr == head) return;
		Link temp = head;
		while(temp.next != curr) {
			temp = temp.next;
		}
		curr = temp;
	}
	
	public void next() {
		if(curr != tail) curr = curr.next;
	}
	
	public int length() {
		return cnt;
	}
	
	public int currPos() {
		Link temp = head;
		int i = 0;
		while(curr != temp) {
			temp = temp.next;
			i++;
		}
		return i;
	}
	
	public boolean moveToPos(int pos) {
		if(pos<0 || pos >= cnt) return false;
		curr = head;
		int i = 0;
		while(i < pos) {
			curr = curr.next;
			i++;
		}
		return true;
	}
	
	public E getValue() {
		if(curr.next == null) return null;
		return curr.next.element;
	}
	public E at(int pos) {
		Link temp = curr;
		if(moveToPos(pos)) {
			E it = getValue();
			curr = temp;
			return it;
		}
		else {
			System.out.println("Posição inválida");
			return null;
		}		
	}
	
	public E delete(int pos) {
		Link temp = curr;
		if(moveToPos(pos)) {
			E it = remove();
			curr = temp;
			return it;
		}
		else {
			System.out.println("Posição inválida");
			return null;
		}		
	}
	
	public void printList() {
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
	
	public static void main(String[] args) {
		List<Integer> lista = new List<Integer>();
		lista.append(1);
		lista.append(2);
		lista.append(3);
		lista.append(4);
		System.out.println(lista.length());
		lista.printList();
		lista.delete(2);
		System.out.println(lista.length());
		lista.printList();
	}
	
}
