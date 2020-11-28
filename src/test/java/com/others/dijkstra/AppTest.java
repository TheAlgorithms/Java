package dijkstra;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/* this is a simple implementation of dijkstra algorithm that all based on the explanation of the code in wikipedia
* (https://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode). You can just check this pseudo code 
*(https://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode). Please remember that any test you apply to this algorithm 
* should respect all the rules of a Dijkstra algorithm, otherwise it will give you bad results. The given test is also from the 
* same wikpedia page(you will find it if you head directly to the first section of the page).
*/ 

public class AppTest {

    Set<Edge> edges = Stream.of(new Edge("A", "D", 14), new Edge("A", "B", 7), new Edge("A", "C", 9),
    new Edge("D", "F", 9), new Edge("D", "C", 2), new Edge("C", "E", 11), new Edge("E", "F", 6),
    new Edge("B", "C", 10), new Edge("B", "E", 15)).collect(Collectors.toSet());
    Graph graph = new Graph(edges);
    Result actual = graph.dijkstraAlgorithm("A");
    HashMap<String,Float> expectedDistances = new HashMap<String,Float>();
    HashMap<String,String> expectedPreviouses = new HashMap<String,String>();

    @Test
    public void shouldHaveTheSamePreviouses() {

        expectedPreviouses.put("A","");
        expectedPreviouses.put("B","A");
        expectedPreviouses.put("C","A");
        expectedPreviouses.put("D","C");
        expectedPreviouses.put("E","C");
        expectedPreviouses.put("F","D");
        
        assertEquals(expectedPreviouses, actual.previouses);
    }

    @Test
    public void shouldHaveTheSameDistances() {
        
        expectedDistances.put("A",(float) 0);
        expectedDistances.put("B",(float) 7);
        expectedDistances.put("C",(float) 9);
        expectedDistances.put("D",(float) 11);
        expectedDistances.put("E",(float) 20);
        expectedDistances.put("F",(float) 20);
        
        assertEquals(expectedDistances, actual.distances);
    }
}
