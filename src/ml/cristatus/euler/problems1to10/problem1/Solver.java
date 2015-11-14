/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Subhomoy Haldar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package ml.cristatus.euler.problems1to10.problem1;

import ml.cristatus.euler.BaseSolver;

import java.math.BigInteger;

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
public class Solver implements BaseSolver {

    /**
     * The entry point for the program if used in a terminal.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println(bruteForce(1000));
        System.out.println(sumOfMultiplesOf(3, 5, 1000));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long getAnswer() {
        return sumOfMultiplesOf(3, 5, 1_000);
    }


    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public BigInteger getBigIntegerAnswer() {
        return BigInteger.valueOf(getAnswer());
    }

    /**
     * This method calculates the answer to the question in the old-fashioned
     * way, that is, using a loop.
     * <h2>Time complexity:</h2>
     * O(n), as it processes n elements.
     * <h2>Space complexity:</h2>
     * O(1), as it maintains only one sum variable.
     *
     * @param limit The (exclusive) limit for the calculation.
     * @return The answer to the question.
     */
    public static long bruteForce(long limit) {
        long sum = 0;
        for (long i = 1; i < limit; i++) {
            if (i % 3 == 0 || i % 5 == 0)
                sum += i;
        }
        return sum;
    }

    /**
     * This method implements a constant-time algorithm to compute the
     * required result. After a certain threshold point, it returns the
     * answer faster that the brute-force method.
     * <h2>Time complexity:</h2>
     * O(1), as it performs a fixed number of calculations.
     * <h2>Space complexity:</h2>
     * O(1), as it consumes a fixed stack size due to method calls.
     *
     * @param n1    The first number whose multiples are needed.
     * @param n2    The second number whose multiples are needed.
     * @param limit The (exclusive) limit for the calculation.
     * @return The answer to the question.
     */
    public static long sumOfMultiplesOf(long n1, long n2, long limit) {
        return sumOfMultiplesOf(n1, limit) +
                sumOfMultiplesOf(n2, limit) -
                sumOfMultiplesOf(n1 * n2, limit);
    }

    /**
     * This method returns the sum of multiples of the given number less than
     * the limit.
     *
     * @param number The number whose multiples to count.
     * @param limit  The (exclusive) limit for the calculation.
     * @return The sum of multiples of the given number less than the limit.
     */
    public static long sumOfMultiplesOf(long number, long limit) {
        long n = (limit - 1) / number;  // exclude 'limit' itself
        return (number * n * (n + 1)) >>> 1;
    }
}
