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

package ml.cristatus.euler.problems1to10.problem9;

/**
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class Solver {
    public static void main(String[] args) {
        long sum = 1_000;
        bruteForce(sum);
    }

    public static long bruteForce(long sum) {
        for (long c = 1_000; c >= 3; c--) {
            long cSquare = c * c;
            for (long b = c - 1; b >= 2; b--) {
                long bSquare = b * b;
                for (long a = b - 1; a >= 1; a--) {
                    if (a + b + c != sum)
                        continue;
                    long aSquare = a * a;
                    if (aSquare + bSquare == cSquare) {
                        System.out.println(a * b * c);
                    }
                }
            }
        }
        return -1;  // not found
    }
}
