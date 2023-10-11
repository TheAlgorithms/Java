
import java.util.Scanner;

public class DuplicateStack {
    static int[] St;
    static int[] dupSt2;
    static int max;
    static int top = -1;
    static int max_a = 50;
    static int top2 = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the maximum in the stack");
        max = scanner.nextInt();

        St = new int[max];
        dupSt2 = new int[max_a];

        System.out.println("Enter the values of elements");
        for (int i = 0; i < max; i++) {
            push();
        }

        System.out.println("Stack is:");
        display();

        duplicate(St, dupSt2, top2);
    }

    public static void push() {
        if (top == max_a - 1) {
            System.out.println("Stack Overflow");
        } else {
            top++;
            Scanner scanner = new Scanner(System.in);
            int val = scanner.nextInt();
            St[top] = val;
        }
    }

    public static void display() {
        System.out.println("The elements in the stack are:");
        for (int j = top; j >= 0; j--) {
            System.out.print(St[j] + " ");
        }
        System.out.println();
    }

    public static void duplicate(int[] St, int[] dupSt2, int top2) {
        int[] dupSt = new int[max_a];
        int topx = -1;

        for (int j = top; j >= 0; j--) {
            topx++;
            dupSt[topx] = St[j];
        }

        for (int j = topx; j >= 0; j--) {
            top2++;
            dupSt2[top2] = dupSt[j];
        }

        System.out.println("\nDuplicate Stack is:");
        for (int i = top2; i >= 0; i--) {
            System.out.print(dupSt2[i] + " ");
        }
    }
}
