package com.thealgorithms.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static java.lang.System.out;

public class ReflectionTest {
  @Test
  public void testFieldReflection() throws NoSuchFieldException, IllegalAccessException {
    // Let's try to publicly and privately declared elements from ReflectionTarget clazz
    Class<?> ourTarget = ReflectionTarget.class;
    int aPublicField = (int) ReflectionFields.getStaticField(ourTarget,
            "IMPUBLICFIELD",
            false);
    out.println("Just dynamically accessed private static field, value = " + aPublicField);
    String secretValue = (String) ReflectionFields.getField("SECRET_VALUE",
            new ReflectionTarget(),
            true);
    out.println("Got a privately declared field!, value = " + secretValue);
  }

  @Test
  public void testMethodReflection() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    Class<?> ourTarget = ReflectionTarget.class;
    String simpleCall = (String) ReflectionMethods.invokeStaticMethod(ourTarget,
            "whoami",
            false);
    out.println("Dynamicaly Invoked a simple static method, value = " + simpleCall);
    String secretKey = (String) ReflectionMethods.invokeMethod("getSecretKey",
            new ReflectionTarget(),
            // the second private argument is for the method getSecretKey(boolean pleaseWork)
            true, true);
    out.println("Just invoked a private method!, value = " + secretKey);
  }
}
