package Maths;
import java.util.*;
class Circle{
	static double r;
	public static void main(String args[]){
		double area;
		double peri;
		Scanner input=new Scanner(System.in);
		System.out.print("Enter Radius Of Circle :");
		r=input.nextDouble();
		
		area=(Math.PI*r*r);
		System.out.println("Area of Circle is : "+area);
		peri=(2*Math.PI*r);
		System.out.println("Perimeter of Circle is : "+peri);
		}	
	}
