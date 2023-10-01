import java.util.Arrays;
import java.util.Comparator;

class Item {
    int value, weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {
    public static double getMaxValue(Item[] items, int capacity) {
        Arrays.sort(items, Comparator.comparingDouble(item -> (double) item.value / item.weight));

        double maxValue = 0.0;

        for (Item item : items) {
            if (capacity >= item.weight) {
                maxValue += item.value;
                capacity -= item.weight;
            } else {
                double fraction = (double) capacity / item.weight;
                maxValue += item.value * fraction;
                break;
            }
        }

        return maxValue;
    }

    public static void main(String[] args) {
        Item[] items = {new Item(60, 10), new Item(100, 20), new Item(120, 30)};
        int capacity = 50;

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value that can be obtained: " + maxValue);
    }
}
