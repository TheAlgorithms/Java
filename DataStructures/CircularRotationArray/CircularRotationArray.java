import java.util.*;
public class Main
{
public static void main(String[] args) 
{
int no_of_elements, no_of_rotations, no_to_display;
Scanner sc = new Scanner(System.in);

System.out.println("Enter the number of elements of the array : ");
no_of_elements = sc.nextInt();

System.out.println("Enter the number of rotations of the array : ");
no_of_rotations = sc.nextInt();

System.out.println("Enter the number of indexes to be displayed : ");
no_to_display = sc.nextInt();

int arr[] = new int[no_of_elements];
System.out.println("\n Input the array elements\n");
for(int i = 0; i < no_of_elements; i++)
{
arr[i] = sc.nextInt();
}

System.out.println("\nThe Array Elements are\n");
for(int i = 0; i < no_of_elements; i++)
{
System.out.print(arr[i]);
System.out.print("\t");
}

no_of_rotations %= no_of_elements; // Remove the number of full array rotations from k

for(int i = 0; i < no_to_display; i++)
{
int index;
System.out.println("\nEnter the index of the array to be displayed : ");
index = sc.nextInt();

System.out.println("\nThe element in the array is ");
System.out.println(arr[(no_of_elements + index - no_of_rotations) % no_of_elements]); // Calculate the new index 
}}}