package Concept;

import java.util.ArrayList;
import java.util.Scanner;

public class BinarySearchTree {


    // Private inner class for tree nodes (encapsulated)
    private class Node { // Private to prevent external access

        int value; // Node data (BST key)

        int height; // Height for balance checking

        Node left; // Left child reference

        Node right; // Right child reference

        public Node(int value) {
            this.value = value; // Sets value
            this.height = 0; // Initial height (leaf node)
        } // Summary: Creates a leaf node with given value.

        public int getValue() { // Getter for value
            return value; // Returns node’s value
        } // Summary: Provides access to value for display.
    } // Summary: Represents a BST node with value, height, and child pointers.

    private Node root; // Root node of the BST

    // Constructor
    public BinarySearchTree() { // Initializes empty BST
        // root is null by default
    } // Summary: Creates an empty Binary Search Tree.

    // Returns height of a node
    public int height(Node node) { // Computes height for balance checks
        if (node == null) { // Base case: empty subtree
            return -1; // Null node has height -1
        } // Handles null nodes
        return node.height; // Returns stored height
    } // Summary: Returns node height (-1 for null, else node.height).

    // Checks if tree is empty
    public boolean isEmpty() { // Verifies if BST has no nodes
        return root == null; // True if root is null
    } // Summary: Returns true if tree is empty, false otherwise.

    // Public method to insert a value
    public void insert(int value) { // Initiates insertion into BST
        root = insert(value, root); // Updates root with new node
    } // Summary: Calls recursive insert with root and updates tree.

    // Private recursive method to insert a value
    private Node insert(int value, Node node) { // Inserts value into subtree
        if (node == null) { // Base case: empty subtree
            return new Node(value); // Creates new leaf node
        } // Returns new node
        if (value < node.value) { // If value is less than node
            node.left = insert(value, node.left); // Recurses on left subtree
        } else if (value > node.value) { // If value is greater
            node.right = insert(value, node.right); // Recurses on right subtree
        } else { // Duplicate value
            return node; // Skips insertion (no duplicates)
        } // Maintains BST property
        node.height = Math.max(height(node.left), height(node.right)) + 1; // Updates height
        return node; // Returns current node
    } // Summary: Recursively inserts value, updates height, skips duplicates (O(h) time, h = height).

    // Checks if tree is height-balanced
    public boolean balanced() { // Initiates balance check
        return balanced(root); // Calls recursive balanced
    } // Summary: Returns true if tree is balanced.

    // Private recursive method to check balance
    private boolean balanced(Node node) { // Verifies subtree balance
        if (node == null) { // Base case: empty subtree
            return true; // Null tree is balanced
        } // Handles null nodes
        int heightDiff = Math.abs(height(node.left) - height(node.right)); // Computes height difference
        return heightDiff <= 1 && balanced(node.left) && balanced(node.right); // Checks balance and subtrees
    } // Summary: True if height difference ≤ 1 and subtrees are balanced (O(n) time).

    // Helper method to get tree height
    private int getHeight(Node node) { // Computes height for display sizing
        if (node == null) { // Base case: empty tree
            return 0; // Height 0
        } // Handles null nodes
        return 1 + Math.max(getHeight(node.left), getHeight(node.right)); // Height is 1 + max child height
    } // Summary: Returns tree height for grid allocation.

    // Public method for pre-order traversal
    public void preOrder() { // Initiates pre-order traversal
        ArrayList<Integer> result = new ArrayList<>(); // Stores traversal
        preOrder(root, result); // Calls recursive pre-order
        System.out.println("Pre-order traversal: " + result); // Prints result
    } // Summary: Prints nodes in pre-order (Root, Left, Right).

    // Private recursive method for pre-order traversal
    private void preOrder(Node node, ArrayList<Integer> result) { // Traverses subtree pre-order
        if (node == null) { // Base case: empty subtree
            return; // Stops recursion
        } // Handles null nodes
        result.add(node.value); // Visits root
        preOrder(node.left, result); // Recurses on left
        preOrder(node.right, result); // Recurses on right
    } // Summary: Adds root, then left, then right values to result (O(n) time).

    // Public method for in-order traversal
    public void inOrder() { // Initiates in-order traversal
        ArrayList<Integer> result = new ArrayList<>(); // Stores traversal
        inOrder(root, result); // Calls recursive in-order
        System.out.println("In-order traversal: " + result); // Prints result
    } // Summary: Prints nodes in in-order (Left, Root, Right), sorted for BST.

    // Private recursive method for in-order traversal
    private void inOrder(Node node, ArrayList<Integer> result) { // Traverses subtree in-order
        if (node == null) { // Base case: empty subtree
            return; // Stops recursion
        } // Handles null nodes
        inOrder(node.left, result); // Recurses on left
        result.add(node.value); // Visits root
        inOrder(node.right, result); // Recurses on right
    } // Summary: Adds left, then root, then right values to result (O(n) time).

