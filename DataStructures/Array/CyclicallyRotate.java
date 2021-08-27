public class CyclicallyRotate 
{		
		// Method for rotation
		static void rotate(int arr[])
		{
			int x = arr[arr.length-1], i;
			for (i = arr.length-1; i > 0; i--)
			{
				arr[i] = arr[i-1];
			}
			arr[0] = x;
		}
		
		/* Driver program */
		public static void main(String[] args)
		{
			int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7};
			
			System.out.println("Given Array is");
			for (int i = 0; i < arr.length; i++)
			{
				System.out.print(arr[i]+" ");
			}

			System.out.println();
			rotate(arr);
			
			System.out.println("Rotated Array is");
			for (int i = 0; i < arr.length; i++)
			{
				System.out.print(arr[i]+" ");
			}
		}
}
