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

package ml.cristatus.euler.utils;

import java.util.Arrays;

/**
 * This is a utility class that contains methods related to prime numbers.
 *
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class Primes {

    /**
     * Private constructor to prevent instantiation.
     */
    private Primes() {
    }

    /**
     * Returns a boolean array that is essentially a sieve. In order to check
     * if a number is prime, check if the boolean at that index is true. For
     * example,
     * <pre><code>
     *  // ...
     *  boolean[] sieve = Prime.siftUntil(someLimit);
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
    public static boolean[] siftUntil(final int n) {
        final int size = n + 1;
        boolean[] sieve = new boolean[size];
        Arrays.fill(sieve, 2, size, true);  // 0 and 1 are not prime
        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (!sieve[i]) continue;

            for (int j = i * i; j <= n; j += i)
                sieve[j] = false;
        }
        return sieve;
    }

    /**
     * Returns an array of primes upto the given inclusive limit.
     * <p>
     * <h2>Time complexity:</h2>
     * O(n log log n)
     * <h2>Space complexity:</h2>
     * O(n)
     *
     * @param limit The inclusive upper limit.
     * @return An array of primes upto the given inclusive limit.
     */
    public static long[] getPrimesUntil(final int limit) {
        boolean[] isPrime = siftUntil(limit);
        int count = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i])
                count++;
        }
        long[] primes = new long[count];
        for (int i = 2, index = 0; i <= limit; i++) {
            if (isPrime[i])
                primes[index++] = i;
        }
        return primes;
    }
}
