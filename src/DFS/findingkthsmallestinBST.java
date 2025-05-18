package DFS;

public class findingkthsmallestinBST { // Class for finding kth smallest in BST


    public class TreeNode {

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

    // Global counter to track nodes visited during in-order traversal
    int count = 0;

    // Finds the kth smallest value in the BST (1-indexed)
    public int kthSmallest(TreeNode root, int k) { // Main method to find kth smallest
        TreeNode result = helper(root, k); // Get kth node
        return result.val; // Return its value
    } // Summary: Initiates kth smallest search (O(n) time, n=nodes).

    // Helper method to perform in-order traversal and find kth node
    public TreeNode helper(TreeNode root, int k) { // Finds kth node via in-order DFS

        if (root == null) { // Empty node
            return null; // No node found
        }

        // Traverse left subtree first (smaller values)
        TreeNode left = helper(root.left, k);
        if (left != null) { // Kth node found in left subtree
            return left; // Return it
        }

        // Process current node
        count++; // Increment count for current node
        if (count == k) { // Current node is kth smallest
            return root; // Return it
        }

        // Traverse right subtree (larger values)
        return helper(root.right, k); // Return result from right
    } // Summary: Uses in-order traversal to find kth smallest node.


    public static void main(String[] args) { // Entry point for testing
        findingkthsmallestinBST solution = new findingkthsmallestinBST(); // Creates instance

        // Test Case 1: Example 1 - [3,1,4,null,2], k=1
        System.out.println("Test Case 1:");
        TreeNode root1 = solution.new TreeNode(3);
        root1.left = solution.new TreeNode(1);
        root1.right = solution.new TreeNode(4);
        root1.left.right = solution.new TreeNode(2);
        int k1 = 1;
        System.out.println("Output: " + solution.kthSmallest(root1, k1)); // Expected: 1
        // Note: In-order [1,2,3,4], 1st smallest is 1

        // Test Case 2: Example 2 - [5,3,6,2,4,null,null,1], k=3
        System.out.println("\nTest Case 2:");
        TreeNode root2 = solution.new TreeNode(5);
        root2.left = solution.new TreeNode(3);
        root2.right = solution.new TreeNode(6);
        root2.left.left = solution.new TreeNode(2);
        root2.left.right = solution.new TreeNode(4);
        root2.left.left.left = solution.new TreeNode(1);
        int k2 = 3;
        System.out.println("Output: " + solution.kthSmallest(root2, k2)); // Expected: 3
        // Note: In-order [1,2,3,4,5,6], 3rd smallest is 3
    }
}
