import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] arr={{10,20,30,40},
                {15,25,35,45},
                {28,29,37,49},
                {33,34,38,50}};
        int target=37;
        System.out.println(Arrays.toString(Search(arr,target)));
    }
    static int[] Search(int[][] arr,int target){
        int row=0;
        int col=arr.length-1;
        while(row<=col){
            int ele= row+(col-row)/2;
            if(arr[row][ele]==target){
                return new int[]{row,ele};
            }
            else if(arr[row][ele]<target){
                row++;
            }
            else{
                col--;
            }
        }
        return new int[]{-1,-1};
    }
}