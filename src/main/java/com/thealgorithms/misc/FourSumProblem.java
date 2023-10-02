import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
class Pair
{
    public int x, y;
 
    Pair(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
 
public class FourSumProblem
{
    // Function to check if quadruplet exists in an array with the given sum
    public static boolean hasQuadruplet(int[] nums, int n, int target)
    {
        // create an empty map
        // key —> target of a pair in the array
        // value —> list storing an index of every pair having that sum
        Map<Integer, List<Pair>> map = new HashMap<>();
 
        // consider each element except the last element
        for (int i = 0; i < n - 1; i++)
        {
            // start from the i'th element until the last element
            for (int j = i + 1; j < n; j++)
            {
                // calculate the remaining sum
                int val = target - (nums[i] + nums[j]);
 
                // if the remaining sum is found on the map,
                // we have found a quadruplet
                if (map.containsKey(val))
                {
                    // check every pair having a sum equal to the remaining sum
                    for (Pair pair: map.get(val))
                    {
                        int x = pair.x;
                        int y = pair.y;
 
                        // if quadruplet doesn't overlap, print it and
                        // return true
                        if ((x != i && x != j) && (y != i && y != j))
                        {
                            System.out.println("Quadruplet Found ("
                                        + nums[i] + ", " + nums[j] + ", "
                                        + nums[x] + ", " + nums[y] + ")");
                            return true;
                        }
                    }
                }
 
                // insert the current pair into the map
 
                // null check (Java 8)
                map.putIfAbsent(nums[i] + nums[j], new ArrayList<>());
                map.get(nums[i] + nums[j]).add(new Pair(i, j));
            }
        }
 
        // return false if quadruplet doesn't exist
        return false;
    }
 
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the target sum ");
        int ts = scan.nextInt();
        System.out.print("Enter the number of elements in the array ");
        int n = scan.nextInt();
        System.out.println("Enter all your array elements:");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        if (!hasQuadruplet(arr, arr.length, ts)) {
            System.out.println("Quadruplet Doesn't Exist");
        }   //else case would be handeled in the method hasQuadruplet
    }
}