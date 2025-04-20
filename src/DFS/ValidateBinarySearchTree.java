package DFS;

public class ValidateBinarySearchTree {


    public class TreeNode {
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

    // Determines if the binary tree is a valid BST
    public boolean isValidBST(TreeNode root) { // Main method to validate BST
        return helper(root, null, null); // Start with no bounds
    } // Summary: Initiates BST validation (O(n) time, n=nodes).

    // Helper method to validate BST recursively with range checks
    public boolean helper(TreeNode node, Integer low, Integer high) { // Validates node within range
        if (node == null) { // Empty node
            return true; // Valid by definition
        }

        // Check if node's value is less than or equal to lower bound
        if (low != null && node.val <= low) { // Violates BST (value not > low)
            return false; // Invalid
        }

        // Check if node's value is greater than or equal to upper bound
        if (high != null && node.val >= high) { // Violates BST (value not < high)
            return false; // Invalid
        }

        // Recursively validate left subtree (values < node.val)
        boolean leftTree = helper(node.left, low, node.val); // Update upper bound
        // Recursively validate right subtree (values > node.val)
        boolean rightTree = helper(node.right, node.val, high); // Update lower bound

        return leftTree && rightTree; // Both subtrees must be valid
    } // Summary: Validates BST using DFS with range constraints.


   public static void main(String[] args) { // Entry point for testing
        ValidateBinarySearchTree solution = new ValidateBinarySearchTree(); // Creates instance

        // Test Case 1: Example 1 - [2,1,3]
        System.out.println("Test Case 1:");
        TreeNode root1 = solution.new TreeNode(2);
        root1.left = solution.new TreeNode(1);
        root1.right = solution.new TreeNode(3);
        System.out.println("Output: " + solution.isValidBST(root1)); // Expected: true
        // Note: Tree is valid BST (1 < 2 < 3)

        // Test Case 2: Example 2 - [5,1,4,null,null,3,6]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = solution.new TreeNode(5);
        root2.left = solution.new TreeNode(1);
        root2.right = solution.new TreeNode(4);
        root2.right.left = solution.new TreeNode(3);
        root2.right.right = solution.new TreeNode(6);
        System.out.println("Output: " + solution.isValidBST(root2)); // Expected: false
        // Note: Tree is invalid (4 in right subtree <= 5)
    }
}