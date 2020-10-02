package Scanner;

public class PositiveNumber {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Input number : ");
    int number = scanner.nextInt();
    positiveNumber(number);
  }

  private void positiveNumber(int number){
    int a , b , c , d;
    a = 0;
    b = 0;
    c = 0;
    d = 0;
    for(int a = 0 ; a < number ; a++){
      a *= a * a;
      for(int b = 0; b < number ; b++){
        b *= b * b;
        for(int c = 0; c < number ; c++){
          c *= c * c;
          for(int d = 0 ; d < number ; d++){
            d *= d * d;
            if(a + b == c + d){
              System.out.println("a : %d , b : %d , c : %d , d : %d", a , b , c , d);
            }
          }
        }
      }
    }
  }
}