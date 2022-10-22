public class Number_of_ways_in_a_Maze {
    public static int num_of_ways(int row, int col){
        if(row==1||col==1){
            return 1;
        }
        return num_of_ways(row-1,col)+num_of_ways(row,col-1);
    }
}