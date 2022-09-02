package hashTable.closedHashing;

import hashTable.HashEntry;
import hashTable.Map;

import java.math.BigInteger;

/** The class that implements the Map interface using closed hashing;
 *  uses linear probing to resolve collisions */
public class HashTableClosedHashingLP implements Map {
    private HashEntry[] table; // hash table
    private int maxSize;
    private int size; // the number of elements currently in the hash table


    /** Constructor for class HashTableClosedHashingLP.
     *  Creates a hash table of the given size.
     * @param maxSize maximum number of elements the hash table can store
     */
    public HashTableClosedHashingLP(int maxSize) {
        // FILL IN CODE
        this.table = new HashEntry[maxSize];
        this.maxSize = maxSize;
        size = 0;
    }

    /** Return true if the map contains a (key, value) pair associated with this key,
     *  otherwise return false.
     *
     * @param key  key
     * @return true if the key (and the corresponding value) is the in map
     */
    @Override
    public boolean containsKey(String key) {
        // FILL IN CODE
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int k = Hash(key, this.maxSize);
        while (this.table[k] != null && !this.table[k].isDeleted()) {
            if (this.table[k].getKey().equals(key)) {
                return true;
            } else {
                k = (k + 1) % maxSize;
            }
        }
        return false;
    }

    private int Hash(String key, int maxSize) {
        //maxSize = this.maxSize;
        // a = 33;
        BigInteger a = BigInteger.valueOf(33);

        int resKey = 0;
        BigInteger res = BigInteger.valueOf(0);
        if (key.length() == 0) {
            return resKey;
        }
        int first = (int) key.charAt(0);
        res = BigInteger.valueOf(first);
        for (int i = 1; i < key.length(); i++) {
            int temp = (int) key.charAt(i);
            BigInteger tempBI = BigInteger.valueOf(temp);
            res = tempBI.add(res.multiply(a));
        }
        BigInteger BMaxSize = BigInteger.valueOf(maxSize);
        BigInteger result = res.remainder(BMaxSize);
        return result.intValue();
    }

    /** Add (key, value) to the map.
     * Will replace previous value that this key was mapped to.
     * If key is null, throw IllegalArgumentException.
     *
     * @param key
     * @param value associated value
     */
    @Override
    public void put(String key, Object value) {
        // FILL IN CODE
        if (key == null) {
            throw new IllegalArgumentException();
        }
        double factor = (double) (this.size +  1) / (double) maxSize;
        if (factor > 0.6) {
            reHash();
            put(key, value);
        } else {
            int k = Hash(key, this.maxSize);
            while (this.table[k] != null && !this.table[k].isDeleted()) {
                if (this.table[k].getKey().equals(key)) {
                    this.table[k].setValue(value);
                    return;
                } else {
                    k = (k + 1) % this.maxSize;
                }
            }
            this.table[k] = new HashEntry(key, value);
            size++;
        }
    }

    private void reHash() {
        int maxSize2 = maxSize * 2 + 1;
        HashEntry[] tempArr = this.table;
        this.size = 0;
        this.table = new HashEntry[maxSize2];
        this.maxSize = maxSize2;
        for (int i = 0; i < tempArr.length; i++ ) {
            if (tempArr[i] == null) {
                continue;
            } else if (tempArr[i].isDeleted()) {
                continue;
            } else {
                put(tempArr[i].getKey(), tempArr[i].getValue());
            }
        }

    }

    /** Return the value associated with the given key or null, if the map does not contain the key.
     * If the key is null, throw IllegalArgumentException.
     *
     * @param key key
     * @return value associated value
     */
    @Override
    public Object get(String key) {
        // FILL IN CODE
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int k = Hash(key, maxSize);
        while (this.table[k] != null && !this.table[k].isDeleted()) {
            if (this.table[k].getKey().equals(key)) {
                return this.table[k].getValue();
            } else {
                k = (k + 1) % maxSize;
            }
        }
        return null;
    }

    /** Remove a (key, value) entry if it exists.
     * Return the previous value associated with the given key, otherwise return null
     * @param key key
     * @return previous value
     */
    @Override
    public Object remove(String key) {
        // FILL IN CODE
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int k = Hash(key, maxSize);
        while (this.table[k] != null && !this.table[k].isDeleted()) {
            if (this.table[k].getKey().equals(key)) {
                this.table[k].setDeleted(true);
                this.size--;
                return this.table[k].getValue();
            } else {
                k = (k + 1) % this.maxSize;
            }
        }
        return null;
    }

    /** Return the actual number of elements in the map.
     *
     * @return number of elements currently in the map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * toString
     * @return a string representing a hash table
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // FILL IN CODE
        for (int i = 0; i < this.maxSize; i++) {
            sb.append(i);
            sb.append(": ");
            if (this.table[i] == null) {
                sb.append("null");
            } else {
                sb.append(this.table[i].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Add may implement other helper methods as needed

}
