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

package ml.cristatus.euler.problems1to10.problem5;

import ml.cristatus.euler.BaseSolver;
import ml.cristatus.euler.utils.Primes;

import java.math.BigInteger;

import static java.lang.Math.*;

/**
 * <h1>Smallest multiple</h1>
 * <p>
 * 2520 is the smallest number that can be divided by each of the numbers
 * from 1 to 10 without any remainder.
 * <p>
 * What is the smallest positive number that is evenly divisible by all of
 * the numbers from 1 to 20?
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
        System.out.println(bruteForce(20));
        System.out.println(cacheAndConquer(20));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    public long getAnswer() {
        return cacheAndConquer(20);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    public BigInteger getBigIntegerAnswer() {
        return BigInteger.valueOf(getAnswer());
    }

    /**
     * Returns the solution using Brute Force. The implementation is highly
     * inefficient, but the simplest I could think of.
     *
     * @param number The required argument, upto which the numbers be processed.
     * @return The solution using Brute Force.
     */
    public static long bruteForce(long number) {
        long product = 1;
        for (long i = number; i >= 1; i--) {
            long copy = i, factor = 2;
            while (copy > 0 && factor <= copy) {
                // The temporary factor stores the consecutive powers of the
                // factor (which may not be prime)
                long tempFactor = factor;
                while (copy % factor == 0) {
                    if (product % tempFactor != 0) {
                        product *= factor;
                    }
                    tempFactor *= factor;
                    copy /= factor;
                }
                factor++;
            }
        }
        return product;
    }

    /**
     * This method obtains a list of primes until the number and calculates
     * the maximum power of each prime present in the least common multiple.
     *
     * @param number The required argument, upto which the numbers be processed.
     * @return The solution obtained by optimization.
     */
    public static long cacheAndConquer(long number) {
        long product = 1;
        long limit = (long) sqrt(number);
        long[] primes = Primes.getPrimesUntil(Math.toIntExact(number));
        for (long prime : primes) {
            long power = 1;
            if (prime <= limit) {
                // The maximum power of this prime :
                power = (long) (log(number) / log(prime));
            }
            product *= (long) pow(prime, power);
        }
        return product;
    }
}