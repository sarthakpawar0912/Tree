 package Concept; // Organizes class in package

import java.util.ArrayList; // For heap implementation
import java.util.HashMap; // For frequency and encoder maps
import java.util.Map; // For entry set
import java.util.Scanner; // For interactive input

public class HuffmanCoding { // Declares class for Huffman Coding

    private HashMap<Character, String> encoder; // Maps character to Huffman code
    private Node root; // Root of Huffman tree

    // Private inner class for Huffman tree nodes
    private class Node implements Comparable<Node> { // Node with comparison for heap
        Character data; // Character (null for internal nodes)
        int cost; // Frequency or sum of child frequencies
        Node left; // Left child
        Node right; // Right child

        public Node(Character data, int cost) { // Constructor for leaf nodes
            this.data = data; // Sets character
            this.cost = cost; // Sets frequency
            this.left = null; // No left child
            this.right = null; // No right child
        } // Summary: Creates leaf node with char and frequency.

        public Node(int cost, Node left, Node right) { // Constructor for internal nodes
            this.data = null; // No character
            this.cost = cost; // Combined frequency
            this.left = left; // Left child
            this.right = right; // Right child
        } // Summary: Creates internal node with frequency and children.

        @Override
        public int compareTo(Node other) { // Compares by frequency
            return this.cost - other.cost; // Lower cost first
        } // Summary: Orders nodes for min-heap.
    } // Summary: Defines Huffman tree node structure.

    // Private class for min-heap
    private class MinHeap { // Implements min-heap for nodes
        private ArrayList<Node> heap; // Stores nodes

        public MinHeap() { // Constructor
            heap = new ArrayList<>(); // Initializes empty heap
        } // Summary: Creates empty min-heap.

        public void insert(Node node) { // Adds node to heap
            heap.add(node); // Appends node
            int idx = heap.size() - 1; // Gets index
            while (idx > 0) { // Bubbles up
                int parent = (idx - 1) / 2; // Parent index
                if (heap.get(parent).compareTo(heap.get(idx)) > 0) { // Parent > child
                    swap(idx, parent); // Swaps nodes
                    idx = parent; // Moves up
                } else {
                    break; // Heap property satisfied
                }
            }
        } // Summary: Inserts node, maintains min-heap (O(log n)).

