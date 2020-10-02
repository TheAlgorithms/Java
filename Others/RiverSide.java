import java.util.*;

class RiverSide {
  public static List<Integer> riverSizes(int[][] arr) {
    // Write your code here.
    int row = arr.length-1;
    int column = arr[0].length -1;
    boolean twodimension = true;
    if(column == 0){
      twodimension = false;
    }
    List<Integer> result = new ArrayList<>();
    if(twodimension){
      for(int i = 0 ; i <= row ; i ++){
        for(int j = 0; j <= column ; j++){
          if(arr[i][j] == 1){
            int count = 1;
            int x = j;
            int y = i;
            boolean flag = true;
            boolean donex = false;
            boolean doney = false;
            while(flag){
              if(x + 1 <= column && arr[y][x+1] == 1){
                arr[y][x+1] = 0;
                count += 1;
                x += 1;
              }
              else if(y + 1 <= row && arr[y+1][x] == 1){
                arr[y+1][x] = 0;
                count += 1;
                y += 1;
              }
              else{
                flag = false;
              }
            }
            result.add(count);
          }
        }
      }
    }else{
      for(int i = 0 ; i <= row ; i++){
        if(arr[i][0] == 1){
          int x = i;
          boolean flag = false;
          int count = 1;
          while(flag){
            if(arr[x+1][0] == 1){
              arr[x+1][0] = 0;
              count += 1;
              x += 1;
            }else{
              flag = false;
            }
          }
          result.add(count);
        }
      }
    }

    return result;
  }
  public static void main(String[] args){
    // CUSTOMIZE YOUR INPUT 
    int[][] arr = new int [10][10];
    List<Integer> result = riverSizes(arr);
    for(Integer data : result){
      System.out.println(data);
    }
  }
}
