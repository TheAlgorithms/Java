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
 * Class that checks if all open close parameters are valid in string or in file. Parameters can be
 * default or custom if passed with ie -l "{" - r "}" or --left, --right Passed input can be string
 * line -s or --string or file -f or --file with path after it You can use --example to see how
 * input can look like
 */
public class ValidParentheses {

  private static final String ERROR_MSG = "Submitted input contain improper closures.";
  // Pattern to extract left symbols argument from command line
  private static final String LEFT_SYMBOL_PATTERN = "(--left\\s*\"|-l\\s*\")(.*?)\"";
  private static final String LEFT_SYMBOL_REG = "-l|--left";
  // Pattern to extract right symbols argument from command line
  private static final String RIGHT_SYMBOL_PATTERN = "(--right\\s*\"|-r\\s*\")(.*?)\"";
  private static final String RIGHT_SYMBOL_REG = "-r|--right";
  // Pattern to extract line argument from command line
  private static final String LINE_PATTERN = "(--string\\s*\"|-s\\s*\")(.*?)\"";
  private static final String LINE_ARG_REG = "-s|--string";
  // Pattern to extract file argument from command line
  private static final String FILE_PATTERN = "(--file\\s*\"|-f\\s*\")(.*?)\"";
  private static final String FILE_ARG_REG = "-f|--file";
  // Line separator independent of system
  private static final String LINE_SEPARATOR = System.lineSeparator();
  // Map of symbols that we are checking
  private static final Map<Character, Character> symbolMap = new HashMap<>();
  private static final Stack<Character> stack = new Stack<>();
  // Regex holder
  private static String symbolMapRegex;

  /**
   * Program for check if open-close symbol relationship is met.
   *
   * @param argv Program arguments -s,-f,-l,-r,-e ...
   */
  public static void main(String... argv) {
    // Create a Scanner object
    final Scanner myObj = new Scanner(System.in);
    // Printout starting text
    startText();
    // Read console input
    final String input = myObj.nextLine();
    // Parse console line arguments
    readArgumentsAndAnalyze(input.trim());
    // Printout end text
    endText();
  }

  private static void endText() {
    System.out.println("***Finished***");
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

  private static void readArgumentsAndAnalyze(String input) {
    // Check if example is requested.
    if (scanForExampleParameter(input)) {
      showExample();
      System.exit(0);
    }
    // Check if symbol arguments are passed and initialize symbolMap
    scanForSymbolsParameter(input);
    // If line command parameter is used, get line
    final String line = scanForLineParameter(input);
    // Check if line passed is string containing whitespace
    if (!line.isBlank()) {
      // Check if input is meeting the criteria
      analyseLine(line);
    } else {
      // If file command parameter is used, get file path
      final String filePath = scanForFileParameter(input);
      if (!filePath.isBlank()) {
        // If filePath is not blank check if file meets criteria
        analyseFile(filePath);
      } else {
        System.out.println(
            "Please insert line of file to analyse using parameters -s|--string|-f|--file");
      }
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

  private static boolean scanForExampleParameter(String input) {
    return input.contains("-e") || input.contains("--example");
  }

  private static void analyseFile(String filePath) {
    try {
      final FileInputStream inputStream = new FileInputStream(filePath);
      final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while ((line = br.readLine()) != null) {
        analyseLine(line);
      }
      if (!stack.empty()) {
        System.out.println(ERROR_MSG);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void scanForSymbolsParameter(String input) {
    final char[] leftSymbols = scanForLeftSymbols(input);
    final char[] rightSymbols = scanForRightSymbols(input);
    if (leftSymbols.length != rightSymbols.length) {
      System.out.println("-l (--left) and -r (--right) symbols are unequal length.");
      System.exit(0);
    } else if (leftSymbols.length == 0) {
      // Initialize symbolMap with default symbols.
      initDefaultSymbols();
    } else {
      // Initialize symbolMap using provided symbols
      initSymbols(leftSymbols, rightSymbols);
    }
    generateRegexForSymbolMap();
  }

  private static void initSymbols(char[] leftSymbols, char[] rightSymbols) {
    for (int i = 0; i < leftSymbols.length; i++) {
      symbolMap.put(leftSymbols[i], rightSymbols[i]);
    }
  }

  private static void initDefaultSymbols() {
    // By defaults adds following symbols to analyze.
    symbolMap.put('(', ')');
    symbolMap.put('{', '}');
    symbolMap.put('[', ']');
    // Generates regex for above symbols that is used in removeNonSymbolCharacters method.
    System.out.println("Custom symbols not provided, using default (), {}, []");
  }

  private static void generateRegexForSymbolMap() {
    final StringBuilder regex = new StringBuilder();
    for (char c : symbolMap.keySet()) {
      regex.append("\\")
          .append(c)
          .append("\\")
          .append(symbolMap.get(c));
    }
    symbolMapRegex = regex.toString();
  }

  private static void analyseLine(String line) {
    // Remove all noise that isn't relevant to analysis.
    line = removeNonSymbolCharacters(line);
    // We are analyzing individual chars.
    final char[] chars = line.toCharArray();
    for (char c : chars) {
      // If character is left symbol we are pushing it to the stack.
      // If it is right symbol we are pulling from stack and checking left symbol.
      if (symbolMap.containsKey(c)) {
        stack.push(c);
      } else {
        // If we consumed all left symbols from stack,
        // that means that symbols on the right are not paired.
        if (stack.isEmpty()) {
          System.out.println(ERROR_MSG);
          break;
        }
        final char stackKey = stack.pop();
        // We get matching right symbol for our left stack symbol.
        final char stackValue = symbolMap.get(stackKey);
        // If matching right symbol isn't equal to our current right character we break.
        if (stackValue != c) {
          System.out.println(ERROR_MSG);
          break;
        }
      }
    }
  }

  private static String removeNonSymbolCharacters(String line) {
    // Sanitizes line, so it only contains symbols that we are checking.
    line = line.replaceAll("[^" + symbolMapRegex + "]*", "");
    return line;
  }

  private static char[] scanForRightSymbols(String input) {
    return scanForParameter(RIGHT_SYMBOL_PATTERN, RIGHT_SYMBOL_REG, input).toCharArray();
  }

  private static char[] scanForLeftSymbols(String input) {
    return scanForParameter(LEFT_SYMBOL_PATTERN, LEFT_SYMBOL_REG, input).toCharArray();
  }

  private static String scanForFileParameter(String input) {
    return scanForParameter(FILE_PATTERN, FILE_ARG_REG, input);
  }

  private static String scanForLineParameter(String input) {
    return scanForParameter(LINE_PATTERN, LINE_ARG_REG, input);
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
