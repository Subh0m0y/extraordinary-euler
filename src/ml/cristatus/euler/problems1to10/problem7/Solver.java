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

package ml.cristatus.euler.problems1to10.problem7;

import ml.cristatus.euler.BaseSolver;
import ml.cristatus.euler.utils.Primes;

import java.math.BigInteger;

/**
 * <h1>10001st prime</h1>
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can
 * see that the 6th prime is 13.
 * <p>
 * What is the 10 001st prime number?
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
        System.out.println(bruteForce(10_001));
        System.out.println(sievedApproach(10_001));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long getAnswer() {
        return sievedApproach(10_001);
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
     * This method uses brute force to iterate through the possible prime
     * candidates, performs the primality check on all of them and goes on
     * incrementing the counter till the required prime is obtained.
     * <p>
     * <b>WARNING:</b> This method is horribly slow using it for values of n
     * longer than 6 decimal digits is ill-advised.
     *
     * @param n The rank of the required prime.
     * @return The nth prime number using brute force.
     */
    public static long bruteForce(final long n) {
        long prime = 2;
        long count = 1;
        long candidate = 3;
        while (count < n) {
            if (naiveIsPrime(candidate)) {
                prime = candidate;
                count++;
            }
            candidate += 2;
        }
        return prime;
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
     * This method generates a sieve upto an estimate and then uses that
     * sieve to return the nth prime.
     *
     * @param n The rank of the required prime.
     * @return The nth prime using a sieve.
     */
    public static long sievedApproach(long n) {
        long estimate = (long) (n * (Math.log(n) + Math.log(Math.log(n))));
        long[] primes = Primes.getPrimesUntil((int) estimate);
        if (primes.length >= n)
            return primes[((int) (n - 1))];
        // If the execution control reaches this point, then an overflow must
        // have occurred.
        throw new InternalError("Possible overflow due to overly large 'n'");
    }
}
