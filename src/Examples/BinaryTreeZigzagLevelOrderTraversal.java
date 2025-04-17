package Examples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Deque;

public class BinaryTreeZigzagLevelOrderTraversal { // Class for zigzag traversal


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

    // Returns zigzag level order traversal
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) { // Main traversal method
        List<List<Integer>> result = new ArrayList<>(); // Stores levels

        if (root == null) { // Empty tree
            return result; // Returns empty list
        } // Handles base case

        Deque<TreeNode> queue = new LinkedList<>(); // Deque for BFS
        queue.offerLast(root); // Starts with root
        boolean reverse = false; // Tracks direction (false=left-to-right)

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                if (!reverse) {
                    TreeNode node = queue.pollFirst();
                    level.add(node.val);
                    if (node.left != null) queue.offerLast(node.left);
                    if (node.right != null) queue.offerLast(node.right);
                } else {
                    TreeNode node = queue.pollLast();
                    level.add(node.val);
                    if (node.right != null) queue.offerFirst(node.right);
                    if (node.left != null) queue.offerFirst(node.left);
                }
            }

            result.add(level);
            reverse = !reverse;
        }


        return result; // Returns zigzag levels
    } // Summary: Traverses tree in zigzag order using BFS (O(n) time, n=nodes).


    public static void main(String[] args) { // Entry point for testing
        BinaryTreeZigzagLevelOrderTraversal solution = new BinaryTreeZigzagLevelOrderTraversal(); // Creates instance

        // Test Case 1: [3,9,20,null,null,15,7]
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(3); // Root
        root1.left = new TreeNode(9); // Level 1 left
        root1.right = new TreeNode(20); // Level 1 right
        root1.right.left = new TreeNode(15); // Level 2 left
        root1.right.right = new TreeNode(7); // Level 2 right
        System.out.println("Output: " + solution.zigzagLevelOrder(root1)); // Expected: [[3],[20,9],[15,7]]

        // Test Case 2: [1]
        System.out.println("\nTest Case 2:");
        TreeNode root2 = new TreeNode(1); // Single node
        System.out.println("Output: " + solution.zigzagLevelOrder(root2)); // Expected: [[1]]

        // Test Case 3: []
        System.out.println("\nTest Case 3:");
        TreeNode root3 = null; // Empty tree
        System.out.println("Output: " + solution.zigzagLevelOrder(root3)); // Expected: []
    } // Summary: Tests zigzag traversal with examples.
}
