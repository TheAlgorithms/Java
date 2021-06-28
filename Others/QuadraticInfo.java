import java.util.Scanner;
import java.util.ArrayList;


// Returns information about an inputted quadratic:
// Factored Form
// Roots
// Axis of Symmetry
// Vertex
// Vertex Form
// Focus
// Directrix
// Derivative

public class QuadraticInfo {
  
  public static void main(String[] args) {
    
    System.out.print("Enter a quadratic trinomial in standard form: \nf(x) = ");
    Scanner input = new Scanner(System.in);
    String trinomial = input.nextLine();
    
    for(int i = 0; i < trinomial.length(); i++) {
    	if(trinomial.charAt(i) == ' ') {
    		trinomial = deleteChar(trinomial, i);
    		i--;
    	}
    }
    
    try{
	    if(trinomial.substring(trinomial.indexOf('^')).indexOf('x') < 0) {
	    	trinomial = trinomial.substring(0, trinomial.indexOf('^') + 2) + "+0x" + trinomial.substring(trinomial.indexOf('^') + 2);
	    }
    } catch(StringIndexOutOfBoundsException e) {}
    
    
    int[] signs = new int[3];
    
    // Determine sign value of a
    if(splitString(trinomial)[0] == '-') {
      signs[0] = -1;
    }
    else {
      signs[0] = 1;
    }
    
    int index = 0;
    int index2 = 0;
    
    // Determind sign value of b
    for(int i = 0; i < charToInt(splitString(trinomial)).length; i++) {
      if((charToInt(splitString(trinomial))[i] == (int)'-') && (i != 0)) {
        signs[1] = -1; 
        index = trinomial.indexOf('-', 1);
        break;
      }
      else if((charToInt(splitString(trinomial))[i] == (int)'+') && (i!= 0)) {
        signs[1] = 1; 
        index = trinomial.indexOf('+', 1);
        break;
      }
    }
    
    // Determine sign value of c
    for(int i = 0; i < charToInt(splitString(trinomial)).length; i++) {
      if((charToInt(splitString(trinomial))[i] == (int)'-') && (i != 0) && (i != index)) {
        signs[2] = -1;
        index2 = trinomial.indexOf('-', index + 1);
      }
      else if((charToInt(splitString(trinomial))[i] == (int)'+') && (i!= 0) && (i != index)) {
        signs[2] = 1; 
        index2 = trinomial.indexOf('+', index + 1);
      }
    }
    
    char sign1 = trinomial.charAt(index);
    char sign2 = trinomial.charAt(index2);
    
    // Finds Terms
    String firstTerm = "";
    String secondTerm = "";
    String thirdTerm = "";
    
    
    try{
      
      // find a
    for(int i = 0; i < trinomial.length(); i++) {
      if((int)trinomial.charAt(i) >= 48 && (int)trinomial.charAt(i) <= 57 || (int)trinomial.charAt(i)== (int)'-') {
        if((int)trinomial.charAt(i) == (int)'-' && ((int)trinomial.charAt(i + 1) <= 48 || (int)trinomial.charAt(i + 1) >= 57)) {
          firstTerm = "-1"; break;
        }
        else {
        firstTerm = trinomial.substring(i, index - 3); break;
        }
      }
      else {
        firstTerm = "1"; break;
      }
    }
    
      // find b
    for(int i = index; i < trinomial.length(); i++) {
      if((int)trinomial.charAt(i) >= 48 && (int)trinomial.charAt(i) <= 57) {
        secondTerm = trinomial.substring(index + 1, index2 - 1); break;
      }
    }
    
      // find c
    for(int i = index2; i < trinomial.length(); i++) {
      if((int)trinomial.charAt(i) >= 48 && (int)trinomial.charAt(i) <= 57) {
        thirdTerm = trinomial.substring(index2 + 1, trinomial.length()); break;
      }
    }
    
    
    if(trinomial.substring(index - 3, index).equals("x^2") == false || trinomial.substring(index2 - 1, index2).equals("x") == false) {
      if(trinomial.charAt(0) == '-' && ((int)trinomial.charAt(1) < 48 || (int)trinomial.charAt(1) > 57))  {
        System.err.println("Did you mean: -x^2" + sign1 + secondTerm + "x" + sign2 + thirdTerm);
      }
      else if(trinomial.charAt(0) != 'x') {
        System.err.println("Did you mean: " + firstTerm + "x^2" + sign1 + secondTerm + "x" + sign2 + thirdTerm);
      }
      else if(trinomial.charAt(0) == 'x') {
        System.err.println("Did you mean: x^2" + sign1 + secondTerm + "x" + sign2 + thirdTerm);
      }
    }
    
    // Convert terms to workable integers
    int a = signs[0] * Math.abs(Integer.parseInt(firstTerm));
    int b = signs[1] * Math.abs(Integer.parseInt(secondTerm));
    int c = signs[2] * Math.abs(Integer.parseInt(thirdTerm));
    
    System.out.println(commonFactor(a, b, c));
    
    // Find factors when a is equal to 1c v
    if(a == 1) {
      System.out.println("Factored Form: " + factor(a, b, c));
    }
    
    // When a is not one
    if(a != 1) {
      System.out.println("Factored Form: " + factorByGrouping(trinomial, index, index2, a, b, c));
    }
    
    // Print roots
    ArrayList<Double> zeros = exactRoots(a, b, c);
    System.out.print("Roots: ");
    if(zeros.size() == 0) System.out.println("No Real Roots");
    if(zeros.size() == 1) {
      System.out.print("x = ");
      exactDecimal(zeros.get(0).doubleValue());
      System.out.println();
    }
    if(zeros.size() == 2) {
      System.out.print("x = ");
      exactDecimal(zeros.get(0).doubleValue());
      System.out.print(",  x = ");
      exactDecimal(zeros.get(1).doubleValue());
      System.out.println();
    }
    
    // Print axis of symmetry
    double axisOfSymmetry = (double)-b / (double)(2*a);
    if(axisOfSymmetry % 1 != 0) {
      System.out.printf("Axis of Symmetry: x = %.2f\n", axisOfSymmetry);
    }
    else {
      System.out.println("Axis of Symmetry: x = " + (int)axisOfSymmetry);
      axisOfSymmetry = (int)axisOfSymmetry;
    }
    
    // Print Vertex
    double k = (a*Math.pow(axisOfSymmetry, 2)) + b*axisOfSymmetry + c;
    if(axisOfSymmetry % 1 == 0) {
      System.out.print("Vertex: (" + (int)axisOfSymmetry + ", ");
    }
    else {
      System.out.printf("Vertex: (%.2f, ", axisOfSymmetry);
    }
    if(k%1 != 0) {
      System.out.printf("%.2f)\n", k);
    }
    else {
      System.out.println((int)k + ")");
    }
    
    // Calculate vertex form
    findVertexForm(a, b, c);
    
    // Find focus and directrix
    double y = ((double)1/(4*a)) + k; if(y % 1 == 0) y = (int)y;
    if(axisOfSymmetry %1 != 0) {
    System.out.printf("Focus: (%.2f, ", axisOfSymmetry);
    }
    else {
      System.out.printf("Focus: (%.0f, ", axisOfSymmetry);
    }
    if(y%1==0 && y != 0) {
      System.out.printf("%.0f)\n", y);
    }
    else if(y == 0)
      System.out.println("0)");
    if(y%1!=0) System.out.printf("%.2f)\n", y);
    double directrix = k - ((double)1/(4*a));
    if(directrix%1==0) {
      directrix = (int)directrix;
      System.out.printf("Directrix: y = %.0f\n", directrix);
    }
    else {
      System.out.printf("Directrix: y = %.2f\n", directrix);
    }
    
    System.out.println("Derivative: " + Integer.parseInt(firstTerm)*2 + "x" + " " + sign1 + " " + secondTerm);
    
    // Catches Invalid Input
    }catch(Exception e) {
      System.err.println("Error: Invalid Input");
    }
    
  }
  
