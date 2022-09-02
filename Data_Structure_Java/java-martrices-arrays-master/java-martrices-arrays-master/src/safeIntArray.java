import java.util.Arrays;
import java.util.Iterator;

public class safeIntArray implements Iterable<Integer> {

    int[] internalArray;
    int internalSize;

    public safeIntArray(int initialSize) {
        this.internalArray = new int[initialSize];
        this.internalSize = 0;
    }

    /* default size - 10 */
    public safeIntArray() {
        this.internalArray = new int[10];
        this.internalSize = 0;
    }

    public Iterator iterator() {
        return new safeIntArrayIterator(this);
    }



    @Override
    public String toString() {
        return Arrays.toString(internalArray);
    }

    /* add the value val at the end of the array.
     * If the array is full, create a new array of 2x size, and copy everything there. */
    public void add(int val) {
        if (internalSize == internalArray.length) {
            int allocated = internalArray.length;
            int[] temp = internalArray;
            internalArray = new int[2 * allocated];
            for (int i = 0; i < allocated; i++) {
                internalArray[i] = temp[i];
            }
        }
        internalArray[internalSize] = val;
        internalSize++;
    }

    /* set array[index] = val */
    public void set(int index, int val) throws IllegalArgumentException {
        if (index > internalSize || index < 0) {
            throw new IllegalArgumentException("Index must be non-negative");
        } else {
            internalArray[index] = val;
        }
    }

    /* get the value at index */
    public int get(int index) throws IllegalArgumentException {
        if (index > internalSize || index < 0) {
            throw new IllegalArgumentException("Index must be non-negative");
        } else {
            return internalArray[index];
        }
    }

    /* return true if val is in the array */
    public boolean contains(int val) {
        return (indexOf(val) != -1);
    }

    /* return the index where val is stored, or -1 if it's not present. */
    public int indexOf(int val) {
        for (int i = 0; i < internalSize; i++) {
            if (internalArray[i] == val) {
                return i;
            }
        }
        return -1;
    }

    /* remove the element at index. *
       slide all elements in the array down.
     */
    public void remove(int index) throws IllegalArgumentException {
        if (index > internalSize || index < 0) {
            throw new IllegalArgumentException("Invalid index");
        } else {
            for (int i = index; i < internalSize - 1; i++) {
                internalArray[i] = internalArray[i + 1];
            }
            internalArray[internalSize] = 0;
            internalSize--;
        }
    }

    public int size() {
        return internalSize;
    }
}


