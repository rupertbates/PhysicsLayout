package com.jawnnypoo.physicslayout.sample;

public class RowHelper {


    public static int[] getNumberOfRows(int length) {
        switch (length) {
            case 1:
            case 2:
            case 3:
                return new int[]{length};
            case 4:
                return new int[]{2, 2};
            case 5:
                return new int[]{2, 3};
            case 6:
                return new int[]{3, 3};
            case 7:
                return new int[]{2, 3, 2};
            case 8:
                return new int[]{3, 3, 2};
            case 9:
                return new int[]{3, 3, 3};
            case 10:
                return new int[]{3, 4, 3};
            case 11:
                return new int[]{1, 2, 3, 3, 2};
            case 12:
                return new int[]{3, 3, 3, 3};
        }
        throw new IllegalStateException("Max length is 12");
    }
}


