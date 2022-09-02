package hashTable.closedHashing;

import hashTable.HashEntry;
import hashTable.Map;

import java.math.BigInteger;

/** The class that implements the Map interface using closed hashing;
 *  uses double hashing to resolve collisions */
public class HashTableClosedHashingDH implements Map {
    private HashEntry[] table; // hash table
    private int maxSize;
    private int size; // the number of elements currently in the hash table


    /** Constructor for class HashTableClosedHashingDH.
     *  Creates a hash table of the given size.
     * @param maxSize maximum number of elements the hash table can store
     */
    public HashTableClosedHashingDH(int maxSize) {
        // FILL IN CODE
        this.maxSize = maxSize;
        size = 0;
        this.table = new HashEntry[maxSize];

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
        BigInteger t = getK(key, this.maxSize);
        int hk = getHk(t, this.maxSize);
        //
        int k = hk;
        if (this.table[k] != null && !this.table[k].isDeleted()) {
            if (this.table[k].getKey().equals(key)) {
                return true;
            } else {
                int dk = getDk(t, this.maxSize);
                k = (k + dk) % this.maxSize;
                int times = this.maxSize / dk + 2;
                int tt = 1;
                while (this.table[k] != null && !this.table[k].isDeleted()) {
                    if (tt > times) {
                        break;
                    }
                    if (this.table[k].getKey().equals(key)) {
                        return true;
                    } else {
                        k = (k + dk) % this.maxSize;
                        tt++;
                    }
                }
                return false;
            }
        } else {
            return false;
        }
        //return false;
    }

    private int getHk(BigInteger k, int maxSize) {
        return k.remainder(BigInteger.valueOf(maxSize)).intValue();
    }

    private int getDk(BigInteger k, int maxSize) {
        int q = 0;
        if (maxSize % 2 == 1) {
            int half = (maxSize - 1) / 2;
            if (half % 2 == 0) {
                q = half + 1;
            } else {
                q = half + 2;
            }
        } else {
            int half = maxSize / 2;
            if (half % 2 == 0) {
                q = half + 1;
            } else {
                q = half + 2;
            }
        }
        return q - (k.remainder(BigInteger.valueOf(q))).intValue();
    }

    private BigInteger getK (String key, int maxSize) {
        BigInteger a = BigInteger.valueOf(33);

        int resKey = 0;
        BigInteger res = BigInteger.valueOf(0);
        if (key.length() == 0) {
            return res;
        }
        int first = (int) key.charAt(0);
        res = BigInteger.valueOf(first);
        for (int i = 1; i < key.length(); i++) {
            int temp = (int) key.charAt(i);
            BigInteger tempBI = BigInteger.valueOf(temp);
            res = tempBI.add(res.multiply(a));
        }
        return res;
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

        double factor = (double) (this.size + 1) / (double) this.maxSize;
        if (factor > 0.6) {
            reHash();
            put(key, value);
        } else {
            BigInteger t = getK(key, this.maxSize);
            int hk = getHk(t, this.maxSize);
            int k = hk;
            if (this.table[k] == null) {
                this.table[k] = new HashEntry(key, value);
                size++;
            } else if (this.table[k].isDeleted()) {
                this.table[k] = new HashEntry(key, value);
                size++;
            } else if (this.table[k].getKey().equals(key)) {
                this.table[k].setValue(value);
            } else {
                int dk = getDk(t, this.maxSize);
                k = (k + dk) % this.maxSize;
                int times = this.maxSize / dk + 2;
                int tt = 1;
                while (this.table[k] != null && !this.table[k].isDeleted()) {
                    if (tt > times) {
                        reHash();
                        put(key, value);
                    }
                    if (this.table[k].getKey().equals(key)) {
                        this.table[k].setValue(value);
                    } else {
                        k = (k + dk) % this.maxSize;
                        tt++;
                    }
                }
                this.table[k] = new HashEntry(key, value);
                size++;
            }
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
        BigInteger t = getK(key, this.maxSize);
        int hk = getHk(t, this.maxSize);
        int k = hk;
        if (this.table[k] == null) {
            return null;
        } else if (this.table[k].isDeleted()) {
            return null;
        } else if (this.table[k].getKey().equals(key)) {
            return this.table[k].getValue();
        } else {
            int dk = getDk(t, this.maxSize);
            k = (k + dk) % maxSize;
            int times = this.maxSize / dk + 2;
            int tt = 1;
            while (this.table[k] != null && !this.table[k].isDeleted()) {
                if (tt > times) {
                    break;
                }
                if (this.table[k].getKey().equals(key)) {
                    return this.table[k].getValue();
                } else {
                    k = (k + dk) % this.maxSize;
                    tt++;
                }
            }
            return null;
        }

        //return null;
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
        BigInteger t = getK(key, this.maxSize);
        int hk = getHk(t, this.maxSize);
        int k = hk;
        if (this.table[k] == null) {
            return null;
        } else if (this.table[k].isDeleted()) {
            return null;
        } else if (this.table[k].getKey().equals(key)) {
            this.table[k].setDeleted(true);
            size--;
            return this.table[k].getValue();

        } else {
            int dk = getDk(t, this.maxSize);
            k = (k + dk) % this.maxSize;
            int times = this.maxSize / dk + 2;
            int tt = 1;
            while (this.table[k] != null && !this.table[k].isDeleted()) {
                if (tt > times) {
                    break;
                }
                if (this.table[k].getKey().equals(key)) {
                    this.table[k].setDeleted(true);
                    size--;
                    return  this.table[k].getValue();

                } else {
                    k = (k + dk) % this.maxSize;
                    tt++;
                }
            }
            return null;
        }


        //return null;
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

        //return sb.toString();
    }

    // Add other helper methods as needed

}
