package hashTable.openHashing;
import hashTable.HashEntry;
import hashTable.Map;

import java.math.BigInteger;

public class HashTableOpenHashing implements Map {
    private Node[] table ;
    private int maxSize;
    private int size;

    /** Constructor for class HashTableOpenHashing
     *
     * @param maxSize the size of the table
     */
    public HashTableOpenHashing(int maxSize) {
        // FILL IN CODE
        this.maxSize = maxSize;
        size = 0;
        table = new Node[maxSize];


    }

    /** Return true if the map contains a (key, value) pair associated with this key,
     *  otherwise return false.
     *
     * @param key  key
     * @return true if the key (and the corresponding value) is the in map
     */
    public boolean containsKey(String key) {
        // FILL IN CODE
        int hK = this.Hash(key, this.maxSize);
        Node pointer = this.table[hK];
        while (pointer != null) {
            if (pointer.entry().getKey().equals(key)) {
                return true;
            } else {
                pointer = pointer.next();
            }
        }
        return false;
        //return false; // change
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
    public void put(String key, Object value) {
            // FILL IN CODE
        if (key == null) {
            throw new IllegalArgumentException();
        }
        HashEntry HE = new HashEntry(key, value);
        // load factor = 0.5? ...0.6??  I use 0.6;
        if ((double) (this.size + 1) / (double) this.maxSize > 0.6) {
            //Rehash.
            reHash();
            put(key, value);
            size++;
        } else {
            int k = Hash(HE.getKey(), this.maxSize);
            if (table[k] == null) {
                table[k] = new Node(HE);
            } else {
                //
                Node pointer = this.table[k];
                while (pointer != null) {
                    if (pointer.entry().getKey().equals(key)) {
                        pointer.entry().setValue(value);
                        return;
                    } else {
                        pointer = pointer.next();
                    }
                }
                //
                table[k] = new Node(HE, table[k]);
            }
            size++;
        }
    }

    private void reHash() {
        int maxSize2 = this.maxSize * 2 + 1;
        Node[] tempArr = this.table;
        this.table = new Node[maxSize2];
        int maxSizeOld = this.maxSize;
        int sizeOld = this.size;
        this.size = 0;
        this.maxSize = maxSize2;
        for (int i = 0; i < maxSizeOld; i++) {
            if (tempArr[i] != null) {
                Node pointer = tempArr[i];
                while (pointer != null) {
                    put(pointer.entry().getKey(), pointer.entry().getValue());
                    pointer = pointer.next();
                }
            }
        }
    }

    /** Return the value associated with the given key or null, if the map does not contain the key.
     * If the key is null, throw IllegalArgumentException.
     *
     * @param key key
     * @return value associated value
     */
    public Object get(String key) {
        // FILL IN CODE
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int k = Hash(key, this.maxSize);
        Node pointer = this.table[k];
        while (pointer != null) {
            if (pointer.entry().getKey().equals(key)) {
                return pointer.entry().getValue();
            }
            pointer = pointer.next();
        }
        return null; // change
    }

    /** Remove a (key, value) entry if it exists.
     * Return the previous value associated with the given key, otherwise return null
     * @param key key
     * @return previous value
     */
    public Object remove(String key) {
        // FILL IN CODE
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int k = Hash(key, this.maxSize);
        Node pointer = this.table[k];
        if (pointer == null) {
            return null;
        } else if (pointer.entry().getKey().equals(key)) {
            table[k] = pointer.next();
            this.size--;
            return pointer.entry().getValue();

        } else {
            while (pointer.next() != null) {
                if (pointer.next().entry().getKey().equals(key)) {
                    Node temp = pointer.next();
                    pointer.setNext(temp.next());
                    this.size--;
                    return temp.entry().getValue();
                }
                pointer = pointer.next();
            }
            return null;
        }
        //return null; // change
    }

    /** Return the actual number of elements in the map.
     *
     * @return number of elements currently in the map.
     */
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
        for (int i = 0; i < maxSize; i++) {
            sb.append(i);
            sb.append(": ");
            if (this.table[i] == null) {
                sb.append("null\n");
            } else {
                Node pointer = this.table[i];
                while (pointer != null) {
                    sb.append(pointer.entry().toString());
                    if (pointer.next() != null) {
                        sb.append(", ");
                    }
                    pointer = pointer.next();
                }
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    // Add may implement other helper methods as needed


}
