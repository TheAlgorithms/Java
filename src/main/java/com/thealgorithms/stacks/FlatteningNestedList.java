package com.thealgorithms.stacks;
import java.util.ArrayList;
import java.util.List;

public class FlatteningNestedList {
    public static List<Object> flattenList(List<Object> nestedList) {
        List<Object> flatList = new ArrayList<>();
        for (Object item : nestedList) {
            if (item instanceof List) {
                flatList.addAll(flattenList((List<Object>) item));
            } else {
                flatList.add(item);
            }
        }
        return flatList;
    }
}