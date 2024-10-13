import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SparseTest {

    @Test
    public void testSparsity() {
        // Test case 1: 2x2 matrix with 2 zeroes
        int[][] mat1 = {
            {0, 1},
            {2, 0}
        };
        int zeroCount1 = 2;
        double expectedSparsity1 = 0.5;
        assertEquals(expectedSparsity1, Sparse.sparsity(mat1, 2, 2, zeroCount1), 0.0001);

        // Test case 2: 3x3 matrix with 5 zeroes
        int[][] mat2 = {
            {0, 1, 0},
            {2, 0, 3},
            {0, 0, 0}
        };
        int zeroCount2 = 5;
        double expectedSparsity2 = 5.0 / 9.0;
        assertEquals(expectedSparsity2, Sparse.sparsity(mat2, 3, 3, zeroCount2), 0.0001);

        // Test case 3: 1x1 matrix with no zeroes
        int[][] mat3 = {
            {5}
        };
        int zeroCount3 = 0;
        double expectedSparsity3 = 0.0;
        assertEquals(expectedSparsity3, Sparse.sparsity(mat3, 1, 1, zeroCount3), 0.0001);
    }
}
