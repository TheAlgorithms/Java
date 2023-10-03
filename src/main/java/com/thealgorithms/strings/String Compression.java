public class StringCompression {
    public static String compress(String s) {
        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                compressed.append(s.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        compressed.append(s.charAt(s.length() - 1)).append(count);

        return compressed.length() < s.length() ? compressed.toString() : s;
    }

    public static void main(String[] args) {
        String input = "aabcccccaaa";
        String compressed = compress(input);
        System.out.println(compressed); // Output: "a2b1c5a3"
    }
}
