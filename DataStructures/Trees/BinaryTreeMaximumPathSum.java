public class BinaryTreeMaximumPathSum {
    int max = 0;

    public int maxPathSum(Node root) {
        max = Integer.MIN_VALUE;
        maxPathSumUtil(root);
        return max;
    }

    private int maxPathSumUtil(Node root) {
        if (root == null) {
            return 0;
        }
        int left = maxPathSumUtil(root.left);
        int right = maxPathSumUtil(root.right);
        if (left < 0) {
            left = 0;
        }
        if (right < 0) {
            right = 0;
        }
        max = Math.max(max, root.data + left + right);
        return root.data + Math.max(left, right);
    }
}
