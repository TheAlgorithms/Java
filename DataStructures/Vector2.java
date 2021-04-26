package DataStructures.Vectors;

/**
 * In mathematics and physics, a vector is an element of a vector space. The Vector2-class
 * implements 2-dimensional vectors whose elements are of the type double and implements various
 * vector-operations (description adapted from
 * https://en.wikipedia.org/wiki/Vector_(mathematics_and_physics)).
 */
public class Vector2 {

  double x, y;

  public Vector2(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public static void main(String[] args) {
    // Test toString-method
    assert new Vector2(1, 2.5).toString().equals("(1.000000, 2.500000)");
    assert new Vector2(-5.123, 7).toString().equals("(-5.123000, 7.000000)");

    // Test equalsExactly-method
    assert new Vector2(1, 0).equalsExactly(new Vector2(1, 0));
    assert !new Vector2(1.23, 4.56).equalsExactly(new Vector2(0, 0));

    // Test equalsApproximately-method
    assert new Vector2(1, 0).equalsApproximately(new Vector2(1, 0.0000001), 0.000001);
    assert !new Vector2(1.23, 4.56).equalsApproximately(new Vector2(1.24, 4.56), 0.000001);

    // Test add-method
    assert new Vector2(1, 0)
        .add(new Vector2(0, 1))
        .equalsApproximately(new Vector2(1, 1), 0.000001);
    assert new Vector2(-3.3, -9)
        .add(new Vector2(-2.2, 3))
        .equalsApproximately(new Vector2(-5.5, -6), 0.000001);

    // Test subtract-method
    assert new Vector2(1, 0)
        .subtract(new Vector2(0, 1))
        .equalsApproximately(new Vector2(1, -1), 0.000001);
    assert new Vector2(234.5, 1.7)
        .subtract(new Vector2(3.3, 2.7))
        .equalsApproximately(new Vector2(231.2, -1), 0.000001);

    // Test multiply-method
    assert new Vector2(1, 0).multiply(5).equalsApproximately(new Vector2(5, 0), 0.000001);
    assert new Vector2(3.41, -7.12)
        .multiply(-3.1)
        .equalsApproximately(new Vector2(-10.571, 22.072), 0.000001);

    // Test length-method
    assert new Vector2(1, 0).length() == 1;
    assert new Vector2(-1, 1).length() == Math.sqrt(2);

    // Test normalize-method
    assert new Vector2(1, 0).normalize().equalsApproximately(new Vector2(1, 0), 0.000001);
    assert new Vector2(1, -1)
        .normalize()
        .equalsApproximately(new Vector2(Math.sqrt(2) / 2, -Math.sqrt(2) / 2), 0.000001);

    // Test distance-method
    assert new Vector2(0, 0).distance(new Vector2(0, -1)) == 1;
    assert new Vector2(1, 0).distance(new Vector2(0, 1)) == Math.sqrt(2);

    // Test dotProduct-method
    assert new Vector2(1, 0).dotProduct(new Vector2(0, 1)) == 0;
    assert new Vector2(1, 2).dotProduct(new Vector2(3, 4)) == 1 * 3 + 2 * 4;

    // Test rotate-method
    assert new Vector2(0, -1).rotate(Math.PI / 2).equalsApproximately(new Vector2(1, 0), 0.000001);
    assert new Vector2(1.23, -4.56)
        .rotate(Math.PI)
        .equalsApproximately(new Vector2(-1.23, 4.56), 0.000001);

    // Test angleBetween-method
    assert new Vector2(1, 0).angleBetween(new Vector2(0, 1)) == Math.PI / 2;
    assert new Vector2(1, 0).angleBetween(new Vector2(1, -1)) == -Math.PI / 4;
  }

  @Override
  /**
   * toString-method.
   *
   * @return The string-representation of the vector.
   */
  public String toString() {
    return String.format("(%f, %f)", this.x, this.y);
  }

  /**
   * Check for exact vector equality.
   *
   * @param vector The vector to compare to.
   * @return Whether they are exactly equal or not.
   */
  public boolean equalsExactly(Vector2 vector) {
    return this.x == vector.x && this.y == vector.y;
  }

  /**
   * Check for approximate vector equality.
   *
   * @param vector The vector to compare to.
   * @param epsilon The allowed discrepancy for the x-values and the y-values.
   * @return Whether they are approximately equal or not.
   */
  public boolean equalsApproximately(Vector2 vector, double epsilon) {
    return (Math.abs(this.x - vector.x) < epsilon && Math.abs(this.y - vector.y) < epsilon);
  }

  /**
   * Vector length.
   *
   * @return The length of the vector.
   */
  public double length() {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }

  /**
   * Normalization sets the vector to length 1 while maintaining its direction.
   *
   * @return The normalized vector.
   */
  public Vector2 normalize() {
    double length = this.length();
    if (length == 0) {
      throw new ArithmeticException("Cannot normalize vectors of length 0");
    }
    return new Vector2(this.x / length, this.y / length);
  }

  /**
   * Vector addition
   *
   * @param vector The vector to be added.
   * @return The sum-vector.
   */
  public Vector2 add(Vector2 vector) {
    double x = this.x + vector.x;
    double y = this.y + vector.y;
    return new Vector2(x, y);
  }

  /**
   * Vector subtraction
   *
   * @param vector The vector to be subtracted.
   * @return The difference-vector.
   */
  public Vector2 subtract(Vector2 vector) {
    double x = this.x - vector.x;
    double y = this.y - vector.y;
    return new Vector2(x, y);
  }

  /**
   * Vector scalar multiplication
   *
   * @param scalar The factor by which to multiply the vector.
   * @return The scaled vector.
   */
  public Vector2 multiply(double scalar) {
    double x = this.x * scalar;
    double y = this.y * scalar;
    return new Vector2(x, y);
  }

  /**
   * Distance between this vector and another vector.
   *
   * @param vector The vector to which to calculate the distance.
   * @return The distance.
   */
  public double distance(Vector2 vector) {
    Vector2 difference = vector.subtract(this);
    return difference.length();
  }

  /**
   * Vector dot product
   *
   * @param vector The vector used for the multiplication.
   * @return The resulting dot product.
   */
  public double dotProduct(Vector2 vector) {
    return this.x * vector.x + this.y * vector.y;
  }

  /**
   * Vector rotation (see https://en.wikipedia.org/wiki/Rotation_matrix)
   *
   * @param angleInRadians The angle in radians by which to rotate the vector.
   * @return The rotated vector.
   */
  public Vector2 rotate(double angleInRadians) {
    double ca = Math.cos(angleInRadians);
    double sa = Math.sin(angleInRadians);
    double x = ca * this.x - sa * this.y;
    double y = sa * this.x + ca * this.y;
    return new Vector2(x, y);
  }

  /**
   * Measure angle between two vectors
   *
   * @param vector The 2nd vector for the measurement.
   * @return The angle in radians.
   */
  public double angleBetween(Vector2 vector) {
    return Math.atan2(vector.y, vector.x) - Math.atan2(this.y, this.x);
  }
}
