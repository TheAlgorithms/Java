import java.io.*;
import java.util.*;

/**
 * An algorithm to find articulation points in an undirected graph.
 * <br />
 * An articulation point is a node in a <i><b>connected</b></i> graph, removing which will result in the graph becoming
 * disconnected.
 * For additional documentation, refer
 * <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of <i>Algorithms, 4th Edition</i>
 */
public class ArticulationPoints
{
	static int t, n, m, k, articulationPoints, currTime;
	static int[] parent, visitingTime, low;
	static List<Integer>[] adj;
	static boolean[] visited;
	static Scanner in;
	static PrintWriter out;

	public static void main(String[] args)
	{
		in = new Scanner(System.in);
		out = new PrintWriter(System.out);

		solve();

		out.flush();

		in.close();
		out.close();
	}

	static void solve()
	{
		t = in.nextInt();

		while (t-- > 0)
		{
			n = in.nextInt();
			m = in.nextInt();
			k = in.nextInt();

			articulationPoints = 0;
			currTime = 1;
			parent = new int[n];
			visitingTime = new int[n];
			low = new int[n];
			visited = new boolean[n];

			parent[0] = -1;
			createGraph();
			dfs(0);

			int count = 0;
			for (int i = 0; i < n; i++)
				if (parent[i] == 0)
					count++;

			if (count < 2)
				articulationPoints--;

			out.println(articulationPoints * k);
		}
	}

	static void createGraph()
	{
		adj = new List[n];

		for (int i = 0; i < n; i++)	adj[i] = new ArrayList<>();

		for (int i = 0; i < m; i++)
		{
			int from, to;

			from = in.nextInt();
			to = in.nextInt();
			adj[from].add(to);
			adj[to].add(from);
		}
	}

	static void dfs(int node)
	{
		visited[node] = true;
		visitingTime[node] = low[node] = currTime++;

		Iterator<Integer> iterator = adj[node].iterator();
		boolean isArticulationPoint = false;

		while (iterator.hasNext())
		{
			int curr = iterator.next();

			if (!visited[curr])
			{
				parent[curr] = node;
				dfs(curr);
				low[node] = Math.min(low[node], low[curr]);

				if (visitingTime[node] <= low[curr])
					isArticulationPoint = true;
			}
			else if (curr != parent[node])
				low[node] = Math.min(low[node], visitingTime[curr]);
		}

		if (isArticulationPoint)
			articulationPoints++;
	}

}

/*

1
7 6 5
0 1
1 2
3 4
2 4
2 6
5 2
: 15

1
6 5 5
0 1
1 2
2 3
2 4
1 5
: 10

2
7 6 5
0 1
1 2
3 4
2 4
2 6
5 2
6 5 5
0 1
1 2
2 3
2 4
1 5
: 15, 10

1
16 19 1
0 1
1 2
1 3
2 4
2 5
4 5
0 6
6 7
6 8
6 13
8 9
8 12
9 12
9 10
9 11
7 13
0 14
0 15
14 15
: 6

1
2 1 1
0 1
: 0

1
3 2 1
0 1
0 2
: 1

1
3 3 1
0 1
0 2
1 2
: 0

 */
