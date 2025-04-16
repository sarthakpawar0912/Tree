package Concept; // Organizes class in package

import java.util.Scanner; // Imports Scanner for interactive input

public class SegmentTree { // Declares public class for Segment Tree

    // Private inner class for segment tree nodes
    private class Node { // Private to encapsulate node structure
        int value; // Aggregate value (sum for range sum queries)
        int min; // Minimum value in segment
        int max; // Maximum value in segment
        int start; // Start index of segment
        int end; // End index of segment
        int lazy; // Lazy propagation value for range updates
        Node left; // Left child reference
        Node right; // Right child reference

        public Node(int value, int start, int end) { // Constructor for new node
            this.value = value; // Sets sum
            this.min = value; // Sets min
            this.max = value; // Sets max
            this.start = start; // Sets segment start
            this.end = end; // Sets segment end
            this.lazy = 0; // No pending updates
        } // Summary: Creates a node with aggregate value and range.

        public int getValue() { // Getter for value
            return value; // Returns sum for display
        } // Summary: Provides access to value for display.
    } // Summary: Represents a segment tree node with sum, min, max, range, and children.

    private Node root; // Root node of the segment tree
    private int[] array; // Original array for reference

    // Constructor
    public SegmentTree(int[] arr) { // Initializes segment tree from array
        this.array = arr; // Stores array
        if (arr != null && arr.length > 0) { // If array is valid
            root = build(0, arr.length - 1); // Builds tree
        }
    } // Summary: Creates segment tree for given array.

    // Private method to build segment tree
    private Node build(int start, int end) { // Builds tree for range [start, end]
        if (start == end) { // Leaf node
            return new Node(array[start], start, end); // Stores array element
        } // Base case: single element
        int mid = start + (end - start) / 2; // Finds midpoint
        Node left = build(start, mid); // Builds left subtree
        Node right = build(mid + 1, end); // Builds right subtree
        Node node = new Node(0, start, end); // Creates parent node
        node.left = left; // Links left child
        node.right = right; // Links right child
        node.value = left.value + right.value; // Computes sum
        node.min = Math.min(left.min, right.min); // Computes min
        node.max = Math.max(left.max, right.max); // Computes max
        return node; // Returns node
    } // Summary: Recursively builds tree, aggregates sum/min/max (O(n) time).

    // Public method for range sum query
    public int rangeSum(int left, int right) { // Queries sum in [left, right]
        if (root == null || left < 0 || right >= array.length || left > right) { // Invalid query
            return 0; // Returns 0
        }
        return rangeSum(root, left, right); // Calls recursive query
    } // Summary: Initiates range sum query.

    // Private method for range sum query
    private int rangeSum(Node node, int left, int right) { // Computes sum in [left, right]
        if (node == null) { // Base case: null node
            return 0; // No contribution
        }
        propagate(node); // Applies pending lazy updates
        if (node.start > right || node.end < left) { // No overlap
            return 0; // No contribution
        }
        if (node.start >= left && node.end <= right) { // Full overlap
            return node.value; // Returns segment sum
        }
        // Partial overlap
        return rangeSum(node.left, left, right) + rangeSum(node.right, left, right); // Combines children
    } // Summary: Recursively computes sum for overlapping segments (O(log n) time).

    // Public method for range minimum query
    public int rangeMin(int left, int right) { // Queries minimum in [left, right]
        if (root == null || left < 0 || right >= array.length || left > right) { // Invalid query
            return Integer.MAX_VALUE; // Returns max int
        }
        return rangeMin(root, left, right); // Calls recursive query
    } // Summary: Initiates range minimum query.

    // Private method for range minimum query
    private int rangeMin(Node node, int left, int right) { // Computes min in [left, right]
        if (node == null) { // Base case: null node
            return Integer.MAX_VALUE; // Neutral value
        }
        propagate(node); // Applies pending lazy updates
        if (node.start > right || node.end < left) { // No overlap
            return Integer.MAX_VALUE; // No contribution
        }
        if (node.start >= left && node.end <= right) { // Full overlap
            return node.min; // Returns segment min
        }
        // Partial overlap
        return Math.min(rangeMin(node.left, left, right), rangeMin(node.right, left, right)); // Min of children
    } // Summary: Recursively computes min for overlapping segments (O(log n) time).

