/** Implementation of double ended queue */
import java.util.Scanner;

public class Deque {
  private int maxSize;
  private int front;
  private int rear;
  private int[] deque;

  public Deque(int S) {
    maxSize = S;
    deque = new int[S];
    front = rear = -1;
  }

  int deleteRear() {
    if (front == -1) return -1;
    else if (front == rear) {
      int x = deque[rear];
      front = rear = -1;
      return x;
    } else {
      int x = deque[rear];
      rear--;
      return x;
    }
  }

  int insertFront(int x) {
    if (front == 0) return 0;
    else if (front == -1 || rear == -1) {
      front++;
      rear++;
      deque[front] = x;
      return x;
    } else {
      front--;
      deque[front] = x;
      return x;
    }
  }

  int insertRear(int x) {
    if (rear == maxSize - 1) return -1;
    else if (front == -1 || rear == -1) {
      front++;
      rear++;
      deque[rear] = x;
      return x;
    } else {
      rear++;
      deque[rear] = x;
      return x;
    }
  }

  int deleteFront() {
    if (front == -1) return -1;
    else if (front == rear) {
      int x = deque[front];
      front = rear = -1;
      return x;
    } else {
      int x = deque[front];
      front++;
      return x;
    }
  }

  int display() {
    if (front == -1 && rear == -1) return -1;
    else {
      for (int i = front; i <= rear; i++) System.out.println(deque[i]);
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
      System.out.println("Enter 5 to display all the elements present in the double ended queue");

      choice = sc.nextInt();
      switch (choice) {
        case 1:
          System.out.println("Enter the number you want to add to the rear");
          int x = sc.nextInt();
          int check = obj.insertRear(x);
          if (check == x) System.out.println(x + "Has been successfully added to the rear");
          else System.out.println("Overflow");

          break;
        case 2:
          int x = obj.deleteFront();
          if (x == -1) System.out.println("Underflow");
          else System.out.println(x + "Has been successfully removed from the front");

          break;
        case 3:
          int x = obj.deleteRear();
          if (x == -1) System.out.println("Underflow");
          else System.out.println(x + "Has been successfully removed from the rear");

          break;
        case 4:
          System.out.println("Enter the number you want to add to the Front");
          int x = sc.nextInt();
          int check = obj.insertFront(x);
          if (check == x) System.out.println(x + "Has been successfully added to the Front");
          else System.out.println("Overflow");

          break;
        case 5:
          int x = obj.display();
          if (x == -1) System.out.println("The queue is empty" + x);

          break;
      }
    } while (choice >= 1 && choice <= 5);
  }
}
