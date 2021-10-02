class Solution {
    public static void main(String[] args) {
        int[][] arr = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        searchMatrix(arr,3);
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix[matrix.length-1][0]<=target && matrix[matrix.length-1][matrix[0].length-1]>=target){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[matrix.length-1][j]==target){
                    return true;
                }

                if(matrix[matrix.length-1][j]>target){
                    return false;
                }
            }
        }
        else{
            for(int i=0;i<matrix.length-1;i++){
                if(matrix[i][0]<=target && matrix[i+1][0]>target){
                    for(int j=0;j<matrix[0].length;j++){
                        if(matrix[i][j]>target){
                            return false;
                        }
                        if(matrix[i][j]==target){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