    // Public method for range maximum query
    public int rangeMax(int left, int right) { // Queries maximum in [left, right]
        if (root == null || left < 0 || right >= array.length || left > right) { // Invalid query
            return Integer.MIN_VALUE; // Returns min int
        }
        return rangeMax(root, left, right); // Calls recursive query
    } // Summary: Initiates range maximum query.

    // Private method for range maximum query
    private int rangeMax(Node node, int left, int right) { // Computes max in [left, right]
        if (node == null) { // Base case: null node
            return Integer.MIN_VALUE; // Neutral value
        }
        propagate(node); // Applies pending lazy updates
        if (node.start > right || node.end < left) { // No overlap
            return Integer.MIN_VALUE; // No contribution
        }
        if (node.start >= left && node.end <= right) { // Full overlap
            return node.max; // Returns segment max
        }
        // Partial overlap
        return Math.max(rangeMax(node.left, left, right), rangeMax(node.right, left, right)); // Max of children
    } // Summary: Recursively computes max for overlapping segments (O(log n) time).

    // Public method for point update
    public void update(int index, int value) { // Updates array[index] to value
        if (root == null || index < 0 || index >= array.length) { // Invalid index
            return; // No update
        }
        array[index] = value; // Updates array
        update(root, index, value); // Updates tree
    } // Summary: Initiates point update.

    // Private method for point update
    private void update(Node node, int index, int value) { // Updates node for index
        if (node == null || node.start > index || node.end < index) { // Outside range
            return; // No update
        }
        propagate(node); // Applies pending lazy updates
        if (node.start == node.end && node.start == index) { // Leaf node
            node.value = value; // Updates sum
            node.min = value; // Updates min
            node.max = value; // Updates max
            return; // Done
        }
        update(node.left, index, value); // Recurses on left
        update(node.right, index, value); // Recurses on right
        node.value = node.left.value + node.right.value; // Updates sum
        node.min = Math.min(node.left.min, node.right.min); // Updates min
        node.max = Math.max(node.left.max, node.right.max); // Updates max
    } // Summary: Updates leaf and ancestors for index (O(log n) time).

    // Public method for range update
    public void rangeUpdate(int left, int right, int value) { // Adds value to [left, right]
        if (root == null || left < 0 || right >= array.length || left > right) { // Invalid range
            return; // No update
        }
        rangeUpdate(root, left, right, value); // Calls recursive update
    } // Summary: Initiates range update.

    // Private method for range update
    private void rangeUpdate(Node node, int left, int right, int value) { // Adds value to [left, right]
        if (node == null) { // Base case: null node
            return; // No update
        }
        propagate(node); // Applies pending lazy updates
        if (node.start > right || node.end < left) { // No overlap
            return; // No update
        }
        if (node.start >= left && node.end <= right) { // Full overlap
            node.value += value * (node.end - node.start + 1); // Updates sum
            node.min += value; // Updates min
            node.max += value; // Updates max
            if (node.start != node.end) { // Not a leaf
                node.lazy += value; // Marks lazy update
            }
            return; // Done
        }
        // Partial overlap
        rangeUpdate(node.left, left, right, value); // Recurses on left
        rangeUpdate(node.right, left, right, value); // Recurses on right
        node.value = node.left.value + node.right.value; // Updates sum
        node.min = Math.min(node.left.min, node.right.min); // Updates min
        node.max = Math.max(node.left.max, node.right.max); // Updates max
    } // Summary: Applies increment to overlapping segments, uses lazy propagation (O(log n) time).

