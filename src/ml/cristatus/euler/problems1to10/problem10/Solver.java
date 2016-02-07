/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 - 2016 Subhomoy Haldar
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

package ml.cristatus.euler.problems1to10.problem10;

import ml.cristatus.euler.BaseSolver;
import ml.cristatus.euler.utils.Primes;

import java.math.BigInteger;
import java.util.stream.LongStream;

/**
 * <h1>Summation of primes</h1>
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * <p>
 * Find the sum of all the primes below two million.
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
        int limit = 2_000_000;
        System.out.println(bruteForce(limit));
        System.out.println(sievedApproach(limit));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long getAnswer() {
        return sievedApproach(1_000);
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
     * This solution uses brute force to iterate through the range by
     * skipping to possible candidates.
     *
     * @param limit The inclusive upper limit.
     * @return The solution using brute force.
     */
    public static long bruteForce(int limit) {
        if (limit < 2) return 0;
        if (limit == 2) return 2;
        if (limit == 3) return 5;

        long sum = 5;   // 2 and 3
        long skip;
        boolean two = false;
        for (long i = 5; i <= limit; i += skip) {
            if (naiveIsPrime(i))
                sum += i;
            two ^= true;
            skip = two ? 2 : 4;
        }
        return sum;
    }

    /**
     * This is a implementation of the naive primality checking algorithm
     * with a few minor optimizations.
     *
     * @param number The number whose primality is to be determined.
     * @return {@code true} if the number is prime.
     */
    public static boolean naiveIsPrime(final long number) {
        if (number < 2) return false;   // 0, 1 and (-)ves
        if (number < 4) return true;    // 2 and 3
        if ((number & 1) == 0)
            return false;
        if (number % 3 == 0)
            return false;
        long factor = 5;
        long limit = (long) Math.sqrt(number);
        boolean two = true;
        while (factor <= limit) {
            if (number % factor == 0)
                return false;
            factor += two ? 2 : 4;
            two ^= true;
        }
        return true;
    }

    /**
     * This method uses a sieve to generate a list of primes till the
     * inclusive limit and then returns their sum.
     *
     * @param limit The inclusive upper limit.
     * @return The solution using sieves.
     */
    public static long sievedApproach(int limit) {
        long[] primes = Primes.getPrimesUntil(limit);
        return LongStream.of(primes).sum();
    }
}
