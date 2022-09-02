package problem7;

// Problem 7:
// Assume the range of values in the array of integers A is from 1 to m,
// and that you have a modified counter array C from counting sort computed by first
// incrementing C[A[i]] for each element of A[i] and then setting each element of C for i from 1 to m to:
//        C[i] = C[i] + C[i-1].
// Write a function numBetween(int c[], int m, int low, int high)
// that takes as input this C array, m, and two values low and high,
// and returns the number of elements in the original list A whose values are between low and high, inclusive.
// Array A may contain duplicates, and you should be able to handle this.
// Running time of your function should be constant, Θ(1).

import java.util.Arrays;

public class Problem7 {
    public static int numBetween(int[] c, int m, int low, int high) {
        // FILL IN CODE

        return 0; // change
    }

    public static int[] createCounterArray(int[] arr, int m) {
        int[] c = new int[m + 1];
        for (int i = 0; i < arr.length; i++) {
            c[arr[i]]++;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(c));
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i] + c[i-1];
        }
        System.out.println(Arrays.toString(c));

        return c;

    }
    public static void main(String[] args) {
        int[] arr = {8, 4, 7, 1, 3, 4, 5, 4, 1, 6, 0, 1, 5, 4, 7};
        int[] c = createCounterArray(arr, 8);
        System.out.println(Arrays.toString(c));

    }

}