        public Node remove() { // Removes min node
            if (heap.isEmpty()) { // Empty heap
                return null; // Returns null
            }
            Node min = heap.get(0); // Gets root
            if (heap.size() == 1) { // Single node
                heap.remove(0); // Removes it
                return min; // Returns min
            }
            heap.set(0, heap.remove(heap.size() - 1)); // Moves last to root
            int idx = 0; // Starts at root
            while (true) { // Bubbles down
                int left = 2 * idx + 1; // Left child index
                int right = 2 * idx + 2; // Right child index
                int smallest = idx; // Assumes root smallest
                if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
                    smallest = left; // Updates if left smaller
                }
                if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
                    smallest = right; // Updates if right smaller
                }
                if (smallest == idx) { // Heap property satisfied
                    break; // Exits
                }
                swap(idx, smallest); // Swaps with smallest
                idx = smallest; // Moves down
            }
            return min; // Returns min node
        } // Summary: Removes min node, maintains heap (O(log n)).

        public int size() { // Returns heap size
            return heap.size(); // Number of nodes
        } // Summary: Returns current node count.

        private void swap(int i, int j) { // Swaps nodes
            Node temp = heap.get(i); // Stores temp
            heap.set(i, heap.get(j)); // Sets i to j
            heap.set(j, temp); // Sets j to temp
        } // Summary: Swaps heap elements.
    } // Summary: Min-heap for building Huffman tree.

    // Constructor to build Huffman tree
    public HuffmanCoding(String input) throws Exception { // Initializes coding
        if (input == null || input.isEmpty()) { // Validates input
            throw new Exception("Input string cannot be null or empty"); // Throws error
        }
        buildHuffmanTree(input); // Builds tree and encoder
    } // Summary: Sets up Huffman coding for input.

    // Private method to build Huffman tree and encoder
    private void buildHuffmanTree(String input) { // Constructs tree
        // Build frequency map
        HashMap<Character, Integer> freqMap = new HashMap<>(); // Char to frequency
        for (int i = 0; i < input.length(); i++) { // Scans string
            char c = input.charAt(i); // Current char
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1); // Increments count
        } // Creates frequency map

        // Build min-heap
        MinHeap minHeap = new MinHeap(); // Initializes heap
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) { // Iterates map
            Node node = new Node(entry.getKey(), entry.getValue()); // Creates leaf
            minHeap.insert(node); // Adds to heap
        } // Populates heap

        // Build Huffman tree
        while (minHeap.size() > 1) { // Until one node remains
            Node first = minHeap.remove(); // Lowest frequency
            Node second = minHeap.remove(); // Next lowest
            Node merged = new Node(first.cost + second.cost, first, second); // Merges nodes
            minHeap.insert(merged); // Adds merged node
        } // Constructs tree
        root = minHeap.remove(); // Sets root

        // Initialize encoder
        encoder = new HashMap<>(); // Creates encoder map
        if (root != null) { // If tree exists
            buildCodes(root, ""); // Generates codes
        }
    } // Summary: Builds tree and encoder map (O(n log k), k=unique chars).

    // Private method to generate encoder map
    private void buildCodes(Node node, String code) { // Assigns codes
        if (node == null) { // Base case: null node
            return; // Stops
        }
        if (node.data != null) { // Leaf node
            encoder.put(node.data, code.isEmpty() ? "0" : code); // Sets code
        } // Handles single-char case
        buildCodes(node.left, code + "0"); // Left = 0
        buildCodes(node.right, code + "1"); // Right = 1
    } // Summary: Recursively builds char-to-code map (O(k)).

    // Public method to encode string
    public String encode(String source) { // Encodes input
        if (source == null || source.isEmpty()) { // Checks invalid input
            return ""; // Returns empty
        }
        StringBuilder encoded = new StringBuilder(); // Builds result
        for (int i = 0; i < source.length(); i++) { // Scans string
            char c = source.charAt(i); // Current char
            String code = encoder.get(c); // Gets code
            if (code == null) { // Char not found
                throw new IllegalArgumentException("Character '" + c + "' not in tree");
            }
            encoded.append(code); // Appends code
        }
        return encoded.toString(); // Returns encoded string
    } // Summary: Converts string to codes (O(n), n=string length).

    // Private method to get tree height
    private int getTreeHeight(Node node) { // Computes height for display
        if (node == null) { // Empty tree
            return 0; // Height 0
        }
        return 1 + Math.max(getTreeHeight(node.left), getTreeHeight(node.right)); // Max child height + 1
    } // Summary: Calculates height for grid sizing.

    // Public method to display Huffman tree
    public void displayTree() { // Shows tree structure
        if (root == null) { // Empty tree
            System.out.println("Huffman tree is empty"); // Prints message
            return; // Exits
        }
        System.out.println("Huffman Tree (leaves=char, internal=cost):"); // Labels output
        int height = getTreeHeight(root); // Gets height
        int width = (int) Math.pow(2, height) * 5; // Compact width
        char[][] grid = new char[height * 2][width]; // Grid for nodes
        for (int i = 0; i < height * 2; i++) { // Initializes grid
            for (int j = 0; j < width; j++) {
                grid[i][j] = ' '; // Fills with spaces
            }
        }
        placeNode(root, 0, width / 2, 0, grid, width); // Places nodes
        printGrid(grid); // Prints tree
    } // Summary: Displays tree with chars or costs.

    // Private method to place nodes in grid
    private void placeNode(Node node, int row, int col, int level, char[][] grid, int width) { // Positions node
        if (node == null) { // Empty subtree
            return; // Stops
        }
        // Set node label
        String label = node.data != null ? node.data.toString() : String.valueOf(node.cost); // Char or cost
        for (int i = 0; i < label.length() && col + i < width; i++) { // Places label
            if (col + i >= 0) { // Within bounds
                grid[row][col + i] = label.charAt(i); // Sets char
            }
        }
        if (node.left != null || node.right != null) { // Has children
            int offset = (int) Math.pow(2, getTreeHeight(root) - level - 1) * 2; // Spacing
            if (node.left != null) { // Left child
                if (col - offset >= 0) { // Within bounds
                    grid[row + 1][col - offset] = '/'; // Draws /
                }
                placeNode(node.left, row + 2, col - offset, level + 1, grid, width); // Recurses left
            }
            if (node.right != null) { // Right child
                if (col + offset < width) { // Within bounds
                    grid[row + 1][col + offset] = '\\'; // Draws \
                }
                placeNode(node.right, row + 2, col + offset, level + 1, grid, width); // Recurses right
            }
        }
    } // Summary: Places node labels and branches.

    // Private method to print grid
    private void printGrid(char[][] grid) { // Prints tree
        for (char[] row : grid) { // Scans rows
            boolean empty = true; // Tracks empty row
            for (char c : row) { // Checks content
                if (c != ' ') { // Non-space found
                    empty = false; // Not empty
                    break; // Stops checking
                }
            }
            if (!empty) { // Prints non-empty row
                System.out.println(new String(row).trim()); // Removes trailing spaces
            }
        }
    } // Summary: Prints grid as tree.

    // Main function with test cases
    public static void main(String[] args) { // Tests Huffman coding
        Scanner scanner = new Scanner(System.in); // For input

        // Test Case 1: Interactive input
        System.out.println("Test Case 1: Interactive Input");
        System.out.println("-----------------------------");
        System.out.println("Enter a string to compress:");
        String input1 = scanner.nextLine(); // Reads string
        try {
            HuffmanCoding hc1 = new HuffmanCoding(input1); // Builds tree
            hc1.displayTree(); // Shows tree
            String encoded1 = hc1.encode(input1); // Encodes
            System.out.println("Encoded string: " + encoded1); // Prints result
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // Handles errors
        }
        System.out.println();


        scanner.close(); // Closes scanner
    } // Summary: Tests encoding with various strings.
}


