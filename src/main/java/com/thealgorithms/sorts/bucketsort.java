import java.util.*;
public class Main
{
public static int[] bucketsort(int[] array, int maximum_value)
{
//creating an empty array called newbucket which is considered as bucket array
int[] newbucket = new int[maximum_value + 1];
//creating another empty array called sorted_array to store the result array
int[] sorted_array = new int[array.length];
//traversing through the input array to add each element to the bucket array
for (int a= 0; a <array.length; a++)
newbucket[array[a]]++;
//sorting each element in the bucket array and adding each sorted element in order to the original input array
int position = 0;
for (int b = 0; b < newbucket.length; b++)
for (int c = 0; c < newbucket[b]; c++)
sorted_array[position++] = b;
return sorted_array;
}
//function to find the maximum value in the input array in order to sort the given array using bucket sort technique
static int maximumValue(int[] array)
{
int maximum_value = 0;
for (int d = 0; d < array.length; d++)
if (array[d] > maximum_value)
maximum_value = array[d];
return maximum_value;
}
//main function is called within which we display the resulting array
public static void main(String args[])
{
int[] array ={100, 90, 80, 70, 60, 50, 40, 30, 20, 10};
int maximum_value = maximumValue(array);
System.out.print("\nThe elements of the array to be sorted are:\n ");
System.out.println(Arrays.toString(array));
System.out.print("\nThe elements of the sorted array sorted using bucket sort algorithm are:\n ");
System.out.println(Arrays.toString(bucketsort(array,maximum_value)));
}
}
