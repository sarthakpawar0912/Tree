package Concept;
import java.util.Scanner;

public class BinaryTree {

    // Nested Node class to represent tree nodes
    private static class Node {

        int value; // Node data

        Node left; // Left child reference

        Node right; // Right child reference

        public Node(int value) { // Constructor for leaf node
            this.value = value; // Sets value, children default to null
        }

    } // Summary: Represents a tree node with value and child pointers.


    private Node root; // Root node of the tree

    // Constructor
    public BinaryTree() {

    } // Summary: Creates an empty binary tree.

    // Public method to populate tree interactively
    public void populate(Scanner scanner) {
       
        System.out.println("Enter the root node value (integer): "); // Clear prompt for root
        int value = scanner.nextInt(); // Reads root value
        root = new Node(value); // Creates root node
        populate(scanner, root); // Recursively builds subtrees

    } // Summary: Initializes root and delegates to recursive populate.

    // Private recursive method to build subtrees
    private void populate(Scanner scanner, Node node) { // Builds left/right subtrees

        // Prompt for left child
        System.out.println("Add left child for node " + node.value + "? Enter 'y' for yes, 'n' for no: "); // Clear instruction
        String leftInput = scanner.next(); // Reads y/n
        if (leftInput.equalsIgnoreCase("y")) { // Checks for yes
            System.out.println("Enter value for left child of " + node.value + " (integer): "); // Specific value prompt
            int value = scanner.nextInt(); // Reads child value
            node.left = new Node(value); // Creates left node
            populate(scanner, node.left); // Recurses on left child
        } // If n or invalid, skips left child

        // Prompt for right child
        System.out.println("Add right child for node " + node.value + "? Enter 'y' for yes, 'n' for no: "); // Clear instruction
        String rightInput = scanner.next(); // Reads y/n
        if (rightInput.equalsIgnoreCase("y")) { // Checks for yes
            System.out.println("Enter value for right child of " + node.value + " (integer): "); // Specific value prompt
            int value = scanner.nextInt(); // Reads child value
            node.right = new Node(value); // Creates right node
            populate(scanner, node.right); // Recurses on right child

        } // If n or invalid, skips right child

    } // Summary: Recursively adds children based on user input, stops when 'n' is entered.

    // Helper method to get tree height
    private int getHeight(Node node) { // Computes height of subtree

        if (node == null) { // Base case: empty tree
            return 0; // Height 0
        } // Handles null nodes

        return 1 + Math.max(getHeight(node.left), getHeight(node.right)); // Height is 1 + max of child heights

    } // Summary: Returns tree height for display sizing.

