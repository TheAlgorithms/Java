import java.util.*;

class PentagonArea
{
	public static void main(String args[])
	{
		PentagonArea P=new PentagonArea();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the length of the side: ");
		double side = sc.nextDouble();
		System.out.print("Enter the distance from center of pentagon to any side: ");
		double apothem = sc.nextDouble();
		double area = P.areaOfPentagon(side, apothem);
		System.out.println("Area of the pentagon: "+area);
	}
	private static double areaOfPentagon(double side, double apothem)
	{
		return (5.0/2.0)*side*apothem;
	}
}
