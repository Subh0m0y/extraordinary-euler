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

package ml.cristatus.euler.problems1to10.problem4;

import ml.cristatus.euler.BaseSolver;

import java.math.BigInteger;

/**
 * <h1>Largest palindrome product</h1>
 * <p>
 * A palindromic number reads the same both ways. The largest palindrome made
 * from the product of two 2-digit numbers is 9009 = 91 &times; 99.
 * <p>
 * Find the largest palindrome made from the product of two 3-digit numbers.
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
        System.out.println(bruteForce(3));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long getAnswer() {
        return bruteForce(4_000_000);
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
     * Returns {@code true} if the given Long is a Palindrome, i.e. on
     * reversing the sequence of digits, we get the same number.
     *
     * @param number The number to check.
     * @return {@code true} if the given Long is a Palindrome.
     */
    public static boolean isPalindrome(long number) {
        long reverse = 0, copy = number;
        while (copy > 0) {                      // till there are digits left
            long lastDigit = copy % 10;         // extract last digit
            reverse = reverse * 10 + lastDigit; // shift up by 1 and add digit
            copy /= 10;                         // shift down by 1
        }
        return reverse == number;
    }

    /**
     * This method calculates the answer by starting from the upper limit and
     * goes on finding the products of unique products. If this method
     * returns zero, then no such products were found.
     *
     * @param numberOfDigits The number of digits of the terms.
     * @return The answer using brute force.
     */
    public static long bruteForce(int numberOfDigits) {
        long upper = (long) Math.pow(10, numberOfDigits) - 1;
        long lower = (long) Math.pow(10, numberOfDigits - 1);
        // the default value, returned if no palindromes found
        long highest = 0;
        for (long i = upper; i >= lower; i--) {
            // using "j >= i" ensures we are processing unique pairs.
            for (long j = upper; j >= i; j--) {
                long product = i * j;
                // the comparison seems less expensive than the method call
                if (highest < product && isPalindrome(product))
                    highest = product;
            }
        }
        return highest;
    }
}
