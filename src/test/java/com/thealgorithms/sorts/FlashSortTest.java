package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FlashSortTest extends SortingAlgorithmTest {
    private final FlashSort flashSort = new FlashSort();

    public FlashSort getFlashSort() {
        return flashSort;
    }

    @Override
    SortAlgorithm getSortAlgorithm() {
        return getFlashSort();
    }

    @Test
    public void testDefaultConstructor() {
        double defaultRation = 0.45;
        FlashSort sorter = new FlashSort();
        assertEquals(defaultRation, sorter.getClassificationRatio());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9})
    public void testCustomConstructorValidRatio(double ratio) {
        FlashSort sorter = new FlashSort(ratio);
        assertEquals(ratio, sorter.getClassificationRatio());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 1, -0.1, 1.1})
    public void testCustomConstructorInvalidRatio(double ratio) {
        assertThrows(IllegalArgumentException.class, () -> new FlashSort(ratio));
    }

    @TestFactory
    public Collection<DynamicTest> dynamicTestsForSorting() {
        List<DynamicTest> dynamicTests = new ArrayList<>();
        double[] ratios = {0.1, 0.2, 0.5, 0.9};

        for (double ratio : ratios) {
            FlashSort sorter = (FlashSort) getSortAlgorithm();
            sorter.setClassificationRatio(ratio);
            dynamicTests.addAll(createDynamicTestsForRatio(ratio));
        }

        return dynamicTests;
    }

    private Collection<DynamicTest> createDynamicTestsForRatio(double ratio) {
        List<DynamicTest> dynamicTests = new ArrayList<>();
        for (TestMethod testMethod : getTestMethodsFromSuperClass()) {
            dynamicTests.add(DynamicTest.dynamicTest("Ratio: " + ratio + " - Test: " + testMethod.name(), testMethod.executable()));
        }
        return dynamicTests;
    }

    private List<TestMethod> getTestMethodsFromSuperClass() {
        List<TestMethod> testMethods = new ArrayList<>();
        Method[] methods = SortingAlgorithmTest.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(new TestMethod(() -> {
                    try {
                        method.invoke(this);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }, method.getName()));
            }
        }
        return testMethods;
    }

    record TestMethod(Executable executable, String name) {
    }
}
