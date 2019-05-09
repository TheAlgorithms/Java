package DataStructures.HashMap.Hashing;

class LinkedList {

	private Node Head;
	private int size;

	public LinkedList() {
		Head = null;
		size = 0;
	}

	public void insert(int data) {

		Node temp = Head;
		Node newnode = new Node(data);
	
		size++;

		if(Head == null) {
			Head = newnode;
		}
		else {
			newnode.next = Head;
			Head = newnode;
		}
	}

	public void delete(int data) {
		if(size == 0) {
			System.out.println("UnderFlow!");
			return;
		}

		else {
			Node curr = Head;
			if (curr.data == data) {
				Head = curr.next;
				size--;
				return;
			}
			else {
				
				while(curr.next.next != null) {
					if(curr.next.data == data){
							curr.next = curr.next.next;
							return;
						}
				}

				System.out.println("Key not Found");
			}
		}
	}

	public void display() {
		Node temp = Head;
		while(temp != null) {
			System.out.printf("%d ",temp.data);
			temp = temp.next;
		}
		System.out.println();
	}
}