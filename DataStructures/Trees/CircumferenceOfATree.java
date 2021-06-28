import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//This method is quite useful for finding the circumference of a tree. Many of us dont know about this
//Firt we pick any random node and find the farthest node from that node
//Then we will pick the farthest node and find the node which is farthest from that node
//Now we will calculate the distance between the two nodes and that will be out diameter


public class CircumferenceOfATree {

	public static void main(String[] args) {
	FastScanner fs = new FastScanner();
	int n=fs.nextInt();
	if(n==1) {
		System.out.println(0);
		return;
	}
	Node[] nodes = new Node[n];
	for(int i=0;i<n;i++)nodes[i]=new Node();
	for(int i=1;i<n;i++) {
		int u=fs.nextInt()-1,v=fs.nextInt()-1;
		nodes[u].adj.add(nodes[v]);
		nodes[v].adj.add(nodes[u]);
		
	}
	bfs(nodes[0],nodes);
	Node farthest = farthest(nodes);
	bfs(farthest,nodes);
	System.out.println(3*farthest(nodes).dist);

	}
    static class Node{
    	ArrayList<Node> adj=new ArrayList<>();
    	int dist;
    }
    static Node farthest(Node[] nodes) {
    	Node max =nodes[0];
    	for(Node nn:nodes)if(max.dist<nn.dist)max=nn;
    	return max;
    }
    static void bfs(Node from ,Node[] nodes) {
    	for(Node nn: nodes)nn.dist=-1;
    	from.dist=0;
    	ArrayDeque<Node> bfs = new ArrayDeque<>();
    	bfs.add(from);
    	while(!bfs.isEmpty()) {
    		Node next = bfs.remove();
    		for(Node nn: next.adj) {
    			if(nn.dist==-1) {
    				nn.dist=next.dist+1;
    				bfs.add(nn);
    			}
    		}
    	}
    }
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		String next() {
			while(!st.hasMoreTokens())
				try {
					st = new StringTokenizer(br.readLine());
				}catch(IOException e) {
					e.printStackTrace();
				}
			return st.nextToken();
		}
		int nextInt() {
			return Integer.parseInt(next());
		}
		int[] readArray(int n) {
			int[] a = new int[n];
			for(int i=0;i<n;i++)a[i]=nextInt();
			return a;
		}
		long nextLong() {
			return Long.parseLong(next());
		}
	
	}
}
