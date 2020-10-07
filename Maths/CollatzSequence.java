package Maths;

/**
 * The Collatz conjecture is a conjecture in mathematics that concerns a sequence defined as follows:
 * start with any positive integer n.
 * Then each term is obtained from the previous term as follows:
 * if the previous term is even, the next term is one half of the previous term.
 * If the previous term is odd, the next term is 3 times the previous term plus 1.
 * The conjecture is that no matter what value of n, the sequence will always reach 1.
 * <a href="https://www.wikiwand.com/en/Collatz_conjecture">https://www.wikiwand.com/en/Collatz_conjecture</a>
 */
public class CollatzSequence {
    /**
     * Runs the sequence starting on the value passed in, stopping when the sequence reaches 1.
     * @param start initial value the sequence is run on.
     */
    public static void collatz(int start) {
        while (start != 1) {
            System.out.print(start + " ");
            if (start % 2 == 0) {
                start = start / 2;
            }
            else {
                start = 3 * start + 1;
            }
        }
        System.out.print(start);
        System.out.println();
    }

    /**
     * Driver code
     */
    public static void main(String[] args) {
        collatz(3); // 3, 10, 5, 16, 8, 4, 2, 1
        collatz(6); // 6, 3, 10, 5, 16, 8, 4, 2, 1
    }
}
