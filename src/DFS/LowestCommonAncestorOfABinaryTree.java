package DFS;

public class LowestCommonAncestorOfABinaryTree { // Class for finding LCA

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

    // Finds the lowest common ancestor of nodes p and q
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) { // Main method to find LCA
        if (root == null) { // Empty tree
            return null; // No ancestor
        }

        if (root == p || root == q) { // Current node is p or q
            return root; // Return node (handles self-descendant)
        }

        // Recursively search left subtree for p or q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // Recursively search right subtree for p or q
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) { // p and q found in different subtrees
            return root; // Current node is LCA
        }

        // Return non-null result (p or q in one subtree) or null
        return left == null ? right : left;
    } // Summary: Finds LCA using DFS (O(n) time, n=nodes).


    public static void main(String[] args) { // Entry point for testing
        LowestCommonAncestorOfABinaryTree solution = new LowestCommonAncestorOfABinaryTree(); // Creates instance

        // Test Case 1: Example 1 - [3,5,1,6,2,0,8,null,null,7,4], p=5, q=1
        System.out.println("Test Case 1:");
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(0);
        root1.right.right = new TreeNode(8);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);
        TreeNode p1 = root1.left; // Node 5
        TreeNode q1 = root1.right; // Node 1
        TreeNode result1 = solution.lowestCommonAncestor(root1, p1, q1);
        System.out.println("Output: " + (result1 != null ? result1.val : null)); // Expected: 3
        // Note: LCA of 5 and 1 is 3 (5 in left, 1 in right)

        // Test Case 2: Example 2 - [3,5,1,6,2,0,8,null,null,7,4], p=5, q=4
        System.out.println("\nTest Case 2:");
        TreeNode root2 = root1; // Same tree
        TreeNode p2 = root2.left; // Node 5
        TreeNode q2 = root2.left.right.right; // Node 4
        TreeNode result2 = solution.lowestCommonAncestor(root2, p2, q2);
        System.out.println("Output: " + (result2 != null ? result2.val : null)); // Expected: 5
        // Note: LCA of 5 and 4 is 5 (4 is descendant of 5)

        // Test Case 3: Example 3 - [1,2], p=1, q=2
        System.out.println("\nTest Case 3:");
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        TreeNode p3 = root3; // Node 1
        TreeNode q3 = root3.left; // Node 2
        TreeNode result3 = solution.lowestCommonAncestor(root3, p3, q3);
        System.out.println("Output: " + (result3 != null ? result3.val : null)); // Expected: 1
        // Note: LCA of 1 and 2 is 1 (2 is descendant of 1)
    }
}