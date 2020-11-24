package dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {

    Set<Edge> graph;
    Set<String> vertexes;

    public String closestVertex(String vertex) {
        float distance = Float.MAX_VALUE;
        String minDistantVertex = null;
        for (Edge e : this.graph) {
            if (e.vertexIsInEdge(vertex)) {
                if (distance >= e.distance) {
                    distance = e.distance;
                    minDistantVertex = vertex.equals(e.vertex1) ? e.vertex2 : e.vertex1;
                }
            }
        }
        return Optional.ofNullable(minDistantVertex).orElseThrow(GraphNotConnectedException::new);
    }

    public Set<String> getLinks(String vertex) {
        Set<String> link = new HashSet<String>();
        link = graph.stream().filter(e -> e.getLink().contains(vertex))
                .map(e -> vertex == e.vertex1 ? e.vertex2 : e.vertex1).collect(Collectors.toSet());
        return Optional.ofNullable(link).orElseThrow(GraphNotConnectedException::new);
    }

    public boolean isEdge(String v1, String v2){
        boolean result = false;
        for (Edge e : graph) {
            if(e.vertexIsInEdge(v1)&&e.vertexIsInEdge(v2)){
                result = true;
            }
        }
        return result;
    }

    public Edge getEdge(String v1, String v2){
        Edge result = null;
        for (Edge e : graph) {
            if(e.vertexIsInEdge(v1)&&e.vertexIsInEdge(v2)){
                result = e;
            }
        }
        return Optional.ofNullable(result).orElseThrow(NullPointerException::new);
    }

    public Graph(Set<Edge> graph) {
        this.graph = graph;
        this.vertexes = new HashSet<>();
        this.vertexes.addAll(graph.stream().map(Edge::getVertex1).collect(Collectors.toSet()));
        this.vertexes.addAll(graph.stream().map(Edge::getVertex2).collect(Collectors.toSet()));
    }

    public Result dijkstraAlgorithm(String source) {
        HashMap<String,Float> distances = new HashMap<String,Float>();
        HashMap<String,String> previouses = new HashMap<String,String>();
        Result result = new Result();
        Set<String> bufferes = this.vertexes;
        String buffer = source;
        String u = source;
        boolean x;
        float alt;
        for(String v : bufferes){
            previouses.put(v, "");
            distances.put(v, Float.MAX_VALUE);
        }
        distances.replace(source, (float)0);
        while (!(bufferes.isEmpty())){
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