package learningspace.swtest.examples;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairRandomizedTest {

    @Test
    public void testRandomizedClosestPair() {
        ClosestPairRandomized.Point[] points = {
            new ClosestPairRandomized.Point(0, 0),
            new ClosestPairRandomized.Point(3, 4),
            new ClosestPairRandomized.Point(1, 1),
            new ClosestPairRandomized.Point(2, 2)
        };

        double result = ClosestPairRandomized.closestPair(points, 10000);
        double expectedMinDist = Math.sqrt(2);  // Entre (1,1) e (2,2)
        assertTrue(result <= expectedMinDist + 0.5, "Distância deve ser próxima ou menor que esperado");
    }

    @Test
    public void testWithIdenticalPoints() {
        ClosestPairRandomized.Point[] points = {
            new ClosestPairRandomized.Point(1, 1),
            new ClosestPairRandomized.Point(1, 1),
            new ClosestPairRandomized.Point(2, 2)
        };

        double result = ClosestPairRandomized.closestPair(points, 1000);
        assertEquals(0.0, result, 0.001);
    }
}