    // Private method for lazy propagation
    private void propagate(Node node) { // Applies lazy updates to node and children
        if (node == null || node.lazy == 0) { // No pending updates
            return; // No propagation
        }
        node.value += node.lazy * (node.end - node.start + 1); // Updates sum
        node.min += node.lazy; // Updates min
        node.max += node.lazy; // Updates max
        if (node.start != node.end) { // Not a leaf
            if (node.left != null) { // Propagates to left child
                node.left.lazy += node.lazy; // Adds lazy value
            }
            if (node.right != null) { // Propagates to right child
                node.right.lazy += node.lazy; // Adds lazy value
            }
        }
        node.lazy = 0; // Clears lazy value
    } // Summary: Propagates lazy updates to maintain consistency (O(1) time).

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
        System.out.println("Segment Tree structure (sum values, [start,end]):"); // Labels output
        int height = getHeight(root); // Gets tree height
        int width = (int) Math.pow(2, height) * 10; // Wider width for range labels
        char[][] grid = new char[height * 2][width]; // Grid for nodes and branches
        for (int i = 0; i < height * 2; i++) { // Initializes grid
            for (int j = 0; j < width; j++) {
                grid[i][j] = ' '; // Fills with spaces
            }
        } // Creates empty grid
        placeNode(root, 0, width / 2, 0, grid, width); // Places root and builds tree
        printGrid(grid); // Prints tree diagram
    } // Summary: Renders segment tree as ASCII tree with / \ branches.

    // Private method to place node and branches in grid
    private void placeNode(Node node, int row, int col, int level, char[][] grid, int width) { // Positions node and children
        if (node == null) { // Base case: empty subtree
            return; // Stops recursion
        } // Prevents null pointer issues
        // Place node value and range (e.g., "10[0,3]")
        String label = String.format("%d[%d,%d]", node.value, node.start, node.end); // Formats sum and range
        for (int i = 0; i < label.length() && col + i < width; i++) { // Places characters
            if (col + i >= 0) { // Ensures within grid
                grid[row][col + i] = label.charAt(i); // Sets character
            }
        } // Node label centered at col
        if (node.left != null || node.right != null) { // If node has children
            int offset = (int) Math.pow(2, getHeight(root) - level - 1) * 5; // Wider spacing
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
    } // Summary: Places node label (sum[start,end]) and / \ branches.

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

        // Test Case 1: Interactive Segment Tree
        System.out.println("Test Case 1: Interactive Segment Tree");
        System.out.println("-----------------------------------------");
        System.out.println("Enter array size:"); // Prompts for size
        int n1 = scanner.nextInt(); // Reads size
        int[] arr1 = new int[n1]; // Creates array
        System.out.println("Enter " + n1 + " elements:"); // Prompts for elements
        for (int i = 0; i < n1; i++) { // Reads elements
            arr1[i] = scanner.nextInt();
        }
        SegmentTree st1 = new SegmentTree(arr1); // Creates segment tree
        System.out.println("Is tree empty? " + (st1.root == null)); // Checks emptiness
        st1.display(); // Displays tree
        System.out.println("Enter number of queries:"); // Prompts for queries
        int q1 = scanner.nextInt(); // Reads query count
        for (int i = 0; i < q1; i++) { // Processes queries
            System.out.println("Enter query type (1=Sum, 2=Min, 3=Max, 4=Point Update, 5=Range Update):");
            int type = scanner.nextInt(); // Reads query type
            if (type == 4) { // Point update
                System.out.println("Enter index and value:");
                int idx = scanner.nextInt(); // Reads index
                int val = scanner.nextInt(); // Reads value
                st1.update(idx, val); // Updates
                System.out.println("After update:");
                st1.display(); // Shows updated tree
            } else if (type == 5) { // Range update
                System.out.println("Enter left, right, value:");
                int left = scanner.nextInt(); // Reads left
                int right = scanner.nextInt(); // Reads right
                int val = scanner.nextInt(); // Reads value
                st1.rangeUpdate(left, right, val); // Updates range
                System.out.println("After range update:");
                st1.display(); // Shows updated tree
            } else { // Range query
                System.out.println("Enter left and right indices:");
                int left = scanner.nextInt(); // Reads left
                int right = scanner.nextInt(); // Reads right
                if (type == 1) { // Sum query
                    System.out.println("Sum [" + left + "," + right + "]: " + st1.rangeSum(left, right));
                } else if (type == 2) { // Min query
                    System.out.println("Min [" + left + "," + right + "]: " + st1.rangeMin(left, right));
                } else if (type == 3) { // Max query
                    System.out.println("Max [" + left + "," + right + "]: " + st1.rangeMax(left, right));
                }
            }
        }
        System.out.println();

        // Test Case 2: Empty array
        System.out.println("Test Case 2: Empty Array");
        System.out.println("-----------------------------------------");
        SegmentTree st2 = new SegmentTree(new int[0]); // Creates empty tree
        System.out.println("Is tree empty? " + (st2.root == null)); // Expected: true
        st2.display(); // Expected: "Tree is empty"
        System.out.println();

        // Test Case 3: Single element
        System.out.println("Test Case 3: Single Element");
        System.out.println("-----------------------------------------");
        int[] arr3 = {5}; // Single element array
        SegmentTree st3 = new SegmentTree(arr3); // Creates tree
        System.out.println("Is tree empty? " + (st3.root == null)); // Expected: false
        st3.display(); // Expected: 5[0,0]
        System.out.println("Sum [0,0]: " + st3.rangeSum(0, 0)); // Expected: 5
        System.out.println("Min [0,0]: " + st3.rangeMin(0, 0)); // Expected: 5
        System.out.println("Max [0,0]: " + st3.rangeMax(0, 0)); // Expected: 5
        st3.update(0, 10); // Updates to 10
        System.out.println("After update [0]=10:");
        st3.display(); // Expected: 10[0,0]
        System.out.println();

        // Test Case 4: Range queries
        System.out.println("Test Case 4: Range Queries");
        System.out.println("-----------------------------------------");
        int[] arr4 = {1, 3, 5, 7, 9}; // Array for range queries
        SegmentTree st4 = new SegmentTree(arr4); // Creates tree
        System.out.println("Is tree empty? " + (st4.root == null)); // Expected: false
        st4.display(); // Shows tree
        System.out.println("Sum [1,3]: " + st4.rangeSum(1, 3)); // Expected: 3+5+7=15
        System.out.println("Min [1,3]: " + st4.rangeMin(1, 3)); // Expected: 3
        System.out.println("Max [1,3]: " + st4.rangeMax(1, 3)); // Expected: 7
        System.out.println();

        // Test Case 5: Point updates
        System.out.println("Test Case 5: Point Updates");
        System.out.println("-----------------------------------------");
        int[] arr5 = {2, 4, 6, 8}; // Array for updates
        SegmentTree st5 = new SegmentTree(arr5); // Creates tree
        System.out.println("Is tree empty? " + (st5.root == null)); // Expected: false
        st5.display(); // Shows tree
        st5.update(2, 10); // Updates index 2 to 10
        System.out.println("After update [2]=10:");
        st5.display(); // Shows updated tree
        System.out.println("Sum [0,3]: " + st5.rangeSum(0, 3)); // Expected: 2+4+10+8=24
        System.out.println();

        // Test Case 6: Range updates
        System.out.println("Test Case 6: Range Updates");
        System.out.println("-----------------------------------------");
        int[] arr6 = {1, 2, 3, 4, 5}; // Array for range updates
        SegmentTree st6 = new SegmentTree(arr6); // Creates tree
        System.out.println("Is tree empty? " + (st6.root == null)); // Expected: false
        st6.display(); // Shows tree
        st6.rangeUpdate(1, 3, 5); // Adds 5 to indices 1-3
        System.out.println("After range update [1,3]+=5:");
        st6.display(); // Shows updated tree
        System.out.println("Sum [1,3]: " + st6.rangeSum(1, 3)); // Expected: (2+5)+(3+5)+(4+5)=24
        System.out.println();

        scanner.close(); // Closes scanner to prevent resource leak
    } // Summary: Tests segment tree with queries and updates.
}