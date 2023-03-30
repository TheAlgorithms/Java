import java.util.HashSet;

public class intersection_of_array {
    public static int intersection(int[] a,int[] b){
        HashSet<Integer> set = new HashSet<>();
        int count = 0;
        for (int i=0;i<a.length;i++) {
            set.add(a[i]);
        }

        for (int j=0; j < b.length; j++) {
            if (set.contains(b[j])) {
                count++;
                set.remove(b[j]);
            }
        }
        return count; //give count of common elements
    }
    public static void main(String[] args) {
        int[] nums = {1,2,5,1,3,1,5,1};
        int[] nums1 = {9,5,5,8};
        System.out.println(intersection(nums,nums1));
    }
}