    // Public method for post-order traversal
    public void postOrder() { // Initiates post-order traversal
        ArrayList<Integer> result = new ArrayList<>(); // Stores traversal
        postOrder(root, result); // Calls recursive post-order
        System.out.println("Post-order traversal: " + result); // Prints result
    } // Summary: Prints nodes in post-order (Left, Right, Root).

    // Private recursive method for post-order traversal
    private void postOrder(Node node, ArrayList<Integer> result) { // Traverses subtree post-order
        if (node == null) { // Base case: empty subtree
            return; // Stops recursion
        } // Handles null nodes
        postOrder(node.left, result); // Recurses on left
        postOrder(node.right, result); // Recurses on right
        result.add(node.value); // Visits root
    } // Summary: Adds left, then right, then root values to result (O(n) time).

    // Public method to display tree
    public void display() { // Initiates tree display
        if (root == null) { // Checks for empty tree
            System.out.println("Tree is empty"); // Prints empty message
            return; // Exits
        } // Handles edge case
        System.out.println("Tree structure:"); // Labels output
        int height = getHeight(root); // Gets tree height
        int width = (int) Math.pow(2, height) * 3; // Compact width for nodes
        char[][] grid = new char[height * 2][width]; // Grid for nodes and branches
        for (int i = 0; i < height * 2; i++) { // Initializes grid
            for (int j = 0; j < width; j++) {
                grid[i][j] = ' '; // Fills with spaces
            }
        } // Creates empty grid
        placeNode(root, 0, width / 2, 0, grid, width); // Places root and builds tree
        printGrid(grid); // Prints tree diagram
    } // Summary: Renders BST as ASCII tree with / \ branches.

    // Private method to place node and branches in grid
    private void placeNode(Node node, int row, int col, int level, char[][] grid, int width) { // Positions node and children
        if (node == null) { // Base case: empty subtree
            return; // Stops recursion
        } // Prevents null pointer issues
        // Place node value (pad to 3 characters)
        String valueStr = String.format("%3d", node.value); // Pads value (e.g., 7 -> "  7")
        for (int i = 0; i < 3 && col + i < width; i++) { // Places digits
            if (col + i >= 0) { // Ensures within grid
                grid[row][col + i] = valueStr.charAt(i); // Sets character
            }
        } // Node value centered at col
        if (node.left != null || node.right != null) { // If node has children
            int offset = (int) Math.pow(2, getHeight(root) - level - 1); // Tighter spacing
            if (node.left != null) { // Place left branch and child
                if (col - offset >= 0) { // Ensures within grid
                    grid[row + 1][col - offset] = '/'; // Draws / above left child
                }
                placeNode(node.left, row + 2, col - offset, level + 1, grid, width); // Recurses on left
            }
            if (node.right != null) { // Place right branch and child
                if (col + offset < width) { // Ensures within grid
                    grid[row + 1][col + offset] = '\\'; // Draws \ above right child
                }
                placeNode(node.right, row + 2, col + offset, level + 1, grid, width); // Recurses on right
            }
        } // Draws branches and recurses
    } // Summary: Places padded node value and / \ branches, aligns with child centers.

    // Private method to print grid
    private void printGrid(char[][] grid) { // Prints 2D grid as tree
        for (char[] row : grid) { // Iterates through rows
            boolean empty = true; // Tracks if row is all spaces
            for (char c : row) { // Checks row content
                if (c != ' ') { // If non-space found
                    empty = false; // Row isn’t empty
                    break; // Stops checking
                }
            }
            if (!empty) { // Prints non-empty rows
                System.out.println(new String(row).trim()); // Prints row, removes trailing spaces
            }
        } // Skips empty rows
    } // Summary: Prints grid rows, trimming empty ones for clean output.

