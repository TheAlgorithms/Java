import java.util.*;

public class LeftRotateArray {

/*
* This method is rotating Array to left by number of Rotation  specified by user.
* for ex : Arr = {1, 3, 4, 5, 8}
* NoOfTurns = 2
* After Two Rotation
* Arr = {4, 5, 8, 1, 3}
* @param size of array
* @param arr to contain integer elments
* @param NoOfTurns to tell how many digits to be rotated to Left
* @return resultant arr after rotation
*/
	public int [] LeftRotationSolution(int [] arr, int NoOfTurns, int size){
		System.out.println("Before Rotation..");
		displayArray(arr,  size);

		for(int i=0;i<NoOfTurns;i++){
			LeftTurn(arr, size);
		}

		System.out.println("After Rotation..");
		displayArray(arr,  size);
		return arr;
	}

/*
* This method displays the array
* @param arr that to be displayed
* @param size of array
*/

	public void displayArray(int [] arr, int size){
		for(int i=0;i<size;i++)
			System.out.print(arr[i]+ " ");
		System.out.println();
	}
		
/*
* This method perform operaton to rotate array
* @param arr that to be rotated
* @param size of array
*/
	public void LeftTurn(int [] arr, int size){
		int temp = arr[0];
		for(int i=1;i<size;i++){
			arr[i-1] = arr[i];
		}
		arr[size-1] = temp;
	}
}