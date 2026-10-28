package com.thealgorithms.geometry;
/**
 * This Class implements the Haversine formula to calculate the distance between two points on a sphere (like Earth) from their latitudes and longitudes.
 *
 * The Haversine formula is used in navigation and mapping to find the great-circle distance,
 * which is the shortest distance between two points along the surface of a sphere. It is often
 * used to calculate the "as the crow flies" distance between two geographical locations.
 *
 * The formula is reliable for all distances, including small ones, and avoids issues with
 * numerical instability that can affect other methods.
 *
 * @see "https://en.wikipedia.org/wiki/Haversine_formula" - Wikipedia
 */
public final class Haversine {

    // Average radius of Earth in kilometers
    private static final double EARTH_RADIUS_KM = 6371.0;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Haversine() {
    }

    /**
     * Calculates the great-circle distance between two points on the earth
     * (specified in decimal degrees).
     *
     * @param lat1 Latitude of the first point in decimal degrees.
     * @param lon1 Longitude of the first point in decimal degrees.
     * @param lat2 Latitude of the second point in decimal degrees.
     * @param lon2 Longitude of the second point in decimal degrees.
     * @return The distance between the two points in kilometers.
     */
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        // Convert latitude and longitude from degrees to radians
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);

        // Apply the Haversine formula
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1Rad) * Math.cos(lat2Rad);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
