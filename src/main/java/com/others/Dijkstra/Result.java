package dijkstra;

import java.util.HashMap;

/* this is a simple implementation of dijkstra algorithm that all based on the explanation of the code in wikipedia
* (https://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode). You can just check this pseudo code 
*(https://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode). Please remember that any test you apply to this algorithm 
* should respect all the rules of a Dijkstra algorithm, otherwise it will give you bad results. The given test is also from the 
* same wikpedia page(you will find it if you head directly to the first section of the page).
*/ 

/*
*This class represents the final result of Dijkstra's algorithm. While distances mapped to each vertex the minimum distance from
*the source, the previouses gives the previous vertex to visit, starting from the source, before to get into this one. So basically,
*by reading those two hashmaps you can draw a shortest path covering from the source on the given graph.
*/

public class Result {
    HashMap<String,Float> distances;
    HashMap<String,String> previouses;

    public void setResult(HashMap<String, Float> distances, HashMap<String, String> previouses) {
        this.distances = distances;
        this.previouses = previouses;
    }

    @Override
    public String toString() {
        return "Result [distances=" + distances + ", previouses=" + previouses + "]";
    }
}