  public static String factor(int a, int b, int c) {
    char operator1 = '+';
    char operator2 = '+';
    
    // When a == 1
    int[] zerosArray = toPrimitiveInt(roots(a, b, c));
    String newTrinomial = "Does not factor";
    if(zerosArray.length == 0 && a == 1) {
      return newTrinomial;
    }
    if(zerosArray.length == 1) {
      if(zerosArray[0] > 0) operator1 = '-';
      newTrinomial = ("(x " + operator1 + " " + Math.abs(zerosArray[0]) + ")(x " + operator1 + " " + Math.abs(zerosArray[0]) + ")");
    }
    if(zerosArray.length == 2) {
      if(zerosArray[0] > 0) operator1 = '-';
      if(zerosArray[1] > 0) operator2 = '-';
      newTrinomial = ("(x " + operator1 + " " + Math.abs(zerosArray[0]) + ")(x " + operator2 + " " + Math.abs(zerosArray[1]) + ")");
    }
    return newTrinomial;
  }
  
  public static char[] splitString(String string) {
    char[] charArray = new char[string.length()];
    for(int i = 0; i < string.length(); i++) {
      charArray[i] = string.charAt(i);
    }
    return charArray;
  }
  
  public static int[] charToInt(char[] charArray) {
    int[] intArray = new int[charArray.length];
    for(int i = 0; i < charArray.length; i++) {
      intArray[i] = charArray[(int)i];
    }
    return intArray;
  }
  
