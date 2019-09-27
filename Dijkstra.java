    import org.junit.Test;
	import static org.junit.Assert.*;
	
	@test
	private static final Graph.Edge[] GRAPH = 
	{
            new Graph.Edge(" ", " ", " "),
            new Graph.Edge("a", "c", 9),
            new Graph.Edge("a", "f", 14),
    };
	
	@test
	private static final Graph.Edge[] GRAPH = 
	{
            new Graph.Edge("x", "y", 3),
            new Graph.Edge("c", "d", 6),
            new Graph.Edge("z", "t", 9),
    };
	
	@test
	private static final Graph.Edge[] GRAPH = 
	{
            new Graph.Edge("a", " ", 7),
            new Graph.Edge(" ", "d", 1),
            new Graph.Edge("a", "b", 14),
    };