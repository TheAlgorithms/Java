// this program outputs the volume of a Cylinder
package Maths;

import java.util.Scanner;

public class VolOfCylinder {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        System.out.print("Cylinder radius : ");
        double r = obj.nextDouble();
        System.out.print("Cylinder height : ");
        double hgt = obj.nextDouble();

        double vol = Math.PI * r * r * hgt;
        
        System.out.println("Volume = "+ vol);
    }
}