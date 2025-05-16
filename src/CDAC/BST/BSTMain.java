package CDAC.BST;

// Binary Search Tree implementation
class BinarySearchTree {

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

    // Constructor: initializes an empty BST with no nodes
    public BinarySearchTree() {
        root = null;
    }

    // Method to add a new value to the BST
    // This maintains BST property: left child < parent <= right child
    void add(int val) {
        // Create a new node with the given value
        Node newNode = new Node(val);

        // If the tree is empty (no root), make the new node the root
        if (root == null) {
            root = newNode;
        } else {
            // Start traversing from the root to find the correct spot
            Node trav = root;
            while (true) {
                // If the new value is less than the current node's value
                if (val < trav.data) {
                    // If there’s a left child, move to it
                    if (trav.left != null) {
                        trav = trav.left;
                    } else {
                        // If no left child, insert the new node as the left child
                        trav.left = newNode;
                        break; // Exit the loop since insertion is done
                    }
                } else { // If the new value is greater than or equal to the current node's value
                    // If there’s a right child, move to it
                    if (trav.right != null) {
                        trav = trav.right;
                    } else {
                        // If no right child, insert the new node as the right child
                        trav.right = newNode;
                        break; // Exit the loop since insertion is done
                    }
                }
            }
        }
    }

    public void preOrder(Node trav){
        if(trav==null){
            return;
        }
        System.out.println(trav.data+", ");
        preOrder(trav.left);
        preOrder(trav.right);
    }

    public  void preorder(){
        preOrder(root);
    }



    public void inOrder(Node trav){
        if(trav==null){
            return;
        }
        inOrder(trav.left);
        System.out.println(trav.data+", ");
        inOrder(trav.right);
    }
    public void inorder(){
        inOrder(root);
    }



    public void postOrder(Node trav){
        if(trav==null){
            return;
        }
        postOrder(trav.left);
        postOrder(trav.right);
        System.out.println(trav.data+", ");
    }
    public void postorder(){
        postOrder(root);
    }










    // Method to display the BST using in-order traversal
    // In-order traversal visits nodes in ascending order: left, root, right
    void display() {
        // Print a header for clarity
        System.out.println("In-order traversal of the BST:");
        // Start the recursive in-order traversal from the root
        displayInOrder(root);
        // Add a newline for clean output
        System.out.println();
    }

    // Helper method for in-order traversal
    private void displayInOrder(Node node) {
        // Base case: if the node is null, stop recursion
        if (node == null) {
            return;
        }
        // Recursively visit the left subtree first
        displayInOrder(node.left);
        // Print the current node's value
        System.out.print(node.data + " ");
        // Recursively visit the right subtree
        displayInOrder(node.right);
    }
}

// Main class to demonstrate Binary Search Tree operations
public class BSTMain {
    // Main function to test the BST
    public static void main(String[] args) {
        // Create a new Binary Search Tree
        BinarySearchTree bst = new BinarySearchTree();

        // Add multiple nodes to build a BST
        // This creates a tree like:
        //       10
        //      /  \
        //     5   15
        //    / \   \
        //   3   7  20
        bst.add(10); // Root node
        bst.add(5);  // Left child of 10
        bst.add(15); // Right child of 10
        bst.add(3);  // Left child of 5
        bst.add(7);  // Right child of 5
        bst.add(20); // Right child of 15

        // Displa y the tree’s nodes in sorted order
        bst.display();

        bst.preorder();
        System.out.println();
        bst.inorder();
        System.out.println();
        bst.postorder();
    }
}