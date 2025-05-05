package BFS;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {


    static class TreeNode {
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

    // Checks if tree is symmetric (mirror of itself)
    public boolean isSymmetric(TreeNode root) { // Main method to check symmetry
        if (root == null) { // Empty tree
            return true; // Symmetric by definition
        }

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for BFS
        queue.offer(root.left); // Add left subtree root
        queue.offer(root.right); // Add right subtree root

        while (!queue.isEmpty()) { // Process until queue is empty
            TreeNode left = queue.poll(); // Get left node
            TreeNode right = queue.poll(); // Get right node

            if (left == null && right == null) { // Both null
                continue; // Symmetric, check next pair
            }
            if (left == null || right == null) { // One null, one not
                return false; // Asymmetric
            }
            if (left.val != right.val) { // Values differ
                return false; // Asymmetric
            }

            // Add mirrored children for next comparison
            queue.offer(left.left); // Left child's left
            queue.offer(right.right); // Right child's right
            queue.offer(left.right); // Left child's right
            queue.offer(right.left); // Right child's left
        }
        return true; // All pairs symmetric
    } // Summary: Checks if tree is symmetric using BFS (O(n) time, n=nodes).

    // Main function to test symmetric tree check
    public static void main(String[] args) { // Entry point for testing
        SymmetricTree solution = new SymmetricTree(); // Creates instance

        // Test Case 1: Example 1 - [1,2,2,3,4,4,3]
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);
        System.out.println("Output: " + solution.isSymmetric(root1)); // Expected: true

        // Test Case 2: Example 2 - [1,2,2,null,3,null,3]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);
        System.out.println("Output: " + solution.isSymmetric(root2)); // Expected: false
    } // Summary: Tests symmetric tree check with problem examples.
}
