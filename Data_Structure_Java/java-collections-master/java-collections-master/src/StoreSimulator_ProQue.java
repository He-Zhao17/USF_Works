import java.util.*;


public class StoreSimulator_ProQue {

    public Random generator;

    public StoreSimulator_ProQue() {
        generator = new Random();
    }


    class Task {
        int arrivalTime;
        int duration;

        Task(int a, int d) {
            arrivalTime = a;
            duration = d;
        }
        public String toString() {
            return "[" + Integer.toString(arrivalTime) + " " + Integer.toString(duration) + "]";
        }
    }

    /* return between 0 and n new arrivals. Currently n=3 */
    /* currently each task has duration 5 */
    public List<Task> getNewArrivals(int currentTime, int maxdura, int narrivals) {
        //------To extend the duration to a range of random as parameter
        Random radu = new Random();
        int du = radu.nextInt(maxdura);
        //------
        int nArrivals = generator.nextInt(narrivals);
        ArrayList<Task> returnTasks = new ArrayList<>();
        for (int i = 0; i < nArrivals; i++) {
            returnTasks.add(new Task(currentTime, du));
        }
        return returnTasks;
    }


    public List<Integer> simulate(int nIterations, int nQueues, int maxdura, int narrivals) {
        Comparator<Task> cpt = new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o2.arrivalTime - o1.arrivalTime;
            }
        };
        LinkedList<Queue<Task>> qs = new LinkedList<>();
        for (int i = 0; i < nQueues; i++) {
            Queue<Task> temp = new PriorityQueue<>(cpt);
            qs.add(temp);
        }
        Comparator<Queue<Task>> cpq = new Comparator<Queue<Task>>() {
            @Override
            public int compare(Queue<Task> o1, Queue<Task> o2) {
                return o1.size() - o2.size();
            }
        };
        Collections.sort(qs, cpq);
        int nArrivals = 0;
        Random gen = new Random();
        List<Task> newArrivals;
        List<Integer> re = new ArrayList<>();

        for (int i = 0; i < nIterations; i++) {

            /* get the new customers and add them to the queue */
            newArrivals = getNewArrivals(i, maxdura, narrivals);
            for (Task t : newArrivals) {
                qs.get(0).add(t);
                Collections.sort(qs, cpq);
            }

            /* check to see if the person at the front of the queue is done */
            for (Queue<Task> q: qs) {
                if (!q.isEmpty() && q.peek().arrivalTime + q.peek().duration < i) {
                    Task done = q.remove();
                    System.out.println("Waiting time:" + (i - done.arrivalTime));
                    re.add(i - done.arrivalTime);
                }
            }

        }
        return re;
    }


    public static void main(String[] args) {
        StoreSimulator st = new StoreSimulator();
        List<Integer> throughout = st.simulate(500,2,15,3);
        int sum = 0;
        int min = throughout.get(0);
        int max = throughout.get(0);
        for (int i: throughout) {
            sum += i;
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        double sumd = (double) sum;
        double num = (double) throughout.size();
        double ave = sumd / num;
        System.out.println("Max throughout: " + max + "\nMin throughout: " + min + "\nAverage throughout: " + ave);



    }
}


