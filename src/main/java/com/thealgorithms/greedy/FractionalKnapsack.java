import java.util.Arrays;
import java.util.Comparator;

// Class representing an item with a value and weight
class Item {
    int value, weight;

    // Constructor to initialize an item with value and weight
    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {
    // Function to calculate the maximum value achievable in the knapsack
    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items based on value-to-weight ratio (value per unit weight)
        Arrays.sort(items, Comparator.comparingDouble(item -> (double) item.value / item.weight));

        double maxValue = 0.0;

        for (Item item : items) {
            if (capacity >= item.weight) {
                // Take the whole item if it fits in the knapsack
                maxValue += item.value;
                capacity -= item.weight;
            } else {
                // Take a fraction of the item if it doesn't fit entirely
                double fraction = (double) capacity / item.weight;
                maxValue += item.value * fraction;
                break;
            }
        }

        return maxValue;
    }

    public static void main(String[] args) {
        // Sample items with their values and weights
        Item[] items = {new Item(60, 10), new Item(100, 20), new Item(120, 30)};
        int capacity = 50; // Knapsack capacity

        // Calculate the maximum value that can be obtained
        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value that can be obtained: " + maxValue);
    }
}
