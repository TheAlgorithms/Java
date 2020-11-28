package dijkstra;

/* this is a simple implementation of dijkstra algorithm that all based on the explanation of the code in wikipedia
* (https://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode). You can just check this pseudo code 
*(https://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode). Please remember that any test you apply to this algorithm 
* should respect all the rules of a Dijkstra algorithm, otherwise it will give you bad results. The given test is also from the 
* same wikpedia page(you will find it if you head directly to the first section of the page).
* This is the only exception that this implementation checks. You can add more exceptions or simply costumize this one.
*/ 

public class GraphNotConnectedException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public GraphNotConnectedException() {
        super("This the given graph is not connected please check it again.");
    }
    
    
}
