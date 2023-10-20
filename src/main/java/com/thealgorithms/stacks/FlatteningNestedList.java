package com.thealgorithms.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;

public class FlatteningNestedList {
    // This function takes a nested list and returns a flattened list.
    public static List<Object> flattenList(List<Object> nestedList) {
        // Create a list to store the flattened elements.
        List<Object> flatList = new ArrayList<>();

        // Create a stack to process elements.
        Deque<Object> stack = new ArrayDeque<>();

        // Push all elements of the input nested list onto the stack.
        for (Object item : nestedList) {
            stack.push(item);
        }

        // Process elements on the stack until it's empty.
        while (!stack.isEmpty()) {
            // Pop the top element from the stack.
            Object current = stack.pop();

            // If the current element is a list, we further process it.
            if (current instanceof List) {
                // Cast it to a list and process its elements.
                @SuppressWarnings("unchecked")
                List<Object> sublist = (List<Object>) current;

                // Push the elements of the sublist onto the stack for processing.
                for (Object subItem : sublist) {
                    stack.push(subItem);
                }
            } else {
                // If the current element is not a list, add it to the flatList.
                flatList.add(current);
            }
        }

        // Return the flattened list.
        return flatList;
    }
}
