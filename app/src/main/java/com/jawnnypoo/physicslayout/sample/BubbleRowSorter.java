package com.jawnnypoo.physicslayout.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * This class will take a list of words and split them into rows using a
 * minimum raggedness algorithm https://en.wikipedia.org/wiki/Line_wrap_and_word_wrap#Minimum_raggedness
 * then sort them so that the shortest lines are at the top and bottom and the longest lines
 * are in the middle. This is used to layout tag bubbles with a nice oval outline.
 */
public class BubbleRowSorter {
    public static final String SEPARATOR = "£";
    public static final String NEW_LINE = "\n";
    protected String[][] results = new String[1000][1000];
    protected List<String> words;
    int[][] cac = new int[1000][1000];
    int lineLength = 10;

    public BubbleRowSorter(int lineLength) {
        this.lineLength = lineLength;
        for (int[] aCac : cac) {
            Arrays.fill(aCac, -1);
        }
    }

    public List<String[]> getRows(List<String> words) {
        this.words = words;
        doWrap(0, words.size());
        return getRowsAsArrays(results[0][words.size()]);
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
            length += word.length() + 2; //Plus 2 because more words take up more space when displayed on screen because of padding etc.
        }
        return length;
    }

    protected List<String[]> getRowsAsArrays(String rows) {
        String[] rowSplit = rows.split(NEW_LINE);
        List<String[]> results = new ArrayList<>(rowSplit.length);
        for (String row : rowSplit) {
            results.add(row.split(SEPARATOR));
        }
        return sortRows(results);
    }


    protected int doWrap(int a, int b) {
        if (cac[a][b] >= 0)
            return cac[a][b];
        if (a == b)
            return 0;

        int csum = -1;
        for (int i = a; i < b; ++i) {
            csum += words.get(i).length() + 1;
        }
        if (csum <= lineLength || a == b - 1) {
            String sep = "";
            for (int i = a; i < b; ++i) {
                append(a, b, sep + words.get(i));
                sep = SEPARATOR;
            }
            return cac[a][b] = (lineLength - csum) * (lineLength - csum);
        }

        int ret = 1000000000;
        int best_sp = -1;
        for (int sp = a + 1; sp < b; ++sp) {
            int cur = doWrap(a, sp) + doWrap(sp, b);
            if (cur <= ret) {
                ret = cur;
                best_sp = sp;
            }
        }
        results[a][b] = results[a][best_sp] + NEW_LINE + results[best_sp][b];
        return cac[a][b] = ret;
    }

    private void append(int a, int b, String s) {
        if (results[a][b] == null)
            results[a][b] = s;
        else
            results[a][b] = results[a][b] + s;
    }
}
