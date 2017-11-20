import java.util.ArrayList;

class UndirecteAdjacencyGraph<E extends Comparable<E>>{
	ArrayList<Vertex> verticies;

    public UndirecteAdjacencyGraph() {
        verticies = new ArrayList<>();
    }
    
	
    /**
     * this method adds two edges to the graph between two specified
     * verticies (from, to) and (to, from)
     *
     * @param from the data of the vertex the edge is from
     * @param to the data of the vertex the edge is going to
     * @return returns true if the edge did not exist, return false if it already did
     */
    public boolean addEdge(E from, E to) {
        Vertex fromV = null, toV = null;
        for (Vertex v: verticies) {
            if (from.compareTo(v.data) == 0) { // see if from vertex already exists
                fromV = v;
            } else if (to.compareTo(v.data) == 0) { // see if to vertex already exists
                toV = v;
            }
            if (fromV != null && toV != null) break; // both nodes exist so stop searching
        }
        if (fromV == null) {
            fromV = new Vertex(from);
            verticies.add(fromV);
        }
        if (toV == null) {
            toV = new Vertex(to);
            verticies.add(toV);
        }
        
        
        boolean addCheckFrom = fromV.addAdjacentVertex(toV);
        boolean addCheckTo = toV.addAdjacentVertex(fromV);
        // UndirectedGraph adds both (A,B) and (B,A) because B is adjacent to A if B is adjacent.
        if(addCheckFrom && addCheckTo) return true;
        else return false;
    }
    
    /**
     * this method removes two edges  from the graph between two specified
     * verticies (from, to) and (to, from)
     *
     * @param from the data of the vertex the edge is from
     * @param to the data of the vertex the edge is going to
     * @return returns false if the edge doesn't exist, returns true if the edge exists and is removed
     */
    public boolean removeEdge(E from, E to) {
        Vertex fromV = null;
        Vertex toV = null;
        
        for (Vertex v: verticies) {
            if (from.compareTo(v.data) == 0) {
                fromV = v;
            }
            if(to.compareTo(v.data) == 0){
            	toV = v;
            }
        }
        

        //UndirectedGraph deletes both from the graph because A is adjacent to B, and B is also adjacent to A.
        if (fromV == null && toV == null) return false;
        else{
        	if(fromV == null && toV != null){
        		return toV.removeAdjacentVertex(from);
        	}
        	else if(fromV != null && toV == null){
        		return fromV.removeAdjacentVertex(to);
        	}
        	else{
        		boolean removeCheck_from = fromV.removeAdjacentVertex(to);
        		boolean removeCheck_to = toV.removeAdjacentVertex(from);
        		
        		if(removeCheck_from && removeCheck_to) return true;
        		else return false;
        	}
        }
    }
    
    /**
     * this gives a list of verticies in the graph and their adjacencies
     * 
     * @return returns a string describing this graph
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v: verticies) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append(" -> ");
            for (Vertex v2: v.adjacentVerticies) {
                sb.append(v2.data);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    private class Vertex {
        E data;
        ArrayList<Vertex> adjacentVerticies;

        public Vertex(E data) {
            adjacentVerticies = new ArrayList<>();
            this.data = data;
        }

        public boolean addAdjacentVertex(Vertex to) {
            for (Vertex v: adjacentVerticies) {
                if (v.data.compareTo(to.data) == 0) {
                    return false; // the edge already exists
                }
            }
            return adjacentVerticies.add(to); // this will return true;
        }

        public boolean removeAdjacentVertex(E to) {
            // use indexes here so it is possible to 
            // remove easily without implementing 
            // equals method that ArrayList.remove(Object o) uses
            for (int i = 0; i < adjacentVerticies.size(); i++) {
                if (adjacentVerticies.get(i).data.compareTo(to) == 0) {
                    adjacentVerticies.remove(i);
                    return true;
                }
            }
            return false;
        }
    }
}
public class UndirectedGraph{
	public static void main(String[] args) {
		UndirecteAdjacencyGraph<Character> dg = new UndirecteAdjacencyGraph<Character>();
		
		dg.addEdge('A', 'B');
		dg.addEdge('B', 'E');
		dg.addEdge('B', 'F');
		dg.addEdge('C', 'E');
		dg.removeEdge('E', 'B');
		
		System.out.println(dg);
	}
}