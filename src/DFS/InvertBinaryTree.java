package DFS;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {


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

    // Inverts the binary tree by swapping left and right children
    public TreeNode invertTree(TreeNode root) { // Main method to invert tree
        if (root == null) { // Empty tree
            return null; // Return null
        }

        // Recursively invert left and right subtrees
        TreeNode left = invertTree(root.left); // Invert left subtree
        TreeNode right = invertTree(root.right); // Invert right subtree

        // Swap left and right children
        root.left = right;
        root.right = left;

        return root; // Return root of inverted tree
    } // Summary: Inverts tree using DFS (O(n) time, n=nodes).

    // Main function to test tree inversion
    public static void main(String[] args) { // Entry point for testing
        InvertBinaryTree solution = new InvertBinaryTree(); // Creates instance

        // Test Case 1: Example 1 - [4,2,7,1,3,6,9]
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(9);
        TreeNode result1 = solution.invertTree(root1);
        System.out.println("Output: " + printTree(result1)); // Expected: [4,7,2,9,6,3,1]

        // Test Case 2: Example 2 - [2,1,3]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        TreeNode result2 = solution.invertTree(root2);
        System.out.println("Output: " + printTree(result2)); // Expected: [2,3,1]

        // Test Case 3: Example 3 - []
        System.out.println("\nTest Case 3:");
        TreeNode root3 = null;
        TreeNode result3 = solution.invertTree(root3);
        System.out.println("Output: " + printTree(result3)); // Expected: []
    } // Summary: Tests tree inversion with problem examples.

    // Helper method to print tree in level-order for verification
    private static String printTree(TreeNode root) { // Prints tree as list
        if (root == null) { // Empty tree
            return "[]";
        }

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for BFS
        queue.offer(root); // Start with root
        StringBuilder result = new StringBuilder("["); // Build output
        boolean nonNull = true; // Track if non-null nodes remain

        while (!queue.isEmpty() && nonNull) { // Process until queue empty or no non-null nodes
            int levelSize = queue.size();
            nonNull = false; // Reset for level

            for (int i = 0; i < levelSize; i++) { // Process level
                TreeNode node = queue.poll();
                if (node != null) { // Non-null node
                    result.append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                    if (node.left != null || node.right != null) {
                        nonNull = true; // Non-null children found
                    }
                } else {
                    result.append("null");
                    queue.offer(null);
                    queue.offer(null);
                }
                if (i < levelSize - 1 || nonNull) { // Add comma unless last in tree
                    result.append(",");
                }
            }
        }

        // Remove trailing comma if present
        if (result.charAt(result.length() - 1) == ',') {
            result.setLength(result.length() - 1);
        }
        result.append("]");
        return result.toString();
    } // Summary: Prints tree in level-order format (e.g., [4,7,2,9,6,3,1]).
}
