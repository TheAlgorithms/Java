/**Implementation of double ended queue*/

import java.util.Scanner;

public class Deque {
	private int maxSize;
	private int f, r, a[];

	public Deque(int S) {
		maxSize = S;
		a = new int[S];
		f = r = -1;
	}

	int deleteRear() {
		if (f == -1)
			return -1;
		else if (f == r) {
			int x = a[r];
			f = r = -1;
			return x;
		} else {
			int x = a[r];
			r--;
			return x;
		}
	}

	int insertFront(int x) {
		if (f == 0)
			return 0;
		else if (f == -1 || r == -1) {
			f++;
			r++;
			a[f] = x;
			return x;
		} else {
			f--;
			a[f] = x;
			return x;
		}
	}

	int insertRear(int x) {
		if (r == maxSize - 1)
			return -1;
		else if (f == -1 || r == -1) {
			f++;
			r++;
			a[r] = x;
			return x;
		} else {
			r++;
			a[r] = x;
			return x;

		}
	}

	int deleteFront() {
		if (f == -1)
			return -1;
		else if (f == r) {
			int x = a[f];
			f = r = -1;
			return x;
		} else {
			int x = a[f];
			f++;
			return x;
		}
	}

	int display() {
		if (f == -1 && r == -1)
			return -1;
		else {
			for (int i = f; i <= r; i++)
				System.out.println(a[i]);
			return 0;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice;
		System.out.println("Enter the size of the Dequeue");
		int S = sc.nextInt();
		Deque obj = new Deque(S);

		do {
			System.out.println("Enter an operation of your choice");

			System.out.println("Enter 1 to perform insertRear");
			System.out.println("Enter 2 to perform deleteFront");
			System.out.println("Enter 3 to perform deleteRear");
			System.out.println("Enter 4 to perform insertFront");
			System.out
					.println("Enter 5 to display all the elements present in the double ended queue");

			choice = sc.nextInt();
			switch (choice) {
			case 1: {
				System.out
						.println("Enter the number you want to add to the rear");
				int x = sc.nextInt();
				int check = obj.insertRear(x);
				if (check == x)
					System.out.println(x
							+ "Has been successfully added to the rear");
				else
					System.out.println("Overflow");
			}
				break;
			case 2: {

				int x = obj.deleteFront();
				if (x == -1)
					System.out.println("Underflow");
				else
					System.out.println(x
							+ "Has been successfully removed from the front");
			}
				break;
			case 3: {

				int x = obj.deleteRear();
				if (x == -1)
					System.out.println("Underflow");
				else
					System.out.println(x
							+ "Has been successfully removed from the rear");

			}
				break;
			case 4: {
				System.out
						.println("Enter the number you want to add to the Front");
				int x = sc.nextInt();
				int check = obj.insertFront(x);
				if (check == x)
					System.out.println(x
							+ "Has been successfully added to the Front");
				else
					System.out.println("Overflow");

			}
				break;
			case 5: {

				int x = obj.display();
				if (x == -1)
					System.out.println("The queue is empty" + x);

			}
				break;
			}
		} while (choice >= 1 && choice <= 5);

	}

}