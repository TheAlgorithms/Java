//This program discusses the concept of OOP: Polymorphism.
//More specifically, it disusses, METHOD OVERLOADING.

public class Overloading {
    public static void main(String[] args) {
        sum obj = new sum();
        System.out.println("I'm a SUM of 2 no.s: " + obj.add(2, 3));
        System.out.println("I'm a SUM of 3 no.s: " + obj.add(-192, 89, 90));
        System.out.println("I'm a SUM of 4 no.s: " + obj.add(-92, 76, 89, 34));
        System.out.println("I'm a SUM of 5 no.s: " + obj.add(-2, 6, 9, -34, 135));
    }
}
class sum{
    int result = 0;
    int add(int num1, int num2){
        result = num1 + num2;
        return result;
    }
    int add(int num1, int num2, int num3){
        result = num1 + num2 + num3;
        return result;
    }
    int add(int num1, int num2, int num3, int num4){
        result = num1 + num2 + num3 + num4;
        return result;
    }
    int add(int num1, int num2, int num3, int num4, int num5){
        result = num1 + num2 + num3 + num4;
        return result;
    }
}
