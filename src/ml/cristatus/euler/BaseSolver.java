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

package ml.cristatus.euler;

import java.math.BigInteger;

/**
 * This a common interface that helps to provide a common access point for all
 * the Solvers.
 *
 * @author Subhomoy Haldar
 * @version 1.0
 */
public interface BaseSolver {

    /**
     * This method returns the answer to the question as per the arguments
     * stated originally. Therefore, it is incapable of accepting any arguments.
     * It returns the answer using the fastest method implemented.
     *
     * @return The answer to the question.
     */
    long getAnswer();

    /**
     * In case the answer required exceeds the range of a Long, (which is
     * very unlikely), it returns the result without loss of precision in the
     * form a BigInteger.
     *
     * @return The answer as a BigInteger.
     */
    BigInteger getBigIntegerAnswer();
}
