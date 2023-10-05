import java.util.Scanner;

public class DivisibilityChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        
        System.out.println("Choose divisibility checks:");
        System.out.println("1. Check divisibility by 2");
        System.out.println("2. Check divisibility by 3");
        System.out.println("3. Check divisibility by 5");
        System.out.println("4. Check divisibility by 7");
        System.out.println("5. Check divisibility by 11");
        System.out.println("Enter option (1-5): ");

        int option = scanner.nextInt();

        
        switch (option) {
            case 1:
                if (number % 2 == 0) {
                    System.out.println(number + " is divisible by 2.");
                } else {
                    System.out.println(number + " is not divisible by 2.");
                }
                break;
            case 2:
                if (number % 3 == 0) {
                    System.out.println(number + " is divisible by 3.");
                } else {
                    System.out.println(number + " is not divisible by 3.");
                }
                break;
            case 3:
                if (number % 5 == 0) {
                    System.out.println(number + " is divisible by 5.");
                } else {
                    System.out.println(number + " is not divisible by 5.");
                }
                break;
            case 4:
                if (number % 7 == 0) {
                    System.out.println(number + " is divisible by 7.");
                } else {
                    System.out.println(number + " is not divisible by 7.");
                }
                break;
            case 5:
                if (number % 11 == 0) {
                    System.out.println(number + " is divisible by 11.");
                } else {
                    System.out.println(number + " is not divisible by 11.");
                }
                break;
            default:
                System.out.println("Invalid option. Please choose a number between 1 and 5.");
        }

        scanner.close();
    }
}
