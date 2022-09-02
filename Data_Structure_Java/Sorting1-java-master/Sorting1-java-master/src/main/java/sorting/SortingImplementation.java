package sorting;

import org.junit.Assert;

import java.io.*;
import java.util.*;

/**  A class that implements SortingInterface. Has various methods
 *   to sort a list of elements. */
public class SortingImplementation  implements SortingInterface {
    private static void swap(Object[] array, int i, int j) {
        Object t;
        t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using insertion sort
     * @param array array of Comparable-s
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     * @param reversed if true, the list should be sorted in descending order
     */



    @Override
    public void insertionSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        if (!reversed) {
            for (int curr = lowindex + 1; curr <= highindex; curr++) {
                for (int currComp = lowindex; currComp < curr; currComp++) {
                    if ( array[currComp].compareTo(array[curr]) > 0) {
                        Comparable temp = array[curr];
                        for (int i = currComp; i <= curr; i++) {
                            Comparable temp1 = temp;
                            temp = array[i];
                            array[i] = temp1;
                        }
                        break;
                    }
                }
            }
        } else {
            for (int curr = lowindex + 1; curr <= highindex; curr++) {
                for (int currComp = lowindex; currComp < curr; currComp++) {
                    if ( array[currComp].compareTo(array[curr]) < 0) {
                        Comparable temp = array[curr];
                        for (int i = currComp; i <= curr; i++) {
                            Comparable temp1 = temp;
                            temp = array[i];
                            array[i] = temp1;
                        }
                        break;
                    }
                }
            }
        }
        // FILL ON CODE
    }

    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     *  using the shaker sort (see pdf for description)
     * @param array array of Comparable-s
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     * @param reversed if true, the list should be sorted in descending order
     */
    public void shakerSort(Comparable[] array, int lowindex, int highindex, boolean reversed) {
        int i, left = 0,
                right = array.length - 1,
                shift = 0;

        while(left < right) {
            for(i = left; i < right; i++) {
                if(array[i].compareTo(array[i + 1]) > 0) {
                    swap(array, i, i+1);
                    shift = i;
                }
            }
            right = shift;
            for(i = right; i > left; i--) {
                if(array[i].compareTo(array[i - 1]) < 0) {
                    swap(array, i ,i-1);
                    shift = i;
                }
            }
            left = shift;
        }


    }


    /**
     * Sorts the sublist of the given list (from lowindex to highindex)
     * using the randomizedQuickSort
     * @param array array to sort
     * @param lowindex the beginning index of a sublist
     * @param highindex the end index of a sublist
     */
    public int partition(Comparable[] array, int lowindex, int highindex) {
        int pointer;
        if (highindex - lowindex + 1 >= 3) {
            int pointer1 = (int) (Math.random() * (highindex - lowindex + 1 ) + lowindex);
            int pointer2;
            int pointer3;
            do {
                pointer2 = (int) (Math.random() * (highindex - lowindex + 1 ) + lowindex);
            } while (pointer2 != pointer1);
            do {
                pointer3 = (int) (Math.random() * (highindex - lowindex + 1 ) + lowindex);
            } while (pointer3 != pointer1 && pointer3 != pointer2);
            if (pointer2 < pointer1) {
                if (pointer1 < pointer3) {
                    pointer = pointer1;
                } else {
                    if (pointer2 > pointer3) {
                        pointer = pointer2;
                    } else {
                        pointer = pointer3;
                    }
                }
            } else {
                if (pointer1 > pointer3) {
                    pointer = pointer1;
                } else {
                    if (pointer2 < pointer3) {
                        pointer = pointer2;
                    } else {
                        pointer = pointer3;
                    }
                }
            }
        } else {
            pointer = (int) (Math.random() * 2 + lowindex);
        }

        swap( array, lowindex , pointer);
        Comparable v = array[lowindex];
        int j = lowindex;
        for( int i = lowindex + 1 ; i <= highindex ; i ++ )
            if( array[i].compareTo(v) <= 0 ){
                j ++;
                swap(array, j, i);
            }
        swap(array, lowindex, j);
        return j;
    }
    @Override
    public void randomizedQuickSort(Comparable[] array, int lowindex, int highindex) {
        if (lowindex >= highindex) {
            return;
        }
        int par = partition(array, lowindex, highindex);
        randomizedQuickSort(array, lowindex, par - 1);
        randomizedQuickSort(array, par + 1, highindex);
        // FILL ON CODE
    }

