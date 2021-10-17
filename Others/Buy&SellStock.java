public class BuyAndSellStock {
    public static void main(String []args)
    {
        int[] arr ={3,1,4,8,7,2,5};
     int profit=0,max=0;
     for(int i=arr.length-1;i>=0;i--)
     {
        if(arr[i]>max)
            max=arr[i];
        if(max-arr[i]>profit)
            profit=max-arr[i];
     }
     System.out.println(profit);
    }
}
