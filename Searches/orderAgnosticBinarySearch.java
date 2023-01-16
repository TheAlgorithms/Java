
public class orderAgnosticBinarySearch {

	static int ascend(int arr[],int target) {
		int strt =0;
		
		/**
		 * varibale strt ans end defines search space for the loop.
		 */
		int end = arr.length;
		while(strt<=end) {
			int mid = (strt + end)/2;
			if(arr[mid] == target) { 
				return mid;//returns the index of the targeted element.
			}else if(arr[mid] < target) {
				strt = mid+1;
			}else {
				end = mid-1;
			}
			
		}
		return -1;////Return -1 if the targeted element is not found.
	}
	static int descend(int arr[],int target) {
		/**
		 * varibale strt ans end defines search space for the loop.
		 */
		int strt =0;
		int end = arr.length;
		while(strt<=end) {
			int mid = (strt + end)/2;
			if(arr[mid] == target) { 
				return mid;//Return -1 if the targeted element is not found.
			}else if(arr[mid] < target) {
				end = mid-1;
			}else {
				strt = mid+1;
			}
			
		}
		return -1;//Return -1 if the targeted element is not found.
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int arr[] = {7,6,5,4,3,2,1};
        int target = 3;//element to be searched.
        if(arr[0] < arr[arr.length-1]) {//will execute if the array is in ascending order.
        	System.out.println(ascend(arr,target));
        }else {
        	System.out.println(descend(arr,target));//will execute if the array is in descending order.
        }
	}

}
