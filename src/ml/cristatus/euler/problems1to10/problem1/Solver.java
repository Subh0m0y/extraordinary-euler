package ml.cristatus.euler.problems1to10.problem1;

/**
 * <h1>Multiples of 3 and 5</h1>
 * If we list all the natural numbers below 10 that are multiples of 3 or 5,
 * we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * <p>
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class Solver {
    public static void main(String[] args) {
        System.out.println(bruteForce(1000));
        System.out.println(sumOfMultiplesOf(3, 5, 1000));
    }

    public static long bruteForce(long limit) {
        long sum = 0;
        for (long i = 1; i < limit; i++) {
            if (i % 3 == 0 || i % 5 == 0)
                sum += i;
        }
        return sum;
    }

    public static long sumOfMultiplesOf(long n1, long n2, long limit) {
        return sumOfMultiplesOf(n1, limit) +
                sumOfMultiplesOf(n2, limit) -
                sumOfMultiplesOf(n1 * n2, limit);
    }

    public static long sumOfMultiplesOf(long number, long limit) {
        long n = (limit - 1) / number;  // exclude 'limit' itself
        return (number * n * (n + 1)) >>> 1;
    }
}
