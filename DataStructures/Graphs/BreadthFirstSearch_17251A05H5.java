import java.util.*;
class bfs{
  static int n,visit[],a[][];
  static Queue<Integer> q=new LinkedList<Integer>();
  public static void main(String args[]){
    Scanner sc=new Scanner(System.in);
    System.out.println("enter no of nodes");
    n=sc.nextInt();
    visit=new int[n];
    a=new int[n][n];
    for(int i=0;i<n;i++)
      for(int j=0;j<n;j++)
        a[i][j]=sc.nextInt();
    for(int i=0;i<n;i++) visit[i]=0;
    bfsearch(0);
  }
  public static void bfsearch(int v){
    visit[v]=1;
    System.out.print(v+" ");
    for(int i=0;i<n;i++){
      if(a[v][i]==1  && visit[i]==0){
        visit[i]=1;
        q.add(i);
      }
    }
    if(q.isEmpty()) return;
    int x=q.remove();
    bfsearch(x);
  }
}
