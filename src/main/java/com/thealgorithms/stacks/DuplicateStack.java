import java.util.Scanner;

public class DuplicateStack {
    static int[] stack;
    static int max;
    static int top = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the maximum in the stack");
        max = scanner.nextInt();

        stack = new int[max];

        System.out.println("Enter the values of elements");
        for (int i = 0; i < max; i++) {
            push(scanner.nextInt());
        }

        System.out.println("Stack is:");
        display();

        int[] duplicateStack = duplicate(stack);

        System.out.println("\nDuplicate Stack is:");
        displayArray(duplicateStack);
    }

    public static void push(int val) {
        if (top == max - 1) {
            System.out.println("Stack Overflow");
        } else {
            top++;
            stack[top] = val;
        }
    }

    public static void display() {
        System.out.println("The elements in the stack are:");
        for (int j = top; j >= 0; j--) {
            System.out.print(stack[j] + " ");
        }
        System.out.println();
    }

    public static int[] duplicate(int[] stack) {
        int[] duplicateStack = new int[top + 1];
        for (int i = top; i >= 0; i--) {
            duplicateStack[top - i] = stack[i];
        }
        return duplicateStack;
    }

    public static void displayArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
