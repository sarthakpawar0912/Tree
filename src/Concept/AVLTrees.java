package Concept; // Organizes class in package

import java.util.Scanner; // Imports Scanner for interactive input

public class AVLTrees { // Declares public class for AVL Tree

    // Private inner class for tree nodes
    private class Node { // Private to encapsulate node structure
        int value; // Node data (AVL key)
        int height; // Height for balance checking
        Node left; // Left child reference
        Node right; // Right child reference

        public Node(int value) { // Constructor for new node
            this.value = value; // Sets value
            this.height = 0; // Initial height (leaf node)
        } // Summary: Creates a leaf node with given value.

        public int getValue() { // Getter for value
            return value; // Returns node’s value
        } // Summary: Provides access to value for display.
    } // Summary: Represents an AVL node with value, height, and child pointers.

    private Node root; // Root node of the AVL tree

    // Constructor
    public AVLTrees() { // Initializes empty AVL tree
        // root is null by default
    } // Summary: Creates an empty AVL tree.

    // Returns height of a node
    public int height(Node node) { // Computes height for balance checks
        if (node == null) { // Base case: empty subtree
            return -1; // Null node has height -1
        } // Handles null nodes
        return node.height; // Returns stored height
    } // Summary: Returns node height (-1 for null, else node.height).

    // Checks if tree is empty
    public boolean isEmpty() { // Verifies if AVL tree has no nodes
        return root == null; // True if root is null
    } // Summary: Returns true if tree is empty, false otherwise.

    // Public method to insert a value
    public void insert(int value) { // Initiates insertion into AVL tree
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
        return rotate(node); // Balances tree via rotations
    } // Summary: Inserts value like BST, updates height, balances (O(log n) average).

    // Private method to balance node via rotations
    private Node rotate(Node node) { // Checks balance factor and rotates
        int balanceFactor = height(node.left) - height(node.right); // Computes balance factor
        if (balanceFactor > 1) { // Left-heavy (balance > 1)
            if (height(node.left.left) >= height(node.left.right)) { // Left-Left case
                return rightRotate(node); // Single right rotation
            } else { // Left-Right case
                node.left = leftRotate(node.left); // Left rotate left child
                return rightRotate(node); // Right rotate node
            }
        }
        if (balanceFactor < -1) { // Right-heavy (balance < -1)
            if (height(node.right.right) >= height(node.right.left)) { // Right-Right case
                return leftRotate(node); // Single left rotation
            } else { // Right-Left case
                node.right = rightRotate(node.right); // Right rotate right child
                return leftRotate(node); // Left rotate node
            }
        }
        return node; // No rotation needed
    } // Summary: Balances node using LL, LR, RR, RL rotations (O(1) time).

    // Private method for right rotation
    private Node rightRotate(Node node) { // Performs right rotation
        Node newRoot = node.left; // New root is left child
        node.left = newRoot.right; // Move new root’s right to node’s left
        newRoot.right = node; // Node becomes new root’s right
        node.height = Math.max(height(node.left), height(node.right)) + 1; // Update node height
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1; // Update new root height
        return newRoot; // Return new subtree root
    } // Summary: Rotates right to fix LL or LR imbalance (O(1) time).

    // Private method for left rotation
    private Node leftRotate(Node node) { // Performs left rotation
        Node newRoot = node.right; // New root is right child
        node.right = newRoot.left; // Move new root’s left to node’s right
        newRoot.left = node; // Node becomes new root’s left
        node.height = Math.max(height(node.left), height(node.right)) + 1; // Update node height
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1; // Update new root height
        return newRoot; // Return new subtree root
    } // Summary: Rotates left to fix RR or RL imbalance (O(1) time).

    // Helper method to get tree height for display
    private int getHeight(Node node) { // Computes height for grid sizing
        if (node == null) { // Base case: empty tree
            return 0; // Height 0
        } // Handles null nodes
        return 1 + Math.max(getHeight(node.left), getHeight(node.right)); // Height is 1 + max child height
    } // Summary: Returns tree height for display layout.

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
    } // Summary: Renders AVL tree as ASCII tree with / \ branches.

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

        // Test Case 1: Interactive AVL construction
        System.out.println("Test Case 1: Interactive AVL Construction");
        System.out.println("-----------------------------------------");
        AVLTrees avl1 = new AVLTrees(); // Creates new AVL tree
        System.out.println("Enter values to insert (enter -1 to stop):"); // Prompt for values
        while (true) { // Loops until -1
            int value = scanner.nextInt(); // Reads value
            if (value == -1) { // Checks for stop condition
                break; // Exits loop
            }
            avl1.insert(value); // Inserts value
        }
        System.out.println("Is tree empty? " + avl1.isEmpty()); // Checks emptiness
        avl1.display(); // Displays tree
        System.out.println();

