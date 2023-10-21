import java.util.ArrayList;
import java.util.List;

public class aAlgorithm implements Comparable<aAlgorithm> {
    private static int idCounter = 0;
    public int id;
    public aAlgorithm parent = null;
    public List<Edge> neighbors;
    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;
    public double h;

    aAlgorithm(double h) {
        this.h = h;
        this.id = idCounter++;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public int compareTo(aAlgorithm n) {
        return Double.compare(this.f, n.f);
    }

    public static class Edge {
        Edge(int weight, aAlgorithm node) {
            this.weight = weight;
            this.node = node;
        }
        public int weight;
        public aAlgorithm node;
    }

    public void addBranch(int weight, aAlgorithm node) {
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }

    public double calculateHeuristic(aAlgorithm target) {
        return this.h;
    }

    public static void main(String[] args) {
        aAlgorithm startNode = new aAlgorithm(0);
        aAlgorithm endNode = new aAlgorithm(10);
    }
}
