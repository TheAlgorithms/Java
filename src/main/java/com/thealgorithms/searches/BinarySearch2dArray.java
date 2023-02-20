package com.thealgorithms.searches;

/*
To apply this method, the provided array must be strictly sorted. In this method, two pointers, one at 0th row
& the other at the last row are taken & the searching is done on the basis of the middle element of the middle column.
If that element is equal to target, its coordinates are returned, else if it is smaller than the target, the rows above
that element are ignored (because the elements above it will also be smaller than the target), else that element is
greater than the target, then the rows below it are ignored.
 */
public class BinarySearch2dArray {

    static int[] BinarySearch(int[][] arr, int target) {
        int rowCount = arr.length, colCount = arr[0].length;

        int startRow = 0, endRow = rowCount - 1, midCol = colCount / 2;

        int[] innerSearchResult = searchInnerArrays(arr, startRow, endRow, midCol,target);
        if (innerSearchResult[0] != -1){
            return new int[] {innerSearchResult[0], innerSearchResult[1]};
        }
        midCol = innerSearchResult[1];
        startRow = innerSearchResult[2];
        endRow = innerSearchResult[3];

        /*
            if the above search fails to find the target element, this method will be used to find the target
            element, which further uses the binary search algorithm in the places which were left unexplored.
             */
        return checkEdgeCases(arr,startRow,endRow, midCol, target, colCount, rowCount);
    }

    static int[] searchInnerArrays(int[][] arr, int startRow, int endRow, int midCol, int target){
        while (startRow < endRow - 1) {
            int midRow = startRow + (endRow - startRow) / 2; //getting the index of middle row

            if (arr[midRow][midCol] == target) {
                return new int[] { midRow, midCol, startRow, endRow};
            } else if (arr[midRow][midCol] < target) startRow =
                    midRow; else endRow = midRow;
        }
        return new int[]{-1, midCol, startRow, endRow};
    }
    static int[] checkEdgeCases(int[][] arr, int startRow, int endRow, int midCol, int target, int colCount, int rowCount){

        if (rowCount == 1) {
            return binarySearch(arr, target, 0, 0, colCount);
        }

        if (arr[startRow][midCol] == target) return new int[] {
                startRow,
                midCol,
        };

        if (arr[endRow][midCol] == target) return new int[] { endRow, midCol };

        if (target <= arr[startRow][midCol - 1]) return binarySearch(
                arr,
                target,
                startRow,
                0,
                midCol - 1
        );

        if (
                target >= arr[startRow][midCol + 1] &&
                        target <= arr[startRow][colCount - 1]
        ) return binarySearch(arr, target, startRow, midCol + 1, colCount - 1);

        if (target <= arr[endRow][midCol - 1]) return binarySearch(
                arr,
                target,
                endRow,
                0,
                midCol - 1
        ); else return binarySearch(
                arr,
                target,
                endRow,
                midCol + 1,
                colCount - 1
        );

    }

    static int[] binarySearch(
        int[][] arr,
        int target,
        int row,
        int colStart,
        int colEnd
    ) {
        while (colStart <= colEnd) {
            int midIndex = colStart + (colEnd - colStart) / 2;

            if (arr[row][midIndex] == target) return new int[] {
                row,
                midIndex,
            }; else if (arr[row][midIndex] < target) colStart =
                midIndex + 1; else colEnd = midIndex - 1;
        }

        return new int[] { -1, -1 };
    }
}
