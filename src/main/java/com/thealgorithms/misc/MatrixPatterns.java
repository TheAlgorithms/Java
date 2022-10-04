package com.thealgorithms.misc;
import java.io.*; 
/*
 * A simple Java program to diplay the 10 most common patterns of 2-dimensional arrays.
 * 
 * Code by @hexaorzo
 */
public class MatrixPatterns
{
	public static void main(String args[]) throws IOException
	{
		int i, j;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter number of rows or columns of the square matrix : ");
		int n = Integer.parseInt(br.readLine());
		if (n>3)
        	{
            		int a[][] = new int[n][n];
            		System.out.println("Enter all the elements of matrix ");
			for (i = 0; i < n; i++) 
            		{
				for (j = 0; j < n; j++) 
					a[i][j] = Integer.parseInt(br.readLine());
			}
			while (true) 
			{
        System.out.println("\nMENU");
				System.out.println("1. Border Elements");
				System.out.println("2. Inner Elements");
				System.out.println("3. Lower left triangle including diagonal");
				System.out.println("4. Lower left triangle excluding diagonal");
				System.out.println("5. Upper right triangle including diagonal");
				System.out.println("6. Upper right triangle excluding diagonal");
				System.out.println("7. Lower right triangle including diagonal");
				System.out.println("8. Lower right triangle excluding diagonal");
				System.out.println("9. Upper left triangle including diagonal");
				System.out.println("10. Upper left triangle excluding diagonal");
				System.out.println("11. Exit");
				System.out.print("\nEnter your choice : ");
				int ch = Integer.parseInt(br.readLine());
				System.out.println();
				switch (ch) 
				{
					case 1:
						for (i = 0; i < n; i++) 
						{
							for (j = 0; j < n; j++)
							{
								if (i == 0 || i == n - 1 || j == 0 || j == n - 1)
									System.out.print(a[i][j] + "\t");
								else
									System.out.print(" \t");
							}
							System.out.println();
						}
						break;
					case 2:
						for (i = 0; i < n; i++)
						{
							for (j = 0; j < n; j++)
							{
								if (i == 0 || i == n - 1 || j == 0 || j == n - 1)
									System.out.print(" \t");
								else
									System.out.print(a[i][j] + "\t");
							}
							System.out.println();
						}
						break;
					case 3:
						for (i = 0; i < n; i++)
						{
							for (j = 0; j < n; j++)
							{
								if (i >= j)
									System.out.print(a[i][j] + "\t");
								else
									System.out.print(" \t");
							}
							System.out.println();
						}
						break;
					case 4:
						for (i = 0; i < n; i++)
						{
							for (j = 0; j < n; j++)
							{
								if (i > j)
									System.out.print(a[i][j] + "\t");
								else
									System.out.print(" \t");
							}
							System.out.println();
						}
						break;
					case 5:
						for (i = 0; i < n; i++)
						{
							for (j = 0; j < n; j++)
							{
								if (i <= j)
									System.out.print(a[i][j] + "\t");
								else
									System.out.print(" \t");
							}
							System.out.println();
						}
						break;
					case 6:
						for (i = 0; i < n; i++)
						{
							for (j = 0; j < n; j++)
							{
								if (i < j)
									System.out.print(a[i][j] + "\t");
								else
									System.out.print(" \t");
							}
							System.out.println();
						}
						break;
					case 7:
						for (i = 0; i < n; i++)
						{
							for (j = 0; j < n; j++)
							{
								if (i + j >= n - 1)
									System.out.print(a[i][j] + "\t");
								else
									System.out.print(" \t");
							}
							System.out.println();
						}
						break;
					case 8:
						for (i = 0; i < n; i++)
						{
							for (j = 0; j < n; j++)
							{
								if (i + j > n - 1)
									System.out.print(a[i][j] + "\t");
								else
									System.out.print(" \t");
							}
							System.out.println();
						}
						break;
					case 9:
						for (i = 0; i < n; i++)
						{
							for (j = 0; j < n; j++)
							{
								if (i + j <= n - 1)
									System.out.print(a[i][j] + "\t");
								else
									System.out.print(" \t");
							}
							System.out.println();
						}
						break;
					case 10:
						for (i = 0; i < n; i++)
						{
							for (j = 0; j < n; j++)
							{
								if (i + j < n - 1)
									System.out.print(a[i][j] + "\t");
								else
									System.out.print(" \t");
							}
							System.out.println();
						}
						break;
					case 11:
						System.exit(0);
					default:
						System.out.println("Invalid choice");
				}
			}
		}
		else
			System.out.println("Size is out of range");
	}
}
