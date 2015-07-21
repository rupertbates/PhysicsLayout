package com.jawnnypoo.physicslayout.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class TestBubbleRowSorter {
    int lineLength;

    public TestBubbleRowSorter(int lineLength) {
        this.lineLength = lineLength;
    }

    public List<String[]> getSortedRows(List<String> words) {
        List<String[]> permutations = new HeapsPermutation().getPermutations(words.toArray(new String[words.size()]));

        int bestScore = Integer.MAX_VALUE;
        List<String[]> bestOption = null;
        for (String[] permutation : permutations) {
            List<String[]> option = sortRows(getRows(Arrays.asList(permutation)));
            int score = getRaggednessScore(option);
            if(score < bestScore) {
                bestOption = option;
                bestScore = score;
            }
        }
        return bestOption;
    }

    //To calculate raggedness score we take the sum of the squares of length of the spaces at the end of the lines
    public int getRaggednessScore(List<String[]> rows) {
        int sumOfSquaredSpace = 0;
        for (String[] row : rows) {
            int remainingSpace = (lineLength - lineLength(row));
            sumOfSquaredSpace += (remainingSpace * remainingSpace);
        }
        return sumOfSquaredSpace;
    }

    protected List<String[]> sortRows(List<String[]> rows) {
        Collections.sort(rows, new Comparator<String[]>() {
            @Override
            public int compare(String[] lhs, String[] rhs) {
                return Integer.valueOf(lineLength(lhs)).compareTo(lineLength(rhs));
            }
        });
        List<String[]> result = new ArrayList<>(rows);
        int upperBound = result.size() - 1;
        for (int i = 0; i < result.size(); i++) {
            if (i % 2 == 0)
                result.set(i / 2, rows.get(i));
            else
                result.set((int) (upperBound - Math.floor(i / 2)), rows.get(i));
        }
        return result;
    }

    private static int lineLength(String[] words) {
        int length = 0;
        for (String word : words) {
            length += word.length();
        }
        return length;
    }

    public List<String[]> getRows(List<String> words) {
        List<String[]> result = new ArrayList<>();
        List<String> subList = new ArrayList<>();
        int letterCount = 0;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (letterCount + word.length() > lineLength) {
                result.add(subList.toArray(new String[subList.size()]));
                subList = new ArrayList<>();
                letterCount = 0;
            }
            subList.add(word);
            letterCount += word.length();
            if (i == words.size() - 1) { //last element
                result.add(subList.toArray(new String[subList.size()]));
            }
        }
        return result;
    }

}