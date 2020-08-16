package Maths;

public class AbsoluteValue {

    public static void main(String[] args) {
    	assert absVal(-13) == 13;
    	assert absVal(0) == 0;
    	assert absVal(100) == 100;
    	
        int value = -34;
        System.out.println("The absolute value of " + value + " is " + absVal(value));
    }

    /**
     * If value is less than zero, make value positive.
     *
     * @param value a number
     * @return the absolute value of a number
     */
    public static int absVal(int value) {
        return value < 0 ? -value : value;
    }

}
