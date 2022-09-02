package priorityQueue;

/** A priority queue: represented by the min heap.
 *  Used in Prim's algorithm. */
public class MinHeap {
    // FILL IN CODE
    private MHNode[] heapArr;
    private int[] posArr;
    private int size;

    // get & set
    public MHNode[] getHeapArr() {
        return heapArr;
    }
    public int[] getPosArr() {
        return posArr;
    }
    public int size() {
        return size;
    }



    public MinHeap () {
        size = 0;
        heapArr = new MHNode[size];
        posArr = new int[size];
    }

    public void insert (int nodeId, int priority) {
        this.size++;
        MHNode[] old = heapArr;
        heapArr = new MHNode[this.size];
        for (int i = 0; i < this.size - 1; i++) {
            heapArr[i] = old[i];
        }
        heapArr[size - 1] = new MHNode(nodeId, priority);

        // update posArr
        int max = 0;
        for (MHNode k : heapArr) {
            if (max < k.id) {
                max = k.id;
            }
        }
        posArr = new int[max + 1];
        for (int i = 0; i < posArr.length; i++) {
            posArr[i] = -1;
        }
        for (int i = 0; i < this.size; i++) {
            posArr[heapArr[i].id] = i;
        }



        // up
        int pointer = this.size - 1;
        while ((pointer - 1) / 2 >= 0 && heapArr[pointer].cost < heapArr[(pointer - 1) / 2].cost) { // fater Node >= 0
            swap(pointer, (pointer - 1) / 2);
            pointer = (pointer - 1) / 2;
        }


    }

    public int removeMin() {
        if (this.size == 1) {
            MHNode res = heapArr[0];
            heapArr = new MHNode[0];
            this.size --;
            return res.id;
        }
        MHNode[] old = heapArr;
        this.size--;
        heapArr = new MHNode[this.size];
        heapArr[0] = old[old.length - 1];
        for (int i = 1; i < this.size; i++) {
            heapArr[i] = old[i];
        }
        MHNode resMHNode = old[0];

        // update posArr
        if (posArr.length - 1 == resMHNode.id) {
            int max = 0;
            for (MHNode k : heapArr) {
                if (max < k.id) {
                    max = k.id;
                }
            }
            posArr = new int[max + 1];
            for (int i = 0; i < posArr.length; i++) {
                posArr[i] = -1;
            }
            for (int i = 0; i < this.size; i++) {
                posArr[heapArr[i].id] = i;
            }
        } else {
            for (int i = 0; i < posArr.length; i++) {
                posArr[i] = -1;
            }
            for (int i = 0; i < this.size; i++) {
                posArr[heapArr[i].id] = i;
            }
        }

        // down
        int pointer = 0;
        int child = 2 * pointer + 1;
        while (child < size) { // only have left child
            if (child + 1 < size && heapArr[child].cost > heapArr[child + 1].cost) {
                child++;
            }
            if (heapArr[pointer].cost > heapArr[child].cost) {
                swap(pointer, child);
                pointer = child;
                child = 2 * pointer + 1;
            } else {
                break;
            }
        }




        return resMHNode.id;


    }

    public void reduceKey (int nodeId, int newPriority) {
        heapArr[posArr[nodeId]].cost = newPriority;

        //fix positions
        int pointer = posArr[nodeId];
        while (pointer > 0 && heapArr[pointer].cost < heapArr[(pointer - 1) / 2].cost) {
            swap(pointer, (pointer - 1) / 2);

        }
        int child = 2 * pointer + 1;
        while (child < size) { // only have left child
            if (child + 1 < size && heapArr[child].cost > heapArr[child + 1].cost) {
                child++;
            }
            if (heapArr[pointer].cost > heapArr[child].cost) {
                swap(pointer, child);
                pointer = child;
                child = 2 * pointer + 1;
            } else {
                break;
            }
        }

    }




    // private functions
    private void swap (int id1, int id2) {
        try {
            posArr[heapArr[id1].id] = id2;
            posArr[heapArr[id2].id] = id1;
            MHNode temp = heapArr[id1];
            heapArr[id1] = heapArr[id2];
            heapArr[id2] = temp;
        } catch (Exception ex) {
            ex.getMessage();
        }
    }


    class MHNode {
        public int id;
        public int cost;

        public MHNode(int d, int ost) {
            id = d;
            cost = ost;
        }
    }



}
