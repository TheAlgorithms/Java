//Java program to print all possible substrings of a given string
class AllSubstrings {

    static void printAllSubstrings(char str[], int n) {
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                // Print characters from current
                // starting point to current ending
                // point.
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {
                    System.out.print(str[k]);
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        char str[] = {'a', 'b', 'c'};
        printAllSubstrings(str, str.length);
    }
}