import java.util.LinkedList; // Replace with specific imports
import java.util.Queue;

// Define the TreeNode class to represent nodes in the binary tree.
class TreeNode {
    int val; // Value stored in the node
    TreeNode left; // Pointer to the left child
    TreeNode right; // Pointer to the right child

    // Constructor to create a new node with a given value
    TreeNode(int val) {
        this.val = val;
        this.left = null; // Initially, the left child is null
        this.right = null; // Initially, the right child is null
    }
}

public class SerializeaBinaryTree {

    // The 'serialize' method converts a binary tree into a string.
    public String serialize(TreeNode root) {
        // If the tree is empty (root is null), return an empty string.
        if (root == null) {
            return "";
        }

        // StringBuilder is used to build the final serialized string efficiently.
        StringBuilder sb = new StringBuilder();

        // We will use a queue to perform level-order traversal (breadth-first search).
        Queue<TreeNode> queue = new LinkedList<>();

        // Start by adding the root node to the queue.
        queue.add(root);

        // Process each node in the queue, and append its value (or "null" for empty nodes) to the result.
        while (!queue.isEmpty()) {
            // Remove the front node from the queue.
            TreeNode node = queue.poll();

            // If the node is not null, process its value and add its children to the queue.
            if (node != null) {
                // Append the node's value to the string, followed by a comma to separate values.
                sb.append(node.val).append(",");

                // Add the left and right children to the queue (even if they are null).
                queue.add(node.left);
                queue.add(node.right);
            } else {
                // If the node is null, append "null" to the string to represent the absence of a node.
                sb.append("null,");
            }
        }

        // Remove the last comma from the end of the string to make it clean.
        sb.setLength(sb.length() - 1);

        // Return the final serialized string.
        return sb.toString();
    }

    // The 'deserialize' method converts a string back into a binary tree.
    public TreeNode deserialize(String data) {
        // If the input string is empty, return null (i.e., the tree is empty).
        if (data == null || data.isEmpty()) {
            return null;
        }

        // Split the string by commas to get the serialized node values.
        String[] nodes = data.split(",");

        // The first value in the string is the root node.
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

        // Use a queue to manage the nodes during reconstruction of the tree.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Use an index to track the current position in the node array (starting from 1, since 0 is root).
        int index = 1;

        // Process each node in the queue to reconstruct the tree.
        while (!queue.isEmpty()) {
            // Get the current node from the queue.
            TreeNode node = queue.poll();

            // Rebuild the left child.
            if (!nodes[index].equals("null")) {
                // If the value is not "null", create a new node and add it to the left.
                node.left = new TreeNode(Integer.parseInt(nodes[index]));
                // Add the left child to the queue for further processing.
                queue.add(node.left);
            }
            index++; // Move to the next value in the array.

            // Rebuild the right child.
            if (!nodes[index].equals("null")) {
                // If the value is not "null", create a new node and add it to the right.
                node.right = new TreeNode(Integer.parseInt(nodes[index]));
                // Add the right child to the queue for further processing.
                queue.add(node.right);
            }
            index++; // Move to the next value in the array.
        }

        // Return the root of the reconstructed tree.
        return root;
    }

    public static void main(String[] args) {
        // Example of creating a binary tree manually.
        //       1
        //      / \
        //     2   3
        //        / \
        //       4   5

        TreeNode root = new TreeNode(1); // Create the root node with value 1
        root.left = new TreeNode(2); // Create the left child (value 2)
        root.right = new TreeNode(3); // Create the right child (value 3)
        root.right.left = new TreeNode(4); // Create the left child of node 3 (value 4)
        root.right.right = new TreeNode(5); // Create the right child of node 3 (value 5)

        // Instantiate the SerializeaBinaryTree class
        SerializeaBinaryTree serializer = new SerializeaBinaryTree();

        // Serialize the tree to a string format
        String serializedTree = serializer.serialize(root);
        System.out.println("Serialized Tree: " + serializedTree);
        // Expected output: "1,2,3,null,null,4,5,null,null,null,null"

        // Deserialize the string back to a binary tree
        TreeNode deserializedRoot = serializer.deserialize(serializedTree);
        System.out.println("Tree deserialized successfully. Root value: " + deserializedRoot.val);
        // Expected output: Root value should be 1
    }
}
