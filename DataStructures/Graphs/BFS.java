import java.util.*;

public class BFSs {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int V = s.nextInt();
		int E = s.nextInt();

		int edges[][]=new int[V][V];
        for(int i=0;i<E;i++){
            int fv=s.nextInt();
            int sv=s.nextInt();
            edges[fv][sv]=1;
            edges[sv][fv]=1;
        }
        printBFS(edges);
	}
    
    private static void printBFS(int edges[][]){
        boolean visited[] = new boolean[edges.length];
        // printBFS(edges,0,visited);
		for(int i=0;i<edges.length;i++) {
			if(!visited[i]) {
				printBFS(edges,i,visited);
			}
		}
    }
    
    private static void printBFS(int edges[][],int sv,boolean visited[]){
        Queue<Integer> q=new LinkedList<>();
        q.add(sv);
        int n=edges.length;
        visited[sv]=true;
        String s=new String();
        while(!q.isEmpty()){
            int v=q.remove();
            System.out.print(v+" ");
            for(int i=0;i<n;i++){
                if(edges[v][i]==1 && !visited[i]){
                    q.add(i);
                    visited[i]=true;
                }
            }    
        }
    }
}