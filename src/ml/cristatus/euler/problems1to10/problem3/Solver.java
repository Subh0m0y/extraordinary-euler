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

package ml.cristatus.euler.problems1to10.problem3;

/**
 * <h1>Largest prime factor</h1>
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * <p>
 * What is the largest prime factor of the number 600851475143 ?
 *
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class Solver {

    /**
     * The entry point for the program if used in a terminal.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        bruteForce(600851475143L);
        betterBruteForce(600851475143L);
    }

    /**
     * This method keeps on trying to divide the number by all numbers less
     * than or equal to it till it ends up as a 1.
     *
     * @param number The number whose largest prime factor is sought.
     * @return The largest prime factor of the number.
     */
    public static long bruteForce(long number) {
        long lastFactor = 1;
        long factor = 2;
        while (number > 1) {
            if (number % factor == 0) {
                lastFactor = factor;
                number /= factor;
                while (number % factor == 0) {
                    number /= factor;
                }
            }
            factor++;
        }
        return lastFactor;
    }

    /**
     * This method skips ahead alternatively by 2 and 4 to find the factors
     * and goes on till either the number is 1 or it is a prime itself.
     *
     * @param number The number whose largest prime factor is sought.
     * @return The largest prime factor of the number.
     */
    public static long betterBruteForce(long number) {
        long lastFactor = 1;
        // check for 2
        if ((number & 1) == 0) {
            lastFactor = 2;
            number >>>= Long.numberOfTrailingZeros(number);
        }
        // check for 3
        if (number % 3 == 0) {
            lastFactor = 3;
            number /= 3;
            while (number % 3 == 0) number /= 3;
        }
        long factor = 5, limit = Math.round(Math.sqrt(number));
        boolean two = true;
        while (factor <= limit) {
            if (number % factor == 0) {
                lastFactor = factor;
                number /= factor;
                while (number % factor == 0) number /= factor;
                // update the limit to reduce the number of iterations
                limit = Math.round(Math.sqrt(number));
            }
            factor += two ? 2 : 4;
            two ^= true;   // flip the boolean
        }
        return number == 1 ? lastFactor : number;
    }
}
