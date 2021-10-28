package Misc;
import java.util.*;

/**
 * This class contains the method for returning a list of elements after traversing
 * an input matrix in the clockwise direction. This class also contains a variety of junit tests
 * to test the spiralTraverse method with matrices of different sizes.
 *
 * @author Phillip La (https://github.com/pdsla112)
 */
public class SpiralMatrixTraversal {

    /**
     * Method that implements spiral travers of a generic matrix. Requires an input
     * of a generic matrix of any size. Traverses the matrix in clockwise direction.
     *
     * @author Phillip La (https://github.com/pdsla112)
     * @return List of elements in order of the spiral traverse of the input matrix.
     */
    public static <T> List<T> spiralTraverse(T[][] matrix) {
        List<T> result = new ArrayList<>();

        int startCol = 0;
        int endCol = matrix[0].length - 1;
        int startRow = 0;
        int endRow = matrix.length - 1;
        int count = 0;
        int goal = matrix[0].length * matrix.length;

        while (count < goal) {
            for (int i = startCol; i <= endCol; i++) {
                result.add(matrix[startRow][i]);
                count++;
            }

            for (int i = startRow + 1; i <= endRow; i++) {
                result.add(matrix[i][endCol]);
                count++;
            }

            for (int i = endCol - 1; i >= startCol; i--) {
                if (startRow == endRow) break;
                result.add(matrix[endRow][i]);
                count++;
            }

            for (int i = endRow - 1; i >= startRow + 1; i--) {
                if (startCol == endCol) break;
                result.add(matrix[i][startCol]);
                count++;
            }

            startCol++;
            endCol--;
            startRow++;
            endRow--;
        }

        return result;
    }
}
