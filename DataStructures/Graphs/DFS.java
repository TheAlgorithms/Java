import java.util.*;

public class DFS {

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
        int v1=s.nextInt();
        int v2=s.nextInt();
        boolean visited[]=new boolean[V];
        ArrayList<Integer> p=new ArrayList<>();
        p=getPathDFS(edges,v1,v2,visited);
        if(p!=null){
            for(int i:p)
                System.out.print(i+" ");
        }
	}
    
    private static ArrayList<Integer> getPathDFS(int edges[][],int sv,int ev,boolean visited[]){
        ArrayList<Integer> out=new ArrayList<>();
        if(sv==ev){
            out.add(sv);
            return out;
        }
        visited[sv]=true;
        for(int i=0;i<edges.length;i++){
            if(edges[sv][i]==1 && !visited[i]){
                ArrayList<Integer> temp=getPathDFS(edges,i,ev,visited);
                if(temp!=null){
                    temp.add(sv);
                    return temp;
                }
            }
        }
        return null;        
    }
}