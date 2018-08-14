package sort;

import static java.util.stream.Collectors.toList;
import static sort.SortUtils.print;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * Created by rajat on 14/08/18.
 */
public class BucketSort{

  public List<Double> sort(List<Double> unsortedValues) {
    Map<Integer, List<Double>> mapOfLists = new TreeMap<>();
    List<Double> sortedValues = new ArrayList<>();

    int listSize = unsortedValues.size();

    for (Double val : unsortedValues) {
      int currBucket = (int)(listSize * val);
      List<Double> currBucketList = mapOfLists.get(currBucket);
      if(null == currBucketList) {
        mapOfLists.put(currBucket,new ArrayList<>());
        currBucketList = mapOfLists.get(currBucket);
      }
      currBucketList.add(val);
    }

    for(Map.Entry<Integer,List<Double>> doubleListEntry: mapOfLists.entrySet()) {
      doubleListEntry.getValue().sort(Comparator.naturalOrder());
      doubleListEntry.getValue().stream().forEach(val -> sortedValues.add(val));
    }

    return sortedValues;
  }

  // Main method
  public static void main(String[] args) {
    // Double Input
    List<Double> unsortedDoubleValues = Stream
        .of(4.2, 23.1, 6.4, 78.2, 1.5, 54.2, 23.4, 1.7, 9.2, 231.9, 9.3, 12.1).collect(toList());
    BucketSort bucketSort = new BucketSort();

    System.out.println("Double values before Sorting:");
    print(unsortedDoubleValues);

    System.out.println("Double values after Sorting:");
    print(bucketSort.sort(unsortedDoubleValues));

  }
}
