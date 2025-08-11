import java.util.Scanner;

class public SimpleCalculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("""
===== Simple Calculator =====
1. Addition
2. Subtraction
3. Multiplication
4. Division
5. Exit
Enter your choice: """);

        int choice = in.nextInt();

        switch (choice) {
            case 1 : performOperation(in, '+');
            case 2 : performOperation(in, '-');
            case 3 : performOperation(in, '*');
            case 4 : performOperation(in, '/');
            case 5 : System.out.println("Exiting...");
            default : System.out.println("Invalid choice! Please select between 1-5.");
        }

        in.close();
    }

    private static void performOperation(Scanner in, char operator) {
        System.out.print("Enter 2 numbers: ");
        int a = in.nextInt();
        int b = in.nextInt();
        int result = 0;

        switch (operator) {
            case '+' : result = a + b;
            case '-' : result = a - b;
            case '*' : result = a * b;
            case '/' : {
                if (b != 0) {
                    result = a / b;
                } else {
                    System.out.println("Error: Division by zero!");
                    return;
                }
            }
        }
        System.out.println("Result: " + result);
    }
}
