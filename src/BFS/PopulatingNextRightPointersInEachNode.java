package BFS;

public class PopulatingNextRightPointersInEachNode { // Class for populating next pointers


    static class Node { // Node structure for perfect binary tree
        public int val; // Node value
        public Node left; // Left child
        public Node right; // Right child
        public Node next; // Next right node at same level

        public Node() {}

        public Node(int _val) { // Constructor with value
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) { // Full constructor
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        } // Summary: Creates node with value, children, and next pointer.
    }


    // Populates next pointers to point to right neighbor
    public Node connect(Node root) { // Main method to connect next pointers
        if (root == null) { // Empty tree
            return null; // Returns null
        } // Handles base case

        Node leftmost = root; // Tracks leftmost node of current level

        while (leftmost.left != null) { // While there are more levels (has children)
            Node current = leftmost; // Start at leftmost node of current level
            while (current != null) { // Traverse level using next pointers
                current.left.next = current.right; // Connect left child to right child
                if (current.next != null) { // If there's a next node in level
                    current.right.next = current.next.left; // Connect right to next's left
                }
                current = current.next; // Move to next node in level
            }
            leftmost = leftmost.left; // Move to leftmost node of next level
        }
        return root; // Returns modified tree
    } // Summary: Connects next pointers level by level (O(n) time, O(1) space).

    // Main function to test next pointer population
    public static void main(String[] args) { // Entry point for testing
        PopulatingNextRightPointersInEachNode solution = new PopulatingNextRightPointersInEachNode(); // Creates instance

        // Test Case 1: Example 1 - [1,2,3,4,5,6,7]
        System.out.println("Test Case 1:");
        Node root1 = new Node(1); // Root
        root1.left = new Node(2); // Level 1 left
        root1.right = new Node(3); // Level 1 right
        root1.left.left = new Node(4); // Level 2 left
        root1.left.right = new Node(5); // Level 2 middle
        root1.right.left = new Node(6); // Level 2 middle
        root1.right.right = new Node(7); // Level 2 right
        Node result1 = solution.connect(root1); // Connect next pointers
        printLevelOrder(result1); // Print level order with next pointers

        // Test Case 2: Example 2 - []
        System.out.println("\nTest Case 2:");
        Node root2 = null; // Empty tree
        Node result2 = solution.connect(root2); // Connect (no-op)
        printLevelOrder(result2); // Expected: []
    } // Summary: Tests next pointer population with examples.

    // Helper method to print level order with next pointers for verification
    private static void printLevelOrder(Node root) { // Prints tree level by level
        if (root == null) { // Empty tree
            System.out.println("Output: []");
            return;
        }
        System.out.print("Output: [");
        Node levelStart = root; // Start of current level
        while (levelStart != null) { // Process each level
            Node current = levelStart; // Current node in level
            while (current != null) { // Traverse level using next
                System.out.print(current.val); // Print value
                if (current.next != null || current.left == null) { // End of level or tree
                    System.out.print(",");
                }
                current = current.next; // Move to next node
            }
            System.out.print("#"); // Mark end of level
            if (levelStart.left != null) { // Move to next level
                System.out.print(",");
            }
            levelStart = levelStart.left; // Start of next level
        }
        System.out.println("]");
    }
}
