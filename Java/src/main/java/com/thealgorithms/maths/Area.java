/**
* Calculate the surface area of a pyramid with a square base.
*
* @param sideLength side length of the square base
* @param slantHeight slant height of the pyramid
* @return surface area of the given pyramid
*/
public static double surfaceAreaPyramid(final double sideLength, final double slantHeight) {
if (sideLength <= 0) {
throw new IllegalArgumentException("Must be a positive sideLength");
}
if (slantHeight <= 0) {
throw new IllegalArgumentException("Must be a positive slantHeight");
}
double baseArea = sideLength * sideLength;
double lateralSurfaceArea = 2 * sideLength * slantHeight;
return baseArea + lateralSurfaceArea;

}