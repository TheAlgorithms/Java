// Java Program to muller method
import java.io.*;
import static java.lang.Math.*;

class Muller
{
	static final int MAX_ITERATIONS = 10000;

	static double f(double x)
	{
		return 1*pow(x, 3) + 2*x*x + 10*x - 20;
	}

	static void Muller(double a, double b, double c)
	{
		int i;
		double res;

		for (i = 0;; ++i)
		{
			double f1 = f(a);
			double f2 = f(b);
			double f3 = f(c);
			double d1 = f1 - f3;
			double d2 = f2 - f3;
			double h1 = a - c;
			double h2 = b - c;
			double a0 = f3;
			double a1 = (((d2*pow(h1, 2)) - (d1*pow(h2, 2)))
						/ ((h1*h2) * (h1-h2)));
			double a2 = (((d1*h2) - (d2*h1))/((h1*h2) * (h1-h2)));
			double x = ((-2*a0)/(a1 + abs(sqrt(a1*a1-4*a0*a2))));
			double y = ((-2*a0)/(a1-abs(sqrt(a1*a1-4*a0*a2))));

			
			if (x >= y)
				res = x + c;
			else
				res = y + c;

			double m = res*100;
			double n = c*100;
			m = floor(m);
			n = floor(n);
			if (m == n)
				break;
			a = b;
			b = c;
			c = res;
			if (i > MAX_ITERATIONS)
			{
				System.out.println("Root cannot be found using" +
								" Muller's method");
				break;
			}
		}
		if (i <= MAX_ITERATIONS)
			System.out.println("The value of the root is " + res);
	}

	// driver
	public static void main(String args[])
	{
		double a = 0, b = 1, c = 2;
		Muller(a, b, c);
	}
}
