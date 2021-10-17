//max sum subarray problem
public class KadaneAlgorithm {
    public static void main(String[] args){
        int[] arr ={-1,-2,-3,-4};
        int max=0,curr=0,neg=Integer.MIN_VALUE;
        for (int value : arr) {
            curr += value;
            if (curr > max)
                max = curr;
            else if (curr < 0)
                curr = 0;
            if(neg<value)
                neg=value;
        }
        if(neg>0)
            System.out.println(max);
        else
            System.out.println(neg);
    }
}
