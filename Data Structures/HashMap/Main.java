import java.util.Scanner;

class Node {
	int data;
	Node next;

	public Node(int data) {
		this.data = data;
		this.next = null;
	}
}

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


class HashMap {
	private int hsize;
	private LinkedList[] buckets;

	public HashMap(int hsize) {
		buckets = new LinkedList[hsize];
		for (int i = 0; i < hsize ; i++ ) {
			buckets[i] = new LinkedList();
			// Java requires explicit initialisaton of each object 
		}
		this.hsize = hsize;
	}

	public int hashing(int key) {
		int hash = key % hsize;
		if(hash < 0)
			hash += hsize;
		return hash;
	}
	
	public void insertHash(int key) {
		int hash = hashing(key);
		//System.out.println(hash);
		buckets[hash].insert(key);
	}


	public void deleteHash(int key) {
		int hash = hashing(key);

		buckets[hash].delete(key);
	}
	public void displayHashtable() {
		for (int i = 0;i < hsize ; i++) {
			System.out.printf("Bucket %d :",i);
			buckets[i].display();
		}
	}

}

public class Main {
	public static void main(String[] args) {

		int choice, key;

		HashMap h = new HashMap(7);


		while (true) {
			System.out.println("Enter your Choice :");
			System.out.println("1. Add Key");
			System.out.println("2. Delete Key :");
			System.out.println("3. Print Table");
			System.out.println("4. Exit");

			Scanner In = new Scanner(System.in);

			choice = In.nextInt();

			switch (choice) {
				case 1:{
					System.out.println("Enter the Key: ");
					key = In.nextInt();
					h.insertHash(key);
					break;
				}
				case 2: {
					System.out.println("Enter the Key delete:  ");
					key = In.nextInt();
					h.deleteHash(key);
					break;
				}
				case 3: {
					System.out.println("Print table");
					h.displayHashtable();
					break;
				}
				case 4: {
					return;
				}	
			}
		}
	}
}