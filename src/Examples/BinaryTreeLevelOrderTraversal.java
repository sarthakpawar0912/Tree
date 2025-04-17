package Examples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal { // Class for level order traversal

    // Definition for a binary tree node
    static class TreeNode { // Simple node structure
        int val; // Node value
        TreeNode left; // Left child
        TreeNode right; // Right child

        TreeNode(int val) { // Constructor
            this.val = val; // Sets value
            this.left = null; // No left child
            this.right = null; // No right child
        } // Summary: Creates node with value.
    } // Summary: Represents a binary tree node.

    // Returns level order traversal as list of lists
    public List<List<Integer>> levelOrder(TreeNode root) { // Main traversal method
        List<List<Integer>> result = new ArrayList<>(); // Stores levels

        if (root == null) { // Empty tree
            return result; // Returns empty list
        } // Handles base case

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for BFS
        queue.offer(root); // Starts with root

        while (!queue.isEmpty()) { // Processes until no nodes left
            int levelSize = queue.size(); // Nodes in current level
            List<Integer> level = new ArrayList<>(); // Values in current level

            for (int i = 0; i < levelSize; i++) { // Processes all nodes in level
                TreeNode currentNode = queue.poll(); // Gets next node
                level.add(currentNode.val); // Adds value to level

                if (currentNode.left != null) { // If left child exists
                    queue.offer(currentNode.left); // Adds to queue
                }
                if (currentNode.right != null) { // If right child exists
                    queue.offer(currentNode.right); // Adds to queue
                }
            }
            result.add(level); // Adds level to result
        }
        return result; // Returns all levels
    } // Summary: Traverses tree level by level using BFS (O(n) time, n=nodes).

    // Main function to test level order traversal
    public static void main(String[] args) { // Entry point for testing
        BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal(); // Creates instance

        // Test Case 1: Example 1 - [3,9,20,null,null,15,7]
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(3); // Root
        root1.left = new TreeNode(9); // Level 1 left
        root1.right = new TreeNode(20); // Level 1 right
        root1.right.left = new TreeNode(15); // Level 2 left
        root1.right.right = new TreeNode(7); // Level 2 right
        System.out.println("Output: " + solution.levelOrder(root1)); // Expected: [[3],[9,20],[15,7]]

        // Test Case 2: Example 2 - [1]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = new TreeNode(1); // Single node
        System.out.println("Output: " + solution.levelOrder(root2)); // Expected: [[1]]

        // Test Case 3: Example 3 - []
        System.out.println("\nTest Case 3:");
        TreeNode root3 = null; // Empty tree
        System.out.println("Output: " + solution.levelOrder(root3)); // Expected: []
    } // Summary: Tests traversal with problem examples.
}
