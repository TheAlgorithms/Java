import java.util.*;

class BucketSort {
	public static int[] bucketSort(int[] array, int bucketCount) {

	if (bucketCount <= 0) throw new IllegalArgumentException("Invalid bucket count");
		if (array.length <= 1) return array; //trivially sorted
	int high = array[0];
		int low = array[0];
		for (int i = 1; i < array.length; i++) { //find the range of input elements
	if (array[i] > high) high = array[i];
	if (array[i] < low) low = array[i];
	}
	double interval = ((double)(high - low + 1))/bucketCount; //range of one bucket
	ArrayList<Integer> buckets[] = new ArrayList[bucketCount];
	for (int i = 0; i < bucketCount; i++) { //initialize buckets
	buckets[i] = new ArrayList();
	}
	for(int i = 0; i < array.length; i++) 
	{ //partition the input array
		buckets[(int)((array[i] - low)/interval)].add(array[i]);
	}	
		int pointer = 0;
		for (int i = 0; i < buckets.length; i++) 
		{
		Collections.sort(buckets[i]); //mergeSort
		for (int j = 0; j < buckets[i].size(); j++) 
		{ //merge the buckets
	    array[pointer] = buckets[i].get(j);
		pointer++;
		}
	
	   }
	return array;
	
	}


	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		int[] a=new int[t];
		for(int i=0;i<t;i++)
		{
			a[i]=sc.nextInt();
		}
		bucketSort(a,t);
		for(int i=0;i<t;i++)
		{
			System.out.println(a[i]);
		}

	}
}