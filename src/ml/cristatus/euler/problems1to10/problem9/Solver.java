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

package ml.cristatus.euler.problems1to10.problem9;

import ml.cristatus.euler.BaseSolver;

import java.math.BigInteger;

/**
 * <h1>Special Pythagorean triplet</h1>
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * <p>
 * a&sup2; + b&sup2; = c&sup2;
 * <p>
 * For example, 3&sup2; + 4&sup2; = 9 + 16 = 25 = 5&sup2;.
 * <p>
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
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
        long sum = 1_000;
        System.out.println(bruteForce(sum));
        System.out.println(numberTheoreticalApproach(sum));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long getAnswer() {
        return numberTheoreticalApproach(1_000);
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
     * This method tries to find a set of three numbers that satisfy the
     * condition.
     *
     * @param sum The sum that the three numbers must reach.
     * @return The solution using brute force.
     */
    public static long bruteForce(long sum) {
        for (long c = 1_000; c >= 3; c--) {
            long cSquare = c * c;
            for (long b = c - 1; b >= 2; b--) {
                long bSquare = b * b;
                for (long a = b - 1; a >= 1; a--) {
                    if (a + b + c != sum)
                        continue;
                    long aSquare = a * a;
                    if (aSquare + bSquare == cSquare) {
                        return a * b * c;
                    }
                }
            }
        }
        return -1;  // not found
    }

    /**
     * Using a bit of number theory, the number of unknowns is reduced to 2.
     * By further adding a constraint, the running time is further reduced.
     *
     * @param sum The sum that the three numbers must reach.
     * @return The solution using a number theoretical approach.
     */
    public static long numberTheoreticalApproach(long sum) {
        long halfSum = sum >>> 1;
        long estimate = (long) Math.sqrt(halfSum);
        for (long m = 1; m < estimate; m++) {
            for (long n = 0; n < m; n++) {
                long partial = m * (m + n);
                if (partial == halfSum) {
                    return ((m * m * m * m - n * n * n * n) * m * n) << 1;
                }
            }
        }
        return -1;  // not found
    }
}
