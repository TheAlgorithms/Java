import java.util.*;
public class shortestPath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i,j,r,c;
        System.out.println("Enter Rows: ");
        r = sc.nextInt();
        System.out.println("Enter Columns: ");
        c = sc.nextInt();
        int a[][] = new int[r][c];
        System.out.println("Enter Values: ");
        for(i=0; i<r; i++){
            for (j = 0; j<c; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        int startRow,startCol,endRow,endCol;
        System.out.println("Enter startRow: ");
        startRow = sc.nextInt();
        System.out.println("Enter startCol: ");
        startCol = sc.nextInt();
        System.out.println("Enter endRow: ");
        endRow = sc.nextInt();
        System.out.println("Enter endCol: ");
        endCol = sc.nextInt();
        int res = shortPath(a,startRow,startCol,endRow,endCol);
        if(res >=1000000){
            System.out.println("Path Not Found!");
        }else{
        System.out.println("Shortest Path: "+res);
        }
    }

    public static int shortPath(int a[][],int i,int j, int x, int y){
        int rows = a.length;
        int cols = a[0].length;
        boolean visited[][] = new boolean[rows][cols];
        return shortPath(a,i,j,x,y,visited);
    }

    public static boolean isValid(int a[][],int i, int j, boolean visited[][]){
        int rows = a.length;
        int cols = a[0].length;
        return i>=0 && j>=0 && i<rows && j<cols && a[i][j]==1 && !visited[i][j];
    }

    public static int shortPath(int a[][], int i, int j,int x, int y, boolean visited[][]){
        if(!isValid(a,i,j,visited))
        return 1000000;
        if(i==x && j==y) return 0;
        visited[i][j] = true;
        int left = shortPath(a,i,j-1,x,y,visited)+1;
        int right = shortPath(a,i,j+1,x,y,visited)+1;
        int bottom = shortPath(a,i+1,j,x,y,visited)+1;
        int top = shortPath(a,i-1,j,x,y,visited)+1;
        
        //backtracking
        visited[i][j] = false;
        return Math.min(Math.min(left,right),Math.min(top,bottom));

    }
}
