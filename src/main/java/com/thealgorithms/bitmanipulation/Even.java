import java.util.Scanner;

public class Even {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int number = scanner.nextInt();

        int evenMask = 1 << 0; // 0000 0001

        // Check if the least significant bit of the number is set
        if ((number & evenMask) != 0) {
            System.out.println("The number is even.");
        } else {
            System.out.println("The number is odd.");
        }
    }
}
