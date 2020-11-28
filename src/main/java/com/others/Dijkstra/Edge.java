package dijkstra;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/* this is a simple implementation of dijkstra algorithm that all based on the explanation of the code in wikipedia
* (https://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode). You can just check this pseudo code 
*(https://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode). Please remember that any test you apply to this algorithm 
* should respect all the rules of a Dijkstra algorithm, otherwise it will give you bad results. The given test is also from the 
* same wikpedia page(you will find it if you head directly to the first section of the page).
*/ 

public class Edge {
    //This class represent an implementation of graph's edge as two vertexes and distance between them.

    String vertex1;
    String vertex2;
    float distance;

    public Edge(String vertex1, String vertex2, float distance) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.distance = distance;
    }

    public String getVertex1() {
        return vertex1;
    }

    public String getVertex2() {
        return vertex2;
    }

    //This method returns a set of two vertexes that are connected by an edge. If they aren't connected.
    public Set<String> getLink() {
        Set<String> link = new HashSet<String>();
        link.add(this.vertex1);
        link.add(this.vertex2);
        return Optional.ofNullable(link).orElseThrow(GraphNotConnectedException::new);
    }

    //This method checks if a vertex is connected to a certain edge. If so, it returns true.
    public boolean vertexIsInEdge(String vertex){
        boolean result = false;
        if(vertex.equals(this.vertex1) || vertex.equals(this.vertex2)){
            result = true;
        }
        return result;
    }
}
