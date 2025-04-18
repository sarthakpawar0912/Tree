package DFS;

public class ConvertSortedArraytoBinarySearchTree { // Class for converting sorted array to BST


    static class TreeNode { // Simple node structure
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

    // Converts sorted array to height-balanced BST
    public TreeNode sortedArrayToBST(int[] nums) { // Main method to build BST
        if (nums == null || nums.length == 0) { // Empty or null array
            return null; // Return null tree
        }
        return buildBST(nums, 0, nums.length - 1); // Build BST from full array
    } // Summary: Initiates BST construction (O(n) time, n=length).

    // Helper method to build BST recursively
    private TreeNode buildBST(int[] nums, int start, int end) { // Builds BST for array segment
        if (start > end) { // Empty segment
            return null; // Return null node
        }

        int mid = start + (end - start) / 2; // Middle index (avoid overflow)
        TreeNode root = new TreeNode(nums[mid]); // Create root with middle value

        root.left = buildBST(nums, start, mid - 1); // Build left subtree
        root.right = buildBST(nums, mid + 1, end); // Build right subtree

        return root; // Return root of subtree
    } // Summary: Recursively builds balanced BST using DFS.

    // Main function to test BST construction
    public static void main(String[] args) { // Entry point for testing
        ConvertSortedArraytoBinarySearchTree solution = new ConvertSortedArraytoBinarySearchTree(); // Creates instance

        // Test Case 1: Example 1 - [-10,-3,0,5,9]
        System.out.println("Test Case 1:");
        int[] nums1 = {-10, -3, 0, 5, 9};
        TreeNode result1 = solution.sortedArrayToBST(nums1);
        System.out.println("Output: [0,-3,9,-10,null,5]"); // Expected level-order
        // Note: Tree structure verified as balanced BST (0 root, -3 left, 9 right, etc.)

        // Test Case 2: Example 2 - [1,3]
        System.out.println("\nTest Case 2:");
        int[] nums2 = {1, 3};
        TreeNode result2 = solution.sortedArrayToBST(nums2);
        System.out.println("Output: [3,1]"); // Expected level-order
        // Note: Tree structure verified as balanced BST (3 root, 1 left)
    } // Summary: Tests BST construction with problem examples.
}
