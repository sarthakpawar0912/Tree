package CDAC.BFS;
import java.util.LinkedList;
import java.util.Queue;

// Binary Search Tree class with BFS search
public class BFSMAIN {

    // Node class representing a single node in the BST
    static class Node {

        int data;       // Stores the value of the node

        Node left;      // Reference to the left child node

        Node right;     // Reference to the right child node

        // Default constructor: creates an empty node with default values
        public Node() {

            data = 0;

            left = null;

            right = null;

        }


        // Parameterized constructor: creates a node with a given value
        public Node(int val) {

            data = val;

            left = null;

            right = null;

        }
    }

    // Tree fields
    private Node root;  // Reference to the root node of the BST (topmost node)


    // Constructor: initializes an empty BST
    public BFSMAIN() {
        root = null;
    }

    // Method to add a new value to the BST
    // Maintains BST property: left child < parent <= right child
    void add(int val) {
        // Create a new node with the given value
        Node newNode = new Node(val);
        // If tree is empty, set new node as root
        if (root == null) {
            root = newNode;
        } else {
            // Traverse to find the correct spot
            Node trav = root;
            while (true) {
                // Go left if value is smaller
                if (val < trav.data) {
                    if (trav.left != null) {
                        trav = trav.left; // Move to left child
                    } else {
                        trav.left = newNode; // Add as left child
                        break;
                    }
                } else { // Go right if value is larger or equal
                    if (trav.right != null) {
                        trav = trav.right; // Move to right child
                    } else {
                        trav.right = newNode; // Add as right child
                        break;
                    }
                }
            }
        }
    }

    // Method to search for a key using Breadth-First Search
    public Node bfs(int key) {
        // Create a queue to hold nodes
        Queue<Node> queue = new LinkedList<>();
        // Start with the root
        queue.offer(root);
        // Process nodes until queue is empty
        while (!queue.isEmpty()) {
            // Get the next node
            Node trav = queue.poll();
            // Check if this node has the key
            if (trav.data == key) {
                return trav;
            }
            // Add left child to queue if it exists
            if (trav.left != null) {
                queue.offer(trav.left);
            }
            // Add right child to queue if it exists
            if (trav.right != null) {
                queue.offer(trav.right);
            }
        }
        // Return null if key is not found
        return null;
    }

    // Method to display the BST in two formats:
    // 1. In-order traversal (sorted order)
    // 2. Tree structure (visual hierarchy)
    void display() {
        // Show in-order traversal
        System.out.println("In-order (sorted):");
        displayInOrder(root);
        System.out.println();

        // Show tree structure
        System.out.println("Tree Structure:");
        if (root == null) {
            System.out.println("Empty");
        } else {
            displayTree(root, "");
        }
        System.out.println();
    }

    // Helper method for in-order traversal (left, root, right)
    private void displayInOrder(Node node) {
        // Stop if node is null
        if (node == null) {
            return;
        }
        // Visit left subtree
        displayInOrder(node.left);
        // Print current node
        System.out.print(node.data + " ");
        // Visit right subtree
        displayInOrder(node.right);
    }

    // Helper method for tree structure with indentation
    private void displayTree(Node node, String indent) {
        // Stop if node is null
        if (node == null) {
            return;
        }
        // Print right child (appears above)
        displayTree(node.right, indent + "  ");
        // Print current node
        System.out.println(indent + node.data);
        // Print left child (appears below)
        displayTree(node.left, indent + "  ");
    }

    // Main function to test the BST
    public static void main(String[] args) {
        // Create a new BST
        BFSMAIN bst = new BFSMAIN();

        // Add nodes to create this tree:
        //       10
        //      /  \
        //     5   15
        //    / \   \
        //   3   7  20
        bst.add(10); // Root
        bst.add(5);  // Left of 10
        bst.add(15); // Right of 10
        bst.add(3);  // Left of 5
        bst.add(7);  // Right of 5
        bst.add(20); // Right of 15

        // Display the tree in both formats
        bst.display();

        // Test BFS search
        int key = 7; // Search for node with value 7
        Node result = bst.bfs(key);
        System.out.println("BFS Search for " + key + ": " +
                (result != null ? "Found " + result.data : "Not found"));

        key = 20; // Search for a value not in the tree
        result = bst.bfs(key);
        System.out.println("BFS Search for " + key + ": " +
                (result != null ? "Found " + result.data : "Not found"));
    }
}