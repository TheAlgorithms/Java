// Initialisation of strings algorithm Z function
// detailed review and proper examples can be seen on the link bewlow
//
// Link of algorithm review: https://www.geeksforgeeks.org/z-algorithm-linear-time-pattern-searching-algorithm/
package Strings;

public class ZFunction {
    private String string;

    public ZFunction(String string){
        this.string = string;
    }

    public int[] getArray(){
        int length = string.length();
        int[] z = new int[length];
        int l = 0, r = 0;
        for (int i=0; i<length; i++){
            if (i > l && i <= r){
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < length && string.charAt(z[i]) == string.charAt(i + z[i])){
                z[i]++;
            }
            if (i + z[i] > r){
                l = i;
                r = i + z[i] - 1;
            }
        }

        return z;
    }
}
