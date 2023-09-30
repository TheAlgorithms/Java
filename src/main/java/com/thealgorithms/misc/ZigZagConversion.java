import java.util.Scanner;

public class ZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            rows[currentRow].append(c);
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }
            currentRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row.toString());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the input string: ");
        String s = scanner.nextLine();

        System.out.print("Enter the number of rows: ");
        int numRows = scanner.nextInt();

        scanner.close();

        ZigzagConversion solution = new ZigzagConversion();
        String result = solution.convert(s, numRows);
        System.out.println("Output: " + result);
    }
}
