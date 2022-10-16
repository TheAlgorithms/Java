package sortingapp;
import java.util.concurrent.TimeUnit;
public class SortingAlgos 
{   long btime;
    private int bSwap;
    private int bComp;
   long stime;
    private int sswap;
    private int scomp;
    long itime;
    private int iswap;
    private int icomp;
    void SortingAlgos()
    {   
        bSwap = 0;
        bComp = 0;
        sswap = 0;
        scomp = 0;
    }
    
    public void BubbleSort(int bList[])
    {   	int marker;
   	int size = bList.length;
        long start =System.nanoTime();
   	for (int i = 0; i < size - 1; i++)
   	{
          for (int j = 0; j < size - (i+1); j++)
	 {   bComp++;
	   if (bList[j] > bList[j+1])
	   {		//swap
                        bSwap++;
                        
			marker = bList[j];
			bList[j] = bList[j+1];
			bList[j+1] = marker;
	   }
          
	 }
           long end=System.nanoTime();
           btime=end-start;
          }

    
    }
    
    public void PrintList(int List[])
    {
        for(int i=0; i<List.length; i++)
        {
            System.out.print(List[i]+" ");
        }
        
    }
    public void selectionsort(int slist[]){
        int temp,min;
        int size=slist.length;
        long start =System.nanoTime();
        for(int i=0;i<size;i++){
            min=i;
            
            for (int j=i+1;j<size;j++){
                if (slist[j]<slist[min]){
                  scomp++;
                    min=j;   
                
                }
            
            }
         
        temp=slist[min];
        slist[min]=slist[i];
        slist[i]=temp;
        sswap++;
         long end=System.nanoTime();
         stime=end-start;
        }
    
    }
    public void insertion_sort(int ilist[]){
                int temp;
                int size=ilist.length;
                long start =System.nanoTime();
                for (int i=1;i<size;i++){
                    temp=ilist[i];
                    int j=i;
                    while(j<0 && temp<ilist[j-1]){
                    
                      ilist[j]=ilist[j-1];
                      j--;
                      
                    }
                    ilist[j]=temp;
                }
    
    
    }
    
    public void PrintReport()
    {
        System.out.println("----------------------------------");
        System.out.println("\t Comparison Chart");
        System.out.println("----------------------------------");
        System.out.println("Bubble Sort Comparisons    : "+bComp);
        System.out.println("Bubble Swaps Swaps         : "+bSwap);
        System.out.println("b-time:"+btime);
        
        System.out.println("Selection Sort Comparisons    : "+scomp);
        System.out.println("Selection Swaps Swaps         : "+sswap);
        System.out.println("Selection -time               : "+stime);
        
          System.out.println("Selection Sort Comparisons    : "+icomp);
        System.out.println("Selection Swaps Swaps         : "+iswap);
        System.out.println("Selection -time               : "+itime);
    }
    
}
