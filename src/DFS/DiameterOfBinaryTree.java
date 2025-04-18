package DFS;

public class DiameterOfBinaryTree { // Class for computing tree diameter


    static class TreeNode {
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
    }

    private int diameter = 0; // Tracks maximum diameter (edges)

    // Returns the diameter of the binary tree
    public int diameterOfBinaryTree(TreeNode root) { // Main method to compute diameter
        height(root); // Compute heights and update diameter
        return diameter; // Return max path length
    } // Summary: Initiates diameter computation (O(n) time, n=nodes).

    // Computes height of subtree and updates diameter
    private int height(TreeNode node) { // Helper to compute height
        if (node == null) { // Empty subtree
            return 0; // No edges
        }

        int leftHeight = height(node.left); // Height of left subtree
        int rightHeight = height(node.right); // Height of right subtree

        int dia = leftHeight + rightHeight; // Path through current node (edges)
        diameter = Math.max(diameter, dia); // Update max diameter

        return Math.max(leftHeight, rightHeight) + 1; // Return height (edges to deepest leaf)
    } // Summary: Computes height and updates diameter via DFS.

    // Main function to test diameter computation
    public static void main(String[] args) { // Entry point for testing
        DiameterOfBinaryTree solution = new DiameterOfBinaryTree(); // Creates instance

        // Test Case 1: Example 1 - [1,2,3,4,5]
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        System.out.println("Output: " + solution.diameterOfBinaryTree(root1)); // Expected: 3

        // Test Case 2: Example 2 - [1,2]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        System.out.println("Output: " + solution.diameterOfBinaryTree(root2)); // Expected: 1
    } // Summary: Tests diameter computation with problem examples.
}