    /**
     * Sorts a given sublist using hybrid sort that combines insertion sort and randomized quick sort.
     * See pdf for details.
     * @param array array of Comparable-s to sort
     * @param lowindex the beginning index of the sublist
     * @param highindex the end index of the sublist
     */
    @Override
    public void hybridSort(Comparable[] array, int lowindex, int highindex) {
        if (lowindex >= highindex) {
            return;
        }
        int par = partition(array, lowindex, highindex);
        /* The threshold is 10 */
        if (par - 1 - lowindex <= 10) {
            insertionSort(array, lowindex, par -1, false);
        } else {
            randomizedQuickSort(array, lowindex, par - 1);
        }
        /* The threshold is 10 */
        if (highindex - par - 1 <= 10) {
            insertionSort(array, par + 1, highindex, false);
        } else {
            randomizedQuickSort(array, par + 1, highindex);
        }
        // FILL ON CODE

    }

    /**
     * Sorts a sub-array of records using bucket sort.
     * @param array array of records
     * @param lowindex the beginning index of the sublist to sort
     * @param highindex the end index of the sublist to sort
     * @param reversed if true, sort in descending (decreasing) order, otherwise ascending
     */
    @Override
    public void bucketSort(Elem[] array, int lowindex, int highindex, boolean reversed) {
        int max = array[highindex].key();
        /* Get the range of the value in the array. */
        for (int i = lowindex; i <= highindex; i++) {
            if (array[i].key() > max) {
                max = array[i].key();
            }
        }

        int numBuckets = (highindex - lowindex + 1) / 2;
        LinkedList<Elem>[] result = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            result[i] = new LinkedList<Elem>();
        }
        float difference =((float) max) / ((float) numBuckets);
        int index;
        boolean ke = false;
        outer:
        for (int i = lowindex; i <= highindex; i++) {
            index = (int) Math.floor(array[i].key() / difference);
            if (index == numBuckets) {
                index = numBuckets - 1;
            }
            if (reversed) {
                if (result[index].size() == 0) {
                    result[index].addLast(array[i]);
                } else {
                    ke = false;
                    for (int j = 0; j < result[index].size(); j++) {
                        if (array[i].key() >= result[index].get(j).key()) {
                            result[index].add(j, array[i]);
                            ke = true;
                            break;
                        }
                    }
                    if (ke == false) {
                        result[index].addLast(array[i]);
                    }

                }
            } else {
                if (result[index].size() == 0) {
                    result[index].add(array[i]);
                } else {
                    ke = false;
                    for (int j = 0; j < result[index].size(); j++) {
                        if (array[i].key() <= result[index].get(j).key()) {
                            result[index].add(j, array[i]);
                            ke = true;
                            break;
                        }
                    }
                    if (ke == false) {
                        result[index].addLast(array[i]);
                    }
                }

            }

        }
        index = lowindex;
        if (reversed) {
            for (int i = numBuckets - 1; i >= 0; i--) {
                for (Elem k : result[i]) {
                    array[index] = k;
                    index++;
                }
            }
        } else {
            for (int i = 0; i < numBuckets; i++) {
                for (Elem k : result[i]) {
                    array[index] = k;
                    index++;
                }
            }
        }
    // FILL IN CODE

    }




    /**
     * Sorts a sub-array of integers using a radix sort (you may use any base). You may
     * assume that all elements of the array have the same # of digits.
     * @param array array of records
     * @param lowindex the beginning index of the sublist to sort
     * @param highindex the end index of the sublist to sort
     * @param reversed if true, sort in descending (decreasing) order, otherwise ascending
     */
    @Override
    public void radixSort(int[] array, int lowindex, int highindex, boolean reversed) {
        int maxValue = array[lowindex];
        for (int i = lowindex; i <= highindex; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        String maxValueStr = String.valueOf(maxValue);
        int maxValueDigit = maxValueStr.length();
        LinkedList<Integer>[] result = new LinkedList[10];
        for (LinkedList k : result) {
            k = new LinkedList<Integer>();
        }
        int a = 10;
        int b = 1;
        int index;
        for (int i = 0; i < maxValueDigit; i++, a *= 10, b *= 10) {
            for (int k = 0; k < result.length; k++) {
                result[k] = new LinkedList<Integer>();
            }
            for (int j = lowindex; j <= highindex; j++) {
                result[(array[j] % a) / b].add(array[j]);
            }
            index = lowindex;
            for (LinkedList<Integer> l : result) {
                for (int k : l) {
                    array[index] = k;
                    index++;
                }
            }
        }

        // FILL IN CODE

    }

    /**
     * Implements external sort method
     * @param inputFile The file that contains the input list
     * @param outputFile The file where to output the sorted list
     * @param k number of elements that fit into memory at once
     * @param m number of chunks
     */
    public void externalSort(String inputFile, String outputFile, int k, int m) {
        try {
            Scanner scan = new Scanner(new File(inputFile));
            int[] temp1 = new int[k];
            for (int i = 0; i < m - 1; i++) {
                temp1 = new int[k];
                for (int j = 0; j < k; j++) {
                    try {
                        temp1[k] = Integer.getInteger(scan.nextLine());
                    } catch (Exception e) {
                        e.printStackTrace();
                        
                    }
                }
                for (int curr = 1; curr < k; curr++) {
                    for (int currComp = 0; currComp < curr; currComp++) {
                        if ( temp1[currComp] > temp1[curr]) {
                            int temp = temp1[curr];
                            for (int l = currComp; l <= curr; l++) {
                                int temp2 = temp;
                                temp = temp1[l];
                                temp1[l] = temp2;
                            }
                            break;
                        }
                    }
                }
                BufferedWriter wr = new BufferedWriter(new FileWriter("temp" + String.valueOf(i)));
                for (int j = 0; j < k; j++) {
                    wr.write(temp1[j] + "\n");
                }
                wr.close();
            }
            ArrayList<Integer> temp3 = new ArrayList<>();
            while (scan.hasNext()) {
                temp3.add(Integer.getInteger(scan.nextLine()));
            }
            scan.close();
            int[] temp4 = new int[temp3.size()];
            for (int l = 0; l < temp3.size(); l++) {
                temp4[l] = temp3.get(l);
            }
            for (int curr = 1; curr < temp3.size(); curr++) {
                for (int currComp = 0; currComp < curr; currComp++) {
                    if ( temp1[currComp] > temp1[curr]) {
                        int temp = temp1[curr];
                        for (int l = currComp; l <= curr; l++) {
                            int temp2 = temp;
                            temp = temp1[l];
                            temp1[l] = temp2;
                        }
                        break;
                    }
                }
            }
            BufferedWriter wr = new BufferedWriter(new FileWriter("temp" + String.valueOf(m - 1)));
            for (int j = 0; j < temp3.size(); j++) {
                wr.write(temp1[j] + "\n");
            }
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        // FILL IN CODE

    }

    /**
     * Suppose some city has n people, and these people need to vote to select a mayor of the city. There are three candidates for a mayor: "A",  "B" and "C".  We are given an array of n Strings where each element represents a vote for either candidate "A" or candidate "B", or candidate "C". For the purpose of this problem, let's assume there is a clear winner (so one candidate has more votes than the other two).
     * Design and implement (in Java) an in-place algorithm for sorting this array and determining who wins the election, "A", "B" or "C".
     * Example: if we are given the following array that represents votes of 11 people:
     *             ["A", "B", "A", "C", "A", "A", "A", "B", "C", "A", "B"],
     *     your method should return "A" and change the array so that it is sorted:
     *             ["A", "A", "A", "A", "A", "A", "B", "B", "B",  "C", "C"]
     *  The algorithm must satisfy the following requirements:
        - Use the variation of the partition method of quicksort)
        - Should run in linear time
        - Use no extra memory (except for two integer indices and a tmp variable for swapping).
        - Run in two passes
     * Important: Do NOT just iterate over the array and count the number of "A"s, "B"s and "C"s  - such solutions will get 0 points.
     * Do NOT use counting sort.
     *
     * @param votes input array of votes
     * @return winner
     */
    public String sortAndFindWinner (String[] votes) {
        int hIndex = votes.length - 1;

        int pointer1 = partition(votes, 0, votes.length - 1);
        while (votes[pointer1] == "C") {
            hIndex--;
            pointer1 = partition(votes, 0, hIndex);
        }

        if (votes[pointer1] == "A") {
            hIndex = partition(votes, pointer1 + 1, hIndex);
            if (pointer1 + 1 > hIndex - pointer1) {
                if (votes.length - pointer1 - 1 > pointer1 + 1) {
                    return "C";
                } else {
                    return "A";
                }
            } else {
                if (hIndex - pointer1 > votes.length - 1 - hIndex) {
                    return "B";
                } else {
                    return "C";
                }
            }
        } else {
            hIndex = pointer1;
            while(votes[pointer1] == "B") {
                pointer1--;
                pointer1 = partition(votes, 0, pointer1);
            }

            pointer1 = partition(votes, 0, pointer1);
            if (pointer1 + 1 > hIndex - pointer1) {
                if (votes.length - pointer1 - 1 > pointer1 + 1) {
                    return "C";
                } else {
                    return "A";
                }
            } else {
                if (hIndex - pointer1 > votes.length - 1 - hIndex) {
                    return "B";
                } else {
                    return "C";
                }
            }
        }

        // FILL IN CODE

        //return ""; // replace
    }


    // FILL IN CODE:
    // Research and implement one more sorting method that we did not discuss in class.
    // Do not copy code from the web. Implement the algorithm yourself.
    // Describe it in a Readme file.

    public void selectedSort (Comparable[] array, int lowindex, int highindex) {
        int start = lowindex;
        Comparable min;
        int minIndex;
        for (; start <= highindex; start++) {
            min = array[start];
            minIndex = start;
            for (int i = start; i <= highindex; i++) {
                if (array[i].compareTo(min) < 0) {
                    min = array[i];
                    minIndex = i;
                }
            }
            swap(array, minIndex, start);
        }
    }

}