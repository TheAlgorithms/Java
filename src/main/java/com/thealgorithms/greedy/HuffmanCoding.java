import java.util.PriorityQueue;

// Node structure for Huffman Tree
class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.frequency - o.frequency;
    }
}

public class HuffmanCoding {
    public static void generateHuffmanCode(char[] characters, int[] frequencies) {
        // Create a min-heap based on frequencies
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>();
        for (int i = 0; i < characters.length; i++) {
            minHeap.offer(new HuffmanNode(characters[i], frequencies[i]));
        }

        // Build the Huffman Tree
        while (minHeap.size() > 1) {
            HuffmanNode leftNode = minHeap.poll();
            HuffmanNode rightNode = minHeap.poll();

            HuffmanNode mergedNode = new HuffmanNode('\0', leftNode.frequency + rightNode.frequency);
            mergedNode.left = leftNode;
            mergedNode.right = rightNode;

            minHeap.offer(mergedNode);
        }

        // Print Huffman Codes
        HuffmanNode root = minHeap.poll();
        printHuffmanCodes(root, "");
    }

    private static void printHuffmanCodes(HuffmanNode root, String code) {
        if (root == null) return;

        if (root.data != '\0') {
            System.out.println(root.data + ": " + code);
            return;
        }

        printHuffmanCodes(root.left, code + "0");
        printHuffmanCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] frequencies = {5, 9, 12, 13, 16, 45};

        generateHuffmanCode(characters, frequencies);
    }
}
