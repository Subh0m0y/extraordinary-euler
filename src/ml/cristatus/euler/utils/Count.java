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

import java.math.BigInteger;

import static java.math.BigInteger.*;

/**
 * A useful class that has functionality to support several functions of
 * counting theory, like Permutations, Combinations, etc.
 *
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class Count {

    public static BigInteger factorialOf(long number) {
        return new ParallelMultiplier(ONE, valueOf(number + 1)).compute();
    }

    public static BigInteger factorialOf(BigInteger integer) {
        return new ParallelMultiplier(ONE, integer.add(ONE)).compute();
    }

    public static BigInteger combinations(long n, long r) {
        // speedy exits
        if (n == r || r == 1)
            return ONE;
        if (n < r)
            throw new IllegalArgumentException
                    ("Invalid parameter for 'r' : " + r);

        // A simple trick to reduce the number of operations
        if (r + r > n)
            r = n - r;

        BigInteger product = ONE, i = ONE, R = valueOf(r);
        for (; i.compareTo(R) <= 0; i = i.add(ONE)) {
            product = product.multiply(valueOf(n--)).divide(i);
        }
        return product;
    }

    public static BigInteger permutations(long n, long r) {
        if (n == r)
            return factorialOf(n);
        if (r == 1)
            return valueOf(n);
        if (n < r)
            throw new IllegalArgumentException
                    ("Invalid parameter for 'r' : " + r);

        long nPlus1 = n + 1;
        return new ParallelMultiplier
                (valueOf(nPlus1 - r), valueOf(nPlus1)).compute();
    }
}
