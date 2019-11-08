public class BeadSort 
{
	public static void main(String[] args)
	{
		BeadSort now=new BeadSort();
		int[] arr=new int[(int)(Math.random()*11)+5];
		for(int i=0;i<arr.length;i++)
			arr[i]=(int)(Math.random()*10);
		System.out.print("Unsorted: ");
		now.display1D(arr);
 
		int[] sort=now.beadSort(arr);
		System.out.print("Sorted: ");
		now.display1D(sort);
	}
	int[] beadSort(int[] arr)
	{
		int max=a[0];
		for(int i=1;i<arr.length;i++)
			if(arr[i]>max)
				max=arr[i];
 
		//Set up abacus
		char[][] grid=new char[arr.length][max];
		int[] levelcount=new int[max];
		for(int i=0;i<max;i++)
		{
			levelcount[i]=0;
			for(int j=0;j<arr.length;j++)
				grid[j][i]='_';
		}
		/*
		display1D(arr);
		display1D(levelcount);
		display2D(grid);
		*/
 
		//Drop the beads
		for(int i=0;i<arr.length;i++)
		{
			int num=arr[i];
			for(int j=0;num>0;j++)
			{
				grid[levelcount[j]++][j]='*';
				num--;
			}
		}
		System.out.println();
		display2D(grid);
		//Count the beads
		int[] sorted=new int[arr.length];
		for(int i=0;i<arr.length;i++)
		{
			int putt=0;
			for(int j=0;j<max&&grid[arr.length-1-i][j]=='*';j++)
				putt++;
			sorted[i]=putt;
		}
 
		return sorted;
	}
	void display1D(int[] arr)
	{
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
	void display1D(char[] arr)
	{
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
	void display2D(char[][] arr)
	{
		for(int i=0;i<arr.length;i++)
			display1D(arr[i]);
		System.out.println();
	}
}
