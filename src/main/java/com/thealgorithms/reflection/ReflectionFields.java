package com.thealgorithms.reflection;

import java.lang.reflect.Field;

public class ReflectionFields {

  /**
   * Retrieves a statically declared field
   *
   * @param clazz The class where the field is present in
   * @parm isPrivate if the field trying to retrieve is marked as private
   */

  public static Object getStaticField(Class<?> clazz, String fieldName, boolean isPrivate)
          throws NoSuchFieldException, IllegalAccessException {
    // getDeclaredField() is used in place of getField() if field is marked as private
    Field field = isPrivate ? clazz.getDeclaredField(fieldName) : clazz.getField(fieldName);
    if (isPrivate)
      // additional step to access private field
      field.setAccessible(true);
    return field.get(null);
  }

  /**
   * Retrieves a declared field from an object instance
   *
   * @parm object the object where the field is present in
   * @parm isPrivate if the field trying to retrieve is marked as private
   */

  public static Object getField(String fieldName, Object object, boolean isPrivate) throws NoSuchFieldException, IllegalAccessException {
    // get the clazz of the Object to retrieve the field
    Class<?> clazz = object.getClass();
    Field field = isPrivate ? clazz.getDeclaredField(fieldName) : clazz.getField(fieldName);
    if (isPrivate)
      // additional step to access private field
      field.setAccessible(true);
    return field.get(object);
  }
}
