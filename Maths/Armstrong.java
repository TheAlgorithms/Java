
/**
 * An Armstrong number is equal to the sum of the cubes of its digits.
 * For example, 370 is an Armstrong number because 3*3*3 + 7*7*7 + 0*0*0 = 370.
 * An Armstrong number is often called Narcissistic number.
 */
public class Armstrong 
{

    public static void main(String[] args) 
    {
        assert (isArmStrong(0));
        assert (isArmStrong(1));
        assert (isArmStrong(153));
        assert (!isArmStrong(1634));
        assert (isArmStrong(371));
        assert (!isArmStrong(200));
    }

    /**
     * Checks whether a given number is an armstrong number or not.
     *
     * @param number number to check
     * @return {@code true} if given number is armstrong number, {@code false} otherwise
     */
    private static boolean isArmStrong(int number) 
    {
        int sum = 0 ,a,temp; 
        temp=number;  
        while(number>0)  
        {  
            a=number%10;  
            number/=10;  
            sum+=(a*a*a);  
        }  
        if(temp==sum)  
            return true;
        else
            return false;
    }
}
