public class Main {
  public static void main(String[] args) {
    class Complex {
      private double re;
      private double im;

      public Complex(double re, double im) {
        this.re = re;
        this.im = im;
      }

      public Complex add(Complex other) {
        return new Complex(re + other.re, im + other.im);
      }

      public double getReal() {
        return re;
      }

      public double getImaginary() {
        return im;
      }

      @Override
      public String toString() {
        return re + " + " + im + "i";
      }
    }

    Complex z1 = new Complex(3, 4); // 3 + 4i
    Complex z2 = new Complex(1, 2); // 1 + 2i

    Complex z3 = z1.add(z2);
    System.out.println(z3); // prints "4.0 + 6.0i"
  }
}
