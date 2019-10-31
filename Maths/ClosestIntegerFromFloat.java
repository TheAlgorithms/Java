package Maths;

public class ClosestIntegerFromFloat {
	//Driver
    public static void main(String[] args) {
        float f = 5.60f;
        System.out.println("closest integer = " + closestInteger(f));
    }

    /**
     * find the closest integer from a given float
     *
     * @param a float number
     * @return closest integer
     */
	public static int closestInteger(float a) {
		return Math.round(a);
	}
}