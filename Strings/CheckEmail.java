package Strings;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Example of email address validation. Checks if a given string is a valid email or not.
 */
public class CheckEmail {

  public static void main(String[] args) {
    assert isEmail("hello@gmail.com");
    assert isEmail("iamweasel@yahoo.com.mx");
    assert isEmail("coco@hotmail.com");
    assert !isEmail("coco@hotmail@google.com");
    assert !isEmail("www.google.com");
    assert !isEmail("hello world");
    assert !isEmail(null);
  }

  /**
   * Checks if the given string is an email address
   *
   * @param stringToEvaluate the string to evaluate
   * @return {@code true} if stringToEvaluate is an email address, otherwise {@code false}
   */
  public static boolean isEmail(String stringToEvaluate) {
    if(Objects.isNull(stringToEvaluate)) return false;
    String emailRegex = "^[A-Za-z0-9_+&*-]+(?:\\.[A-Za-z0-9_+&*-]+)*@(?:[A-Za-z0-9-]+\\.)+[A-Za-z]{2,7}$";
    return Pattern.compile(emailRegex)
            .matcher(stringToEvaluate)
            .matches();
  }
}
