# Rotating Calipers Algorithm

## Overview

The Rotating Calipers algorithm is a computational geometry technique used to efficiently compute various properties of convex polygons. This implementation provides methods to calculate:

- **Diameter**: The largest distance between any two points of a convex polygon
- **Width**: The smallest distance between two parallel lines enclosing the polygon  
- **Minimum-area bounding rectangle**: The rectangle with minimal area that encloses all points

## Time Complexity

O(n) where n is the number of vertices in the convex polygon

## Usage

```java
import com.thealgorithms.geometry.RotatingCalipers;
import com.thealgorithms.geometry.Point;
import java.util.Arrays;
import java.util.List;

// Create a convex polygon (triangle)
List<Point> triangle = Arrays.asList(
    new Point(0, 0),
    new Point(4, 0), 
    new Point(2, 3)
);

// Calculate diameter
RotatingCalipers.PointPair diameter = RotatingCalipers.diameter(triangle);
System.out.println("Diameter: " + diameter.distance);

// Calculate width (requires 3+ points)
double width = RotatingCalipers.width(triangle);
System.out.println("Width: " + width);

// Calculate minimum bounding rectangle (requires 3+ points)
RotatingCalipers.Rectangle rect = RotatingCalipers.minimumBoundingRectangle(triangle);
System.out.println("Minimum area: " + rect.area);
```

## Key Features

- **Static and final class**: Follows utility class pattern
- **Comprehensive JavaDoc**: Full documentation for all methods
- **Input validation**: Proper error handling for invalid inputs
- **Immutable data structures**: PointPair and Rectangle classes are immutable
- **Educational focus**: Clear, readable implementation suitable for learning

## Algorithm Details

The rotating calipers technique works by:

1. Starting with a pair of parallel lines (calipers) touching the convex polygon
2. Rotating the calipers around the polygon while maintaining contact
3. Computing the desired property at each rotation step
4. Returning the optimal result found during the rotation

## References

- Shamos, M. I. (1978). Computational Geometry
- [Wikipedia: Rotating Calipers](https://en.wikipedia.org/wiki/Rotating_calipers)

## Files Added

- `src/main/java/com/thealgorithms/geometry/RotatingCalipers.java` - Main algorithm implementation
- `src/test/java/com/thealgorithms/geometry/RotatingCalipersTest.java` - Comprehensive unit tests
- `src/main/java/com/thealgorithms/geometry/RotatingCalipersDemo.java` - Demonstration class

## Testing

The implementation includes comprehensive JUnit 5 tests covering:
- Simple geometric shapes (triangles, squares, hexagons)
- Edge cases (minimum point requirements)
- Input validation
- String representations of result objects

Run the demo class to see the algorithm in action:
```bash
java com.thealgorithms.geometry.RotatingCalipersDemo
```