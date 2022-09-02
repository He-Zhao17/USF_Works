package problem1;

// (4 pt) Give Theta of the running time (as a function of n) of the following code snippets:
// (a)
// Your answer: Theta(n^3)
// Justification:
// For the outer loop, it is Theta(n).
// And for the inner loop, it is (n-1) + (n-2) + (n-3) + ... + 1 + 0 = [(n-1) + 1]/2 * (n - 1) = n(n - 1)/2 = Theta(n^2)
// For the printlb loop, k is up to the duplicates elements in the array, and we can use it as Theta(n)
// So, Theta(n^2) + Theta(n) = Theta(n^2)
//

// (b)
// Your answer: Theta(n^2logn)
// Justification:
// For the outer loop, it is Theta ((n^2 - 1)/2)
// For the inner loop, it is
// log( n^2/1) + log (n^2/2) + ... + log(n^2/n^2)
// = log(n^2) - log 1 + log (n^2) -log 2 + ...
// = n^2log(n^2) - (log 1 + log 2 + ... + log (n^2))
// = 2n^2logn - (log 1 + log 2 + ... + log (n^2))
// = Theta(n^2logn) - n^2log(n^2)
// = Theta(n^2logn)

// You may submit the answer to this question either via Github or
// write down your solution on paper, take a photo at the end of the exam and upload to Canvas.
public class Problem1_AlgorithmAnalysis {

    // (a) (2 pt) Give Theta of the running time of printDup function
    public static void printDup(int[] arr) {
        int n = arr.length;
        int[] dup = new int[arr.length];
        int k = 0;
        for (int i = 0; i < n; i++) { // Theta( n )
            boolean flag = false;
            for (int j = i + 1; j < n; j++) {// (n-1) + (n - 2) + (n - 3) + ... + 0 = Theta (n^2)
                if (arr[i] == arr[j]) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                dup[k] = arr[i];
                k++;
            }
        }
        for (int m = 0; m < k; m++) { // Theta (n)
            System.out.println(dup[m]);
        }
    }

    // (b) (2 pt)
    // Give Theta of the running time of compute(n) function
    public static int compute(int n) {
        int sum = 0;
        int i = 1;
        while (i <= n*n)  { // note that i goes to n^2
            int j = 1;
            while (j <= n*n) { // note that j goes to n^2   log (n^2/1) + log
                sum +=  i + j*j;
                j = j * 4; // note that j is multiplied by 4
            }
            i = i + 2;
        }
        return sum;
    }

}
