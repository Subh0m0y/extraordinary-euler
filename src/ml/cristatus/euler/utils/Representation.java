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
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class Representation {

    private static final String[] DATA = {
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thir", "four",
            "fif", "six", "seven", "eigh", "nine"
    };

    private static final String[] TENS = {
            "", // zero
            "", // 10
            "twen", "thir", "for", "fif", "six", "seven", "eigh", "nine"
    };

    public static String inWords(int number) {
        if (number < 0) return "negative " + inWords(-number);
        if (number < 13) return DATA[number];
        if (number < 20) return DATA[number] + "teen";
        if (number < 100) {
            String tens = TENS[number / 10] + "ty";
            int remainder = number % 10;
            if (remainder == 0) return tens;
            String ones = DATA[remainder];
            return tens + " " + ones;
        }
        if (number < 1000) {
            String hundreds = DATA[number / 100] + " hundred";
            int remainder = number % 100;
            if (remainder == 0) return hundreds;
            return hundreds + " and " + inWords(remainder);
        }
        if (number == 1000) return "one thousand";
        return "";
    }
}
