package Strings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that checks if all open close parameters are valid
 * in string or in file. Parameters can be default or custom
 * if passed with ie -l "{" - r "}" or --left, --right.
 * Passed input can be string line -s or --string or
 * file -f or --file with path after it.
 * You can use --example to see how input can look like
 */
class ValidParentheses {

  /**
   * Error message in case input doesn't contain valid parentheses.
   */
  private static final String ERROR_MSG =
      "Submitted input contain non valid parentheses.";
  /**
   * Pattern to extract left parentheses' argument from command line.
   */
  private static final String LEFT_PARENTHESES_PATTERN =
      "(--left\\s*\"|-l\\s*\")(.*?)\"";
  /**
   * Pattern to extract left parentheses' argument from command line.
   */
  private static final String LEFT_PARENTHESES_REG = "-l|--left";
  /**
   * Pattern to extract right parentheses' argument from command line.
   */
  private static final String RIGHT_PARENTHESES_PATTERN =
      "(--right\\s*\"|-r\\s*\")(.*?)\"";
  /**
   * Pattern to split by right parentheses' argument value from command line.
   */
  private static final String RIGHT_PARENTHESES_REG = "-r|--right";
  /**
   * Pattern to extract line argument from command line.
   */
  private static final String LINE_PATTERN = "(--string\\s*\"|-s\\s*\")(.*?)\"";
  /**
   * Pattern to split by string input argument value from command line.
   */
  private static final String LINE_ARG_REG = "-s|--string";
  /**
   * Pattern to extract file argument from command line.
   */
  private static final String FILE_PATTERN = "(--file\\s*\"|-f\\s*\")(.*?)\"";
  /**
   * Pattern to split by file path input argument value from command line.
   */
  private static final String FILE_ARG_REG = "-f|--file";
  /**
   * Line separator independent of system.
   */
  private static final String LINE_SEPARATOR = System.lineSeparator();
  /**
   * Map of parentheses that we are checking.
   */
  private static final Map<Character, Character> PARENTHESES_MAP =
      new HashMap<>();
  /**
   * Stack data-structure to hold our most recently read character sign.
   */
  private static final Stack<Character> CHARACTER_STACK = new Stack<>();
  /**
   * Regex holder.
   */
  private static String parenthesesMapRegex;

  /**
   * Program for check if open-close parentheses relationship is met.
   *
   * @param argv Program arguments -s,-f,-l,-r,-e ...
   */
  public static void main(final String... argv) {
    /* Create a Scanner object */
    final Scanner myObj = new Scanner(System.in);
    /* Printout starting text */
    startText();
    /* Read console input */
    final String input = myObj.nextLine();
    /* Parse console line arguments */
    readArgumentsAndCheckValidity(input.trim());
    /* Printout end text */
    endText();
  }

  private static void endText() {
    System.out.println("***Finished successfully***");
  }

  private static void startText() {
    System.out.print("Insert string."
        + LINE_SEPARATOR
        + "Optionally insert custom bracket pairs or defaults will be used."
        + LINE_SEPARATOR
        + "To press enter"
        + LINE_SEPARATOR
        + "-s --string"
        + LINE_SEPARATOR
        + "-f --file"
        + LINE_SEPARATOR
        + "-l --left"
        + LINE_SEPARATOR
        + "-r --right"
        + LINE_SEPARATOR
        + "-e --example"
        + LINE_SEPARATOR);
  }

  private static void readArgumentsAndCheckValidity(final String input) {
    // Check if example is requested.
    if (scanForExampleParameter(input)) {
      showExample();
      System.exit(0);
    }
    // Check if parentheses arguments are passed and initialize parenthesesMap
    scanForParenthesesParameter(input);
    // If line command parameter is used, get line
    final String line = scanForLineParameter(input);
    // Check if line passed is string containing whitespace
    if (!line.isBlank()) {
      // Check if input is meeting the criteria
      validateLine(line);
    } else {
      // If file command parameter is used, get file path
      final String filePath = scanForFileParameter(input);
      if (!filePath.isBlank()) {
        // If filePath is not blank check if file meets criteria
        validateFile(filePath);
      } else {
        System.out.println(
            "Please insert line of file to analyse"
                + " using parameters -s|--string|-f|--file");
      }
    }
    if (!CHARACTER_STACK.empty()) {
      System.out.println(ERROR_MSG);
      System.exit(0);
    }
  }

  private static void showExample() {
    System.out.print("Examples:"
        + LINE_SEPARATOR
        + "--string \"{some text{ some other text}}\" -l \"{\" -r \"}\""
        + LINE_SEPARATOR
        + "-s \"{some text[ some (other)] text}\""
        + LINE_SEPARATOR
        + "--file \"C:\\Users\\Username\\Folder\\file.json\" -l \"{\" -r \"}\""
        + LINE_SEPARATOR
        + "-f \"C:\\Users\\Username\\Folder\\file.txt\"");
  }

