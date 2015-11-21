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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class Solver {

    private static final Map<Long, Long> lengthMap
            = new HashMap<>(1_000_000);

    static {
        lengthMap.put(1L, 1L);
    }

    public static void main(String[] args) {
        long max = 0, term = 0;
        for (long i = 1; i < 1_000_000; i++) {
            long length = plainLengthOf(i);
            if (max < length) {
                max = length;
                term = i;
            }
        }
        System.out.println(term);
    }

    public static long plainLengthOf(long number) {
        if (number == 1)
            return 1;
        return 1 + ((number & 1) == 0
                ? lengthOf(number >>> 1)
                : lengthOf(1 + number + (number << 1)));
    }

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
