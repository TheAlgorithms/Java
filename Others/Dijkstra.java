import java.util.Scanner;
/* Added by vishnoitanuj */
public class Dijkstra {

	public static void djikstra(int a[][]) {
		boolean visited[]=new boolean[a.length];
		int dist[]=new int[a.length];
		dist[0]=0;
		for(int i=1;i<a.length;i++)
			dist[i]=Integer.MAX_VALUE;
		for(int i=0;i<a.length;i++) {
			int min=findMinVertex(dist,visited);
			visited[min]=true;
			for(int j=0;j<a.length;j++) {
				if(a[min][j]!=0 && !visited[j]) {
					int d=dist[min]+a[min][j];
					if(d<dist[j]) {
						dist[j]=d;
					}
				}
			}
		}
		for(int i=0;i<a.length;i++) {
			System.out.println(i+" "+dist[i]);
		}
	}
	
	public static int findMinVertex(int a[],boolean visited[]) {
		int min=-1;
		for(int i=0;i<a.length;i++) {
			if(!visited[i] && (min==-1 || a[i]<a[min]))
				min=i;
		}
		return min;
	}

	public static void main(String[] args) {
		Scanner d=new Scanner(System.in);
		int V=d.nextInt();
		int E=d.nextInt();
		int a[][]=new int[V][V];
		for(int i=0;i<E;i++) {
			int sv=d.nextInt();
			int ev=d.nextInt();
			int w=d.nextInt();
			a[sv][ev]=w;
			a[ev][sv]=w;
		}
		djikstra(a);

	}
}