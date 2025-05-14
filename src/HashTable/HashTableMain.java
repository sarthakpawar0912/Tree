package HashTable;
import java.util.LinkedList;
import java.util.Scanner;

class HashTable {


    // Inner class to represent a key-value pair (roll number and name)
    static class Pair {

        int key; // Key represents the roll number

        String value; // Value represents the student's name

        // Constructor to initialize key-value pair
        public Pair(int key, String value) {

            this.key = key;

            this.value = value;

        }

        // Override toString for readable output when printing pairs
        @Override
        public String toString() {

            return "Pair{key=" + key + ", value='" + value + "'}";

        }

    }

    // Constant defining the number of slots (buckets) in the hash table
    private static final int SLOTS = 10;


    // Array of LinkedLists to store key-value pairs (chaining for collision resolution)
    private LinkedList<Pair>[] table;


    // Constructor to initialize the hash table with empty LinkedLists
    public HashTable() {

        table = new LinkedList[SLOTS];

        for (int i = 0; i < SLOTS; i++) {

            table[i] = new LinkedList<>();

        }
    }

    // Custom hash function to map a key to a slot (0 to SLOTS-1)
    private int hash(int key) {

        // Use modulo to ensure the slot is within [0, SLOTS-1]
        // Math.abs to handle negative keys
        return Math.abs(key % SLOTS);

    }

    // Method to insert or update a key-value pair in the hash table
    public void put(int key, String value) {

        // Step 1: Compute the slot for the given key using the hash function
        int slot = hash(key);

        // Step 2: Access the bucket (LinkedList) at the computed slot
        LinkedList<Pair> bucket = table[slot];

        // Step 3: Search for the key in the bucket (linear search)
        for (Pair pair : bucket) {

            // If key exists, update its value and return
            if (pair.key == key) {

                pair.value = value;

                return;
            }
        }

        // Step 4: If key is not found, create a new Pair and add it to the bucket
        Pair pair = new Pair(key, value);
        bucket.add(pair);
    }

    // Method to retrieve the value associated with a given key
    public String get(int key) {
        // Step 1: Compute the slot for the given key using the hash function
        int slot = hash(key);

        // Step 2: Access the bucket (LinkedList) at the computed slot
        LinkedList<Pair> bucket = table[slot];

        // Step 3: Search for the key in the bucket (linear search)
        for (Pair pair : bucket) {
            // If key is found, return its associated value
            if (pair.key == key) {
                return pair.value;
            }
        }

        // Step 4: If key is not found, return null
        return null;
    }
}

// Main class to test the HashTable implementation
public class HashTableMain {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner sc = new Scanner(System.in);

        // Create a new HashTable instance
        HashTable ht = new HashTable();

        // Insert sample key-value pairs (roll numbers and names)
        ht.put(1, "Sarthak");
        ht.put(2, "Raj");
        ht.put(3, "Amit");
        ht.put(4, "Shubham");
        ht.put(5, "Ashwin");
        ht.put(24, "Rahul"); // Note: 24 hashes to slot 4 (24 % 10 = 4), causing a collision

        // Prompt user to enter a roll number to search
        System.out.println("Enter roll to find:");
        int roll = sc.nextInt();

        // Retrieve the name associated with the roll number
        String name = ht.get(roll);

        // Check if the name was found and print the result
        if (name == null) {
            System.out.println("No such roll");
        } else {
            System.out.println("Name Found: " + name);
        }

        // Close the scanner to prevent resource leaks
        sc.close();
    }
}