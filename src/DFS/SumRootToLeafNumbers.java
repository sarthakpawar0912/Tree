package DFS;

public class SumRootToLeafNumbers { // Class for summing root-to-leaf numbers

    // Definition for a binary tree node
    public class TreeNode { // Simple node structure (non-static as provided)
        int val; // Node value
        TreeNode left; // Left child
        TreeNode right; // Right child

        TreeNode() {} // Default constructor

        TreeNode(int val) { // Constructor with value
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with children
            this.val = val;
            this.left = left;
            this.right = right;
        } // Summary: Creates node with value and optional children.
    } // Summary: Represents a binary tree node.

    // Computes the sum of all root-to-leaf path numbers
    public int sumNumbers(TreeNode root) { // Main method to sum numbers
        return helper(root, 0); // Start with initial sum of 0
    } // Summary: Initiates sum computation (O(n) time, n=nodes).

    // Helper method to build and sum path numbers recursively
    int helper(TreeNode node, int sum) { // Builds numbers along paths
        if (node == null) { // Empty node
            return 0; // No contribution to sum
        }

        // Build number by appending current digit
        sum = sum * 10 + node.val;

        // If leaf node, return the current path number
        if (node.left == null && node.right == null) {
            return sum; // Completed number
        }

        // Sum numbers from left and right subtrees
        return helper(node.left, sum) + helper(node.right, sum);
    } // Summary: Uses DFS to build and sum path numbers.

    // Main function to test sum computation
    public static void main(String[] args) { // Entry point for testing
        SumRootToLeafNumbers solution = new SumRootToLeafNumbers(); // Creates instance

        // Test Case 1: Example 1 - [1,2,3]
        System.out.println("Test Case 1:");
        TreeNode root1 = solution.new TreeNode(1);
        root1.left = solution.new TreeNode(2);
        root1.right = solution.new TreeNode(3);
        System.out.println("Output: " + solution.sumNumbers(root1)); // Expected: 25
        // Note: Paths 1->2 (12), 1->3 (13); sum = 12 + 13 = 25

        // Test Case 2: Example 2 - [4,9,0,5,1]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = solution.new TreeNode(4);
        root2.left = solution.new TreeNode(9);
        root2.right = solution.new TreeNode(0);
        root2.left.left = solution.new TreeNode(5);
        root2.left.right = solution.new TreeNode(1);
        System.out.println("Output: " + solution.sumNumbers(root2)); // Expected: 1026
        // Note: Paths 4->9->5 (495), 4->9->1 (491), 4->0 (40); sum = 495 + 491 + 40 = 1026
    } // Summary: Tests sum computation with problem examples.
}
