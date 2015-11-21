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

package ml.cristatus.euler.problems11to20.problem11;

import java.util.Scanner;

/**
 * @author Subhomoy Haldar
 * @version 1.0
 */
public class Solver {
    public static final String RAW_DATA
            = "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08\n"
            + "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00\n"
            + "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65\n"
            + "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91\n"
            + "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80\n"
            + "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50\n"
            + "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70\n"
            + "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21\n"
            + "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72\n"
            + "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95\n"
            + "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92\n"
            + "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57\n"
            + "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58\n"
            + "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40\n"
            + "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66\n"
            + "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69\n"
            + "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36\n"
            + "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16\n"
            + "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54\n"
            + "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48";

    public static final long[][] GRID;
    public static final int LENGTH = 4;

    private static final int TWICE_LENGTH = LENGTH << 1;

    static {
        // The size of the grid must be updated in case the question changes
        GRID = new long[20][20];

        Scanner lineScanner = new Scanner(RAW_DATA);
        int i = 0, j;
        while (lineScanner.hasNextLine()) {
            Scanner numberScanner = new Scanner(lineScanner.nextLine());
            j = 0;
            while (numberScanner.hasNextLong()) {
                GRID[i][j++] = numberScanner.nextLong();
            }
            i++;
        }
    }

    public static void main(String[] args) {
        System.out.println(bruteForce());
    }

    public static long bruteForce() {
        long max = 0;
        for (int i = 0; i < GRID.length; i++) {
            for (int j = 0; j < GRID[i].length; j++) {
                long product = 1;
                // the horizontal product
                for (int k = 0; k < LENGTH && j + k < GRID[i].length; k++) {
                    product *= GRID[i][j + k];
                }
                if (max < product) max = product;
                // the vertical product
                product = 1;
                for (int k = 0; k < LENGTH && i + k < GRID.length; k++) {
                    product *= GRID[i + k][j];
                }
                if (max < product) max = product;
                // the diagonal product '\'
                product = 1;
                for (int k = 0; k < LENGTH && i + k < GRID.length
                        && j + k < GRID[i].length; k++) {
                    product *= GRID[i + k][j + k];
                }
                if (max < product) max = product;
                // the diagonal product '/'
                product = 1;
                for (int k = 0; k < LENGTH && i + k < GRID.length
                        && j - k >= 0; k++) {
                    product *= GRID[i + k][j - k];
                }
                if (max < product) max = product;
            }
        }
        return max;
    }
}
