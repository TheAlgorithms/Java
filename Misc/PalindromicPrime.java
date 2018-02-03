import java.util.Scanner;
public class PalindromePrime {

    public static void main(String[] args) { // Main funtion
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the quantity of First Palindromic Primes you want");
        int n = in.nextInt(); // Input of how mant first pallindromic prime we want
        funtioning(n); // calling funtion - functioning  
    }

    public static boolean prime(int num) { // checking if number is prime or not
        for (int divisor = 2; divisor <= num / 2; divisor++) {
            if (num % divisor == 0) {
                return false; //  false if not prime
            }
        }
        return true; // True if prime
    }

    public static int reverse(int n){ //  Returns  the reverse of the number
        int reverse = 0;
        while(n!=0){
            reverse = reverse * 10;
            reverse = reverse + n%10;
            n = n/10;
        }
        return reverse;
    }

    public static void funtioning(int y){
            int count =0;
            int num = 2;
            while(count < y){
                if(prime(num) && num == reverse(num)){ // number is prime and it's reverse is same
                    count++; // counts check when to terminate while loop
                    System.out.print(num + "\n"); // Print the Palindromic Prime
                }
                num++; // inrease iterator value by one
            }
    }
};
