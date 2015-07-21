package com.jawnnypoo.physicslayout.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapsPermutation {
    private static void swap(String[] v, int i, int j) {
        String t = v[i];
        v[i] = v[j];
        v[j] = t;
    }

    public void permute(String[] v, int n, List<String[]> results) {
        if (n == 1) {
            results.add(Arrays.copyOf(v, v.length));
        } else {
            for (int i = 0; i < n; i++) {
                permute(v, n - 1, results);
                if (n % 2 == 1) {
                    swap(v, 0, n - 1);
                } else {
                    swap(v, i, n - 1);
                }
            }
        }
    }

    public List<String[]> getPermutations(String[] values) {
        List<String[]> results = new ArrayList<>();
        permute(values, values.length, results);
        return results;
    }
}
