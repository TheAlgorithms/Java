package com.thealgorithms.others;

public class Triangle {

    public static String determineType(int a, int b, int c) {

        if (a >= (b + c) || c >= (b + a) || b >= (a + c)) {
            return "Not a Triangle";
        } else if (a == b && b == c) {
            return "Equilateral Triangle";
        } else if (((a * a) + (b * b)) == (c * c) || ((a * a) + (c * c)) == (b * b) || ((c * c) + (b * b)) == (a * a)) {
            return "Right Triangle";
        } else if (a != b && b != c && c != a) {
            return "Scalene Triangle";
        } else if ((a == b && b != c) || (a != b && c == a) || (c == b && c != a)) {
            return "Isosceles Triangle";
        }
        // other case
        return null;
    }
    
    enum TriangleType {
        EQUILATERAL, RIGHT, SCALENE, ISOSCELES, NONE
    }

    public static void main(String[] args) {
        System.out.println(determineType(1, 1, 9));
    }
}
