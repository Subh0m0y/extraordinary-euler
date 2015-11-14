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

package ml.cristatus.euler.problems1to10.problem6;

import ml.cristatus.euler.BaseSolver;

import java.math.BigInteger;

/**
 * <h1>Sum square difference</h1>
 * The sum of the squares of the first ten natural numbers is,
 * <p>
 * 1&sup2; + 2&sup2; + ... + 10&sup2; = 385}
 * <p>
 * The square of the sum of the first ten natural numbers is,
 * <p>
 * (1 + 2 + ... + 10)&sup2; = 552 = 3025
 * <p>
 * Hence the difference between the sum of the squares of the first ten
 * natural numbers and the square of the sum is 3025 &minus; 385 = 2640.
 * <p>
 * Find the difference between the sum of the squares of the first one
 * hundred natural numbers and the square of the sum.
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
        System.out.println(bruteForce(100));
        System.out.println(betterApproach(100));
        System.out.println(bestApproach(100));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long getAnswer() {
        return bestApproach(100);
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
     * This method calculates the answer by looping through the integers from
     * 1 to the number and incrementally summing up the terms.
     *
     * @param number The inclusive upper limit.
     * @return The solution calculated using brute force.
     */
    public static long bruteForce(final long number) {
        long sum1 = naiveSumOfNumbersUntil(number);
        long sum2 = naiveSumOfSquaresUntil(number);
        return sum1 * sum1 - sum2;
    }

    /**
     * Returns the sum of the square of each number from 1 to the number.
     * <p>
     * The implementation uses the naive approach of loops.
     *
     * @param number The inclusive upper limit.
     * @return The sum of the square of each number from 1 to the number.
     */
    public static long naiveSumOfSquaresUntil(final long number) {
        long sum = 0;
        for (long i = 1; i <= number; i++)
            sum += i * i;
        return sum;
    }

    /**
     * Returns the sum of numbers from 1 to the number.
     * <p>
     * The implementation uses the naive approach of loops.
     *
     * @param number The inclusive upper limit.
     * @return The sum of numbers from 1 to the number.
     */
    public static long naiveSumOfNumbersUntil(final long number) {
        long sum = 0;
        for (long i = 1; i <= number; i++)
            sum += i;
        return sum;
    }

    /**
     * This method uses two separate constant-time methods to calculate the
     * solution.
     *
     * @param number The inclusive upper limit.
     * @return The solution in constant time.
     */
    public static long betterApproach(final long number) {
        long sum1 = betterSumOfNumbersUntil(number);
        long sum2 = betterSumOfSquaresUntil(number);
        return sum1 * sum1 - sum2;
    }

    /**
     * Returns the sum of the square of each number from 1 to the number.
     * <p>
     * This method uses a formula and returns the value in constant time.
     *
     * @param number The inclusive upper limit.
     * @return The sum of the square of each number from 1 to the number.
     */
    public static long betterSumOfSquaresUntil(final long number) {
        // n  * (  n + 1 ) * ( 2n + 1 ) / 6
        return ((number * (number + 1) * ((number << 1) + 1)) >>> 1) / 3;
    }

    /**
     * Returns the sum of numbers from 1 to the number.
     * <p>
     * This method uses a formula and returns the value in constant time.
     *
     * @param number The inclusive upper limit.
     * @return The sum of numbers from 1 to the number.
     */
    public static long betterSumOfNumbersUntil(final long number) {
        return (number * (number + 1)) >>> 1;
    }

    /**
     * Calculates the solution using a single formula. Out of the methods
     * implemented here, it is the fastest as it uses the lest number of
     * operations.
     *
     * @param number The inclusive upper limit.
     * @return The solution in constant time.
     */
    public static long bestApproach(final long number) {
        long nTimes3 = (number << 1) + number;
        return number * (number * number - 1) * (nTimes3 + 2) / 12;
    }
}