//        // Test Case 2: Empty tree
//        System.out.println("Test Case 2: Empty AVL");
//        System.out.println("-----------------------------------------");
//        AVLTrees avl2 = new AVLTrees(); // Creates empty AVL tree
//        System.out.println("Is tree empty? " + avl2.isEmpty()); // Expected: true
//        avl2.display(); // Expected: "Tree is empty"
//        System.out.println();
//
//        // Test Case 3: Single node
//        System.out.println("Test Case 3: Single Node AVL");
//        System.out.println("-----------------------------------------");
//        AVLTrees avl3 = new AVLTrees(); // Creates new AVL tree
//        avl3.insert(10); // Inserts single node
//        System.out.println("Is tree empty? " + avl3.isEmpty()); // Expected: false
//        avl3.display(); // Expected: 10
//        System.out.println();
//
//        // Test Case 4: Left-Left imbalance (triggers right rotation)
//        System.out.println("Test Case 4: Left-Left Imbalance");
//        System.out.println("-----------------------------------------");
//        AVLTrees avl4 = new AVLTrees(); // Creates new AVL tree
//        int[] llValues = {30, 20, 10}; // Triggers LL imbalance
//        for (int value : llValues) { // Inserts values
//            avl4.insert(value);
//        }
//        System.out.println("Is tree empty? " + avl4.isEmpty()); // Expected: false
//        avl4.display(); // Expected: Balanced tree (20 / \ 10 30)
//        System.out.println();
//
//        // Test Case 5: Right-Right imbalance (triggers left rotation)
//        System.out.println("Test Case 5: Right-Right Imbalance");
//        System.out.println("-----------------------------------------");
//        AVLTrees avl5 = new AVLTrees(); // Creates new AVL tree
//        int[] rrValues = {10, 20, 30}; // Triggers RR imbalance
//        for (int value : rrValues) { // Inserts values
//            avl5.insert(value);
//        }
//        System.out.println("Is tree empty? " + avl5.isEmpty()); // Expected: false
//        avl5.display(); // Expected: Balanced tree (20 / \ 10 30)
//        System.out.println();
//
//        // Test Case 6: Left-Right imbalance
//        System.out.println("Test Case 6: Left-Right Imbalance");
//        System.out.println("-----------------------------------------");
//        AVLTrees avl6 = new AVLTrees(); // Creates new AVL tree
//        int[] lrValues = {30, 10, 20}; // Triggers LR imbalance
//        for (int value : lrValues) { // Inserts values
//            avl6.insert(value);
//        }
//        System.out.println("Is tree empty? " + avl6.isEmpty()); // Expected: false
//        avl6.display(); // Expected: Balanced tree (20 / \ 10 30)
//        System.out.println();
//
//        // Test Case 7: Right-Left imbalance
//        System.out.println("Test Case 7: Right-Left Imbalance");
//        System.out.println("-----------------------------------------");
//        AVLTrees avl7 = new AVLTrees(); // Creates new AVL tree
//        int[] rlValues = {10, 30, 20}; // Triggers RL imbalance
//        for (int value : rlValues) { // Inserts values
//            avl7.insert(value);
//        }
//        System.out.println("Is tree empty? " + avl7.isEmpty()); // Expected: false
//        avl7.display(); // Expected: Balanced tree (20 / \ 10 30)
//        System.out.println();
//
//        // Test Case 8: Complex AVL tree
//        System.out.println("Test Case 8: Complex AVL Tree");
//        System.out.println("-----------------------------------------");
//        AVLTrees avl8 = new AVLTrees(); // Creates new AVL tree
//        int[] complexValues = {50, 30, 70, 20, 40, 60, 80}; // Complex balanced tree
//        for (int value : complexValues) { // Inserts values
//            avl8.insert(value);
//        }
//        System.out.println("Is tree empty? " + avl8.isEmpty()); // Expected: false
//        avl8.display(); // Expected: Balanced tree
//        System.out.println();

        scanner.close(); // Closes scanner to prevent resource leak
    } // Summary: Tests AVL tree with all rotation cases and interactive input.
}