  // when a = 1
  public static ArrayList<Integer> roots(int a, int b, int c) {
    double dis = ((b*b)-(4*a*c));
    ArrayList<Integer> zeros = new ArrayList<>(0);
    double x1 = (-b + (Math.sqrt((b*b) - 4*a*c))) / (2*a);
    double x2 = (-b - (Math.sqrt((b*b) - 4*a*c))) / (2*a);
    if((dis == 0) && (x1 == (int)x1)) zeros.add((int)x1); 
    if((dis > 0) && (x1 == (int)x1) && (x2 == (int)x2)) {
      zeros.add((int)x1);
      zeros.add((int)x2);
    }
    return zeros;
  }
  
  public static ArrayList<Double> exactRoots(int a, int b, int c) {
    double dis = ((b*b)-(4*a*c));
    ArrayList<Double> zeros = new ArrayList<>(0);
    double x1 = (-b + (Math.sqrt((b*b) - 4*a*c))) / (2*a);
    double x2 = (-b - (Math.sqrt((b*b) - 4*a*c))) / (2*a);
    if(dis == 0) zeros.add(x1);
    if(dis > 0) {
      zeros.add(x1);
      zeros.add(x2);
    }
    return zeros;
  }
  
  public static int findGCF(int num1, int num2) {
    int gcf = 1;
    int k = 2;
    while(k <= num1 && k <= num2) {
      if(num1 % k == 0 && num2 % k == 0) {
        gcf = k;
      }
      k++;
    }
    return gcf;
  }
  
  public static int[] toPrimitiveInt(ArrayList<Integer> intList) {
    int[] returnArray = new int[intList.size()];
    for(int i = 0; i < returnArray.length; i++) {
      returnArray[i] = intList.get(i).intValue();
    }
    return returnArray;
  }
  
  public static void findVertexForm(int a, int b, int c) {
    
    double h = (double)-b / (double)(2*a); double hDisplay = Math.abs(h);
    double k = (a*Math.pow(h, 2)) + b*h + c; double kDisplay = Math.abs(k);
    
    char operatorH = '+'; if(h > 0) operatorH = '-';
    char operatorK = '+'; if(k < 0) operatorK = '-';
    
    String stringA = Integer.toString(a);
    if(a == 1) stringA = "";
    
    System.out.print("Vertex Form: y = ");
    if(hDisplay%1==0 ^ kDisplay%1==0) {
      hDisplay = (int)hDisplay;
      if(kDisplay == 0) System.out.printf("%s(x %c %.0f)^2\n", stringA, operatorH, hDisplay);
      if(hDisplay == 0) System.out.printf("%s(x)^2 %c %.2f\n", stringA, operatorK, kDisplay);
      if(hDisplay != 0 && kDisplay != 0)
      System.out.printf("%s(x %c %.0f)^2 %c %.2f\n", stringA, operatorH, hDisplay, operatorK, kDisplay);
    }
    else if(kDisplay%1==0 ^ hDisplay%1==0) {
      kDisplay = (int)kDisplay;
      if(kDisplay == 0) System.out.printf("%s(x %c %.2f)^2\n", stringA, operatorH, hDisplay);
      if(hDisplay == 0) System.out.printf("%s(x)^2 %c %.0f\n", stringA, operatorK, kDisplay);
      if(hDisplay != 0 && kDisplay != 0)
      System.out.printf("%s(x %c %.2f)^2 %c %.0f\n", stringA, operatorH, hDisplay, operatorK, kDisplay);
    }
    else if(kDisplay%1==0 && hDisplay%1==0) {
      kDisplay = (int)kDisplay; hDisplay = (int)hDisplay;
      if(kDisplay == 0 && hDisplay == 0) {
        System.out.println("x^2");
      }
      else if(kDisplay == 0) {
        System.out.printf("%s(x %c %.0f)^2\n", stringA, operatorH, hDisplay);
      }
      else if(hDisplay == 0) {
        System.out.printf("%s(x)^2 %c %.0f\n", stringA, operatorK, kDisplay);
      }
      else if(hDisplay != 0 && kDisplay != 0) {
        System.out.printf("%s(x %c %.0f)^2 %c %.0f\n", stringA, operatorH, hDisplay, operatorK, kDisplay);
      }
    }
    else {
      if(kDisplay == 0) System.out.printf("%s(x %c %.2f)^2\n", stringA, operatorH, hDisplay);
      if(hDisplay == 0) System.out.printf("%s(x)^2 %c %.2f\n", stringA, operatorK, kDisplay);
      if(hDisplay != 0 && kDisplay != 0)
      System.out.printf("%s(x %c %.2f)^2 %c %.2f\n", stringA, operatorH, hDisplay, operatorK, kDisplay);
    }
    
  }
  
