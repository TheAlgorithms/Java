// Java code to demonstrate to draw
// circle without floating
// point arithmetic

class GFG
{
static void drawCircle(int r)
{
	// Consider a rectangle of size N*N
	int N = 2*r+1;

	int x, y; // Coordinates inside the rectangle

	// Draw a square of size N*N.
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			// Start from the left most corner point
			x = i-r;
			y = j-r;

			// If this point is inside the circle, print it
			if (x*x + y*y <= r*r+1 )
				System.out.print(".");
			else
				// If outside the circle, print space
				System.out.print(" ");

			System.out.print(" ");
		}

		System.out.println();
	}
}

// Driver Program to test above function
public static void main(String arg[])
{
	drawCircle(8);
}
}