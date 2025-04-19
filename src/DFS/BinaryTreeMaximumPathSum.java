package DFS;

public class BinaryTreeMaximumPathSum { // Class for finding maximum path sum


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) { // Constructor with value
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with children
            this.val = val;
            this.left = left;
            this.right = right;
        } // Summary: Creates node with value and optional children.
    } // Summary: Represents a binary tree node.

    // Global variable to track maximum path sum
    int ans = Integer.MIN_VALUE;

    // Finds the maximum path sum in the tree
    public int maxPathSum(TreeNode node) { // Main method to find max path sum
        helper(node); // Compute max path sum
        return ans; // Return result
    } // Summary: Initiates max path sum computation (O(n) time, n=nodes).

    // Helper method to compute max path sum recursively
    int helper(TreeNode node) { // Computes max path sum starting from node
        if (node == null) { // Empty node
            return 0; // No contribution to path
        }

        // Recursively compute max path sums from left and right subtrees
        int left = helper(node.left);
        int right = helper(node.right);

        // Exclude negative sums to maximize path
        left = Math.max(0, left);
        right = Math.max(0, right);

        // Compute path sum through current node (left + node + right)
        int pathSum = left + right + node.val;

        // Update global max if current path sum is larger
        ans = Math.max(ans, pathSum);

        // Return max path sum starting from node (for parent)
        return Math.max(left, right) + node.val;
    } // Summary: Uses DFS to compute and track max path sum.

    // Main function to test max path sum computation
    public static void main(String[] args) { // Entry point for testing
        BinaryTreeMaximumPathSum solution = new BinaryTreeMaximumPathSum(); // Creates instance

        // Test Case 1: Example 1 - [1,2,3]
        System.out.println("Test Case 1:");
        TreeNode root1 = solution.new TreeNode(1);
        root1.left = solution.new TreeNode(2);
        root1.right = solution.new TreeNode(3);
        System.out.println("Output: " + solution.maxPathSum(root1)); // Expected: 6
        // Note: Optimal path 2->1->3 sums to 2+1+3=6

        // Test Case 2: Example 2 - [-10,9,20,null,null,15,7]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = solution.new TreeNode(-10);
        root2.left = solution.new TreeNode(9);
        root2.right = solution.new TreeNode(20);
        root2.right.left = solution.new TreeNode(15);
        root2.right.right = solution.new TreeNode(7);
        System.out.println("Output: " + solution.maxPathSum(root2)); // Expected: 42
        // Note: Optimal path 15->20->7 sums to 15+20+7=42
    } // Summary: Tests max path sum computation with problem examples.
}
