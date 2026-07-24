public class MyHashSet {
    // Primary array size chosen as a prime number to minimize collisions
    private static final int BASE = 769;
    private Node[] buckets;

    // Singly linked list node for bucket chaining
    private static class Node {
        int key;
        Node next;

        Node(int key) {
            this.key = key;
        }
    }

    public MyHashSet() {
        buckets = new Node[BASE];
    }

    private int hash(int key) {
        return key % BASE;
    }

    public void add(int key) {
        int index = hash(key);
        Node head = buckets[index];

        // If bucket is empty, create new head node
        if (head == null) {
            buckets[index] = new Node(key);
            return;
        }

        // Traverse to check if key already exists or append at the end
        Node curr = head;
        while (curr != null) {
            if (curr.key == key) {
                return; // Key already present
            }
            if (curr.next == null) {
                curr.next = new Node(key);
                return;
            }
            curr = curr.next;
        }
    }

    public void remove(int key) {
        int index = hash(key);
        Node head = buckets[index];

        if (head == null) return;

        // If the key is at the head of the list
        if (head.key == key) {
            buckets[index] = head.next;
            return;
        }

        // Traverse to find and remove the node
        Node curr = head;
        while (curr.next != null) {
            if (curr.next.key == key) {
                curr.next = curr.next.next;
                return;
            }
            curr = curr.next;
        }
    }

    public boolean contains(int key) {
        int index = hash(key);
        Node curr = buckets[index];

        while (curr != null) {
            if (curr.key == key) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
}