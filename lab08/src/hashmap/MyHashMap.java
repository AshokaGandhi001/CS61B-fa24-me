package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.lang.Math;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    int capacity;
    double loadFactor;
    int size;

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        capacity = initialCapacity;
        size = 0;
        this.loadFactor = loadFactor;
        buckets = new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }
    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void put(K key, V value) {
        //replace the key's mapping if key is already in map.
        if (containsKey(key)) {
            for (Node node : buckets[keyIndex(key)]) {
                if (key.equals(node.key)) {
                    node.value = value;
                }
            }
        } else {
            size++;
            //adding a node with key-value pair to hash table
            buckets[keyIndex(key)].add(new Node(key, value));
            //resize if size / capacity exceed load Factor
            if ((double) size / capacity > loadFactor) {
                capacity = capacity * 2;
                Collection<Node>[] oldBuckets = buckets;
                buckets = new Collection[capacity];
                for (int i = 0; i < capacity; i++) {
                    buckets[i] = createBucket();
                }
                for (Collection<Node> Nodes: oldBuckets) {
                    for (Node node : Nodes) {
                        buckets[keyIndex(node.key)].add(new Node(node.key, node.value));
                    }
                }
            }
        }

    }

    @Override
    public V get(K key) {
        for (Node node : buckets[keyIndex(key)]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int keyIndex = Math.floorMod(key.hashCode(), capacity);

        for ( Node node : buckets[keyIndex]) {
            if (key.equals(node.key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        buckets = new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public int keyIndex(K key) {
        return Math.floorMod(key.hashCode(), capacity);
    }
}
