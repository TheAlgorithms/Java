import java.util.ArrayList;
import java.util.Queue;

public class DFS_2{
	final static int infinity = Integer.MAX_VALUE;
	static String NIL = " ";
	static Queue<String> Q;
	static boolean[][] edge;
	static int time;

	public static class Graph {
		String V;
		String color;
		int d;
		int f;
		String pi;

		Graph(String V) {
			this.V = V;
			this.color = "WHITE";
			this.d = 0;
			this.f = 0;
			this.pi = NIL;
		}
	}

	public static void main(String[] args) {
		ArrayList<Graph> G = new ArrayList<>();
		String[] vertexes = { "u", "v", "w", "x", "y", "z" };
		edge = new boolean[vertexes.length][vertexes.length];
		for (int i = 0; i < vertexes.length; i++) {
			G.add(new Graph(vertexes[i]));
		}

		add("u", "v");
		add("u", "x");
		add("v", "y");
		add("w", "y");
		add("w", "z");
		add("x", "v");
		add("y", "x");
		add("z", "z");

		dfs(G);

		print(G);
	}

	private static void print(ArrayList<Graph> G) {
		for (int i = 0; i < G.size(); i++) {
			Graph g = G.get(i);
			System.out.print(g.V + "->");
			System.out.print(g.pi + " : ");
			System.out.printf("%2d/%2d\n", g.d, g.f);
		}
	}

	private static void add(String src, String des) {
		edge[index(src)][index(des)] = true;
	}

	private static int index(String vertex) {
		switch (vertex) {
		case "u":
			return 0;
		case "v":
			return 1;
		case "w":
			return 2;
		case "x":
			return 3;
		case "y":
			return 4;
		case "z":
			return 5;
		default:
			return -1;
		}
	}

	public static void dfs(ArrayList<Graph> G) {
		for (int i = 0; i < G.size(); i++) {
			Graph u = G.get(i);
			u.color = "WHITE";
			u.pi = NIL;
		}
		time = 0;

		for (int i = 0; i < G.size(); i++) {
			Graph u = G.get(i);
			if (u.color == "WHITE") {
				dfs_visit(G, u);
			}
		}

	}

	private static void dfs_visit(ArrayList<Graph> G, Graph u) {
		time = time + 1;
		u.d = time;
		u.color = "GRAY";
		for (int i = 0; i < edge[0].length; i++) {
			if (edge[index(u.V)][i]) {
				Graph v = G.get(i);
				if (G.get(i).color == "WHITE") {
					v.pi = u.V;
					dfs_visit(G, v);
				}
			}
		}
		u.color = "BLACK";
		time = time + 1;
		u.f = time;
	}
}
