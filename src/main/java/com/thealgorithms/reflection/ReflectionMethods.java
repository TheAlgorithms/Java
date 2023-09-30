package com.thealgorithms.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionMethods {
  /**
   * Invokes a statically declared method
   *
   * @param clazz The class where the method is present in
   * @parm isPrivate if the method trying to retrieve is marked as private
   * @parm args arguments for the method call
   */

  public static Object invokeStaticMethod(Class<?> clazz, String methodName, boolean isPrivate, Object... args)
          throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    // getDeclaredMethod() is used in place of getMethod() if method is marked as private
    Method method = getMethod(isPrivate ? clazz.getDeclaredMethods() : clazz.getMethods(), methodName, args.length);
    if (isPrivate)
      // additional step to access private method
      method.setAccessible(true);
    // since the method is static, pass in a null value
    return method.invoke(null, args);
  }

  /**
   * Retrieves a declared method from an object instance
   *
   * @parm object the object where the method is present in
   * @parm isPrivate if the method trying to retrieve is marked as private
   * @parm args arguments for the method call
   */

  public static Object invokeMethod(String methodName, Object object, boolean isPrivate, Object... args)
          throws IllegalAccessException, InvocationTargetException {
    // get the clazz of the Object to retrieve the method
    Class<?> clazz = object.getClass();
    Method method = getMethod(isPrivate ? clazz.getDeclaredMethods() : clazz.getMethods(), methodName, args.length);
    if (isPrivate)
      // additional step to access private method
      method.setAccessible(true);
    return method.invoke(object, args);
  }

  private static Method getMethod(Method[] methods, String methodName, int argsCount) {
    for (Method method : methods) {
      int methodParameterCount = method.getParameterTypes().length;
      if (method.getName().equals(methodName) && methodParameterCount == argsCount) {
        return method;
      }
    }
    throw new IllegalArgumentException("Method of name " + methodName + "() not found, argsCount = " + argsCount);
  }
}
