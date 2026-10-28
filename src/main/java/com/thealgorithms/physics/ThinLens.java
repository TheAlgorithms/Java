package com.thealgorithms.physics;

/**
 * Implements the Thin Lens Formula used in ray optics:
 *
 * <pre>
 *     1/f = 1/v + 1/u
 * </pre>
 *
 * where:
 * <ul>
 *   <li>f = focal length</li>
 *   <li>u = object distance</li>
 *   <li>v = image distance</li>
 * </ul>
 *
 * Uses the Cartesian sign convention.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Thin_lens">Thin Lens</a>
 */
public final class ThinLens {

    private ThinLens() {
        throw new AssertionError("No instances.");
    }

    /**
     * Computes the image distance using the thin lens formula.
     *
     * @param focalLength focal length of the lens (f)
     * @param objectDistance object distance (u)
     * @return image distance (v)
     * @throws IllegalArgumentException if focal length or object distance is zero
     */
    public static double imageDistance(double focalLength, double objectDistance) {

        if (focalLength == 0 || objectDistance == 0) {
            throw new IllegalArgumentException("Focal length and object distance must be non-zero.");
        }

        return 1.0 / ((1.0 / focalLength) - (1.0 / objectDistance));
    }

    /**
     * Computes magnification of the image.
     *
     * <pre>
     *     m = v / u
     * </pre>
     *
     * @param imageDistance image distance (v)
     * @param objectDistance object distance (u)
     * @return magnification
     * @throws IllegalArgumentException if object distance is zero
     */
    public static double magnification(double imageDistance, double objectDistance) {

        if (objectDistance == 0) {
            throw new IllegalArgumentException("Object distance must be non-zero.");
        }

        return imageDistance / objectDistance;
    }

    /**
     * Determines whether the image formed is real or virtual.
     *
     * @param imageDistance image distance (v)
     * @return {@code true} if image is real, {@code false} if virtual
     */
    public static boolean isRealImage(double imageDistance) {
        return imageDistance > 0;
    }
}
