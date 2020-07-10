package Strings;

public class ZFunction {
    private String s;

    public ZFunction(String s){
        this.s = s;
    }

    public int[] getArray(){
        int length = s.length();
        int[] z = new int[length];
        int l = 0, r = 0;
        for (int i=0; i<length; i++){
            if (i > l && i <= r){
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < length && s.charAt(z[i]) == s.charAt(i + z[i])){
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
