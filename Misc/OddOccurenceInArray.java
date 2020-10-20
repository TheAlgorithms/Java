public class OddOccurenceInArray{
    public static void main(String args[]){
        int arr[]={1,2,3,4,1,2,3,5,6,5,6};
        int result=printOddOccurenceNo(arr);
        System.out.println(result);
    }
    public static int printOddOccurenceNo(int arr[]){
        int result=0;
        for(int number=0;number<arr.length;number++){
            result=result^arr[number];
        }
        return result;
    }
}