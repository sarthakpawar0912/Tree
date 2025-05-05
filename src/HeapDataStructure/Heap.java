package HeapDataStructure;

import java.util.ArrayList;

// Generic Min-Heap implementation supporting insert, remove, and heap sort
public class Heap<T extends Comparable<T>> { // T must be comparable for ordering

    // ArrayList to store heap elements (Min-Heap: parent <= children)
    private ArrayList<T> list;

    // Constructor: Initializes empty heap
    public Heap() { // Creates new ArrayList for heap storage
        this.list = new ArrayList<>();
    } // Summary: Initializes empty Min-Heap.

    // Swaps elements at indices first and second in the heap
    private void swap(int first, int second) { // Swaps two elements
        T temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    } // Summary: Swaps elements to maintain heap property.

    // Returns parent index of node at index
    private int parent(int index) { // Computes parent index
        return (index - 1) / 2; // Integer division for parent
    } // Summary: Finds parent index in array representation.

    // Returns left child index of node at index
    private int left(int index) { // Computes left child index
        return index * 2 + 1; // Formula for left child
    } // Summary: Finds left child index.

    // Returns right child index of node at index
    private int right(int index) { // Computes right child index
        return index * 2 + 2; // Formula for right child
    } // Summary: Finds right child index.

    // Inserts a new value into the heap
    public void insert(T value) { // Adds value to heap
        list.add(value); // Append to end
        upheap(list.size() - 1); // Bubble up to restore Min-Heap property
    } // Summary: Inserts value and maintains Min-Heap (O(log n)).

    // Bubbles up element at index to restore Min-Heap property
    private void upheap(int index) { // Restores heap property upward
        if (index == 0) { // Root reached
            return;
        }

        int p = parent(index); // Parent index
        // If current < parent, swap and recurse
        if (list.get(index).compareTo(list.get(p)) < 0) {
            swap(index, p); // Swap with parent
            upheap(p); // Recurse on parent
        }
    } // Summary: Bubbles up smaller element to maintain Min-Heap.

    // Removes and returns the minimum element (root)
    public T remove() throws Exception { // Removes root (minimum)
        if (list.isEmpty()) { // Empty heap
            throw new Exception("Removing from an empty heap!");
        }

        T temp = list.get(0); // Store root to return
        T last = list.remove(list.size() - 1); // Remove last element
        if (!list.isEmpty()) { // If heap not empty after removal
            list.set(0, last); // Place last at root
            downheap(0); // Bubble down to restore Min-Heap
        }

        return temp; // Return removed root
    } // Summary: Removes and returns minimum, maintains Min-Heap (O(log n)).

    // Bubbles down element at index to restore Min-Heap property
    private void downheap(int index) { // Restores heap property downward
        int min = index; // Assume current is smallest
        int left = left(index); // Left child index
        int right = right(index); // Right child index

        // Compare with left child
        if (left < list.size() && list.get(min).compareTo(list.get(left)) > 0) {
            min = left; // Left child is smaller
        }
        // Compare with right child
        if (right < list.size() && list.get(min).compareTo(list.get(right)) > 0) {
            min = right; // Right child is smaller
        }

        // If a child is smaller, swap and recurse
        if (min != index) {
            swap(min, index); // Swap with smallest child
            downheap(min); // Recurse on swapped child
        }
    } // Summary: Bubbles down larger element to maintain Min-Heap.

    // Sorts elements using heap sort (ascending order)
    public ArrayList<T> heapSort() throws Exception { // Performs heap sort
        ArrayList<T> data = new ArrayList<>(); // Result list
        while (!list.isEmpty()) { // Remove all elements
            data.add(this.remove()); // Add minimum to result
        }
        return data; // Return sorted list
    } // Summary: Sorts heap elements in ascending order (O(n log n)).

    // Main function to test heap operations and heap sort
    public static void main(String[] args) throws Exception { // Entry point for testing
        // Test Case 1: Heap with [4, 10, 3, 5, 1]
        System.out.println("Test Case 1:");
        Heap<Integer> heap1 = new Heap<>(); // Create empty heap
        int[] input1 = {4, 10, 3, 5, 1};
        for (int val : input1) { // Insert elements
            heap1.insert(val);
        }
        System.out.println("Inserted: " + java.util.Arrays.toString(input1));
        System.out.println("Heap Sort Result: " + heap1.heapSort()); // Expected: [1, 3, 4, 5, 10]
        // Note: Min-Heap sorts in ascending order

        // Test Case 2: Heap with [9, 8, 7, 6]
        System.out.println("\nTest Case 2:");
        Heap<Integer> heap2 = new Heap<>(); // Create empty heap
        int[] input2 = {9, 8, 7, 6};
        for (int val : input2) { // Insert elements
            heap2.insert(val);
        }
        System.out.println("Inserted: " + java.util.Arrays.toString(input2));
        System.out.print("Remove Min: ");
        System.out.println(heap2.remove()); // Expected: 6 (minimum)
        System.out.println("Heap Sort Result: " + heap2.heapSort()); // Expected: [7, 8, 9]
        // Note: Demonstrates remove and sort
    } // Summary: Tests heap operations and heap sort with sample inputs.
}
