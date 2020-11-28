package dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/* this is a simple implementation of dijkstra algorithm that all based on the explanation of the code in wikipedia
* (https://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode). You can just check this pseudo code 
*(https://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode). Please remember that any test you apply to this algorithm 
* should respect all the rules of a Dijkstra algorithm, otherwise it will give you bad results. The given test is also from the 
* same wikpedia page(you will find it if you head directly to the first section of the page).
*/ 

public class Graph {
/*   
    *This class containe the algorithm function. It returns an object of type Result. The attribut graph represent a set of all the graph's edges, while vertexes
    *represent a set all of its vertexes. 
    *
    *
    *
*/ 

    Set<Edge> graph;
    Set<String> vertexes;

    public Graph(Set<Edge> graph) {
        this.graph = graph;
        this.vertexes = new HashSet<>();
        this.vertexes.addAll(graph.stream().map(Edge::getVertex1).collect(Collectors.toSet()));
        this.vertexes.addAll(graph.stream().map(Edge::getVertex2).collect(Collectors.toSet()));
    }

    // This function returns all the vertex's adjacents.
    public Set<String> getLinks(String vertex) {
        Set<String> link = new HashSet<String>();
        link = graph.stream().filter(e -> e.getLink().contains(vertex))
                .map(e -> vertex == e.vertex1 ? e.vertex2 : e.vertex1).collect(Collectors.toSet());
        return Optional.ofNullable(link).orElseThrow(GraphNotConnectedException::new);
    }

    // This method checks if two vertexes are related and return one edge between them.
    public Edge getEdge(String v1, String v2){
        Edge result = null;
        for (Edge e : graph) {
            if(e.vertexIsInEdge(v1)&&e.vertexIsInEdge(v2)){
                result = e;
            }
        }
        return Optional.ofNullable(result).orElseThrow(GraphNotConnectedException::new);
    }

    //This is the dijkstra algorithm funtion that returns the shortest path to all vertexes of a graph
    //(respecting the algorithm conditions) from a given source.
    public Result dijkstraAlgorithm(String source) {
        //distances is a hashmap that gives for each vertex the minimum distance from the source
        HashMap<String,Float> distances = new HashMap<String,Float>();
        // previouses is a hashmap that gives for each vertex the previous vertex to visit before coming from the source
        HashMap<String,String> previouses = new HashMap<String,String>();
        Result result = new Result();
        // bufferes is a set of all unvisited vertexes
        Set<String> bufferes = this.vertexes;
        String buffer = source;
        String u = source;
        boolean x;
        float alt;

        // this for boucle initialize previouses and distances. Only the source by the end of the execution keeps the ""
        for(String v : bufferes){
            previouses.put(v, "");
            distances.put(v, Float.MAX_VALUE);
        }
        distances.replace(source, (float)0);

        // the while condition meanes that there is more unvisited vertexes.
        while (!(bufferes.isEmpty())){
            // the code below is the equivalent of this pseudo code from wikipedia:
            //[u ← vertex in Q with min dist[u]
            //remove u from Q]
            x = true;
            for (String v : bufferes) {
                if(x){
                    u = v;
                    x = false;
                }
                if(distances.get(v) <= distances.get(u)){
                    u = v;
                }
            }

            bufferes.remove(buffer);
            buffer = u;

            /*
            16          for each neighbor v of u:           // only v that are still in Q
            17              alt ← dist[u] + length(u, v)
            18              if alt < dist[v]:              
            19                  dist[v] ← alt
            20                  prev[v] ← u
            21
            22      return dist[], prev[]
            This is also the traduction of the pseudo code above in java:
            */
            for (String v : this.getLinks(buffer)) {
                if(bufferes.contains(v)){
                    alt = distances.get(buffer) + this.getEdge(v, buffer).distance;
                    if(alt<distances.get(v)){
                        distances.replace(v, alt);
                        previouses.replace(v, buffer);
                    }
                }
            }
        }
        result.setResult(distances, previouses);
        return Optional.ofNullable(result).orElseThrow(GraphNotConnectedException::new);
    }
}