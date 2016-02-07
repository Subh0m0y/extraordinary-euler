/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Subhomoy Haldar
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

package ml.cristatus.euler.problems11to20.problem19;

import ml.cristatus.euler.BaseSolver;

import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

/**
 * <h1>Counting Sundays</h1>
 * You are given the following information, but you may prefer to do some
 * research for yourself.
 * <ul>
 * <li>1 Jan 1900 was a Monday.</li>
 * <li>Thirty days has September,<br>
 * April, June and November.<br>
 * All the rest have thirty-one,<br>
 * Saving February alone,<br>
 * Which has twenty-eight, rain or shine.<br>
 * And on leap years, twenty-nine.</li>
 * <li>A leap year occurs on any year evenly divisible by 4, but not on a
 * century unless it is divisible by 400.</li>
 * </ul>
 * How many Sundays fell on the first of the month during the twentieth century
 * (1 Jan 1901 to 31 Dec 2000)?
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
        System.out.println(getSundaysBetween(
                LocalDate.of(1901, Month.JANUARY, 1),
                LocalDate.of(2000, Month.DECEMBER, 31),
                Period.ofMonths(1)
        ));
        // OR... Try to find out the logic of the following code ;-)
        System.out.println(1200 / 7);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long getAnswer() {
        return getSundaysBetween(
                LocalDate.of(1901, Month.JANUARY, 1),
                LocalDate.of(2000, Month.DECEMBER, 31),
                Period.ofMonths(1)
        );
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
     * Returns the number of Sundays that fell on the 1st of any month within
     * the given LocalDate limits (both inclusive).
     *
     * @param date  The starting date (inclusive).
     * @param limit The limiting date (inclusive).
     * @param step  The Period to skip over during every iteration.
     * @return The number of Sundays that fell on the 1st of any month within
     * the given LocalDate limits (both inclusive).
     */
    public static long getSundaysBetween(LocalDate date,
                                         LocalDate limit,
                                         Period step) {
        long count = 0;
        for (; date.compareTo(limit) <= 0; date = date.plus(step)) {
            if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                    date.getDayOfMonth() == 1) {
                count++;
            }
        }
        return count;
    }
}
