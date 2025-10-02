
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// A node in the Huffman Tree
class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    char character;
    HuffmanNode left;
    HuffmanNode right;

    // Compare nodes based on frequency. Used by the PriorityQueue.
    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}

public final class HuffmanCoding {

    private static Map<Character, String> huffmanCodes = new HashMap<>();
    private static HuffmanNode root;
    private HuffmanCoding() {
        throw new UnsupportedOperationException("Utility class");
    }
    /**
     * Builds the Huffman Tree and generates codes.
     * @param text The input string to be encoded.
     */
    public static void buildTree(String text) {
        // 1. Calculate character frequencies
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char character : text.toCharArray()) {
            frequencies.put(character, frequencies.getOrDefault(character, 0) + 1);
        }

        // 2. Create leaf nodes and add them to a priority queue
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            HuffmanNode node = new HuffmanNode();
            node.character = entry.getKey();
            node.frequency = entry.getValue();
            node.left = null;
            node.right = null;
            pq.add(node);
        }

        // Handle case of single unique character
        if (pq.size() == 1) {
            HuffmanNode singleNode = pq.peek();
            huffmanCodes.put(singleNode.character, "0");
            root = singleNode;
            return;
        }

        // 3. Build the tree by merging nodes
        while (pq.size() > 1) {
            // Get the two nodes with the lowest frequency
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            // Create a new internal node
            HuffmanNode internalNode = new HuffmanNode();
            internalNode.frequency = left.frequency + right.frequency;
            internalNode.character = '-'; // Internal nodes have no character
            internalNode.left = left;
            internalNode.right = right;

            // Add the new node back to the queue
            pq.add(internalNode);
        }

        // The remaining node is the root of the tree
        root = pq.poll();

        // 4. Generate Huffman codes by traversing the tree
        generateCodes(root, "");
    }

    /**
     * Recursively traverses the tree to generate codes for each character.
     * @param node The current node in the traversal.
     * @param code The binary code generated so far.
     */
    private static void generateCodes(HuffmanNode node, String code) {
        if (node == null) {
            return;
        }

        // If it's a leaf node, it contains a character
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, code);
            return;
        }

        // Traverse left (append '0') and right (append '1')
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    /**
     * Encodes the given text using the generated Huffman codes.
     * @param text The text to encode.
     * @return The encoded binary string.
     */
    public static String encode(String text) {
        StringBuilder encodedText = new StringBuilder();
        for (char character : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(character));
        }
        return encodedText.toString();
    }

    /**
     * Decodes the given binary string using the Huffman Tree.
     * @param encodedText The binary string to decode.
     * @return The original decoded text.
     */
    public static String decode(String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;
        for (int i = 0; i < encodedText.length(); i++) {
            char bit = encodedText.charAt(i);
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            // If it's a leaf node, we found a character
            if (current.left == null && current.right == null) {
                decodedText.append(current.character);
                current = root; // Return to the root for the next character
            }
        }
        return decodedText.toString();
    }

    public static void main(String[] args) {
        String text = "huffman coding is a lossless data compression algorithm";

        System.out.println("Original Text: " + text);
        System.out.println("----------------------------------------");

        // Build the Huffman Tree and generate codes
        buildTree(text);

        // Print the Huffman codes for each character
        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }
        System.out.println("----------------------------------------");

        // Encode the text
        String encodedText = encode(text);
        System.out.println("Encoded Text: " + encodedText);
        System.out.println("----------------------------------------");

        // Decode the text
        String decodedText = decode(encodedText);
        System.out.println("Decoded Text: " + decodedText);
        System.out.println("----------------------------------------");

        // Verification
        System.out.println("Verification (Original equals Decoded): " + text.equals(decodedText));
    }
}
