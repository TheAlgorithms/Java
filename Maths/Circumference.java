package Maths;

public class Circumference {
  public static void main(String[] args) {

    /* test sphere */
    assert Double.compare(CircumferenceSphere(5), 31.415926535897932) == 0;

  }
    
    private static double CircumferenceSphere(double radius) {
        return 2 * Math.PI * radius;
      }
  
}

