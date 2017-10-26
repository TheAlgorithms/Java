import java.util.Scanner;

/**
 * You can check any number to see if
 * its prime or composite number
 * this code returns 1 also as a Prime number
 * @author Shishir
 *
 */
class PrimeNumber {

  public static void main(String[] args){
    Scanner input  = new Scanner(System.in);
    int flag=0;
    System.out.println("Enter the number:");
    int num = input.nextInt();

    for(int i=1; i <= num; i++) {
      if(num % i == 0) {
        flag++;
      }
    }

    if(flag <= 2) {
      System.out.println(num+" is a Prime Number");
    }
    else {
      System.out.println(num+" is a Composite Number");
    }
  }

}
