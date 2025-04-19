package DFS;

public class MaximumDepthOfBinaryTree { // Class for computing max depth


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with children
            this.val = val;
            this.left = left;
            this.right = right;
        } // Summary: Creates node with value and optional children.
    }

    // Returns the maximum depth of the binary tree
    public int maxDepth(TreeNode root) { // Main method to compute depth
        if (root == null) { // Empty tree
            return 0; // Depth is 0
        }

        int leftDepth = maxDepth(root.left); // Depth of left subtree
        int rightDepth = maxDepth(root.right); // Depth of right subtree

        return Math.max(leftDepth, rightDepth) + 1; // Max depth plus current node
    } // Summary: Computes max depth using DFS (O(n) time, n=nodes).

    // Main function to test max depth computation
    public static void main(String[] args) { // Entry point for testing
        MaximumDepthOfBinaryTree solution = new MaximumDepthOfBinaryTree(); // Creates instance

        // Test Case 1: Example 1 - [3,9,20,null,null,15,7]
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println("Output: " + solution.maxDepth(root1)); // Expected: 3

        // Test Case 2: Example 2 - [1,null,2]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        System.out.println("Output: " + solution.maxDepth(root2)); // Expected: 2
    }
}
