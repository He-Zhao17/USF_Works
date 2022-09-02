import java.util.Iterator;

public class safeIntArrayIterator implements Iterator<Integer> {

    private int counter;
    safeIntArray sa;

    /* you do this part. */
    public safeIntArrayIterator(safeIntArray arr) {
        sa = arr;
        counter = 1;
    }

    public int getCounter() {
        return counter;
    }


    /* you do this part. */
    @Override
    public boolean hasNext() {
        if (counter >= sa.internalSize || counter < 1) {
            return false;
        } else {
            return true;
        }
    }

    /* you do this part. */
    @Override
    public Integer next() {
        return sa.get(counter);

    }

    /* you do this part. */
    @Override
    public void remove() {
        sa.remove(counter - 1);
    }
}