  private static boolean scanForExampleParameter(final String input) {
    return input.contains("-e") || input.contains("--example");
  }

  private static void validateFile(final String filePath) {
    try {
      final FileInputStream inputStream = new FileInputStream(filePath);
      final BufferedReader br =
          new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while ((line = br.readLine()) != null) {
        validateLine(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void scanForParenthesesParameter(final String input) {
    final char[] leftParentheses = scanForLeftParentheses(input);
    final char[] rightParentheses = scanForRightParentheses(input);
    if (leftParentheses.length != rightParentheses.length) {
      System.out.println(
          "-l (--left) and -r (--right)"
              + " parentheses are unequal length.");
      System.exit(0);
    } else if (leftParentheses.length == 0) {
      // Initialize parenthesesMap with default parentheses.
      initDefaultParentheses();
    } else {
      // Initialize parenthesesMap using provided parentheses
      initParentheses(leftParentheses, rightParentheses);
    }
    generateRegexForParenthesesMap();
  }

  private static void initParentheses(
      final char[] leftParentheses,
      final char[] rightParentheses) {
    for (int i = 0; i < leftParentheses.length; i++) {
      PARENTHESES_MAP.put(leftParentheses[i], rightParentheses[i]);
    }
  }

  private static void initDefaultParentheses() {
    // By defaults adds following parentheses to analyze.
    PARENTHESES_MAP.put('(', ')');
    PARENTHESES_MAP.put('{', '}');
    PARENTHESES_MAP.put('[', ']');
    // Generates regex for above parentheses that,
    // used in removeNonParenthesesCharacters method.
    System.out.println(
        "Custom parentheses not provided,"
            + " using default (), {}, []");
  }

  private static void generateRegexForParenthesesMap() {
    final StringBuilder regex = new StringBuilder();
    for (char c : PARENTHESES_MAP.keySet()) {
      regex.append("\\")
          .append(c)
          .append("\\")
          .append(PARENTHESES_MAP.get(c));
    }
    parenthesesMapRegex = regex.toString();
  }

  private static void validateLine(final String line) {
    // Remove all noise that isn't relevant to analysis.
    final String sanitizeLine = removeNonParenthesesCharacters(line);
    // We are analyzing individual chars.
    final char[] chars = sanitizeLine.toCharArray();
    for (char c : chars) {
      // If character is left parentheses
      // we are pushing it to the stack.
      // If it is right parentheses
      // we are pulling from stack and checking left parentheses.
      if (PARENTHESES_MAP.containsKey(c)) {
        CHARACTER_STACK.push(c);
      } else {
        // If we consumed all left parentheses from stack,
        // that means that parentheses on the right are not paired.
        if (CHARACTER_STACK.isEmpty()) {
          System.out.println(ERROR_MSG);
          break;
        }
        final char stackKey = CHARACTER_STACK.pop();
        // We get matching right parentheses for our left stack parentheses.
        final char stackValue = PARENTHESES_MAP.get(stackKey);
        // If matching right parentheses isn't equal
        // to our current right character we break.
        if (stackValue != c) {
          System.out.println(ERROR_MSG);
          break;
        }
      }
    }
  }

  private static String removeNonParenthesesCharacters(final String line) {
    // Sanitizes line, so it only contains parentheses that we are checking.
    return line.replaceAll("[^" + parenthesesMapRegex + "]*", "");
  }

  private static char[] scanForRightParentheses(final String input) {
    return scanForParameter(
        RIGHT_PARENTHESES_PATTERN,
        RIGHT_PARENTHESES_REG,
        input).toCharArray();
  }

  private static char[] scanForLeftParentheses(final String input) {
    return scanForParameter(
        LEFT_PARENTHESES_PATTERN,
        LEFT_PARENTHESES_REG,
        input).toCharArray();
  }

  private static String scanForFileParameter(final String input) {
    return scanForParameter(
        FILE_PATTERN,
        FILE_ARG_REG,
        input);
  }

  private static String scanForLineParameter(final String input) {
    return scanForParameter(
        LINE_PATTERN,
        LINE_ARG_REG,
        input);
  }

  private static String scanForParameter(
      final String argPattern,
      final String regex,
      final String lineInput) {
    final Pattern pattern = Pattern.compile(argPattern);
    final Matcher matcher = pattern.matcher(lineInput);
    if (matcher.find()) {
      return matcher.group(0)
          .split(regex)[1]
          .replace("\"", "")
          .trim();
    }
    return "";
  }

}
