package com.thealgorithms.backtracking;

 = List.of(List.of(0, 1, 2),List.of(0, 2));
        List<List<Integer>> list1 = AllPathsFromSourceToTarget.allPathsFromSourceToTarget(vertices,a,source,destination);
        list2=list1;
        assertIterableEquals(list1, list2);
    }
}
