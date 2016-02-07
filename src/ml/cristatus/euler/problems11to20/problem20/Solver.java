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

package ml.cristatus.euler.problems11to20.problem20;

import ml.cristatus.euler.BaseSolver;
import ml.cristatus.euler.utils.Count;

import java.math.BigInteger;

/**
 * <h1>Factorial digit sum</h1>
 * n! means n &times; (n &minus; 1) &times; ... &times; 3 &times; 2 &times; 1
 * <p>
 * For example, 10! = 10 &times; 9 &times; ... &times; 3 &times; 2 &times; 1
 * = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * <p>
 * Find the sum of the digits in the number 100!
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
        long number = 100;
        System.out.println(sumOfDigitsByLooping(Count.factorialOf(number)));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long getAnswer() {
        return sumOfDigitsByLooping(Count.factorialOf(100));
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
     * Returns the sum of digits of the given BigInteger using Stream.
     *
     * @param integer The integer whose digits to add.
     * @return The sum of digits of the given BigInteger using Stream.
     */
    public static long sumOfDigitsByStream(BigInteger integer) {
        return integer.toString().chars()
                .map(x -> x & 0xF)
                .sum();
    }

    /**
     * Returns the sum of digits of the given BigInteger using loop.
     *
     * @param integer The integer whose digits to add.
     * @return The sum of digits of the given BigInteger using loop.
     */
    public static long sumOfDigitsByLooping(BigInteger integer) {
        String data = integer.toString();
        long sum = 0;
        for (int i = 0; i < data.length(); i++) {
            sum += data.charAt(i) & 0xF;
        }
        return sum;
    }
}
