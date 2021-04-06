package uk.co.mruoc.exercises.challenge;

public class Solution {

    private Solution() {
        //utility class
    }

    public static int solution(int numberOfCakes, int requiredLayers, int[] starts, int[] ends, int[] flavours) {
        int[][] cakes = new int[numberOfCakes][requiredLayers];
        int[] indexes = new int[numberOfCakes];
        boolean[] invalids = new boolean[numberOfCakes];
        for (int f = 0; f < flavours.length; f++) {
            int start = starts[f];
            int end = ends[f];
            int flavour = flavours[f];
            for (int c = start - 1; c < end; c++) {
                int index = indexes[c];
                if (index >= requiredLayers) {
                    invalids[c] = true;
                } else {
                    cakes[c][index] = flavour;
                    if (index > 0 && cakes[c][index - 1] != flavour - 1) {
                        invalids[c] = true;
                    }
                    indexes[c] = index + 1;
                }
            }
        }

        updateInvalids(cakes, invalids);

        print(cakes, invalids);
        return toValidCount(invalids);
    }

    private static void updateInvalids(int[][] cakes, boolean[] invalids) {
        for (int c = 0; c < cakes.length; c++) {
            if (cakes[c][cakes[c].length-1] == 0) {
                invalids[c] = true;
            }
        }
    }

    private static void print(int[][] cakes, boolean[] invalids) {
        for (int c = 0; c < cakes.length; c++) {
            for (int d = 0; d < cakes[0].length; d++) {
                System.out.print(cakes[c][d]);
            }
            System.out.print(" " + invalids[c]);
            System.out.println();
        }
    }

    private static int toValidCount(boolean[] invalids) {
        int count = 0;
        for (boolean invalid : invalids) {
            if (invalid) {
                count++;
            }
        }
        return invalids.length - count;
    }

}
