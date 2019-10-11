package Maths;
import java.util.*;
class Cylinder{
	static double r;
	static double h;
	public static void main(String args[]){
		double area;
		double volume;
		Scanner input=new Scanner(System.in);
		System.out.print("Enter Radius Of Cylinder :");
		r=input.nextDouble();
		System.out.print("Enter Height Of Cylinder :");
		h=input.nextDouble();
		area=(2*Math.PI*r*r)+(2*Math.PI*r*h);
		System.out.println("Area of Cylinder is : "+area);
		volume=(Math.PI*r*r*h);
		System.out.println("Volume of Cylinder is : "+volume);
		}	
	}
