package com.thealgorithms.reflection;

/**
 * Class for testing reflection with
 *
 * @ReflectionField and @ReflectionMethods classes
 */
public class ReflectionTarget {

  // publicly declared field!
  public static final int IMPUBLICFIELD = 7;

  // ohh noo, a secret value, let's see how other classes can
  // access the secret value!
  private final String SECRET_VALUE = "*its secret*";

  // for testing accessing public methods using reflection
  public static String whoami() {
    return "TheAlgorithms/Java";
  }

  // for testing accessing private methods (with arguments) using reflection
  private String getSecretKey(boolean pleaseWork) {
    return pleaseWork ? "VERY SECRET KEY" : "Unauthorized!!";
  }
}
