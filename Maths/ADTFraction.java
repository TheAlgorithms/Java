public class ADTFraction{
    private int n; //numerator
    private int d; //denomenator
    public ADTFraction() {//default constructor
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
            System.out.println("Denominator cannot be zero. default values are assigned."); }
    }
    public void set(int a, int b) {//set numerator and denomenator
        if (b != 0) {
            this.n = a;
            this.d = b;
        } else {
            this.n = 0;
            this.d = 1;
            System.out.println("Denominator cannot be zero. default values are assigned."); }
    }
    public ADTFraction plus(ADTFraction x) {//add two fractions this=3/5 and x=7/8
        int num, den;
        num = x.d * this.n + x.n * this.d;
        den = this.d * x.d;
        ADTFraction f1 = new ADTFraction(num, den);
        return f1;
    }
    public ADTFraction times(int a) {//multiply fraction by a number
        int num, den;
        num=this.n*a;
        den=this.d;
        ADTFraction f1=new ADTFraction(num,den);
        return f1;
    }


    public ADTFraction times(ADTFraction x) {//multiply two fractions
        int num ,den;
        num= this.n+ x.n;
        den= this.d+ x.d;
        ADTFraction f1= new ADTFraction(num,den);
        return f1;
    }
    public ADTFraction reciprocal() {//reciprocal of a fraction upside down
        ADTFraction f1=new ADTFraction(this.d,this.n);
        return f1;
    }
    public float value() {//numerical value of a fraction
        return (float)this.n/this.d;
    }
    public void display() {//display the fraction in the format n/d
        System.out.println("Fraction equals to "+this.n+ "/" + this.d); }
}