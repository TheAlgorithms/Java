
import java.util.ArrayList;

public class BipartiteChecker {
    static boolean bi;
    static ArrayList<ArrayList<Integer>> adj;
    static int color[];
    static void Bipartite(int start, int paint){
     if (color[start] == -1) {
            color[start] = paint;
            for (Integer node : adj.get(start)) {
             if (paint == 1) {
                    Bipartite(node, 2);
                } else {
                    Bipartite(node, 1);
                }
            }
        } else {
            if ((paint != color[start])) {
                bi = false;
            }
        }
    }

    public static void main(String[] args) {
        int number_of_vertexes, edges, s, d;
        //  Building the graph
        number_of_vertexes = 6;
        edges = 6;
        adj=new ArrayList();
        color= new int[number_of_vertexes];
        for (int i = 0; i < number_of_vertexes; i++){
            adj.add(new ArrayList<Integer>());
            color[i]=-1;
        }
        
        adj.get(0).add(1);
        adj.get(1).add(0);

        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(3).add(4);
        adj.get(4).add(3);

        adj.get(4).add(5);
        adj.get(5).add(4);
        
        adj.get(5).add(3);
        adj.get(3).add(5);

        bi = true;

        for (int i = 0; i < number_of_vertexes; i++) {
            if (color[i] == -1) {
                Bipartite(i, 1);
            }
        }
        if (bi) {
            System.out.println("BIPARTITE.");
        } else {
            System.out.println("NOT BIPARTITE.");
        }
    }

}
