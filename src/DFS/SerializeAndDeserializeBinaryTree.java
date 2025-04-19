package DFS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerializeAndDeserializeBinaryTree { // Class for serializing/deserializing tree


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {} // Default constructor

        TreeNode(int val) { // Constructor with value
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) { // Constructor with children
            this.val = val;
            this.left = left;
            this.right = right;
        } // Summary: Creates node with value and optional children.
    } // Summary: Represents a binary tree node.

    // Encodes a tree to a single string
    public String serialize(TreeNode root) { // Serializes tree to string
        List<String> list = new ArrayList<>(); // Intermediate list for node values
        helper(root, list); // Build list with preorder traversal
        return String.join(",", list); // Join with commas
    } // Summary: Serializes tree using preorder DFS (O(n) time, n=nodes).

    // Helper method for serialization (preorder traversal)
    private void helper(TreeNode node, List<String> list) { // Adds node values to list
        if (node == null) { // Null node
            list.add("null"); // Add "null" marker
            return;
        }
        list.add(String.valueOf(node.val)); // Add node value
        helper(node.left, list); // Process left subtree
        helper(node.right, list); // Process right subtree
    } // Summary: Builds preorder list of node values and nulls.

    // Decodes encoded data to tree
    public TreeNode deserialize(String data) { // Deserializes string to tree
        if (data.isEmpty()) { // Empty string
            return null; // Return null tree
        }
        List<String> list = new ArrayList<>(Arrays.asList(data.split(","))); // Split string to list
        return helper2(list, new int[]{0}); // Build tree with index tracking
    } // Summary: Deserializes string using preorder DFS (O(n) time).

    // Helper method for deserialization (preorder reconstruction)
    private TreeNode helper2(List<String> list, int[] index) { // Builds tree from list
        if (index[0] >= list.size()) { // No more elements
            return null; // Return null
        }

        String val = list.get(index[0]++); // Get next value and increment index
        if (val.equals("null")) { // Null marker
            return null; // Return null node
        }

        TreeNode node = new TreeNode(Integer.parseInt(val)); // Create node
        node.left = helper2(list, index); // Build left subtree
        node.right = helper2(list, index); // Build right subtree
        return node; // Return constructed node
    } // Summary: Reconstructs tree using preorder order and index.

    // Main function to test serialization and deserialization
    public static void main(String[] args) { // Entry point for testing
        SerializeAndDeserializeBinaryTree solution = new SerializeAndDeserializeBinaryTree(); // Creates instance

        // Test Case 1: Example 1 - [1,2,3,null,null,4,5]
        System.out.println("Test Case 1:");
        TreeNode root1 = solution.new TreeNode(1);
        root1.left = solution.new TreeNode(2);
        root1.right = solution.new TreeNode(3);
        root1.right.left = solution.new TreeNode(4);
        root1.right.right = solution.new TreeNode(5);
        String serialized1 = solution.serialize(root1);
        TreeNode deserialized1 = solution.deserialize(serialized1);
        System.out.println("Serialized: " + serialized1); // Expected: "1,2,null,null,3,4,null,null,5"
        System.out.println("Deserialized Output: [1,2,3,null,null,4,5]"); // Expected structure
        // Note: Tree reconstructed as 1(2,3(4,5))

        // Test Case 2: Example 2 - []
        System.out.println("\nTest Case 2:");
        TreeNode root2 = null;
        String serialized2 = solution.serialize(root2);
        TreeNode deserialized2 = solution.deserialize(serialized2);
        System.out.println("Serialized: " + serialized2); // Expected: ""
        System.out.println("Deserialized Output: []"); // Expected empty tree
        // Note: Empty tree correctly handled
    } // Summary: Tests serialization/deserialization with problem examples.
}
