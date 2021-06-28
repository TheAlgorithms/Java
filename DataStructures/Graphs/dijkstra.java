import java.util.*;

/*Implementation of Dijkstra algorithm to find shortest path in undirected graph. 
This program calculates shortest path from 0 vertex.
*/

class gp4{
    public static void main(String[] args) {
            Graph4 gh4 = new Graph4(5);
            gh4.addEdge(0,1,1);
            gh4.addEdge(0,3,1);
            gh4.addEdge(0,4,8);
            gh4.addEdge(1,2,2);
            gh4.addEdge(2,3,6);
            gh4.addEdge(3,4,2);

            gh4.dijkstra();
        }   
}

class Graph4{
    private int N;
    private int metrix[][];

    public Graph4(int n){
        N=n;
        metrix = new int[N][N];

        for (int i=0; i<N; i++ ) {
            for (int j=0; j<N; j++ ) {
                metrix[i][j] = 0;
            }
        }
    }

    public void addEdge(int from, int to,int weight){
        metrix[from][to] = weight;
        metrix[to][from] = weight;
    }

    public void dijkstra(){
        int dist[] = new int[N];
        boolean visit[] = new boolean[N];

        for (int i=0; i<N; i++ ) {
            dist[i]=Integer.MAX_VALUE;
            visit[i]=false;
        }

        dist[0] = 0;

        //main
        for (int i=0; i<N; i++ ) {
            int min=Integer.MAX_VALUE;
            int ind = -1;
            for (int j=0; j<N; j++ ) {
                if(visit[j]==false && dist[j]<=min){
                    min=dist[j];
                    ind=j;
                }
            }

            visit[ind] = true;//setting true to min value

            //sortest path
            for (int j=0; j<N; j++ ) {
                
                if( visit[j]==false && 
                    dist[ind] != Integer.MAX_VALUE &&
                    dist[ind] + metrix[ind][j] < dist[j] && 
                    metrix[ind][j] != 0
                    ){
                    dist[j] = dist[ind]+metrix[ind][j];
                }
            }
        }
    /*    	 
        0-----------1
       / \          |
      /   \         |
     /     \        |
    4-------3-------2
    	
    (0 to 1) = 1	
    (0 to 3) = 1	
    (0 to 4) = 8	
    (1 to 2) = 2	
    (2 to 3) = 6	
    (3 to 4) = 2

	*/
            int x=0;
            System.out.println();
            for (int p: dist ) {
                System.out.println(" shortest distance is "+p+" to reach vertex "+(x++));
            }
    }
}