    // Main function with test cases
    public static void main(String[] args) { // Entry point for testing
        Scanner scanner = new Scanner(System.in); // Scanner for interactive input

        // Test Case 1: Interactive BST construction
        System.out.println("Test Case 1: Interactive BST Construction");
        System.out.println("-----------------------------------------");
        BinarySearchTree bst1 = new BinarySearchTree(); // Creates new BST
        System.out.println("Enter values to insert (enter -1 to stop):"); // Prompt for values
        while (true) { // Loops until -1
            int value = scanner.nextInt(); // Reads value
            if (value == -1) { // Checks for stop condition
                break; // Exits loop
            }
            bst1.insert(value); // Inserts value
        }
        System.out.println("Is tree empty? " + bst1.isEmpty()); // Checks emptiness
        System.out.println("Is tree balanced? " + bst1.balanced()); // Checks balance
        bst1.preOrder(); // Prints pre-order traversal
        bst1.inOrder(); // Prints in-order traversal
        bst1.postOrder(); // Prints post-order traversal
        bst1.display(); // Displays tree
        System.out.println();

        // Test Case 2: Empty tree
        System.out.println("Test Case 2: Empty BST");
        System.out.println("-----------------------------------------");
        BinarySearchTree bst2 = new BinarySearchTree(); // Creates empty BST
        System.out.println("Is tree empty? " + bst2.isEmpty()); // Expected: true
        System.out.println("Is tree balanced? " + bst2.balanced()); // Expected: true
        bst2.preOrder(); // Expected: []
        bst2.inOrder(); // Expected: []
        bst2.postOrder(); // Expected: []
        bst2.display(); // Expected: "Tree is empty"
        System.out.println();

        // Test Case 3: Single node
        System.out.println("Test Case 3: Single Node BST");
        System.out.println("-----------------------------------------");
        BinarySearchTree bst3 = new BinarySearchTree(); // Creates new BST
        bst3.insert(10); // Inserts single node
        System.out.println("Is tree empty? " + bst3.isEmpty()); // Expected: false
        System.out.println("Is tree balanced? " + bst3.balanced()); // Expected: true
        bst3.preOrder(); // Expected: [10]
        bst3.inOrder(); // Expected: [10]
        bst3.postOrder(); // Expected: [10]
        bst3.display(); // Expected: 10
        System.out.println();

        // Test Case 4: Balanced BST
        System.out.println("Test Case 4: Balanced BST");
        System.out.println("-----------------------------------------");
        BinarySearchTree bst4 = new BinarySearchTree(); // Creates new BST
        int[] balancedValues = {50, 30, 70, 20, 40, 60, 80}; // Balanced tree values
        for (int value : balancedValues) { // Inserts values
            bst4.insert(value);
        }
        System.out.println("Is tree empty? " + bst4.isEmpty()); // Expected: false
        System.out.println("Is tree balanced? " + bst4.balanced()); // Expected: true
        bst4.preOrder(); // Expected: [50, 30, 20, 40, 70, 60, 80]
        bst4.inOrder(); // Expected: [20, 30, 40, 50, 60, 70, 80]
        bst4.postOrder(); // Expected: [20, 40, 30, 60, 80, 70, 50]
        bst4.display(); // Expected: Balanced tree
        System.out.println();

        // Test Case 5: Left-skewed BST
        System.out.println("Test Case 5: Left-Skewed BST");
        System.out.println("-----------------------------------------");
        BinarySearchTree bst5 = new BinarySearchTree(); // Creates new BST
        int[] leftSkewedValues = {100, 90, 80, 70}; // Left-skewed values
        for (int value : leftSkewedValues) { // Inserts values
            bst5.insert(value);
        }
        System.out.println("Is tree empty? " + bst5.isEmpty()); // Expected: false
        System.out.println("Is tree balanced? " + bst5.balanced()); // Expected: false
        bst5.preOrder(); // Expected: [100, 90, 80, 70]
        bst5.inOrder(); // Expected: [70, 80, 90, 100]
        bst5.postOrder(); // Expected: [70, 80, 90, 100]
        bst5.display(); // Expected: Left chain
        System.out.println();

        // Test Case 6: Right-skewed BST
        System.out.println("Test Case 6: Right-Skewed BST");
        System.out.println("-----------------------------------------");
        BinarySearchTree bst6 = new BinarySearchTree(); // Creates new BST
        int[] rightSkewedValues = {10, 20, 30, 40}; // Right-skewed values
        for (int value : rightSkewedValues) { // Inserts values
            bst6.insert(value);
        }
        System.out.println("Is tree empty? " + bst6.isEmpty()); // Expected: false
        System.out.println("Is tree balanced? " + bst6.balanced()); // Expected: false
        bst6.preOrder(); // Expected: [10, 20, 30, 40]
        bst6.inOrder(); // Expected: [10, 20, 30, 40]
        bst6.postOrder(); // Expected: [40, 30, 20, 10]
        bst6.display(); // Expected: Right chain
        System.out.println();

        // Test Case 7: Complex BST
        System.out.println("Test Case 7: Complex BST");
        System.out.println("-----------------------------------------");
        BinarySearchTree bst7 = new BinarySearchTree(); // Creates new BST
        int[] complexValues = {50, 30, 70, 20, 60, 80, 25}; // Complex tree values
        for (int value : complexValues) { // Inserts values
            bst7.insert(value);
        }
        System.out.println("Is tree empty? " + bst7.isEmpty()); // Expected: false
        System.out.println("Is tree balanced? " + bst7.balanced()); // Expected: true
        bst7.preOrder(); // Expected: [50, 30, 20, 25, 70, 60, 80]
        bst7.inOrder(); // Expected: [20, 25, 30, 50, 60, 70, 80]
        bst7.postOrder(); // Expected: [25, 20, 30, 60, 80, 70, 50]
        bst7.display(); // Expected: Mixed structure
        System.out.println();

        scanner.close(); // Closes scanner to prevent resource leak
    } // Summary: Tests BST with traversals, interactive input, empty, single node, balanced, skewed, and complex trees.
}