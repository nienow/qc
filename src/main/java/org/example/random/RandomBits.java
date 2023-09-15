package org.example.random;

import org.redfx.strange.algorithm.Classic;

public class RandomBits {
    public static void main(String[] args) {
        int zero = 0;
        int one = 0;
        for (int i = 0; i < 10000; i++) {
            int bit = Classic.randomBit();
            if (bit == 0) {
                zero++;
            } else {
                one++;
            }
        }
        System.out.println("0: " + zero);
        System.out.println("1: " + one);
    }
}
