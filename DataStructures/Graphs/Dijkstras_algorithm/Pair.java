import java.util.Comparator;

//A class which stores vertices along with weights
public class Pair implements Comparator<Pair> {
	private int node;
	private long dist;
	//Public constructor
	public Pair() {}
	//Parameterized public constructor
	public Pair(int node, long dist) {
		this.node = node;
		this.dist = dist;
	}

	//Getters
	public int getNode() {
		return node;
	}
	public long getDist() {
		return dist;
	}

	//Custom comparator for constructing min priority queue
	@Override
	public int compare(Pair p1, Pair p2) {
		if(p1.dist > p2.dist) return 1;
		return -1;
	}
}
