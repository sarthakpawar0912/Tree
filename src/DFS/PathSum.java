package DFS;

public class PathSum { // Class for checking path sum


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

    // Checks if there is a root-to-leaf path with sum equal to targetSum
    public boolean hasPathSum(TreeNode root, int targetSum) { // Main method to check path sum
        if (root == null) {
            return false;
        }

        // Check if node is a leaf (no children)
        if (root.left == null && root.right == null) { // Leaf node
            return targetSum == root.val; // Path sum equals target
        }

        // Recursively check left and right subtrees with remaining sum
        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    } // Summary: Checks path sum using DFS (O(n) time, n=nodes).


    public static void main(String[] args) { // Entry point for testing
        PathSum solution = new PathSum(); // Creates instance

        // Test Case 1: Example 1 - [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum=22
        System.out.println("Test Case 1:");
        TreeNode root1 = solution.new TreeNode(5);
        root1.left = solution.new TreeNode(4);
        root1.right = solution.new TreeNode(8);
        root1.left.left = solution.new TreeNode(11);
        root1.right.left = solution.new TreeNode(13);
        root1.right.right = solution.new TreeNode(4);
        root1.left.left.left = solution.new TreeNode(7);
        root1.left.left.right = solution.new TreeNode(2);
        root1.right.right.right = solution.new TreeNode(1);
        int targetSum1 = 22;
        System.out.println("Output: " + solution.hasPathSum(root1, targetSum1)); // Expected: true
        // Note: Path 5->4->11->2 sums to 22 (5+4+11+2=22)

        // Test Case 2: Example 2 - [1,2,3], targetSum=5
        System.out.println("\nTest Case 2:");
        TreeNode root2 = solution.new TreeNode(1);
        root2.left = solution.new TreeNode(2);
        root2.right = solution.new TreeNode(3);
        int targetSum2 = 5;
        System.out.println("Output: " + solution.hasPathSum(root2, targetSum2)); // Expected: false
        // Note: Paths 1->2 (sum=3), 1->3 (sum=4); no path sums to 5

        // Test Case 3: Example 3 - [], targetSum=0
        System.out.println("\nTest Case 3:");
        TreeNode root3 = null;
        int targetSum3 = 0;
        System.out.println("Output: " + solution.hasPathSum(root3, targetSum3)); // Expected: false
        // Note: Empty tree has no paths
    }
}