/*
What is Huffman Coding?
Huffman Coding is a method to compress text by representing characters with shorter codes for those that appear more often and longer codes for those that are rare.
It’s like giving nicknames: common words get short nicknames, while rare ones get longer ones, so you use less space overall.

Goal: Reduce the number of bits needed to store or send a string.
Example: For "hello", instead of using 8 bits per character (ASCII), we might use 1 bit for 'l' (appears twice) and 2 bits for 'h', 'e', 'o' (appear once), saving space.
 */



 /*

 Why Do We Need It?
Normally, each character in a string (like 'a', 'b', 'h') is stored using a fixed number of bits:

In ASCII, every character = 8 bits.
For "hello" (5 chars): 5 × 8 = 40 bits.
But not all characters appear equally often:

In "aaaaab", 'a' appears 5 times, 'b' only once.
Why give 'a' and 'b' the same 8 bits? We can use fewer bits for 'a'.
Huffman Coding creates variable-length codes:

Frequent characters get short codes (e.g., 1 bit).
Rare characters get longer codes (e.g., 3 bits).
This reduces the total bits, especially for repetitive text.

  */


 /*
 How Does It Work? (Theory in Simple Steps)
Imagine you’re organizing a party and want to assign name tags to guests. Frequent guests get short tags (like "Bob"), while rare guests get longer tags (like "Jonathan"). Huffman Coding does this for characters in a string. Here’s how, step-by-step:

Step 1: Count How Often Each Character Appears
Look at the string and count each character’s frequency.
Analogy: Count how many times each guest shows up at your parties.
Example: For "hello":
h: 1
e: 1
l: 2
o: 1
In Your Code:
Uses HashMap<Character, Integer> freqMap.
Loops through the string: freqMap.put(c, freqMap.getOrDefault(c, 0) + 1).
Stores: {h=1, e=1, l=2, o=1}.
Step 2: Put Characters in a Priority Queue (Min-Heap)
Each character becomes a "node" with its frequency (called cost in your code).
Put these nodes in a min-heap, which always gives you the two least frequent characters first.
Analogy: Line up guests by how rarely they attend (rarest first).
Example:
Nodes: h(1), e(1), l(2), o(1).
Heap orders by frequency: h(1), e(1), o(1), l(2).
In Your Code:
MinHeap class stores Node objects (data=char, cost=freq).
insert adds nodes: minHeap.insert(new Node(entry.getKey(), entry.getValue())).
remove gets lowest: minHeap.remove().
Step 3: Build a Tree by Combining Least Frequent Nodes
Take the two nodes with the smallest frequencies, combine them into a new node with their frequencies added.
Put the new node back in the heap.
Repeat until only one node remains (the root of the tree).
Analogy: Pair up the rarest guests into groups, then pair groups, until everyone’s in one big family tree.
Example for "hello":
Heap: h(1), e(1), o(1), l(2).
Take h(1) and e(1), make node 2 (left=h, right=e).
Heap: 2(he), o(1), l(2).
Take 2(he) and o(1), make node 3 (left=he, right=o).
Heap: 3(heo), l(2).
Take 3(heo) and l(2), make node 5 (left=heo, right=l).
Done! Root = 5.

    5
   / \
  3   l
 / \
2   o
  */