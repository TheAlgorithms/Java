import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_2 {
	final static int infinity = Integer.MAX_VALUE;
	static String NIL = " ";
	static ArrayList<Graph> G;
	static Queue<String> Q;
	static boolean[][] edge;

	public static class Graph {
		String color;
		int d;
		String pi;
		String V;

		Graph(String V) {
			this.V = V;
			this.color = "WHITE";
			this.d = infinity;
			this.pi = NIL;
		}
	}

	public static void main(String[] args) {
		G = new ArrayList<>();
		String[] vertexes = { "r", "s", "t", "u", "v", "w", "x", "y" };
		edge = new boolean[vertexes.length][vertexes.length];
		for (int i = 0; i < vertexes.length; i++) {
			G.add(new Graph(vertexes[i]));
		}

		add("r", "s");
		add("r", "v");
		add("s", "w");
		add("t", "u");
		add("t", "w");
		add("t", "x");
		add("u", "x");
		add("u", "y");
		add("w", "x");
		add("x", "y");

		String s = "s";

		bfs(G, s);

		print(G);
	}

	private static void add(String src, String des) {
		edge[index(src)][index(des)] = true;
		edge[index(des)][index(src)] = true;
	}

	private static String vertex(int index) {
		switch (index) {
		case 0:
			return "r";
		case 1:
			return "s";
		case 2:
			return "t";
		case 3:
			return "u";
		case 4:
			return "v";
		case 5:
			return "w";
		case 6:
			return "x";
		case 7:
			return "y";
		default:
			return "";
		}
	}

	private static int index(String vertex) {
		switch (vertex) {
		case "r":
			return 0;
		case "s":
			return 1;
		case "t":
			return 2;
		case "u":
			return 3;
		case "v":
			return 4;
		case "w":
			return 5;
		case "x":
			return 6;
		case "y":
			return 7;
		default:
			return -1;
		}
	}

	private static void print(ArrayList<Graph> G) {
		for (int i = 0; i < G.size(); i++) {
			System.out.print(G.get(i).V + "->");
			System.out.print(G.get(i).pi + " : ");
			System.out.println(G.get(i).d);
		}
	}


	public static void bfs(ArrayList<Graph> G, String s) {
		for (int i = 0; i < G.size(); i++) {
			G.get(i).color = "WHITE";
			G.get(i).d = infinity;
			G.get(i).pi = NIL;
		}
		G.get(index(s)).color = "GRAY";
		G.get(index(s)).d = 0;
		G.get(index(s)).pi = NIL;

		Q = new LinkedList<String>();
		enqueue(Q, s);
		while (Q.size() != 0) {
			String u = dequeue(Q);

			for (int i = 0; i < edge[0].length; i++) {
				if (edge[index(u)][i]) {
					if (G.get(i).color == "WHITE") {
						G.get(i).color = "GRAY";
						G.get(i).d = G.get(index(u)).d + 1;
						G.get(i).pi = u;
						enqueue(Q, vertex(i));
					}
				}
			}
			G.get(index(u)).color = "BLACK";
		}
	}

	private static String dequeue(Queue<String> Q) {
		return Q.poll();
	}

	private static void enqueue(Queue<String> Q, String s) {
		Q.add(s);
	}
}
