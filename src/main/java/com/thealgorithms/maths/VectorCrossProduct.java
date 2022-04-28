package com.thealgorithms.maths;

/**
 * @file
 *
 * @brief Calculates the [Cross
 * Product](https://en.wikipedia.org/wiki/Cross_product) and the magnitude of
 * two mathematical 3D vectors.
 *
 *
 * @details Cross Product of two vectors gives a vector. Direction Ratios of a
 * vector are the numeric parts of the given vector. They are the tree parts of
 * the vector which determine the magnitude (value) of the vector. The method of
 * finding a cross product is the same as finding the determinant of an order 3
 * matrix consisting of the first row with unit vectors of magnitude 1, the
 * second row with the direction ratios of the first vector and the third row
 * with the direction ratios of the second vector. The magnitude of a vector is
 * it's value expressed as a number. Let the direction ratios of the first
 * vector, P be: a, b, c Let the direction ratios of the second vector, Q be: x,
 * y, z Therefore the calculation for the cross product can be arranged as:
 *
 * ``` P x Q: 1	1	1 a	b	c x	y	z ```
 *
 * The direction ratios (DR) are calculated as follows: 1st DR, J: (b * z) - (c
 * * y) 2nd DR, A: -((a * z) - (c * x)) 3rd DR, N: (a * y) - (b * x)
 *
 * Therefore, the direction ratios of the cross product are: J, A, N The
 * following Java Program calculates the direction ratios of the cross products
 * of two vector. The program uses a function, cross() for doing so. The
 * direction ratios for the first and the second vector has to be passed one by
 * one separated by a space character.
 *
 * Magnitude of a vector is the square root of the sum of the squares of the
 * direction ratios.
 *
 *
 * For maintaining filename consistency, Vector class has been termed as
 * VectorCrossProduct
 *
 * @author [Syed](https://github.com/roeticvampire)
 */
public class VectorCrossProduct {

    int x;
    int y;
    int z;

    //Default constructor, initialises all three Direction Ratios to 0
    VectorCrossProduct() {
        x = 0;
        y = 0;
        z = 0;
    }

    /**
     * constructor, initialises Vector with given Direction Ratios
     *
     * @param _x set to x
     * @param _y set to y
     * @param _z set to z
     */
    VectorCrossProduct(int _x, int _y, int _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    /**
     * Returns the magnitude of the vector
     *
     * @return double
     */
    double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Returns the dot product of the current vector with a given vector
     *
     * @param b: the second vector
     * @return int: the dot product
     */
    int dotProduct(VectorCrossProduct b) {
        return x * b.x + y * b.y + z * b.z;
    }

    /**
     * Returns the cross product of the current vector with a given vector
     *
     * @param b: the second vector
     * @return vectorCrossProduct: the cross product
     */
    VectorCrossProduct crossProduct(VectorCrossProduct b) {
        VectorCrossProduct product = new VectorCrossProduct();
        product.x = (y * b.z) - (z * b.y);
        product.y = -((x * b.z) - (z * b.x));
        product.z = (x * b.y) - (y * b.x);
        return product;
    }

    /**
     * Display the Vector
     */
    void displayVector() {
        System.out.println("x : " + x + "\ty : " + y + "\tz : " + z);
    }

    public static void main(String[] args) {
        test();
    }

    static void test() {
        //Create two vectors
        VectorCrossProduct A = new VectorCrossProduct(1, -2, 3);
        VectorCrossProduct B = new VectorCrossProduct(2, 0, 3);

        //Determine cross product
        VectorCrossProduct crossProd = A.crossProduct(B);
        crossProd.displayVector();

        //Determine dot product
        int dotProd = A.dotProduct(B);
        System.out.println("Dot Product of A and B: " + dotProd);

    }

}
