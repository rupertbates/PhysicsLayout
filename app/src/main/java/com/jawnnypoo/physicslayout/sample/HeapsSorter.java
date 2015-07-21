package com.jawnnypoo.physicslayout.sample;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HeapsSorter {
    private static void swap(String[] v, int i, int j) {
        String t = v[i];
        v[i] = v[j];
        v[j] = t;
    }

    public void permute(String[] v, int n) {
        if (n == 1) {
            System.out.println(Arrays.toString(v));
        } else {
            for (int i = 0; i < n; i++) {
                permute(v, n - 1);
                if (n % 2 == 1) {
                    swap(v, 0, n - 1);
                } else {
                    swap(v, i, n - 1);
                }
            }
        }
    }

    public List<String> getPermutations(String[] values){
        permute(values, values.length);
        return null;
    }
}

