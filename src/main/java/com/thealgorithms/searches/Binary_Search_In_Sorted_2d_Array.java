class Search_In_2d_Array {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int last=matrix[0].length-1;
        int last2=0;
        while(last>=0&&last2<matrix.length){
            if(matrix[last2][last]==target){
                return true;
            }else if(target>matrix[last2][last]){
                last2++;
            }else{
                 last--;
            }
        }return false;
    }
    public static void main(String[] args) {
        int arr[][]={{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(searchMatrix(arr,10));

    }
}
