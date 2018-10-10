import java.util.Scanner;

public class ArrayRotation {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Input first array dimension: ");
		int x = input.nextInt();
		System.out.print("Input second array dimension: ");
		int y = input.nextInt();
		
		if(Integer.min(x,  y) % 2 != 0) {
			System.out.println("Invalid input. Smallest value must be divisible by 2.");
			input.close();
			return;
		}
		
		int[][] arr = new int[x][y];
		
		System.out.println("Input values: ");
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				arr[i][j] = input.nextInt();
			}
		}
		
		System.out.println("Inputed array:");
		for(int[] e : arr) {
			for(int n : e) {
				System.out.print(n + " ");
			}
			System.out.println();
		}
		
		System.out.println("Input number of rotations: ");
		int rotations = input.nextInt();
		
		System.out.println("Rotated Array: ");
		matrixRotation(arr, rotations);
		
		input.close();
	}
	
	
	// Assuming the smaller dimension of matrix is even
	// Rotates counter clockwise
	static void matrixRotation(int[][] matrix, int rotations) {


        int[][] resMatrix = new int[matrix.length][matrix[0].length];
        
        int minVal = matrix.length;
        if(matrix[0].length < minVal) {
            minVal = matrix[0].length;
        }
        
        for(int layer = 0; layer < minVal/2; layer++) {
            
            int perimeter = 2*(matrix.length - 2*layer) + 2*(matrix[0].length - 2) - 4*layer;
            
            int offset = rotations%perimeter;
            
            int[] nums = new int[perimeter];
            int index = offset;
            int[] coords = {layer, layer};
            for(int i = 0; i < perimeter; i++) {
                nums[index%perimeter] = matrix[coords[0]][coords[1]];
                index++;
                
                // Move down
                if(coords[1] == layer && coords[0] != matrix.length - 1 - layer) {
                    coords[0] += 1; 
                }
                // Move right
                else if(coords[0] == matrix.length - 1 - layer && coords[1] != matrix[0].length - 1 - layer) {
                    coords[1] += 1;
                }
                // move up
                else if(coords[1] == matrix[0].length - 1 - layer && coords[0] != layer) {
                    coords[0] -= 1;
                }
                // move left
                else if(coords[0] == layer && coords[1] != layer) {
                    coords[1] -= 1;
                }
                
            }
            
            coords[0] = layer; coords[1] = layer;
            for(int i = 0; i < perimeter; i++) {
                
                resMatrix[coords[0]][coords[1]] = nums[i];
                
                // Move down
                if(coords[1] == layer && coords[0] != matrix.length - 1 - layer) {
                    coords[0] += 1; 
                }
                // Move right
                else if(coords[0] == matrix.length - 1 - layer && coords[1] != matrix[0].length - 1 - layer) {
                    coords[1] += 1;
                }
                // move up
                else if(coords[1] == matrix[0].length - 1 - layer && coords[0] != layer) {
                    coords[0] -= 1;
                }
                // move left
                else if(coords[0] == layer && coords[1] != layer) {
                    coords[1] -= 1;
                }
                
            }
            
            
        } 
        
        
        for(int[] e : resMatrix) {
            for(int e2 : e) {
                System.out.print(e2 + " ");
            }
            System.out.println();
        }
        
        
    }
	
}
