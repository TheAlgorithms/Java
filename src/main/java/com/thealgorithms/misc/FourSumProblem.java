import java.util.*;

public class FourSumProblem {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int target = in.nextInt(),size = in.nextInt();
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) {
      arr[i] = in.nextInt();
    }
    System.out.println("Naive complete search solution takes time O(n^4)\n" + Naive(arr, target) + "\n");
    System.out.println("Hashing solution takes time O(n^2)\n" + Hashing(arr,target));
    in.close();
  }

/*
The Naive solution iterates over every 4 elements in the array.
It checks if their sum is equal to the target sum, it adds them to the answer list.
Sorting each four elements before adding them to the answer, ensures adding only distinct four elements, so no duplicates are found.
Finally it takes time O(n^4) duo to 4 nested loops each iterates n time.
*/
  public static List<List<Integer>> Naive(int[] arr, int target) {
    List<List<Integer>> answer = new ArrayList<List<Integer>>();

    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        for (int k = j + 1; k < arr.length; k++) {
          for (int m = k + 1; m < arr.length; m++) {
            if (arr[i] + arr[j] + arr[k] + arr[m] == target) {
              List<Integer> result = new ArrayList<>();
              result.add(arr[i]);
              result.add(arr[j]);
              result.add(arr[k]);
              result.add(arr[m]);
              Collections.sort(result);
              answer.add(result);
            }
          }
        }
      }
    }
    return answer;
  }


/*
In the hashing solution, we maintain a hashmap of the sum of each pair of elements in the array.
We then iterate over each pair of elements in the array and check if the reuired sum to be equal to target is found in the map previously.
The map now acts as a frequency table. However, we also need to check that the indices of all four elements are distinct, that's why we maintain a pair of indices in the map itself and compare it with the pair of elements we are at right now.
*/
  public static List<List<Integer>> Hashing(int[] arr, int target) {
    Set<List<Integer>> set = new HashSet();
    HashMap<Integer, List<Integer>> map = new HashMap<>();

    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
      	List<Integer> idx = new ArrayList<>();
      	idx.add(i);
      	idx.add(j);
        map.put(arr[i] + arr[j], idx);
      }
    }

    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        int t = target - (arr[i] + arr[j]);
        if (map.containsKey(t) && map.get(t).get(0) != j && map.get(t).get(1) != j && map.get(t).get(0) != i && map.get(t).get(1) != i) {
          List<Integer> sum = new ArrayList<>();
          sum.add(arr[i]);
          sum.add(arr[j]);
          sum.add(arr[map.get(t).get(0)]);
          sum.add(arr[map.get(t).get(1)]);
          Collections.sort(sum);
          set.add(sum);
        }
      }
    }
    return new ArrayList(set);
  }
}
