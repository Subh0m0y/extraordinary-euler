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

package ml.cristatus.euler.utils;

/**
 * This is a utility class that contains methods related to prime numbers
 * that are used in many problems in Project Euler.
 *
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class Primes {

    /**
     * Returns a boolean array that is essentially a sieve. In order to check
     * if a number is prime, check if the boolean at that index is true. For
     * example,
     * <pre><code>
     *  // ...
     *  boolean[] sieve = Prime.siftTill(someLimit);
     *  int n = (yourNumber);
     *  if (sieve[n])
     *      // ... do operations with the prime number
     *  // ...
     * </code></pre>
     * <p>
     * <h2>Time complexity:</h2>
     * O(n log log n)
     * <h2>Space complexity:</h2>
     * O(n)
     *
     * @param n The number till which the sieve must be generated.
     * @return The sieve in the form of a boolean array.
     */
    public static boolean[] siftTill(int n) {
        // create an array of size (n + 1), to include the index n as well
        boolean[] sieve = new boolean[++n];
        // the outer loop will be limited to ~ sqrt(n) iterations
        int limit = (int) Math.sqrt(n);
        // initially set all to true except 0 and 1
        for (int i = 2; i < n; i++) sieve[i] = true;
        // start from 2 and remove all multiples of primes
        for (int i = 2; i < limit; i++) {
            if (sieve[i]) {
                for (int j = i * i; j < n; j += i) {
                    sieve[j] = false;
                }
            }
        }
        return sieve;
    }

    /**
     * Returns an array of primes upto the given inclusive limit.
     * <p>
     * <h2>Time complexity:</h2>
     * O(n)
     * <h2>Space complexity:</h2>
     * O(n)
     *
     * @param limit The inclusive upper limit.
     * @return An array of primes upto the given inclusive limit.
     */
    public static long[] getPrimesTill(int limit) {
        boolean[] isPrime = siftTill(limit);
        // 1st pass: count the number of primes
        int count = 0, index = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i])
                count++;
        }
        long[] primes = new long[count];
        // 2nd pass: store the primes
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i])
                primes[index++] = i;
        }
        return primes;
    }
}
