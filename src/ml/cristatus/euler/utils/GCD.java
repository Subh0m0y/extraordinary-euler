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
 * This is a utility class that provides the functionality of calculating the
 * GCD or Greatest Common Divisor (also known as HCF or Highest Common
 * Factor) of two or more integers.
 * <p>
 * It has only two methods:
 * <ol>
 * <li>{@link #of(long, long)}</li>
 * <li>{@link #of(long, long, long...)}</li>
 * </ol>
 * Usage is very simple:
 * <pre><code>
 * ...
 * // assuming a, b, c, d and e are integers (long, int, etc.)
 * long gcd1 = GCD.of(a, b);
 * long gcd2 = GCD.of(a, b, c);
 * long gcd3 = GCD.of(a, b, c, d);
 * long gcd4 = GCD.of(a, b, c, e);
 * ...
 * </code></pre>
 *
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class GCD {
    /**
     * Private constructor to prevent instantiation.
     */
    private GCD() {
    }

    /**
     * This method computes the GCD of two positive integers using the Binary
     * GCD algorithm.
     * <p>
     * This method does not complain if the integers provided are negative.
     * In such a case, it simply the absolute value and performs the
     * computation. If one of the arguments is zero, the other argument is
     * returned. If both are zero, then zero is returned.
     *
     * @param a The first positive integer.
     * @param b The second positive integer.
     * @return The GCD of two positive integers.
     */
    public static long of(long a, long b) {
        if (a == 0) return b;
        if (b == 0) return a;
        if (a < 0) a = -a;
        if (b < 0) b = -b;

        // store the power of 2 common to them
        int a0 = Long.numberOfTrailingZeros(a);
        int b0 = Long.numberOfTrailingZeros(b);
        int commonPower = a0 < b0 ? a0 : b0;

        // remove powers of 2
        a >>>= a0;
        b >>>= b0;

        while (a != b) {
            if (a > b) {
                a -= b;
                a >>>= Long.numberOfTrailingZeros(a);
            } else {
                b -= a;
                b >>>= Long.numberOfTrailingZeros(b);
            }
        }

        // restore the common power
        return a << commonPower;
    }

    /**
     * This method returns the GCD of all the integers provided.
     * <p>
     * This method makes use of the {@link #of(long, long) of(long, long)}
     * method.
     *
     * @param a      The first positive integer.
     * @param b      The second positive integer.
     * @param others The other positive integers.
     * @return The GCD of all the integers provided.
     * @see #of(long, long) Internally used by this method.
     */
    public static long of(long a, long b, long... others) {
        long gcd = of(a, b);
        for (long number : others) {
            gcd = of(gcd, number);
        }
        return gcd;
    }
}
