public class SpiralPattern {
    
    public static void generateSpiralPattern(int n) {
        // Initialize an empty matrix
        int[][] spiralMatrix = new int[n][n];
        
        // Define directions for right, down, left, and up movements
        int[][] directions = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
        int currentDirection = 0;
        
        int row = 0, col = 0;  // Start from the top-left corner
        for (int num = 1; num <= n * n; num++) {
            // Assign the current number to the matrix
            spiralMatrix[row][col] = num;
            
            // Calculate the next position
            int nextRow = row + directions[currentDirection][0];
            int nextCol = col + directions[currentDirection][1];
            
            // Check if we need to change direction
            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || spiralMatrix[nextRow][nextCol] != 0) {
                // Change direction
                currentDirection = (currentDirection + 1) % 4;
                nextRow = row + directions[currentDirection][0];
                nextCol = col + directions[currentDirection][1];
            }
            
            // Move to the next cell
            row = nextRow;
            col = nextCol;
        }
        
        // Print the spiral pattern
        for (int[] rows : spiralMatrix) {
            for (int num : rows) {
                System.out.printf("%02d ", num);  // Format numbers as 2 digits
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int n = 5;  // Set the size of the spiral matrix
        generateSpiralPattern(n);
    }
}
