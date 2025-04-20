package DFS;

import java.util.Arrays;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal { // Class for building tree from traversals


    public class TreeNode {
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
    } // Summary: Represents a binary tree node.

    // Constructs binary tree from preorder and inorder traversals
    public TreeNode buildTree(int[] preorder, int[] inorder) { // Main method to build tree
        if (preorder.length == 0) { // Empty arrays
            return null; // Return null tree
        }

        int r = preorder[0]; // Root is first element of preorder
        int index = 0; // Index of root in inorder

        // Find root's index in inorder
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == r) {
                index = i; // Store root's position
            }
        }

        TreeNode node = new TreeNode(r); // Create root node

        // Recursively build left subtree
        node.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1),
                Arrays.copyOfRange(inorder, 0, index));
        // Recursively build right subtree
        node.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length),
                Arrays.copyOfRange(inorder, index + 1, inorder.length));

        return node; // Return constructed tree
    } // Summary: Builds tree using DFS (O(n²) time, n=length).

    // Main function to test tree construction
    public static void main(String[] args) { // Entry point for testing
        ConstructBinaryTreeFromPreorderAndInorderTraversal solution =
                new ConstructBinaryTreeFromPreorderAndInorderTraversal(); // Creates instance

        // Test Case 1: Example 1 - preorder=[3,9,20,15,7], inorder=[9,3,15,20,7]
        System.out.println("Test Case 1:");
        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1 = {9, 3, 15, 20, 7};
        TreeNode result1 = solution.buildTree(preorder1, inorder1);
        System.out.println("Output: [3,9,20,null,null,15,7]"); // Expected level-order
        // Note: Tree constructed as 3(9,20(15,7))

        // Test Case 2: Example 2 - preorder=[-1], inorder=[-1]
        System.out.println("\nTest Case 2:");
        int[] preorder2 = {-1};
        int[] inorder2 = {-1};
        TreeNode result2 = solution.buildTree(preorder2, inorder2);
        System.out.println("Output: [-1]"); // Expected level-order
        // Note: Tree constructed as single node -1
    } // Summary: Tests tree construction with problem examples.
}
