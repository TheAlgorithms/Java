package divideconquer;
/*
* @author pramodkuth
 */
public class FindMinMax {
    public static int[] minMax(int[] num,int l,int r){
        if(l==r){int[] mm={num[l],num[l]};return mm;}
        int m=l+(r-l)/2;
        int[] lmm=minMax(num,l,m);
        int[] rmm=minMax(num,m+1,r);
        int min=Math.min(lmm[0],rmm[0]);
        int max=Math.max(lmm[1],rmm[1]);
        int[] mm={min,max};
        return mm;
    }
    public static void main(String[] args) {
        int[] nums={1,3,2,5,1,2,7,1};
        int[] r=minMax(nums,0,nums.length-1);
        System.out.println("Min: "+r[0]+", Max: "+r[1]);
    }
}

