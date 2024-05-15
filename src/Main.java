import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

class MyHashTable<K, V> {
    private ArrayList<LinkedList<Entry<K, V>>> table;
    private int capacity;
    private int size;

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
    }

    public void put(K key, V value) {
        int index = hash(key);
        LinkedList<Entry<K, V>> list = table.get(index);
        for (Entry<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        list.add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> list = table.get(index);
        for (Entry<K, V> entry : list) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public V getBucketSize(V i) {
        return i;
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}

class MyTestingClass {
    private int value;

    public MyTestingClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        // Your custom hashCode implementation
        // Example: A simple hashCode implementation that returns the value itself
        return value;
    }
}

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>(10);
        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(i);
            table.put(key, i);
        }

        for (int i = 0; i < table.getSize(); i++) {
            int bucketSize = table.getBucketSize(i);
            System.out.println("Bucket " + i + " contains " + bucketSize + " elements");
        }
    }
}

class BinarySearchTree<K extends Comparable<K>, V> implements Iterable<BinarySearchTree.Entry<K, V>> {
    private Node root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    public V get(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new BSTIterator();
    }

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private class BSTIterator implements Iterator<Entry<K, V>> {
        private Stack<Node> stack;

        public BSTIterator() {
            stack = new Stack<>();
            pushLeft(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Entry<K, V> next() {
            Node node = stack.pop();
            pushLeft(node.right);
            return new Entry<>(node.key, node.value);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}