    // Public method to display tree
    public void display() { // Initiates tree display

        if (root == null) { // Checks for empty tree
            System.out.println("Tree is empty"); // Prints empty message
            return; // Exits
        } // Handles edge case

        System.out.println("Tree structure:"); // Labels output

        int height = getHeight(root); // Gets tree height

        int width = (int) Math.pow(2, height) * 3; // Compact width

        char[][] grid = new char[height * 2][width]; // Rows for nodes and branches

        for (int i = 0; i < height * 2; i++) { // Initializes grid
            for (int j = 0; j < width; j++) {
                grid[i][j] = ' '; // Fills with spaces
            }
        } // Creates empty grid

        placeNode(root, 0, width / 2, 0, grid, width); // Places root and builds tree

        printGrid(grid); // Prints grid as tree

    } // Summary: Sets up compact grid and renders tree as ASCII diagram.

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
        }
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
        }
    } // Summary: Prints grid rows, trimming empty ones for clean output.


    // Helper method to insert node programmatically (for testing)
    public void insert(int value, String position, Node parent) { // Inserts node at specified position
        if (root == null && position.equals("root")) { // If inserting root
            root = new Node(value); // Creates root
            return; // Exits
        } // Handles root case
        if (parent == null) { // If parent doesn’t exist
            return; // Exits
        } // Prevents invalid insertion
        if (position.equals("left")) { // Insert as left child
            parent.left = new Node(value); // Creates left node
        } else if (position.equals("right")) { // Insert as right child
            parent.right = new Node(value); // Creates right node
        } // Ignores invalid positions
    } // Summary: Programmatically adds nodes for test cases, specifying parent and position.


    // Main function with multiple test cases
    public static void main(String[] args) { // Entry point for testing
        Scanner scanner = new Scanner(System.in); // Scanner for input


        System.out.println("Test Case 1: Interactive Binary Tree Construction");
        System.out.println("-----------------------------------------------");
        BinaryTree tree1 = new BinaryTree(); // Creates new tree
        tree1.populate(scanner); // Builds tree interactively
        tree1.display(); // Displays tree
        System.out.println();


        System.out.println("Test Case 2: Single Node Tree");
        System.out.println("-----------------------------------------------");
        BinaryTree tree2 = new BinaryTree(); // Creates new tree
        tree2.insert(1, "root", null); // Inserts root node
        tree2.display(); // Expected: Single node
        System.out.println();

        // Test Case 3: Balanced tree
        System.out.println("Test Case 3: Balanced Binary Tree");
        System.out.println("-----------------------------------------------");
        BinaryTree tree3 = new BinaryTree(); // Creates new tree
        tree3.insert(1, "root", null); // Root
        Node root3 = tree3.root; // Gets root for insertion
        tree3.insert(2, "left", root3); // Left child
        tree3.insert(3, "right", root3); // Right child
        tree3.insert(4, "left", root3.left); // Left of 2
        tree3.insert(5, "right", root3.left); // Right of 2
        tree3.insert(6, "left", root3.right); // Left of 3
        tree3.insert(7, "right", root3.right); // Right of 3
        tree3.display(); // Expected: Balanced tree
        System.out.println();

        // Test Case 4: Left-skewed tree
        System.out.println("Test Case 4: Left-Skewed Binary Tree");
        System.out.println("-----------------------------------------------");
        BinaryTree tree4 = new BinaryTree(); // Creates new tree
        tree4.insert(1, "root", null); // Root
        Node root4 = tree4.root; // Gets root
        tree4.insert(2, "left", root4); // Left of 1
        tree4.insert(3, "left", root4.left); // Left of 2
        tree4.insert(4, "left", root4.left.left); // Left of 3
        tree4.display(); // Expected: Left chain
        System.out.println();

        // Test Case 5: Right-skewed tree
        System.out.println("Test Case 5: Right-Skewed Binary Tree");
        System.out.println("-----------------------------------------------");
        BinaryTree tree5 = new BinaryTree(); // Creates new tree
        tree5.insert(1, "root", null); // Root
        Node root5 = tree5.root; // Gets root
        tree5.insert(2, "right", root5); // Right of 1
        tree5.insert(3, "right", root5.right); // Right of 2
        tree5.insert(4, "right", root5.right.right); // Right of 3
        tree5.display(); // Expected: Right chain
        System.out.println();

        // Test Case 6: Complex tree
        System.out.println("Test Case 6: Complex Binary Tree");
        System.out.println("-----------------------------------------------");
        BinaryTree tree6 = new BinaryTree(); // Creates new tree
        tree6.insert(1, "root", null); // Root
        Node root6 = tree6.root; // Gets root
        tree6.insert(2, "left", root6); // Left of 1
        tree6.insert(3, "right", root6); // Right of 1
        tree6.insert(4, "left", root6.left); // Left of 2
        tree6.insert(5, "right", root6.right); // Right of 3
        tree6.insert(6, "left", root6.left.left); // Left of 4
        tree6.display(); // Expected: Mixed structure
        System.out.println();

        scanner.close(); // Closes scanner to prevent resource leak
    } // Summary: Tests binary tree with interactive input, single node, balanced, skewed, and complex trees.
}