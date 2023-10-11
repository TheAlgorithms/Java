public static int[] compilePatternArray(String pattern) {
    int patternLength = pattern.length();
    int len = 0;
    int i = 1;
    int[] compliedPatternArray = new int[patternLength];
    compliedPatternArray[0] = 0;

    while (i < patternLength) {
        if (pattern.charAt(i) == pattern.charAt(len)) {
            len++;
            compliedPatternArray[i] = len;
            i++;
        } else {
            if (len != 0) {
                len = compliedPatternArray[len - 1];
            } else {
                compliedPatternArray[i] = len;
                i++;
            }
        }
    }
    System.out.println("Compiled Pattern Array " + Arrays.toString(compliedPatternArray));
    return compliedPatternArray;
}
