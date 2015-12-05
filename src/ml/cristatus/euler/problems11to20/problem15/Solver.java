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

package ml.cristatus.euler.problems11to20.problem15;

import ml.cristatus.euler.BaseSolver;
import ml.cristatus.euler.utils.Count;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Starting in the top left corner of a 2&times;2 grid, and only being able to move
 * to the right and down, there are exactly 6 routes to the bottom right corner.
 * <p>
 * <img src="data:image/gif;base64,R0lGODlh0ACXAMQAAAAAALXf/7fg/7zi/8Tl/8jn/8fo
 * /8zp/9Ts/9nv/93x/+Pz/+v2/+34//P6/////wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
 * AAAAAAAAAAAAAAAAAAAAAP///yH5BAEAAB8AIf4cVWxlYWQgR0lGIFNtYXJ0U2F2ZXIgVmVyIDIu
 * MAAsAAAAANAAlwAABf/gJ45kaZ5oqq7sB7xwLM9ya98ore8v3vLAoHDmyw2DxSTriFSemNAowEmS
 * WoHU6nVL03K/sawLPHWWxeSzUp0mo91msSidZaPrsLs5H5eri2FUdnV4PYR7hmtyLoJ8fXN6iH9J
 * kziBin6NiZiQh5KRlI6Ui5U3l5yMnmuiqICsPqWAmrE2g4KFtLWzt5mfvJ2/q5utsK+WpLvBuT+4
 * oK7DsMi+j6nBodCy06O9wssrttTX3t/JxOamxscj4yrsRtjR2tnOxfCmVfT17jnl2/no9nSt+wdw
 * 35N+81Q9M1gC3LmCBH+kExhxyUSK1cItrPjt4hJp3VQxbNhMoT5rGi3/efw40ORJlxBT+hMnkmPH
 * gBg3ykyok9pIfAhn9hS6UyJOlsCKGv3ZMqTSe/ISMk1KE2atle1AVvVpsx1WclHVdX031SHRlyhV
 * gtE6dObUjFufel3LLa5bumH1tYlCCg7XN3jlptg79gmdpmljJr56ePHSmoVNnHoI1alji0fBUqWM
 * +a3noDzRonoLVzRnZnnF2pVat+3ZeJYFk4Vs9XHtnKZfMy4L9LYXJqpPr+NbeTXs0roNAy8ufPhy
 * 3Gp5I5Y95sjx5nOeQ1c8unXnJsyTKxdyPfdx0vuslw99E3z46LQvj+fBfn17+vZ3x6eeHUv94Evt
 * IF57+2H3mw4DIhUd/4L/2faXb/MRkaBmC0rYYGcFTiiZgBoO1o2F+TnYXWTOgRjidt9NBiB8yni3
 * Ww0G8iMOjB1uOExmiKEnyVcyyjeYiScS+BtGOn6IY4/8HXjkQfIAKVlvPv7II5IxNqRikHP9wSCS
 * RX4IIYULSRdbdV0wuVmNNpL4pCZqlmglflB2KUybUF5DJ5lv+peUnKG4iKVXfr5oxZ57beFHoYMC
 * higUizTq6KOQRnrfFZJWaumlmKYoRaacduopptp9KuqopIbJYamopqqqjaeu6uqro8IJ66y0glpm
 * rbjmCqmTuvbqq2u/BiusRcMWa+yxyCar7LLMNuvss9BGK+201FZr7f+12Gar7bbcduvtt+CGK+64
 * 5JZr7rnopqvuuuy2y+qijOICr3qzzKvelSv+mWWUWZVUJZP4vqevlEx95i+aAE9pZpKs8uvhwRcK
 * FDCKArP4L0kQD/zwxKg5PBvDGINWsTocK3jxkCDXadzIUJUMJsJ5erzwmBrL6HK/Mjec8nQrU9yy
 * wmvmHPPOZ5rHssRAh0w0nkt7ljTP29z8sNAonwy10T4jveTMMFfd9dUWf92S1FSKzbTVRYcdMWpk
 * c722kGgjh3XHvLQddNNPp300hkunYrfScf8N9t5wm+133npnzXfgilINlOCDK1742zinRncfkCd+
 * ec+bU06w42MjLnf/vmrXfDfNpJMsusEiS1456oSDlfnosX/edON9lzE765abbHbmRe5+Z38FD3+2
 * 4brbG2+9X4B+vOcbKx/qjM3jHRjm0i9PffZb9q7ppmRy756pXDgvfIbQD+048LjfHrnr493Jfsam
 * K83n3PDrHPjqoNxfevpeA6D6GPe+znHHavNrXf4W5j+y0MuA+rGemB7kMOIsMID1w2AGcxSoCOnp
 * gm6S4Jcq10CCjc93/6vdx9ynOQg6UFYgJJ75+Ie+mg1BgDIUoflUhiby4PB5G8whC2mnQuJ1r4ga
 * ROIAf8dDAH4wdYv7HQ1b1CYYKlGIUtza6dzSwSzR6IcJ9N7LWFPFvyNCsXBLcpoWATcG1ZRQU+sT
 * xc3UOEEskrCLXlxjAW1nxGXQ0SS8ihMevTjC+JVoYn9EiRkFKb/zxcaKLZxcaCCZkTcapZGbOGEi
 * NaLJJgZRdEREow8juS9FjrKSgwQUJikVyu+JzxBseCUl9SJLUNZyluW63vZuqUdtVU9evOylL1nZ
 * qGAKM1w3DFYy3WXCRebqlMz04Oxihcto9hGUpGqVNZtJmlLdapuTWlYgwblEZE1zXceEVTrJyc52
 * MisEADs=" alt="Lattice Paths in a 2&times;2 grid.">
 * <p>
 * How many such routes are there through a 20&times;20 grid?
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
        int n = 20;
        System.out.println(Count.combinations(n << 1, n));
        System.out.println(iterativeApproach(n, n));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long getAnswer() {
        return iterativeApproach(20, 20);
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
     * This method employs dynamic programming to perform a bottom-up
     * calculation of the number of paths.
     *
     * @param m The number of rows.
     * @param n The number of columns.
     * @return The number of lattice paths possible.
     */
    public static long iterativeApproach(int m, int n) {
        long[][] data = new long[m + 1][n + 1];
        Arrays.fill(data[0], 1);
        for (int i = 0; i <= m; i++) {
            data[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                data[i][j] = data[i - 1][j] + data[i][j - 1];
            }
        }
        return data[m][n];
    }

}
