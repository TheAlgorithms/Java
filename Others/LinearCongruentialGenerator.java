package Others;

/***
 * A pseudorandom number generator.
 *
 * @author Tobias Carryer
 * @date October 10, 2017
 */
public class LinearCongruentialGenerator {

    private double a, c, m, previousValue;

    /***
     * These parameters are saved and used when nextNumber() is called.
     * The current timestamp in milliseconds is used as the seed.
     *
     * @param multiplier
     * @param increment
     * @param modulo The maximum number that can be generated (exclusive). A common value is 2^32.
     */
    public LinearCongruentialGenerator(double multiplier, double increment, double modulo) {
        this(System.currentTimeMillis(), multiplier, increment, modulo);
    }

    /***
     * These parameters are saved and used when nextNumber() is called.
     *
     * @param seed
     * @param multiplier
     * @param increment
     * @param modulo The maximum number that can be generated (exclusive). A common value is 2^32.
     */
    public LinearCongruentialGenerator(double seed, double multiplier, double increment, double modulo) {
        this.previousValue = seed;
        this.a = multiplier;
        this.c = increment;
        this.m = modulo;
    }

    /**
     * The smallest number that can be generated is zero.
     * The largest number that can be generated is modulo-1. modulo is set in the constructor.
     *
     * @return a pseudorandom number.
     */
    public double nextNumber() {
        previousValue = (a * previousValue + c) % m;
        return previousValue;
    }

    public static void main(String[] args) {
        // Show the LCG in action.
        // Decisive proof that the LCG works could be made by adding each number
        // generated to a Set while checking for duplicates.
        LinearCongruentialGenerator lcg = new LinearCongruentialGenerator(1664525, 1013904223, Math.pow(2.0, 32.0));
        for (int i = 0; i < 512; i++) {
            System.out.println(lcg.nextNumber());
        }
    }
}
