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

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

import static java.math.BigInteger.ONE;

/**
 * Utility class that uses recursion and multi-threading to compute the
 * product of all the (Big)Integers from a given lower limit (inclusive) to a
 * given upper limit (exclusive). This is useful in calculating the factorial
 * of numbers, or the number of combinations and permutations.
 *
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class ParallelMultiplier extends RecursiveTask<BigInteger> {

    /**
     * The threshold beyond which recursion and multithreading starts.
     */
    private static final BigInteger THRESHOLD = BigInteger.valueOf(10_000);

    private final BigInteger upper;
    private final BigInteger lower;

    /**
     * Creates a new instance of ParallelMultiplier with the desired limits.
     *
     * @param lowerLimit The inclusive lower limit.
     * @param upperLimit The exclusive upper limit.
     */
    public ParallelMultiplier(final BigInteger lowerLimit,
                              final BigInteger upperLimit) {
        upper = upperLimit;
        lower = lowerLimit;
    }

    /**
     * Returns the required product.
     *
     * @return The required product.
     */
    @Override
    protected BigInteger compute() {
        if (upper.subtract(lower).compareTo(THRESHOLD) <= 0) {
            // perform sequential multiplication
            BigInteger product = ONE;
            for (BigInteger i = lower; i.compareTo(upper) < 0; i = i.add(ONE))
                product = product.multiply(i);
            return product;
        }
        BigInteger mid = upper.add(lower).shiftRight(1);

        ParallelMultiplier multiplier1 = new ParallelMultiplier(lower, mid);
        ParallelMultiplier multiplier2 = new ParallelMultiplier(mid, upper);

        multiplier1.fork(); // On a (hopefully) separate thread

        // combine and return result
        return multiplier2.compute().multiply(multiplier1.join());
    }
}
