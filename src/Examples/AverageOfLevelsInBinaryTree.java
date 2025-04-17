package Examples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree {

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

    // Returns average value of nodes at each level
    public List<Double> averageOfLevels(TreeNode root) { // Main method for averages
        List<Double> result = new ArrayList<>(); // Stores level averages

        if (root == null) { // Empty tree
            return result;
        } // Handles base case

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for BFS
        queue.offer(root); // Starts with root

        while (!queue.isEmpty()) { // Processes until queue is empty
            int levelSize = queue.size(); // Nodes in current level
            double levelSum = 0; // Sum of values in level

            for (int i = 0; i < levelSize; i++) { // Processes level nodes
                TreeNode node = queue.poll(); // Gets next node
                levelSum += node.val; // Adds value to sum

                if (node.left != null) { // If left child exists
                    queue.offer(node.left); // Adds to queue
                }
                if (node.right != null) { // If right child exists
                    queue.offer(node.right); // Adds to queue
                }
            }
            result.add(levelSum / levelSize); // Adds average to result
        }
        return result; // Returns averages
    } // Summary: Computes level averages using BFS (O(n) time, n=nodes).


    public static void main(String[] args) { // Entry point for testing
        AverageOfLevelsInBinaryTree solution = new AverageOfLevelsInBinaryTree(); // Creates instance

        // Test Case 1: Example 1 - [3,9,20,null,null,15,7]
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(3); // Root
        root1.left = new TreeNode(9); // Level 1 left
        root1.right = new TreeNode(20); // Level 1 right
        root1.right.left = new TreeNode(15); // Level 2 left
        root1.right.right = new TreeNode(7); // Level 2 right
        System.out.println("Output: " + solution.averageOfLevels(root1)); // Expected: [3.0, 14.5, 11.0]

        // Test Case 2: Example 2 - [3,9,20,15,7]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = new TreeNode(3); // Root
        root2.left = new TreeNode(9); // Level 1 left
        root2.right = new TreeNode(20); // Level 1 right
        root2.left.left = new TreeNode(15); // Level 2 left
        root2.left.right = new TreeNode(7); // Level 2 right
        System.out.println("Output: " + solution.averageOfLevels(root2)); // Expected: [3.0, 14.5, 11.0]
    } // Summary: Tests averages with problem examples.
}
