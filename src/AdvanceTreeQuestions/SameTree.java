package AdvanceTreeQuestions;

// Class to check if two binary trees are identical (LeetCode #100)
public class SameTree {
    // Definition for a binary tree node (static inner class)

    static class TreeNode {

        int val;           // Stores the value of the node
        TreeNode left;     // Reference to the left child node
        TreeNode right;    // Reference to the right child node

        // Constructor: creates an empty node
        TreeNode() {}

        // Constructor: creates a node with given value
        TreeNode(int val) {
            this.val = val; // Set node’s value
        }

        // Constructor: creates a node with value and children
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;   // Set node’s value
            this.left = left; // Set left child
            this.right = right; // Set right child
        }
    }

    // Tree field: root node of the binary tree
    private TreeNode root;

    // Constructor: initializes an empty tree
    public SameTree() {
        root = null; // Set root to null for empty tree
    }

    // Method to insert a node into the binary tree
    // Uses level-order insertion to build tree for testing
    void insert(int val) {
        // If tree is empty, create root node
        if (root == null) {
            root = new TreeNode(val);
            return;
        }
        // Use queue to insert nodes level by level
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root); // Start with root
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll(); // Get next node
            // Insert as left child if slot is empty
            if (current.left == null) {
                current.left = new TreeNode(val);
                break;
            } else {
                queue.offer(current.left); // Add left child to queue
            }
            // Insert as right child if slot is empty
            if (current.right == null) {
                current.right = new TreeNode(val);
                break;
            } else {
                queue.offer(current.right); // Add right child to queue
            }
        }
    }

    // Method to check if two binary trees are identical
    boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case: if both nodes are null, trees are identical
        if (p == null && q == null) {
            return true; // Empty subtrees are considered same
        }
        // Base case: if one node is null and other is not, trees differ
        if (p == null || q == null) {
            return false; // Different structure (one subtree is empty)
        }
        // Check if current nodes have same value and their subtrees are identical
        return (p.val == q.val) && // Compare values of current nodes
                isSameTree(p.left, q.left) && // Recursively check left subtrees
                isSameTree(p.right, q.right); // Recursively check right subtrees
    }

    // Method to display the tree structure for verification
    void display() {
        // Check if tree is empty
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        System.out.println("Tree Structure:");
        // Call recursive display with root
        displayTree(root, "");
        System.out.println();
    }

    // Helper method to display tree with indentation
    private void displayTree(TreeNode node, String indent) {
        // Stop if node is null
        if (node == null) {
            return;
        }
        // Print right child first (appears above)
        displayTree(node.right, indent + "  ");
        // Print current node’s value
        System.out.println(indent + node.val);
        // Print left child (appears below)
        displayTree(node.left, indent + "  ");
    }

    // Main method to test if two trees are same
    public static void main(String[] args) {
        // Test Case 1: Identical trees [1,2,3] and [1,2,3]
        System.out.println("Test Case 1: Trees [1,2,3] and [1,2,3]");
        SameTree tree1 = new SameTree();
        tree1.insert(1); // Root
        tree1.insert(2); // Left child
        tree1.insert(3); // Right child
        System.out.println("Tree 1:");
        tree1.display();
        SameTree tree2 = new SameTree();
        tree2.insert(1);
        tree2.insert(2);
        tree2.insert(3);
        System.out.println("Tree 2:");
        tree2.display();
        // Check if trees are same
        boolean result1 = tree1.isSameTree(tree1.root, tree2.root);
        System.out.println("Are trees same? " + result1); // Expected: true
        System.out.println();

        // Test Case 2: Different trees [1,2] and [1,null,2]
        System.out.println("Test Case 2: Trees [1,2] and [1,null,2]");
        SameTree tree3 = new SameTree();
        tree3.insert(1);
        tree3.insert(2); // Only left child
        System.out.println("Tree 3:");
        tree3.display();
        SameTree tree4 = new SameTree();
        tree4.insert(1);
        tree4.insert(0); // Insert dummy value
        tree4.root.left = null; // Set left to null
        tree4.insert(2); // Right child
        System.out.println("Tree 4:");
        tree4.display();
        // Check if trees are same
        boolean result2 = tree3.isSameTree(tree3.root, tree4.root);
        System.out.println("Are trees same? " + result2); // Expected: false
        System.out.println();

        // Test Case 3: Different values [1,2,1] and [1,1,2]
        System.out.println("Test Case 3: Trees [1,2,1] and [1,1,2]");
        SameTree tree5 = new SameTree();
        tree5.insert(1);
        tree5.insert(2);
        tree5.insert(1);
        System.out.println("Tree 5:");
        tree5.display();
        SameTree tree6 = new SameTree();
        tree6.insert(1);
        tree6.insert(1);
        tree6.insert(2);
        System.out.println("Tree 6:");
        tree6.display();
        // Check if trees are same
        boolean result3 = tree6.isSameTree(tree5.root, tree6.root);
        System.out.println("Are trees same? " + result3); // Expected: false
    }
}