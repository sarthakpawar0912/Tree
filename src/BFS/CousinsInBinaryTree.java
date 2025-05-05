 package BFS;

import java.util.LinkedList;
import java.util.Queue;

public class CousinsInBinaryTree { // Class for checking cousins

    static class TreeNode {
        int val; // Node value
        TreeNode left; // Left child
        TreeNode right; // Right child

        TreeNode() {}


        TreeNode(int val) { // Constructor with value
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with children
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    // Checks if nodes with values x and y are cousins
    public boolean isCousins(TreeNode root, int x, int y) { // Main method to check cousins
        if (root == null) { // Empty tree
            return false; // Cannot be cousins
        }

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for BFS
        Queue<TreeNode> parents = new LinkedList<>(); // Tracks parent of each node
        queue.offer(root); // Start with root
        parents.offer(null); // Root has no parent
        int depthX = -1, depthY = -1; // Depths of x and y
        TreeNode parentX = null, parentY = null; // Parents of x and y
        int depth = 0; // Current depth

        while (!queue.isEmpty()) { // Process until queue is empty
            int levelSize = queue.size(); // Nodes in current level

            for (int i = 0; i < levelSize; i++) { // Process all nodes in level
                TreeNode node = queue.poll(); // Current node
                TreeNode parent = parents.poll(); // Its parent

                if (node.val == x) { // Found x
                    depthX = depth;
                    parentX = parent;
                }
                if (node.val == y) { // Found y
                    depthY = depth;
                    parentY = parent;
                }

                if (node.left != null) { // Add left child
                    queue.offer(node.left);
                    parents.offer(node);
                }
                if (node.right != null) { // Add right child
                    queue.offer(node.right);
                    parents.offer(node);
                }
            }
            depth++; // Increment depth for next level
        }

        return depthX == depthY && depthX != -1 && parentX != parentY; // Same depth, different parents
    } // Summary: Checks if x and y are cousins using BFS (O(n) time, n=nodes).


    public static void main(String[] args) { // Entry point for testing
        CousinsInBinaryTree solution = new CousinsInBinaryTree(); // Creates instance

        // Test Case 1: Example 1 - [1,2,3,4], x=4, y=3
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        System.out.println("Output: " + solution.isCousins(root1, 4, 3)); // Expected: false

        // Test Case 2: Example 2 - [1,2,3,null,4,null,5], x=5, y=4
        System.out.println("\nTest Case 2:");
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        System.out.println("Output: " + solution.isCousins(root2, 5, 4)); // Expected: true

        // Test Case 3: Example 3 - [1,2,3,null,4], x=2, y=3
        System.out.println("\nTest Case 3:");
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.right = new TreeNode(4);
        System.out.println("Output: " + solution.isCousins(root3, 2, 3)); // Expected: false
    }
}
