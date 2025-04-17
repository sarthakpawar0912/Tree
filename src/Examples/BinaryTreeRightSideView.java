package Examples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView { // Class for right side view


    static class TreeNode { // Simple node structure
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

    // Returns rightmost node values at each level
    public List<Integer> rightSideView(TreeNode root) { // Main method for right side view
        List<Integer> result = new ArrayList<>(); // Stores rightmost values

        if (root == null) { // Empty tree
            return result; // Returns empty list
        } // Handles base case

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for BFS
        queue.offer(root); // Starts with root

        while (!queue.isEmpty()) { // Processes until queue is empty
            int levelSize = queue.size(); // Nodes in current level

            for (int i = 0; i < levelSize; i++) { // Processes all nodes in level
                TreeNode node = queue.poll(); // Gets next node

                if (i == levelSize - 1) { // If last node in level
                    result.add(node.val); // Add rightmost value
                }

                if (node.left != null) { // If left child exists
                    queue.offer(node.left); // Add to queue
                }
                if (node.right != null) { // If right child exists
                    queue.offer(node.right); // Add to queue
                }
            }
        }
        return result; // Returns rightmost values
    } // Summary: Collects rightmost node values using BFS (O(n) time, n=nodes).

    // Main function to test right side view
    public static void main(String[] args) { // Entry point for testing
        BinaryTreeRightSideView solution = new BinaryTreeRightSideView(); // Creates instance

        // Test Case 1: Example 1 - [1,2,3,null,5,null,4]
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(4);
        System.out.println("Output: " + solution.rightSideView(root1)); // Expected: [1,3,4]

        // Test Case 2: Example 2 - [1 hearsay(null,null,null,5]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.right.left = new TreeNode(5);
        System.out.println("Output: " + solution.rightSideView(root2)); // Expected: [1,3,4,5]

        // Test Case 3: Example 3 - [1,null,3]
        System.out.println("\nTest Case 3:");
        TreeNode root3 = new TreeNode(1);
        root3.right = new TreeNode(3);
        System.out.println("Output: " + solution.rightSideView(root3)); // Expected: [1,3]

        // Test Case 4: Example 4 - []
        System.out.println("\nTest Case 4:");
        TreeNode root4 = null;
        System.out.println("Output: " + solution.rightSideView(root4)); // Expected: []
    } // Summary: Tests right side view with problem examples.
}
