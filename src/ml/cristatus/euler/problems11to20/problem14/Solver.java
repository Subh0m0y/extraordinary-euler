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

package ml.cristatus.euler.problems11to20.problem14;

import ml.cristatus.euler.BaseSolver;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Longest Collatz sequence</h1>
 * <p>The following iterative sequence is defined for the set of positive
 * integers:</p>
 * <p style="margin-left:50px;"><var>n</var> &rarr; <var>n</var>/2 (<var>n</var>
 * is even)<br><var>n</var> &rarr; 3<var>n</var> + 1 (<var>n</var> is odd)</p>
 * <p>Using the rule above and starting with 13, we generate the following
 * sequence:</p>
 * <div style="text-align:center;">13 &rarr; 40 &rarr; 20 &rarr; 10 &rarr; 5
 * &rarr; 16 &rarr; 8 &rarr; 4 &rarr; 2 &rarr; 1
 * </div>
 * <p>It can be seen that this sequence (starting at 13 and finishing at 1)
 * contains 10 terms. Although it has not been proved yet (Collatz Problem),
 * it is thought that all starting numbers finish at 1.</p>
 * <p>Which starting number, under one million, produces the longest chain?</p>
 * <p class="note"><b>NOTE:</b> Once the chain starts the terms are allowed to
 * go above one million.</p>
 *
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class Solver implements BaseSolver {

    /**
     * This map is used to cache the lengths of numbers when invoked for the
     * first time.
     */
    private static final Map<Long, Long> lengthMap
            = new HashMap<>(3_000_000);

    /**
     * The entry point for the program if used in a terminal.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        long limit = 1_000_000;
        System.out.println(bruteForce(limit));
        System.out.println(cacheAndConquer(limit));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long getAnswer() {
        return bruteForce(1_000_000);
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
     * This method uses plain brute force to generate the next term in the
     * sequence till it becomes 1.
     *
     * @return The term which produces the longest sequence.
     */
    public static long bruteForce(final long limit) {
        long max = 0, term = 0;
        for (long i = 1; i < limit; i++) {
            long length = plainLengthOf(i);
            if (max < length) {
                max = length;
                term = i;
            }
        }
        return term;
    }

    /**
     * This method tries to cache the lengths of pre-computed numbers to try
     * and reduce running-time, but actually fails to obtain any edge over
     * the plain old brute-force method.
     *
     * @return The term which produces the longest sequence.
     */
    public static long cacheAndConquer(final long limit) {
        long max = 0, term = 0;
        for (long i = 1; i < limit; i++) {
            long length = lengthOf(i);
            if (max < length) {
                max = length;
                term = i;
            }
        }
        return term;
    }

    /**
     * This is a straightforward iterative implementation of the length
     * counting algorithm that does not use caching.
     * <p>
     * Surprisingly, this method turns out to be just as fast the cahced
     * approach.
     *
     * @param number The number whose sequence length is to be determined.
     * @return The length of the Collatz sequence for this number.
     */
    public static long plainLengthOf(long number) {
        long length = 0;
        while (number != 1) {
            number = (number & 1) == 0
                    ? number >>> 1
                    : 1 + number + (number << 1);
            length++;
        }
        return length;
    }

    /**
     * This method tries to obtain a speed boost by caching the length of
     * sequences of newly encountered number (and fails to do so).
     *
     * @param number The number whose sequence length is to be determined.
     * @return The length of the Collatz sequence for this number.
     */
    public static long lengthOf(long number) {
        if (number == 1)
            return 1;
        if (lengthMap.containsKey(number))
            return lengthMap.get(number);
        long length = 1 + ((number & 1) == 0
                ? lengthOf(number >>> 1)
                : lengthOf(1 + number + (number << 1)));
        lengthMap.put(number, length);
        return length;
    }

}
