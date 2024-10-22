public class Permutation {
    
    // Method to swap characters at position i and j in a character array
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Recursive method to generate permutations
    private static void permute(char[] str, int left, int right) {
        if (left == right) {
            // When left equals right, we have a new permutation
            System.out.println(String.valueOf(str));
        } else {
            for (int i = left; i <= right; i++) {
                // Swap the characters to create a new permutation
                swap(str, left, i);

                // Recursively call permute for the next position
                permute(str, left + 1, right);

                // Backtrack: undo the swap
                swap(str, left, i);
            }
        }
    }

    public static void main(String[] args) {
        String s = "ABC";
        char[] charArray = s.toCharArray();
        int n = charArray.length;

        // Call the recursive method
        permute(charArray, 0, n - 1);
    }
}
