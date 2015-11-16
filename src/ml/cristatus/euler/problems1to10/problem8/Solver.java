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

package ml.cristatus.euler.problems1to10.problem8;

import ml.cristatus.euler.BaseSolver;

import java.math.BigInteger;

/**
 * <h1>Largest product in a series</h1>
 * The four adjacent digits in the 1000-digit number that have the greatest
 * product are 9 × 9 × 8 × 9 = 5832.
 * <pre>
 * 73167176531330624919225119674426574742355349194934
 * 96983520312774506326239578318016984801869478851843
 * 85861560789112949495459501737958331952853208805511
 * 12540698747158523863050715693290963295227443043557
 * 66896648950445244523161731856403098711121722383113
 * 62229893423380308135336276614282806444486645238749
 * 30358907296290491560440772390713810515859307960866
 * 70172427121883998797908792274921901699720888093776
 * 65727333001053367881220235421809751254540594752243
 * 52584907711670556013604839586446706324415722155397
 * 53697817977846174064955149290862569321978468622482
 * 83972241375657056057490261407972968652414535100474
 * 82166370484403199890008895243450658541227588666881
 * 16427171479924442928230863465674813919123162824586
 * 17866458359124566529476545682848912883142607690042
 * 24219022671055626321111109370544217506941658960408
 * 07198403850962455444362981230987879927244284909188
 * 84580156166097919133875499200524063689912560717606
 * 05886116467109405077541002256983155200055935729725
 * 71636269561882670428252483600823257530420752963450
 * </pre>
 * Find the thirteen adjacent digits in the 1000-digit number that have the
 * greatest product. What is the value of this product?
 *
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class Solver implements BaseSolver {
    /**
     * The 1000 digit number.
     */
    public static final String DATA
            = "73167176531330624919225119674426574742355349194934"
            + "96983520312774506326239578318016984801869478851843"
            + "85861560789112949495459501737958331952853208805511"
            + "12540698747158523863050715693290963295227443043557"
            + "66896648950445244523161731856403098711121722383113"
            + "62229893423380308135336276614282806444486645238749"
            + "30358907296290491560440772390713810515859307960866"
            + "70172427121883998797908792274921901699720888093776"
            + "65727333001053367881220235421809751254540594752243"
            + "52584907711670556013604839586446706324415722155397"
            + "53697817977846174064955149290862569321978468622482"
            + "83972241375657056057490261407972968652414535100474"
            + "82166370484403199890008895243450658541227588666881"
            + "16427171479924442928230863465674813919123162824586"
            + "17866458359124566529476545682848912883142607690042"
            + "24219022671055626321111109370544217506941658960408"
            + "07198403850962455444362981230987879927244284909188"
            + "84580156166097919133875499200524063689912560717606"
            + "05886116467109405077541002256983155200055935729725"
            + "71636269561882670428252483600823257530420752963450";

    /**
     * The entry point for the program if used in a terminal.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        int runLength = 13;
        System.out.println(bruteForce(runLength));
        System.out.println(skippingApproach(runLength));
        System.out.println(fasterApproach(runLength));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long getAnswer() {
        return skippingApproach(13);
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
     * This method checks every sequence of adjacent digits of specified run
     * length and compares their products.
     *
     * @param runLength The required length of the sequence.
     * @return The solution using brute force.
     */
    public static long bruteForce(final int runLength) {
        int length = DATA.length() - runLength;
        long max = 0;
        for (int i = 0; i < length; i++) {
            long product = 1;
            for (int j = 0; j < runLength; j++) {
                product *= DATA.charAt(i + j) & 0xF;
            }
            if (max < product)
                max = product;
        }
        return max;
    }

    /**
     * This method is similar, but it skips the run when it finds a 0 and
     * resets the current product.
     *
     * @param runLength The required length of the sequence.
     * @return The solution using a slightly better brute force.
     */
    public static long skippingApproach(final int runLength) {
        int length = DATA.length() - runLength;
        long max = 0;
        for (int i = 0; i < length; i++) {
            long product = 1;
            for (int j = 0; j < runLength; j++) {
                int digit = DATA.charAt(i + j) & 0xF;
                if (digit == 0) {   // reset
                    product = 1;
                    i += j + 1;
                    break;
                }
                product *= digit;
            }
            if (max < product)
                max = product;
        }
        return max;
    }

    public static long fasterApproach(final int runLength) {
        int length = DATA.length() - runLength;
        int index = 0;
        long product = sequentialProduct(0, runLength, length);
        long max = 0;
        while (product < 0) {
            index = (int) (-product);
            product = sequentialProduct(index, runLength, length);
        }
        for (; index < length; index++) {
            int digit = DATA.charAt(index + runLength) & 0xF;
            if (digit == 0) {
                product = sequentialProduct(index, runLength, length);
                while (product < 0) {
                    index = (int) (-product);
                    product = sequentialProduct(index, runLength, length);
                }
            }
            if (max < product) {
                max = product;
            }
            int lastDigit = DATA.charAt(index) & 0xF;
            product = product * digit / lastDigit;
        }
        return max;
    }

    public static long sequentialProduct(int index,
                                         int runLength,
                                         int length) {
        long product = 1;
        for (int i = index; i < length; i++) {
            for (int j = 0; j < runLength; j++) {
                int digit = DATA.charAt(i + j) & 0xF;
                if (digit == 0) {
                    System.out.println("Zero found");
                    return -(i + j + 1);
                }
                product *= digit;
            }
        }
        return product;
    }
}
