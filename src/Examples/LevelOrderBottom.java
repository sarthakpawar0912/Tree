package Examples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderBottom { // Class for bottom-up level order traversal


    static class TreeNode { // Simple node structure
        int val; // Node value
        TreeNode left; // Left child
        TreeNode right; // Right child

        TreeNode(int val) { // Constructor
            this.val = val; // Sets value
            this.left = null; // No left child
            this.right = null; // No right child
        } // Summary: Creates node with value.
    }

    // Returns bottom-up level order traversal
    public List<List<Integer>> levelOrderBottom(TreeNode root) { // Main traversal method
        List<List<Integer>> result = new ArrayList<>(); // Stores levels (bottom to top)

        if (root == null) { // Empty tree
            return result;
        } // Handles base case

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for BFS
        queue.offer(root); // Starts with root

        while (!queue.isEmpty()) { // Processes until queue is empty
            int levelSize = queue.size(); // Nodes in current level
            List<Integer> level = new ArrayList<>(); // Values in current level

            for (int i = 0; i < levelSize; i++) { // Processes all nodes in level
                TreeNode node = queue.poll(); // Gets next node
                level.add(node.val); // Adds value to level

                if (node.left != null) { // If left child exists
                    queue.offer(node.left); // Adds to queue
                }
                if (node.right != null) { // If right child exists
                    queue.offer(node.right); // Adds to queue
                }
            }
            result.add(0, level); // Prepends level to result for bottom-up order
        }
        return result; // Returns levels from leaves to root
    } // Summary: Traverses tree bottom-up using BFS (O(n) time, n=nodes).


    public static void main(String[] args) { // Entry point for testing
        LevelOrderBottom solution = new LevelOrderBottom(); // Creates instance

        // Test Case 1: Example 1 - [3,9,20,null,null,15,7]
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(3); // Root
        root1.left = new TreeNode(9); // Level 1 left
        root1.right = new TreeNode(20); // Level 1 right
        root1.right.left = new TreeNode(15); // Level 2 left
        root1.right.right = new TreeNode(7); // Level 2 right
        System.out.println("Output: " + solution.levelOrderBottom(root1)); // Expected: [[15,7],[9,20],[3]]

        // Test Case 2: Example 2 - [1]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = new TreeNode(1); // Single node
        System.out.println("Output: " + solution.levelOrderBottom(root2)); // Expected: [[1]]

        // Test Case 3: Example 3 - []
        System.out.println("\nTest Case 3:");
        TreeNode root3 = null; // Empty tree
        System.out.println("Output: " + solution.levelOrderBottom(root3)); // Expected: []
    }
}