  // Factors when a is not 1
  public static String factorByGrouping(String trinomial, int index, int index2, int a, int b, int c) {
    int commonFactor = commonFactor(a, b, c);
    
    if(a < 0) commonFactor = -commonFactor;
    
    boolean doesFactor = true;
    double test = Math.sqrt((b*b)-(4*a*c));
    if(test % 1 != 0) {
      doesFactor = false;
    }
    
    a = a / commonFactor;
    b = b / commonFactor;
    c = c / commonFactor;
    
    int endT = a*c;
    int[] factorOfC = findFactors(endT);
    
    int m1 = 0;
    int m2 = 0;
    for(int i = 0; i < factorOfC.length; i++) {
      for(int x = 0; x < factorOfC.length; x++) {
        if(factorOfC[i] + factorOfC[x] == b && factorOfC[i] * factorOfC[x] == endT) {
          m1 = factorOfC[i];
          m2 = factorOfC[x];
        }
      }
    }
    if(findGCF(Math.abs(m2), Math.abs(c)) < findGCF(Math.abs(m1), Math.abs(c))) {
      int temp = m1;
      m1 = m2;
      m2 = temp;
    }
    int firstGCF = findGCF(Math.abs(m1), Math.abs(a));
    int secondGCF = findGCF(Math.abs(m2), Math.abs(c));
    if(m2 < 0) secondGCF = -secondGCF;
    if(a < 0) firstGCF = -firstGCF;
    a = a / firstGCF; m1 = m1 / firstGCF;
    c = c / secondGCF; m2 = m2 / secondGCF;
    
    char firstGroupChar = '+'; if(secondGCF < 0) firstGroupChar = '-';
    char secondGroupChar = '+'; if(c < 0) secondGroupChar = '-';
    
    int firstTimesCommon = (findGCF(Math.abs(firstGCF), Math.abs(secondGCF)));
    firstGCF = firstGCF / firstTimesCommon; secondGCF = secondGCF / firstTimesCommon;
    
    String firstGroupA = Integer.toString(firstGCF); if(firstGCF == 1) firstGroupA = "";
    String secondGroupA = Integer.toString(a); if(a == 1) secondGroupA = "";
    String tripleGCF = Integer.toString(commonFactor * firstTimesCommon); if(commonFactor*firstTimesCommon == 1) tripleGCF = "";
    
    String firstGroup = "(" + firstGroupA + "x " + firstGroupChar + " " + Math.abs(secondGCF) + ")";
    String secondGroup = "(" + secondGroupA + "x " + secondGroupChar + " " + Math.abs(c) + ")";
    
    String newTrinomial = "null";
    
    if(doesFactor == true) {
      newTrinomial = tripleGCF + firstGroup + secondGroup;
    }
    else {
      newTrinomial = "Does not factor";
    }
    
    return newTrinomial;
  }
  
  // Finds all factors (including negatives) of a number and returns them in an integer array
  public static int[] findFactors(int num) {
    ArrayList<Integer> fList = new ArrayList<>();
    for(int i = 1; i < Math.abs(num); i++) {
    	
    	// If i is a factor, add i and -i
    	if(num % i == 0) {
    		fList.add(i);
            fList.add(-i);
    	}
    }
    fList.add(num);
    fList.add(-num);
    
    int[] fArray = toPrimitiveInt(fList);
    return fArray;
  }
  
  // Returns common factor of 3 numbers
  public static int commonFactor(int a, int b, int c) {
   
    // All factors of each number
    int[] factorA = findFactors(a);
    int[] factorB = findFactors(b);
    int[] factorC = findFactors(c);
    
    int commonFacAB = 1;
    int commonFacBC = 1;

    // Finds a common factor between a and b that isn't 1
    for(int i = 0; i < factorA.length; i++ ) {
      for(int j = 0; j < factorB.length; j++) {
        
    	  if(factorA[i] == factorB[j] && factorA[i] > 1) {
          commonFacAB = factorA[i];
        }
      }
    }
    
    // Finds a common factor between b and c that isn't 1
    for(int i = 0; i < factorB.length; i++ ) {
      for(int j = 0; j < factorC.length; j++) {
    	  
        if(factorB[i] == factorC[j] && factorB[i] > 1) {
          commonFacBC = factorB[i];
          
          // Returns common factor between a, b, and c
          if(commonFacBC == commonFacAB) {
        	  return commonFacBC;
          }
        }
      }
  
    }
    
    // No common factor greater than 1 exists
    return 1;
  }
  
  public static void exactDecimal(double num) {
    if(num % 1 == 0) {
      System.out.print((int)num);
    }
    else {
      System.out.printf("%.2f", num);
    }


  }
  
  public static String deleteChar(String string, int index) {
  	String newString = new String();
  	newString = string.substring(0, index) + string.substring(index + 1);
  	return newString;
  }
  
  
}