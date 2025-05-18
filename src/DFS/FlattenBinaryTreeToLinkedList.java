package DFS;

public class FlattenBinaryTreeToLinkedList { // Class for flattening binary tree to linked list

    static class TreeNode {

        int val;

        TreeNode left;

        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with children

            this.val = val;

            this.left = left;

            this.right = right;
        } // Summary: Creates node with value and optional children.
    }

    // Flattens binary tree to linked list in-place using iterative approach
    public void flatten(TreeNode root) { // Main method to flatten tree

        TreeNode current = root; // Start with root node

        while (current != null) { // Process until no more nodes
            if (current.left != null) { // If node has left child
                TreeNode temp = current.left; // Point to left subtree
                while (temp.right != null) { // Find rightmost node of left subtree
                    temp = temp.right; // Move to rightmost
                }
                // Rightmost of left subtree links to current's right
                temp.right = current.right; // Connect left subtree's rightmost to right subtree
                current.right = current.left; // Move left subtree to right
                current.left = null; // Clear left pointer
            }
            current = current.right; // Move to next node (right child)
        } // Summary: Flattens tree into pre-order linked list (O(n) time, O(1) space).
    } // Summary: Transforms tree to right-linked list in pre-order.


   public static void main(String[] args) { // Entry point for testing
        FlattenBinaryTreeToLinkedList solution = new FlattenBinaryTreeToLinkedList(); // Creates instance

        // Test Case 1: Example 1 - [1,2,5,3,4,null,6]
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(6);
        solution.flatten(root1);
        System.out.println("Output: [1,null,2,null,3,null,4,null,5,null,6]"); // Expected linked list
        // Note: Tree flattened to 1->2->3->4->5->6 (right pointers, null left)

        // Test Case 2: Example 2 - []
        System.out.println("\nTest Case 2:");
        TreeNode root2 = null;
        solution.flatten(root2);
        System.out.println("Output: []"); // Expected empty list
        // Note: Empty tree remains unchanged
    }
}
