public class CountSort {

    public static void countingsort(int arr[]) {
         int max = Integer.MIN_VALUE;
         for(int i=0;i<arr.length;i++){
            max = Math.max(arr[i], max);
         }
         //frequency array
         int count [] = new int[max+1];
         for(int i=0;i<arr.length;i++){
            count[arr[i]]++;
            
         }
         // sorting
         int j=0;
         for(int i=0;i<count.length;i++){
            while(count[i]>0){
                arr[j]=i;
                j++;
                count[i]--;
            }
         }
         for(int i=0;i<arr.length ;i++){
            System.out.print(arr[i]+", ");
         }
    }
    public static void main(String[] args) {

        int arr[] = {1,4,1,4,2,3,7};
        countingsort(arr);
        String str = "asda";
        str.charAt(0);
    }
}
