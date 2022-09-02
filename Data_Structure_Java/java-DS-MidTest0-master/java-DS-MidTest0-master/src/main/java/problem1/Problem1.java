package problem1;

// Give Theta of the running time (as a function of n) of the following code snippets:
// Your answer:
// (a)
// (b)
public class Problem1 {

    // (a)
    public static int func1(int n) {
        int sum1 = 0, sum2 = 0;
        for (int m = 1; m <= n; m = m * 2) {
            for (int l = 1; l <= n; l = l + 5)
                sum1++;

        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++)
                sum2++;
        }

        return sum1 * sum2;
    }

    // (b)
    public static void func2(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j = j * 2)
                sum = sum + func1(n*n); // here we call func1 from (a)

        }
        System.out.println(sum);
    }
}
