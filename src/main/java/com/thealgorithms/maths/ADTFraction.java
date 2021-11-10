package com.thealgorithms.maths;

public class ADTFraction {

    public static void main(String[] args) {
        // TODO code application logic here

        ADTFraction f1 = new ADTFraction(3, 5);
        f1.display();
        ADTFraction f2 = new ADTFraction(7, 8);
        f2.display();
        ADTFraction f3 = f1.plus(f2);
        f3.display();
        ADTFraction f4 = f1.times(f2);
        f4.display();
        ADTFraction f5 = f1.times(4);
        f5.display();

    }

    private int n; //numerator
    private int d; //denomenator

    public ADTFraction() {
        this.n = 0;
        this.d = 1;
    }

    public ADTFraction(int a, int b) {//parameter constructor

        if (b != 0) {
            this.n = a;
            this.d = b;
        } else {
            this.n = 0;
            this.d = 1;
            System.out.println("denomerator cannot be 0,default values assinged");
        }
    }

    public void set(int a, int b) {//set numerator and denomenator

        if (b != 0) {
            this.n = a;
            this.d = b;
        } else {
            this.n = 0;
            this.d = 1;
            System.out.println("denomerator cannot be 0,default values assinged");
        }

    }

    //add two fractions
    public ADTFraction plus(ADTFraction x) {

        int num, den;
        num = this.d * x.n + this.n * x.d;
        den = this.d * x.d;
        ADTFraction f = new ADTFraction(num, den);
        return f;

    }

    public ADTFraction times(int a) {//multiply fraction by a number

        int num, den;
        num = this.n * a;
        den = this.d;
        ADTFraction f1 = new ADTFraction(num, den);
        return f1;

    }

    public ADTFraction times(ADTFraction x) {//multiply two fractions

        int num, dem;
        num = this.n * x.n;
        dem = this.d * x.d;
        ADTFraction f3 = new ADTFraction(num, dem);
        return f3;

    }

    //reciprocal of a fraction
    public ADTFraction reciprocal() {
        ADTFraction f1 = new ADTFraction(this.d, this.n);
        return f1;
    }

    //numerical value of a fraction
    public float value() {
        return (float) this.n / this.d;
    }

    //display the fraction in the format n/d
    public void display() {
        System.out.println(this.n + "/" + this.d);
    }
}
