import org.junit.Test;
import static org.junit.Assert.*;

public class TSPTest {

    @Test
    public void testTSPWithSmallGraph() {
        int[][] distanceMatrix = {
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 }
        };

        TSP.initialize(distanceMatrix);

        int minCost = TSP.tsp(0, 1);

        assertEquals(80, minCost);
    }

    @Test
    public void testOptimalPath() {
        int[][] distanceMatrix = {
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 }
        };

        TSP.initialize(distanceMatrix);

        TSP.tsp(0, 1); 

        String expectedPath = "0 → 1 → 3 → 2 → 0";
        String actualPath = TSP.printPath(0, 1);

        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void testDifferentGraph() {
        int[][] distanceMatrix = {
            { 0, 29, 20, 21 },
            { 29, 0, 15, 17 },
            { 20, 15, 0, 28 },
            { 21, 17, 28, 0 }
        };

        TSP.initialize(distanceMatrix);

        int minCost = TSP.tsp(0, 1);
        
        assertEquals(75, minCost);

        String expectedPath = "0 → 2 → 1 → 3 → 0";
        String actualPath = TSP.printPath(0, 1);

        assertEquals(expectedPath, actualPath);
    }
}
