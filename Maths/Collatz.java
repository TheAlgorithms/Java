public class Collatz {
    public static void main(String[] args) {
        System.out.println(getCollatzSequence(5));
    }

    public static String getCollatzSequence(int number) {
	    if(number <= 0) {
            return "Error";
        } 
        String res = "";
        int count = 0;
        while(number != 4) {
            count ++;
            res += number + " ";
            if(number % 2 == 0) {
                number = number / 2;
            } else {
                number = (number * 3) + 1;
            }
            if(count >= 97 && number != 8) {
                return "Does not Converge";
            }
        }
        res = res + "4 2 1";
        return res;
   }
}
