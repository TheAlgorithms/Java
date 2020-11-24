package dijkstra;

public class GraphNotConnectedException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public GraphNotConnectedException() {
        super("This the given graph is not connected please check it again.");
    }
    
    
}
